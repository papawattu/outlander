#ifndef _MAIN_H

#ifndef ARDUINO
#include <iostream.h>
#endif

#define INCOMING_MQTT_MSG "IncomingMqttMsg"

void mainSetup(void);
void eventLoop(void);
#endif