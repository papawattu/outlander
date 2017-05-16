extern "C" {
#include "user_interface.h"
}
//#define _BASE64
#include <ESP8266WiFi.h>
#define TINY_GSM_MODEM_SIM800
#define TINY_GSM_RX_BUFFER 31
#include <TinyGsmClient.h>
#include <PubSubClient.h>
#include <base64.hpp>

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

#define MAX_PAYLOAD_SIZE 200
#define MAX_NUM_MESSAGES 5

struct MqttMessage {
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

unsigned char * incomingBuffer;
unsigned char * outgoingBuffer;

#define  INCOMING_BUFFER_SIZE 1024
#define  OUTGOING_BUFFER_SIZE 1024  

void setup()
{
  WiFi.persistent(false);
  WiFi.mode(WIFI_OFF);
  WiFi.mode(WIFI_STA);
  WiFi.hostname("android-88a84719193c6b9");
  SerialMon.begin(115200);
  delay(10);
  setupGprs();
  //setupWifi();
#ifndef _DIRECTIP 
  mqtt.setServer(broker, 1883);
  mqtt.setCallback(mqttCallback);
#else
  setupWifi(_SSID,_PASSWORD);
  connectToCar(_CARHOST,_CARPORT);
  connectToMobile(_HOST,_PORT);
#endif
  numMessages = 0;
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

void setupWifi(const char * ssid,const char * password)
{
  int times = 0;
  Serial.println("\nWifi starting");

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  uint8_t mac[] = { 0xac, 0x37, 0x43, 0x4d, 0xda, 0x90 };
  bool a = wifi_set_macaddr(STATION_IF, &mac[0]);
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
  if(times < 100) {
    Serial.println("\nWiFi connected");
    Serial.println("IP address: ");
    Serial.println(WiFi.localIP());
    Serial.println("\nWiFi started");
  } else {
    Serial.println("Wifi timeout");
#ifndef _DIRECTIP 
    mqtt.publish(phevError, "Failed to connect to WiFi");
#endif
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

void connectToMobile(const char * host, int port) {
  Serial.print("Connecting to mobile host : ");
  Serial.print(host);
  Serial.print(" port : ");
  Serial.println(port);
  if(!client.connect(host,port)) {
      Serial.println("Failed to connect to mobile.");
      reset();
  }
  Serial.println("Connected OK");
}
void loop()
{
  unsigned char buf[MAX_PAYLOAD_SIZE];
#ifndef _DIRECTIP 
    
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
#endif
  if (carConnected)
  {
    unsigned char buf2[300];
    if(carclient.available() > 0) {
      int i = carclient.readBytes(buf, (carclient.available() < MAX_PAYLOAD_SIZE?carclient.available():255));
      if (i > 0)
      {
#ifdef _DEBUG
        Serial.print("\nSending mobile response : ");
        int j;  
        for(j=0;j<i;j++) {
          Serial.print(buf[j],HEX);
          Serial.print(" ");    
        }
        Serial.println();
#endif
#ifdef _DIRECTIP
        if(client.connected()) {

          client.write((unsigned char * ) buf, i);
        }
        else {
          connectToMobile(_HOST,_PORT);
          client.write((unsigned char * ) buf, i);
        }
#else
#ifdef _BASE64
        encode64((char *) buf, (unsigned char *) buf2,i);
        mqtt.publish(phevReceive, (const char *)buf2);
#else
        mqtt.publish(phevReceive, (byte *)buf,i);
#endif
#endif
      }
    }
  }  else {
    connectToCar(_CARHOST,CARPORT_)
  }
#ifdef _DIRECTIP
  if(client) {
    if(client.available()) {
      int bytes = client.readBytes(buf,(client.available() < 255?client.available():255));  
      
#ifdef _DEBUG
        Serial.print("\nSending to car : ");
        int j;  
        for(j=0;j<bytes;j++) {
          Serial.print(buf[j],HEX);
          Serial.print(" ");    
        }
        Serial.println();
#endif

  
      carclient.write((unsigned char *)buf,bytes);
    } 
  } else {
    connectToMobile(_HOST,_PORT);
  }
#else
  if(numMessages > 0) {
    handleQueuedMessages();
  }
#endif
}

void handleQueuedMessages() {
  int i=0;
  if(numMessages > 0) {
    for(i=0;i<numMessages;i++) {
      handleMessage(messages[i].topic, messages[i].payload,messages[i].length);
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

boolean connectToCar(const char * host,const int httpPort) {

  carConnected = false;
  if (!carclient.connected())
  {
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

  if(len > MAX_PAYLOAD_SIZE) {
    Serial.println("Message payload too big - ignoring");
    mqtt.publish(phevError, "Message payload too big - ignoring");
    
    return;
  }
  if(numMessages == MAX_NUM_MESSAGES) {
    Serial.println("Maximum number of queued messages - ignoring");
    mqtt.publish(phevError, "Maximum number of queued messages - ignoring");
   
    return;
  }

  messages[numMessages].topic = topic;
  messages[numMessages].length = len;
  memcpy((unsigned char *) messages[numMessages].payload,(unsigned char *) payload,len);
  
#ifdef _DEBUG
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("]: ");
  Serial.write(payload, len);
  Serial.println();
#endif
  numMessages ++;
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

void handleMessage(String topic, byte *msg, unsigned int len)
{
  int i;
    
  if (topic == String(phevWifiDetails))
  {
    String ssid = getParameter("SSID", msg);
    String password = getParameter("PASSWORD", msg);
    String host = getParameter("HOST", msg);

    Serial.println("Got wifi details ");
    Serial.println("SSID " + ssid);
    Serial.println("PASSWORD " + password);
    Serial.println("HOST " + host);
    setupWifi(ssid.c_str(),password.c_str());
    connectToCar(host.c_str(),_CARPORT);
    Serial.print("Connected to car host : ");
    Serial.println(host);
    //  Serial.println("Ping");
    //  ping();

    return;
  }
  if (topic == String(phevReset))
  {
    reset();
  }
  if (topic == String(phevSend))
  {
    byte buf[512];
    unsigned char * buf2;
    memset(buf,'\0',sizeof(buf));
  
#ifdef _BASE64
    int length = decode64((char *)msg, (unsigned char *)buf);
    buf2 = buf;
#else
    int length = len;
    buf2 = msg;
#endif

#ifdef _DEBUG
    Serial.print("Sending command ");
    for (i = 0; i < len; i++)
    {
      Serial.print(buf[i], HEX);
    }
    Serial.println("");
#endif
    carclient.write((unsigned char *)buf2, length);
    return;
  }
}

void encode64(unsigned char * src, char * dest,unsigned int len) {
  encode_base64(src, len, (unsigned char *) dest);	
}

int decode64(char * src, unsigned char * dest) {
  return decode_base64((unsigned char *) src,dest);
}
void reset() {
  Serial.println("Resetting");
  ESP.restart();
}
