#ifndef MATTCLIENT_H_
#define MQTTCLIENT_H_

#include <client.h>

class MQTTClient
{
    public:
        MQTTClient(const Client&);
        ~MQTTClient(void);
};
#endif
