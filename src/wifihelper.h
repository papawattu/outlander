#ifndef WIFI_HELPER_H
#define WIFI_HELPER_H
#define WIFI_TIMEOUT 100
#include <WiFi.h>

#define _MAC_ADDR {0xac, 0x37, 0x43, 0x4d, 0xda, 0x90}
#define _HOSTNAME "android-88a84719193c6b9"

void connectToWiFi(const char *ssid, const char *password)
{
    int times = 0;
    Serial.print("Connecting to WIFI");
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED && times < WIFI_TIMEOUT)
    {
        delay(1000);
        Serial.print(".");
        times++;
    }
    Serial.println(" Connected");
}

void WiFiEvent(WiFiEvent_t event)
{
    Serial.printf("[WiFi-event] event: %d\n", event);

    switch (event)
    {
    case SYSTEM_EVENT_STA_START:
        //set sta hostname here
        WiFi.setHostname(_HOSTNAME);
        break;
    case SYSTEM_EVENT_STA_GOT_IP:
        Serial.println("WiFi connected");
        Serial.println("IP address: ");
        Serial.println(WiFi.localIP());
        break;
    case SYSTEM_EVENT_STA_DISCONNECTED:
        Serial.println("WiFi lost connection");
        break;
    }
}

void setupWifi(const char *ssid, const char *password)
{
    int times = 0;
    Serial.println("\nWifi starting");
    WiFi.disconnect(true);
    WiFi.mode(WIFI_OFF);
    delay(1000);
    Serial.print("Connecting to ");
    Serial.println(ssid);
    uint8_t mac[] = _MAC_ADDR;
    if (!esp_wifi_set_mac(WIFI_IF_STA, &mac[0]))
    {
        Serial.println("Cannot set MAC");
    }

    WiFi.onEvent(WiFiEvent);

    WiFi.mode(WIFI_STA);
    if (WiFi.status() != WL_CONNECTED)
    {
        connectToWiFi(ssid, password);
    }
}
#endif
