
#include <ESP8266WiFi.h>

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
#define _DEBUG_
// Select your modem:
#define TINY_GSM_MODEM_SIM800
//#define TINY_GSM_MODEM_SIM900
//#define TINY_GSM_MODEM_A6
//#define TINY_GSM_MODEM_M590
#define TINY_GSM_RX_BUFFER 31
#define MQTT_MAX_PACKET_SIZE 512
//#define TINY_GSM_USE_HEX
#include <TinyGsmClient.h>

#include <PubSubClient.h>
// Your GPRS credentials
// Leave empty, if missing user or pass
const char apn[] = "everywhere";
const char user[] = "eesecure";
const char pass[] = "secure";

// Use Hardware Serial on Mega, Leonardo, Micro
//#define SerialAT Serial1
#define SerialMon Serial
// or Software Serial on Uno, Nano
#include <SoftwareSerial.h>
//#include <StreamDebugger.h>
SoftwareSerial SerialAT(4, 5); // RX, TX

//StreamDebugger debugger(SerialAT, SerialMon);

WiFiClient carclient;
TinyGsm modem(SerialAT);
TinyGsmClient client(modem);
PubSubClient mqtt(client);

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

String ssid = "BTHub3-HSZ3";
String password = "simpsons";
String host = "WATTU";


boolean carConnected = false;
byte num = 0;
long lastReconnectAttempt = 0;

void setup()
{

  // Set console baud rate

  SerialMon.begin(115200);
  delay(10);

  setupGprs();
  //setupWifi();
  // MQTT Broker setup
  mqtt.setServer(broker, 1883);
  mqtt.setCallback(mqttCallback);
}

void setupGprs()
{
  // Set GSM module baud rate
  SerialAT.begin(115200);
  delay(3000);

  // Restart takes quite some time
  // To skip it, call init() instead of restart()
  Serial.println("Initializing modem...");
  modem.restart();

  Serial.print("Waiting for network...");
  if (!modem.waitForNetwork())
  {
    Serial.println(" fail");
    while (true)
      ;
  }
  Serial.println(" OK");

  Serial.print("Connecting to ");
  Serial.print(apn);
  if (!modem.gprsConnect(apn, user, pass))
  {
    Serial.println(" fail");
    while (true)
      ;
  }
  Serial.println(" OK");
}
void setupWifi()
{
  Serial.println("\nWifi starting");

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  if (WiFi.status() != WL_CONNECTED)
  {
    WiFi.persistent(false);
    WiFi.mode(WIFI_OFF);
    WiFi.mode(WIFI_STA);
    WiFi.begin(ssid.c_str(), password.c_str());
    while (WiFi.status() != WL_CONNECTED)
    {
      delay(500);
      Serial.print(".");
    }
  }
  Serial.println("\nWiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  Serial.println("\nWiFi started");
}

void subscribe()
{
  //mqtt.subscribe(phevPingOut);
  mqtt.subscribe(phevConnectToCar);
  mqtt.subscribe(phevWifiDetails);
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

void loop()
{

  if (mqtt.connected())
  {
    //ping();
    mqtt.loop();
    if(carConnected) {
      unsigned char buf[256];
      unsigned char buf2[300];
      int i = 0;
      while (carclient.available()) {
        byte b = carclient.read();
        buf[(i++ & 0xff)] = b;
        Serial.print(b, HEX);
      }
      if(i > 0) {
        Serial.println("\nSending mqtt response");

        encode_base64(buf, i, buf2);
        mqtt.publish(phevReceive, (const char *)buf2);

      }
    }
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

boolean connectToCar()
{
  if (!carclient.connected())
  {
    const int httpPort = 8080;
    if (carclient.connect(host.c_str(), httpPort)) {
      carConnected = true;
      mqtt.publish(phevConnected, "Connected to car");
      return true;
    } else {
      Serial.println("connection failed");
       mqtt.publish(phevConnected, "Failed to connect to car");
       return false;
    }
  } 
}
void ping()
{
  int i = 0;
  unsigned char buf[256];
  unsigned char buf2[300];

  unsigned char ping[] = {0xf9, 0x04, 0x00, 0x00, 0x00};
  unsigned char data[] = {0xf9, 0x04, 0x00, 0x00, 0x00, 0x00};

  ping[3] = (unsigned char)(num++ & 0xff);
  data[5] = checksum(ping);
  if(connectToCar()) {
    encode_base64(buf, i, buf2);
    mqtt.publish(phevPingOut, (const char *)data);
    carclient.write((unsigned char *)data, 6);
    unsigned long timeout = millis();
    while (carclient.available() == 0)
    {
      if (millis() - timeout > 5000)
      {
        Serial.println(">>> Client Timeout !");
        carclient.stop();
        return;
      }
    }
  } else {
    Serial.println(">>> Not connected"); 
    return;
  }

  // Read all the lines of the reply from server and print them to Serial

  i = 0;
  while (carclient.available())
  {
    byte b = carclient.read();
    buf[(i++ & 0xff)] = b;
  }
  encode_base64(buf, i, buf2);
  mqtt.publish(phevPingIn, (const char *)buf2);
}

void mqttCallback(char *topic, byte *payload, unsigned int len)
{

  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("]: ");
  Serial.write(payload, len);
  Serial.println();

  handleMessage(String(topic), payload);
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

void handleMessage(String topic, byte *msg)
{

  if (topic == String(phevWifiDetails))
  {
    ssid = getParameter("SSID", msg);
    password = getParameter("PASSWORD", msg);
    host = getParameter("HOST", msg);

    Serial.println("Got wifi details ");
    Serial.println("SSID " + ssid);
    Serial.println("PASSWORD " + password);
    Serial.println("HOST " + host);
    setupWifi();
    connectToCar();
    Serial.println("Connected to car");
  //  Serial.println("Ping");
  //  ping();

    return;
  }
  if (topic == String(phevConnectToCar))
  {
    Serial.println("Connecting to car");
    setupWifi();
    connectToCar();
    Serial.println("Connected to car");
  //  Serial.println("Ping");
  //  ping();
    return;
  }
  if (topic == String(phevSend))
  {
    String command;
    byte buf[512];
    unsigned char buf2[600];

    int len = decode_base64((unsigned char *)msg, (unsigned char *)buf);
    int i;

    Serial.print("Sending command ");
    for (i = 0; i < len; i++)
    {
      Serial.print(buf[i], HEX);
    }
    Serial.println("");
    carclient.write((unsigned char *)buf, len);
    return;
  }
}
