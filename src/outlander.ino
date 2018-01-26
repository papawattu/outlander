#include "main/gprsclient.h"
#include "main/wificlient.h"
#include "main/mqttclient.h"
#include "main/phevclient.h"
#include "main/events.h"

#define GSM_APN ""
#define GSM_USER ""
#define GSM_PASS ""

#define WIFI_SSID ""
#define WIFI_PASS ""

#define RX_PIN 16
#define TX_PIN 17

EventLoop EVLoop;

void setup()
{
  HardwareSerial SerialAT(1);
  SerialAT.begin(115200, SERIAL_8N1, RX_PIN, TX_PIN, false);
  GPRSClient Gprs(SerialAT, GSM_APN, GSM_USER, GSM_PASS);

  WIFIClient Wifi(WIFI_SSID,WIFI_PASS);

  MQTTClient Mqtt(Gprs);
  Mqtt.attachEventHandler(EVLoop);
  
  PHEVClient Phev(Wifi);
  Phev.attachEventHandler(EVLoop);
  
  EVLoop.addListener(Phev.incomingEvent);
  EVLoop.addListener(Mqtt.incomingEvent);
  
}

void loop()
{
  EVLoop.loop();
}
