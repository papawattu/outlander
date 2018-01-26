#ifndef PHEVCLIENT_H_
#define PHEVCLIENT_H_


#include <client.h>
#include "events.h"

class PHEVClient 
{ 
    public:
        PHEVClient(const Client&);
        ~PHEVClient(void);
        void attachEventHandler(EventLoop&);
        static void incomingEvent(Event&);
};
#endif