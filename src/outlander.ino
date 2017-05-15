//#include <ESP8266.h>
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
String ssid = "BTHub3-HSZ3";
String password = "simpsons";
String host = "WATTU";

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
  SerialMon.begin(115200);
  delay(10);
  incomingBuffer = (unsigned char *) malloc(INCOMING_BUFFER_SIZE);
  outgoingBuffer = (unsigned char *) malloc(OUTGOING_BUFFER_SIZE);
  
  if(incomingBuffer == NULL || outgoingBuffer == NULL) {
    Serial.println("Failed to allocate buffers");
    while(true);
  }
  setupGprs();
  //setupWifi();
 
  mqtt.setServer(broker, 1883);
  mqtt.setCallback(mqttCallback);
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

  if (carConnected)
  {
    unsigned char buf[MAX_PAYLOAD_SIZE];
    unsigned char buf2[300];
    int i = carclient.readBytes(buf, MAX_PAYLOAD_SIZE);
    if (i > 0)
    {
      Serial.print("\nSending mqtt response : ");
      Serial.println(i);
#ifdef _BASE64
      encode64((char *) buf, (unsigned char *) buf2,i);
      mqtt.publish(phevReceive, (const char *)buf2);
#else
      mqtt.publish(phevReceive, (byte *)buf,i);
#endif

    }
  }  else {

  }
  if(numMessages > 0) {
    handleQueuedMessages();
  }
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

boolean connectToCar()
{
  carConnected = false;
  if (!carclient.connected())
  {
    const int httpPort = 8080;
    if (carclient.connect(host.c_str(), httpPort))
    {
      carConnected = true;
      mqtt.publish(phevConnected, "Connected to car");
      return true;
    }
    else
    {
      Serial.println("connection failed");
      mqtt.publish(phevConnected, "Failed to connect to car");
      return false;
    }
    Serial.println("No Wifi");
  }
  return false;
}
void ping()
{
 //this is broken do not use
  int i = 0;
  unsigned char buf[256];
  char buf2[300];

  unsigned char ping[] = {0xf9, 0x04, 0x00, 0x00, 0x00};
  unsigned char data[] = {0xf9, 0x04, 0x00, 0x00, 0x00, 0x00};

  ping[3] = (unsigned char)(num++ & 0xff);
  data[5] = checksum(ping);
  if (connectToCar())
  {
    //encode64(buf, buf2,i);
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
  }
  else
  {
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
//  encode64(buf, buf2 ,i);
  mqtt.publish(phevPingIn, (const char *)buf2);
}

void mqttCallback(char *topic, byte *payload, unsigned int len)
{

  if(len > MAX_PAYLOAD_SIZE) {
    Serial.println("Message payload too big - ignoring");
    return;
  }
  if(numMessages == MAX_NUM_MESSAGES) {
    Serial.println("Maximum number of queued messages - ignoring");
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
  if (topic == String(phevReset))
  {
    Serial.println("Resetting");
    ESP.restart();
    return;
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
