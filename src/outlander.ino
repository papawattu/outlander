extern "C" {
#include "user_interface.h"
}
//#define _BASE64
#include <ESP8266WiFi.h>
#define TINY_GSM_MODEM_SIM800
#define TINY_GSM_RX_BUFFER 31
#include <TinyGsmClient.h>
#include <PubSubClient.h>

const char apn[] = "everywhere";
const char user[] = "eesecure";
const char pass[] = "secure";

#define SerialMon Serial
#include <SoftwareSerial.h>
SoftwareSerial SerialAT(4, 5); // RX, TX

WiFiClient carclient;
TinyGsm modem(SerialAT);
TinyGsmClient client(modem);
PubSubClient mqtt(client);

#define MAX_PAYLOAD_SIZE 512
#define MAX_NUM_MESSAGES 20

const char *broker = "jenkins.wattu.com";

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
#define MAX_SEND_SIZE 1024

int currentSize = 0;
unsigned char *receiveBuffer;

void allocateBuffer()
{
  receiveBuffer = (unsigned char *)malloc(MAX_SEND_SIZE);
  if (receiveBuffer < 0)
  {
    Serial.println("Cannot allocate buffer");
    reset();
  }
  else
  {
    Serial.println("Allocating buffer");
  }
}
void setup()
{

  WiFi.hostname("VF-1397");
  SerialMon.begin(115200);
  delay(10);

  allocateBuffer();

  setupGprs();
  setupWifi(_SSID, _PASSWORD);
  mqtt.setServer(_MQTT_HOST, _MQTT_PORT);
  mqtt.setCallback(mqttCallback);
}

void setupGprs()
{
  SerialAT.begin(115200);
  delay(3000);

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
  Serial.print("...");

  if (!modem.gprsConnect(apn, user, pass))
  {
    Serial.println(" FAIL");
    reset();
  }
  Serial.println(" OK");
}

void setupWifi(const char *ssid, const char *password)
{
  int times = 0;
  //Serial.setDebugOutput(true);
  Serial.println("\nWifi starting");

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  uint8_t mac[] = {0x24, 0x0d, 0xc2, 0xc2, 0x91, 0x85};
  bool a = wifi_set_macaddr(STATION_IF, &mac[0]);
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  WiFi.setAutoConnect(true);
  WiFi.setAutoReconnect(true);
  WiFi.waitForConnectResult();

  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print('.');
    delay(1000);
  }
  Serial.print("IP Address ");
  Serial.println(WiFi.localIP());
}

void subscribe()
{
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

int time = 0;
void status()
{
#ifdef _DEBUG
  {
    if (time == 2000)
    {
      time = 0;
      Serial.println("Status");
      Serial.print("Wifi Connected : ");
      Serial.println((WiFi.status() == WL_CONNECTED ? "True" : "False"));
      Serial.print("Client Connected : ");
      Serial.println((carclient.connected() ? "True" : "False"));
      Serial.print("MQTT Connected : ");
      Serial.println((mqtt.connected() ? "True" : "False"));
      Serial.print("Buffer size ");
      Serial.println(currentSize);
      Serial.println();
    }
    else
    {
      time++;
    }
  }
#endif
}
void loop()
{
  //status();

  if (mqtt.connected())
  {
    handleQueuedMessages()
    if (carclient.connected())
    {
      if (carclient.available())
      {
        byte inputBuf[128];
        int len = carclient.read((byte *)&inputBuf, 128);

        mqtt.publish(phevReceive, (byte *)&inputBuf, len);
        Serial.print("Read bytes : ");
        Serial.println(len);
      }
    } 
    else
    {
      connectToCar(_HOST, _PORT);
    }
  }
  else
  {
    mqttConnect();
  }
  
  mqtt.loop();
}

void handleQueuedMessages()
{
  int length,bytes;

  if (currentSize == 0) {
    return false;
  } else {
    length = currentSize;
  }

  
  bytes = carclient.write((unsigned char *)receiveBuffer, length);
  
#ifdef _DEBUG
  Serial.print("Sent command ");
  printHex(receiveBuffer, bytes);
#endif
  currentSize -= bytes;
  return true;
}

void connectToCar(const char *host, const int httpPort)
{

  boolean connected;
  int counter = 0;
  Serial.println("Connect to car");
  do
  {
    connected = carclient.connect(host, httpPort);
    if (connected)
    {
      break;
    }
    carclient.stop();
    delay(1000);
    counter++;
    Serial.println("Timeout - Retrying");
    mqtt.loop();    
  } while (!connected && counter < 10);
  if (counter == 10)
  {
    reset();
  }
  String msg = "OK : Connected to car host : ";
  msg += host;
  Serial.println(msg);
  mqtt.publish(phevConnected, msg.c_str());
  return;
}
void mqttCallback(char *topic, byte *payload, unsigned int len)
{
  if (len > MAX_PAYLOAD_SIZE)
  {
    Serial.println("Message payload too big - ignoring");
    mqtt.publish(phevError, "Message payload too big - ignoring");
    return;
  }
  if ((currentSize + len) >= MAX_SEND_SIZE)
  {
    Serial.println("Maximum number of queued messages - ignoring");
    mqtt.publish(phevError, "Maximum number of queued messages - ignoring");

    return;
  }
  if (String(topic) == String(phevSend))
  {
    unsigned char * pos = receiveBuffer + currentSize;

    memcpy((unsigned char *) pos, (unsigned char *)payload, len);
    currentSize += len;
  } else if(String(topic) == String(phevReset)) {
    Serial.println("Reset request received");
    reset();
  }

#ifdef _DEBUG
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("]: ");
  printHex(payload, len);
  Serial.println();
#endif
}
void printHex(byte *buf, unsigned int len)
{
  int i;
  for (i = 0; i < len; i++)
  {
    Serial.print(buf[i], HEX);
  }
  Serial.println("");
}
void handleMessage(String topic, byte *msg, unsigned int len)
{
  int i;
  Serial.print("Topic : ");
  Serial.println(topic);
  printHex(msg, len);
  if (topic == String(phevSend))
  {

#ifdef _DEBUG
    Serial.print("Sending command ");
    printHex(msg, len);
#endif
    if (!carclient.write((unsigned char *)msg, len))
    {
      Serial.println("Cannot write - Disconnecting");
      carclient.stop();
    }
    return;
  }
}

void reset()
{
  Serial.println("Resetting");
  ESP.restart();
}
