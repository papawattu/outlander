
#include <ESP8266WiFi.h>
#include <ESP8266WiFiAP.h>
#include <ESP8266WiFiGeneric.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266WiFiScan.h>
#include <ESP8266WiFiSTA.h>
#include <ESP8266WiFiType.h>
#include <WiFiClient.h>
#include <WiFiClientSecure.h>
#include <WiFiServer.h>
#include <WiFiUdp.h>

#include "base64.hpp"
//#include "Base64.h"

/**************************************************************
 *
 * For this example, you need to install PubSubClient library:
 *   https://github.com/knolleary/pubsubclient/releases/latest
 *   or from http://librarymanager/all#PubSubClient
 *
 * TinyGSM Getting Started guide:
 *   http://tiny.cc/tiny-gsm-readme
 *
 **************************************************************
 * Use Mosquitto client tools to work with MQTT
 *   Ubuntu/Linux: sudo apt-get install mosquitto-clients
 *   Windows:      https://mosquitto.org/download/
 *
 * Subscribe for messages:
 *   mosquitto_sub -h test.mosquitto.org -t GsmClientTest/init -t GsmClientTest/ledStatus -q 1
 * Toggle led:
 *   mosquitto_pub -h test.mosquitto.org -t GsmClientTest/led -q 1 -m "toggle"
 *
 * You can use Node-RED for wiring together MQTT-enabled devices
 *   https://nodered.org/
 * Also, take a look at these additional Node-RED modules:
 *   node-red-contrib-blynk-websockets
 *   node-red-dashboard
 *
 **************************************************************/

// Select your modem:
#define TINY_GSM_MODEM_SIM800
//#define TINY_GSM_MODEM_SIM900
//#define TINY_GSM_MODEM_A6
//#define TINY_GSM_MODEM_M590

#include <TinyGsmClient.h>
#include <PubSubClient.h>

// Your GPRS credentials
// Leave empty, if missing user or pass
const char apn[]  = "everywhere";
const char user[] = "eesecure";
const char pass[] = "secure";

// Use Hardware Serial on Mega, Leonardo, Micro
//#define SerialAT Serial1

// or Software Serial on Uno, Nano
#include <SoftwareSerial.h>
SoftwareSerial SerialAT(4, 5); // RX, TX

TinyGsm modem(SerialAT);
TinyGsmClient client(modem);
PubSubClient mqtt(client);

const char* broker = "jenkins.wattu.com";

const char* topicLed = "GsmClientTest/led";
const char* topicInit = "GsmClientTest/init";
const char* topicLedStatus = "GsmClientTest/ledStatus";
const char* pingOut = "GsmClientTest/pingout";
const char* pingIn = "GsmClientTest/pingin";


const char* ssid      = "REMOTE45cfsc";
const char* wifipassword  = "fhcm852767";
const char* host      = "192.168.8.46";

#define LED_PIN 13
int ledStatus = LOW;
byte num  = 0;
long lastReconnectAttempt = 0;
WiFiClient carclient;
void setup() {
  pinMode(LED_PIN, OUTPUT);

  // Set console baud rate
  Serial.begin(115200);
  delay(10);

  setupWifi();

  // Set GSM module baud rate
  SerialAT.begin(115200);
  delay(3000);

  // Restart takes quite some time
  // To skip it, call init() instead of restart()
  Serial.println("Initializing modem...");
  modem.restart();

  // Unlock your SIM card with a PIN
  //modem.simUnlock("1234");

  Serial.print("Waiting for network...");
  if (!modem.waitForNetwork()) {
    Serial.println(" fail");
    while (true);
  }
  Serial.println(" OK");

  Serial.print("Connecting to ");
  Serial.print(apn);
  if (!modem.gprsConnect(apn, user, pass)) {
    Serial.println(" fail");
    while (true);
  }
  Serial.println(" OK");

  // MQTT Broker setup
  mqtt.setServer(broker, 1883);
  mqtt.setCallback(mqttCallback);
}

void setupWifi() {
  Serial.println("\nWifi starting");

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, wifipassword);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  Serial.println("\nWiFi started");

}

boolean mqttConnect() {
  Serial.print("Connecting to ");
  Serial.print(broker);
  if (!mqtt.connect("GsmClientTest")) {
    Serial.println(" fail");
    return false;
  }
  Serial.println(" OK");
  mqtt.publish(topicInit, "GsmClientTest started");
  mqtt.subscribe(topicLed);
  return mqtt.connected();
}

void loop() {

  
  if (mqtt.connected()) {
    ping();
    mqtt.loop();
  } else {
    // Reconnect every 10 seconds
    unsigned long t = millis();
    if (t - lastReconnectAttempt > 10000L) {
      lastReconnectAttempt = t;
      if (mqttConnect()) {
        lastReconnectAttempt = 0;
      }
    }
  }
  delay(100);
}

byte checksum(unsigned char * data){
  byte b = 0;
  int i;
  int j = sizeof(data);

  for (i = 0;i < j; i++)
  {
    b = data[i] + b & 0xff;
  }  
  return b;
} 


void connectToCar(){
  if(!carclient.connected()){
      const int httpPort = 8080;
    if (!carclient.connect(host, httpPort)) {
      Serial.println("connection failed");
      return;
    }
  } 
}
void ping() {
  unsigned char buf[256];
  unsigned char buf2[300];
  
  unsigned char ping[] = {0xf9,0x04,0x00,0x00,0x00,0xfd};
  
  ping[3] = (unsigned char) (num++ & 0xff);
  ping[5] = checksum(ping);
  connectToCar();
  encode_base64(buf,i,buf2);
  mqtt.publish(pingOut, (const char *) ping);
  carclient.write((unsigned char*) ping,6);
  unsigned long timeout = millis();
  while (carclient.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Client Timeout !");
      carclient.stop();
      return;
    }
  }
  
  // Read all the lines of the reply from server and print them to Serial
  
  int i=0;
  while(carclient.available()){
    byte b = carclient.read();
    buf[(i++ & 0xff)] = b;
  }
  encode_base64(buf,i,buf2);
  mqtt.publish(pingIn, (const char *) buf2);
  
}

void mqttCallback(char* topic, byte* payload, unsigned int len) {
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("]: ");
  Serial.write(payload, len);
  Serial.println();

  // Only proceed if incoming message's topic matches
  if (String(topic) == topicLed) {
    ledStatus = !ledStatus;
    digitalWrite(LED_PIN, ledStatus);
    mqtt.publish(topicLedStatus, ledStatus ? "1" : "0");
  }
}

