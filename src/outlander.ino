#define TINY_GSM_MODEM_SIM800
#define TINY_GSM_RX_BUFFER 31
#include <WiFi.h>
#include <TinyGsmClient.h>
#include <PubSubClient.h>
#include <base64.hpp>
#include <esp_wifi.h>
const char apn[] = "everywhere";
const char user[] = "eesecure";
const char pass[] = "secure";

HardwareSerial SerialAT(1);

const char *broker = "jenkins.wattu.com";
WiFiClient carclient;
TinyGsm modem(SerialAT);
TinyGsmClient client(modem);
PubSubClient mqtt(client);
#define WIFI_TIMEOUT 100
#define MAX_PAYLOAD_SIZE 200
#define MAX_NUM_MESSAGES 50

struct MqttMessage
{
  String topic;
  unsigned int length;
  unsigned char payload[MAX_PAYLOAD_SIZE];
};

MqttMessage messages[MAX_NUM_MESSAGES];
int numMessages = 0;

const char *topic = "phev";
const char *phevInit = "phev/init";
const char *phevPingOut = "phev/pingOut";
const char *phevPingIn = "phev/pingIn";
const char *phevWifiDetails = "phev/wifiDetails";
const char *phevConnectToCar = "phev/connectToCar";
const char *phevSend = "phev/send";
const char *phevReceive = "phev/receive";
const char *phevConnected = "phev/connected";
const char *phevReset = "phev/reset";
const char *phevError = "phev/error";

boolean carConnected = false;
byte num = 0;
long lastReconnectAttempt = 0;

unsigned char *incomingBuffer;
unsigned char *outgoingBuffer;

#define INCOMING_BUFFER_SIZE 1024
#define OUTGOING_BUFFER_SIZE 1024

void setup()
{
  Serial.begin(115200);
  delay(10);
  setupGprs();
  mqtt.setServer(broker, 1883);
  mqtt.setCallback(mqttCallback);
}

void setupGprs()
{
  SerialAT.begin(115200,SERIAL_8N1,16,17,false);
  delay(5000);

  Serial.println("Initializing modem...");
  modem.restart();

  Serial.print("Waiting for network...");
  if (!modem.waitForNetwork())
  {
    Serial.println(" fail");
    reset();
  }
  Serial.println(" OK");

  Serial.print("Connecting to ");
  Serial.print(apn);
  if (!modem.gprsConnect(apn, user, pass))
  {
    Serial.println(" fail");
    reset();
  }
  Serial.println(" OK");
}

void setupWifi(const char *ssid, const char *password)
{
  int times = 0;
  Serial.println("\nWifi starting");
  Serial.print("Connecting to ");
  Serial.println(ssid);
  uint8_t mac[] = {0xac, 0x37, 0x43, 0x4d, 0xda, 0x90};
  //bool a = wifi_set_macaddr(STATION_IF, &mac[0]);
  if(!esp_wifi_set_mac(WIFI_IF_STA,&mac[0])) 
  {
    Serial.println("Cannot set MAC");
  }
  WiFi.persistent(false);
  WiFi.mode(WIFI_OFF);
  WiFi.mode(WIFI_STA);
  WiFi.setHostname("android-88a84719193c6b9");
  WiFi.begin(ssid, password);
  if (WiFi.status() != WL_CONNECTED)
  {
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED && times < WIFI_TIMEOUT)
    {
      delay(1000);
      Serial.print(".");
      times++;
    }
  }
  if (times < WIFI_TIMEOUT)
  {
    Serial.println("\nWiFi connected");
    Serial.println("IP address: ");
    Serial.println(WiFi.localIP());
    Serial.println("\nWiFi started");
  }
  else
  {
    Serial.println("Wifi timeout");
    //reset();
  }
}

void subscribe()
{
  //mqtt.subscribe(phevPingOut);
  mqtt.subscribe(phevConnectToCar);
  mqtt.subscribe(phevWifiDetails);
  mqtt.subscribe(phevSend);
  mqtt.subscribe(phevReset);
}

boolean mqttConnect()
{
  Serial.print("Connecting to ");
  Serial.print(broker);
  if (!mqtt.connect(topic))
  {
    Serial.println(" fail");
    return false;
  }
  Serial.println(" OK");
  mqtt.publish(phevInit, "Connected");
  subscribe();
  return mqtt.connected();
}

