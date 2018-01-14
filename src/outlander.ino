#define TINY_GSM_MODEM_SIM800
#define TINY_GSM_RX_BUFFER 31
#include <WiFi.h>
#include <esp_wifi.h>

#include "wifihelper.h"
#include "firmware.h"
#include "gsm.h"
#include "mqtt.h"

WiFiClient carclient;

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

#define _LOCAL_SSID "BTHub3-HSZ3"
#define _LOCAL_PASSWORD "simpsons"
#define _UPDATE_PIN 21
#define _DOWNLOAD_URL "http://192.168.1.103:8080/firmware.bin"

void setup()
{

  Serial.begin(115200);
  delay(10);

  pinMode(_UPDATE_PIN, INPUT);
  if (digitalRead(21) > 0)
  {
    Serial.println("Downloading new firmware");
    connectToWiFi((char *)_LOCAL_SSID, (char *)_LOCAL_PASSWORD);
    downloadUpdate((char *)_DOWNLOAD_URL);
    reset();
  }

  HardwareSerial SerialAT(1);
  SerialAT.begin(115200, SERIAL_8N1, 16, 17, false);
  delay(5000);

  setupMqtt(setupGprs(SerialAT), mqttCallback);
}

void loop()
{
  if (mqttConnected())
  {
    mqttLoop();
  }
  else
  {
    // Reconnect every 10 seconds
    unsigned long t = millis();
    if (t - lastReconnectAttempt > 10000L)
    {
      lastReconnectAttempt = t;
      if (mqttConnect(topic))
      {
        mqttPublish(phevInit, "Connected");
        subscribe(phevSend);
        subscribe(phevReset);

        lastReconnectAttempt = 0;
      }
    }
  }
  if (numMessages)
  {
    handleQueuedMessages();
  }
  if (WiFi.status() != WL_CONNECTED)
  {
    setupWifi(_SSID, _PASSWORD);
  }
  else
  {
    if (!carclient.connected())
    {
      if (!connectToCar(_CARHOST, _CARPORT))
      {
        setupWifi(_SSID, _PASSWORD);
      }
    }
    else
    {
      if (carclient.available())
      {
        unsigned char buf[1024];
        int len = carclient.readBytes(buf, (carclient.available() < 1024 ? carclient.available() : 1024));
#ifdef _DEBUG
        Serial.print("Response from car ");
        int i;
        for (i = 0; i < len; i++)
        {
          Serial.print(buf[i], HEX);
        }
#endif
        mqttPublish(phevReceive, buf, len);
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
      mqttPublish(phevConnected, msg.c_str());
      carclient = car;
      return true;
    }
    else
    {
      Serial.print("connection failed status : ");
      Serial.println(status);

      mqttPublish(phevConnected, "FAIL : cannot connect to car");
      return false;
    }
    Serial.println("No Wifi");
    mqttPublish(phevError, "WiFi not connected");
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
    mqttPublish(phevReset, "Resetting");
    reset();
  }
  if (topic == String(phevConnectToCar))
  {
    setupWifi(_SSID, _PASSWORD);
    connectToCar(_CARHOST, _CARPORT);
  }
  return;
}
void reset()
{
  Serial.println("Resetting");
  ESP.restart();
}
