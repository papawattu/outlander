#include "main/gprsclient.h"
#include "main/wificlient.h"
#include "main/mqttclient.h"
#include "main/phevclient.h"

using namespace std;

#define GSM_APN ""
#define GSM_USER ""
#define GSM_PASS ""

#define WIFI_SSID ""
#define WIFI_PASS ""

#define RX_PIN 16
#define TX_PIN 17

void setup()
{
  HardwareSerial SerialAT(1);
  SerialAT.begin(115200, SERIAL_8N1, RX_PIN, TX_PIN, false);
  
  GPRSClient Gprs(SerialAT, GSM_APN, GSM_USER, GSM_PASS);

  WIFIClient Wifi(WIFI_SSID,WIFI_PASS);

  MQTTClient Mqtt(Gprs);
  
  PHEVClient Phev(1,2);

}

void loop()
{
  
}
