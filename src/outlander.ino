#include "gprsclient.h"
#include "wificlient.h"
#include "mqttclient.h"
#include "phevclient.h"
#include "events.h"

#define GSM_APN ""
#define GSM_USER ""
#define GSM_PASS ""

EventLoop EVLoop;

void setup()
{
  HardwareSerial SerialAT(1);
  SerialAT.begin(115200, SERIAL_8N1, 16, 17, false);
  delay(10);

  GPRSClient Gprs(SerialAT, GSM_APN, GSM_USER, GSM_PASS);
  
  WIFIClient Wifi;

  MQTTClient Mqtt(Gprs);
  Mqtt.attachEventHandler(&EVLoop);
  
  PHEVClient Phev(Wifi);
  Phev.attachEventHandler(&EVLoop);
  
  EVLoop.addListener(Phev.incomingEvent);
  EVLoop.addListener(Mqtt.incomingEvent);
  
}

void loop()
{
  EVLoop.loop();
}
