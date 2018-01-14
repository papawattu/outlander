#ifndef UPDATE_FIRMWARE_H
#define UPDATE_FIRMWARE_H
#include <HTTPClient.h>
#include <Update.h>

void downloadUpdate(char* url)
{
    HTTPClient http;

    size_t size;

    Serial.print("Downloading firmware from "); 
    Serial.println(url);
    http.begin(url);
    int httpCode = http.GET();
    if (httpCode != 200)
    {
        Serial.print("Update error code ");
        Serial.println(httpCode);
        return;
    }

    int len = http.getSize();
    int contentLength = len;

    if (contentLength)
    {
        bool canBegin = Update.begin(contentLength);
        if (canBegin)
        {
            Serial.println("Begining OTA update");
            size_t written = Update.writeStream(http.getStream());

            if (written == contentLength)
            {
                Serial.println("Written : " + String(written) + " successfully");
            }
            else
            {
                Serial.println("Written only : " + String(written) + "/" + String(contentLength));
                return;
            }

            if (Update.end())
            {
                Serial.println("OTA done!");
                if (Update.isFinished())
                {
                    Serial.println("Update successfully completed. Rebooting.");
                    return;
                }
                else
                {
                    Serial.println("Update not finished. Something went wrong.");
                    return;
                }
            }
            else
            {
                Serial.println("Error Occurred. Error #: " + String(Update.getError()));
                return;
            }
        }
        else
        {
            Serial.println("Not enough space to begin OTA");
            return;
        }
    }
    else
    {
        Serial.println("There was no content in the response");
        return;
    }
}
#endif