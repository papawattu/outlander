#ifndef GSM_H
#define GSM_H


#include <TinyGsmClient.h>

const char apn[] = "everywhere";
const char user[] = "eesecure";
const char pass[] = "secure";

TinyGsmClient client;

Client& setupGprs(Stream &serialAT)
{
    
    TinyGsm modem(serialAT);
    client.init(&modem);
    Serial.println("Initializing modem...");
    modem.restart();

    Serial.print("Waiting for network...");
    if (!modem.waitForNetwork())
    {
        Serial.println(" fail");
        ESP.restart();
    }
    Serial.println(" OK");

    Serial.print("Connecting to ");
    Serial.print(apn);
    if (!modem.gprsConnect(apn, user, pass))
    {
        Serial.println(" fail");
        ESP.restart();
    }
    Serial.println(" OK");
    return client;
}
#endif