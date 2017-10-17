extern "C" {
#include "user_interface.h"
}
//#define _BASE64
#include <ESP8266WiFi.h>
#define TINY_GSM_MODEM_SIM800
#define TINY_GSM_RX_BUFFER 31
#include <TinyGsmClient.h>
#include <PubSubClient.h>
//#include <base64.hpp>

const char apn[] = "everywhere";
const char user[] = "eesecure";
const char pass[] = "secure";

#define SerialMon Serial
#include <SoftwareSerial.h>
//#include <StreamDebugger.h>
SoftwareSerial SerialAT(4, 5); // RX, TX
//StreamDebugger debugger(SerialAT, SerialMon);

WiFiClient carclient;
TinyGsm modem(SerialAT);
TinyGsmClient client(modem);
PubSubClient mqtt(client);

#define MAX_PAYLOAD_SIZE 1024
#define MAX_NUM_MESSAGES 10

struct MqttMessage
{
  String topic;
  unsigned int length;
  unsigned char payload[MAX_PAYLOAD_SIZE];
};

MqttMessage messages[MAX_NUM_MESSAGES];
int numMessages = 0;

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

void setup()
{
  WiFi.hostname("VF-1397");
  SerialMon.begin(115200);
  delay(10);
  setupGprs();
  setupWifi("REMOTE45cfsc", "fhcm852767");
  connectToCar(_CARHOST, _CARPORT);
  mqtt.setServer(broker, 1883);
  mqtt.setCallback(mqttCallback);
  mqttConnect();
  //connectToMobile(_HOST, _PORT);
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
  //Serial.setDebugOutput(true);
  Serial.println("\nWifi starting");

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  uint8_t mac[] = {0x24, 0x0d, 0xc2, 0xc2, 0x91, 0x85};
  bool a = wifi_set_macaddr(STATION_IF, &mac[0]);
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid,password);  
  WiFi.setAutoConnect(false);
  WiFi.setAutoReconnect(false);
  WiFi.waitForConnectResult();
  
  while(WiFi.status() != WL_CONNECTED) {
    Serial.print('.');
    delay(1000);
  }
  Serial.print("Address ");
  Serial.println(WiFi.localIP());
  /*
  
  if (WiFi.status() != WL_CONNECTED)
  {
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED && times < 100)
    {
      delay(1000);
      Serial.print(".");
      times++;
    }
  }
  if (times < 100)
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
  } */
}

void subscribe()
{
  mqtt.subscribe(phevSend);
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

  if(time == 500) {
    time = 0;
    Serial.println("Status");
    Serial.print("Wifi Connected : ");
    Serial.println((WiFi.status() == WL_CONNECTED ? "True" : "False"));
    Serial.print("Client Connected : ");
    Serial.println((carclient.connected() ? "True" : "False"));
    Serial.print("MQTT Connected : ");
    Serial.println((mqtt.connected() ? "True" : "False"));
    
    Serial.println();
  } else {
    time ++;
  }
}
void loop()
{ /*
  while (client.available())
  {
    carclient.write(client.read());
  }
  */
  handleQueuedMessages();
   
  if(mqtt.connected()) 
  {
    mqtt.loop();
  } else {
    mqttConnect();
  }
  if(carclient.connected()) 
  {
    while(carclient.available())
    {
      byte inputBuf[1024];
      int len = carclient.read((byte *) &inputBuf,1024); 
      //Serial.print(inputBuf,HEX);
  
      mqtt.publish(phevReceive,(byte *) &inputBuf,len);
    }
  } else {
    connectToCar(_CARHOST, _CARPORT);
  }
  status();
  delay(10);
}

void handleQueuedMessages()
{
  int i = 0;
  if (numMessages > 0)
  {
    for (i = 0; i < numMessages; i++)
    {
      handleMessage(messages[i].topic, messages[i].payload, messages[i].length);
      numMessages--;
    }
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

  carConnected = false;
  if (!carclient.connected())
  {
    carclient.stop();
    if (carclient.connect(host, httpPort))
    {
      String msg = "OK : Connected to car host : ";
      msg += host;
      carConnected = true;
      Serial.println(msg);
#ifndef _DIRECTIP
      mqtt.publish(phevConnected, msg.c_str());
#endif
      return true;
    }
    else
    {
      Serial.println("connection failed");
#ifndef _DIRECTIP
      mqtt.publish(phevConnected, "FAIL : cannot connect to car");
#endif
      return false;
    }
    Serial.println("No Wifi");
#ifndef _DIRECTIP
    mqtt.publish(phevError, "WiFi not connected");
#endif
  }
  return false;
}
#ifndef _DIRECTIP
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
  printHex(payload,len);
  Serial.println();
#endif
  numMessages++;
}
#endif
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

void printHex(byte * buf, unsigned int len) 
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

  if (topic == String(phevSend))
  {
    byte buf[1024];
    unsigned char *buf2;
    memset(buf, '\0', sizeof(buf));

    int length = len;
    buf2 = msg;

#ifdef _DEBUG
    Serial.print("Sending command ");
    printHex(buf2,length);
#endif
    carclient.write((unsigned char *)buf2, length);
    return;
  }
}

void reset()
{
  Serial.println("Resetting");
  ESP.restart();
}
