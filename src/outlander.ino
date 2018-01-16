#define TINY_GSM_MODEM_SIM800
#define TINY_GSM_RX_BUFFER 31
#include <WiFi.h>

#include "events.h"
#include "wifihelper.h"
#include "firmware.h"
#include "gsm.h"
#include "mqtt.h"
//#include "carhandler.h"

#define WIFI_TIMEOUT 100

EventHandler eventHandler(Serial);
  
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


void initSerial() 
{
  Serial.begin(115200);
}
void checkUpdate() 
{
  pinMode(_UPDATE_PIN, INPUT);
  if (digitalRead(_UPDATE_PIN) > 0)
  {
    Serial.println("Downloading new firmware");
    connectToWiFi((char *)_LOCAL_SSID, (char *)_LOCAL_PASSWORD);
    downloadUpdate((char *)_DOWNLOAD_URL);
    reset();
  }
}
#define INCOMING_MQTT_MSG "IncomingMqttMsg"

void setup()
{
  HardwareSerial SerialAT(1);
  SerialAT.begin(115200, SERIAL_8N1, 16, 17, false);
  delay(10);

  initSerial();
  
  checkUpdate();

#ifndef _TEST
  Client mqttClient = setupGprs(SerialAT);
  
  setupMqtt(mqttClient, mqttCallback);
#endif
  eventHandler.addEventHandler(INCOMING_MQTT_MSG);
}

void loop()
{
  delay(1000);
  const char payload[] = "Hello";
  Event event(INCOMING_MQTT_MSG,(uint8_t *) &payload, sizeof(payload));
  eventHandler.dispatchEvent(&event);
  Serial.println(esp_get_free_heap_size());
}

void mqttCallback(char *topic, byte *payload, unsigned int len)
{

  //dispatchEvent(Event.INCOMING_MESSAGE)
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
    
    //carclientWrite((unsigned char *)msg, len);
    return;
  }
  if (topic == String(phevReset))
  {
    //mqttPublish(phevReset, "Resetting");
    reset();
  }
}
void reset()
{
  Serial.println("Resetting");
  ESP.restart();
}
