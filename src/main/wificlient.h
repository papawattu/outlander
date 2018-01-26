#ifndef WIFICLIENT_H_
#define WIFICLIENT_H_

#include <WiFi.h>

class WIFIClient : public WiFiClient
{
    public:
        WIFIClient(const char*, const char*);
        ~WIFIClient(void);
    
};
#endif