#ifndef MATTCLIENT_H_
#define MQTTCLIENT_H_

#include <PubSubClient.h>

#include "gprsclient.h"
#include "events.h"

class MQTTClient
{
    public:
        MQTTClient(const Client&);
        ~MQTTClient(void);
        attachEventHandler(EventLoop&);   
};
#endif
