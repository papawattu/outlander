#ifndef CAR_HANDLER_H
#define CAR_HANDLER_H

#include <WiFi.h>

#include "messagehandler.h"

boolean carConnected = false;
Client client;

boolean connectToCar(const char *host, const int httpPort, Client &carClient)
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
            client = car;
            return true;
        }
        else
        {
            Serial.print("connection failed status : ");
            Serial.println(status);

            return false;
        }
        Serial.println("No Wifi");
    }
    return false;
}

void carLoop()
{
    if (client.available())
    {

        Message message;
        message.payload = malloc(4096);
        message.length = client.readBytes(message.payload, 4096);
        publish(OUTGOING_MSG, message);
        free(message.payload);
    }
}

#endif