void loop()
{
  if (mqtt.connected())
  {
    mqtt.loop();
  }
  else
  {
    // Reconnect every 10 seconds
    unsigned long t = millis();
    if (t - lastReconnectAttempt > 10000L)
    {
      lastReconnectAttempt = t;
      if (mqttConnect())
      {
        lastReconnectAttempt = 0;
      }
    }
  }
  if (numMessages)
  {
    handleQueuedMessages();
  }
  if(WiFi.status() != WL_CONNECTED) {
    setupWifi(_SSID, _PASSWORD);
  } else {
    if (!carclient.connected())
    {
      if(!connectToCar(_CARHOST, _CARPORT)) {
        setupWifi(_SSID, _PASSWORD);
      }
    }
    else
    {
      if (carclient.available())
      {
        unsigned char buf[1024];
        int len = carclient.readBytes(buf, (carclient.available() < 1024?carclient.available():1024));
      #ifdef _DEBUG
        Serial.print("Response from car ");
        int i;
        for (i = 0; i < len; i++)
        {
          Serial.print(buf[i], HEX);
        }
      #endif
        mqtt.publish(phevReceive, buf, len);
      }
    }
  }
}

void handleQueuedMessages()
{
  int i = 0;
  if (numMessages > 0)
  {
    handleMessage(messages[i].topic, messages[i].payload, messages[i].length);
    numMessages--;
  }
}

byte checksum(unsigned char *data)
{
  byte b = 0;
  int i;
  int j = sizeof(data);

  for (i = 0; i < j; i++)
  {
    b = data[i] + b & 0xff;
  }
  return b;
}

boolean connectToCar(const char *host, const int httpPort)
{
  WiFiClient car;
  Serial.print("Connecting to ");
  Serial.print(host);
  Serial.print(":");
  Serial.println(httpPort);
  
  
  carConnected = false;
  if (!carclient.connected())
  {
    int status = car.connect(host, httpPort);
    if (status)
    {
      String msg = "OK : Connected to car host : ";
      msg += host;
      carConnected = true;
      Serial.println(msg);
      mqtt.publish(phevConnected, msg.c_str());
      carclient = car;
      return true;
    }
    else
    {
      Serial.print("connection failed status : ");
      Serial.println(status);
      
      mqtt.publish(phevConnected, "FAIL : cannot connect to car");
      return false;
    }
    Serial.println("No Wifi");
    mqtt.publish(phevError, "WiFi not connected");
  }
  return false;
}
void mqttCallback(char *topic, byte *payload, unsigned int len)
{

  if (len > MAX_PAYLOAD_SIZE)
  {
    Serial.println("Message payload too big - ignoring");
    mqtt.publish(phevError, "Message payload too big - ignoring");

    return;
  }
  if (numMessages == MAX_NUM_MESSAGES)
  {
    Serial.println("Maximum number of queued messages - ignoring");
    mqtt.publish(phevError, "Maximum number of queued messages - ignoring");

    return;
  }

  messages[numMessages].topic = topic;
  messages[numMessages].length = len;
  memcpy((unsigned char *)messages[numMessages].payload, (unsigned char *)payload, len);

#ifdef _DEBUG
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("]: ");
  Serial.write(payload, len);
  Serial.println();
#endif
  numMessages++;
}
String getParameter(String param, byte *data)
{
  String str = String((const char *)data);
  int idx = str.indexOf(param);
  if (idx > -1)
  {
    String p = str.substring(idx + param.length() + 2, str.indexOf('"', idx + param.length() + 2));
    return p;
  }
  else
  {
    Serial.println("Not found " + param);
    return String("");
  }
}

void handleMessage(String topic, byte *msg, unsigned int len)
{
  int i;

  if (topic == String(phevSend))
  {
    #ifdef _DEBUG
    Serial.print("Sending command ");
    for (i = 0; i < len; i++)
    {
      Serial.print(msg[i], HEX);
      Serial.print(" ");
    }
    Serial.println("");

    #endif
    carclient.write((unsigned char *)msg, len);
    return;
  }
  if (topic == String(phevReset))
  {
    mqtt.publish(phevReset, "Resetting");
    reset();
  }
  if (topic == String(phevConnectToCar))
  {
    setupWifi(_SSID, _PASSWORD);
    connectToCar(_CARHOST, _CARPORT);
  }
  return;
}

void encode64(unsigned char *src, char *dest, unsigned int len)
{
  encode_base64(src, len, (unsigned char *)dest);
}

int decode64(char *src, unsigned char *dest)
{
  return decode_base64((unsigned char *)src, dest);
}
void reset()
{
  Serial.println("Resetting");
  ESP.restart();
}
