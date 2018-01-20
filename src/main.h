#ifndef _MAIN_H

#ifndef ARDUINO
#include <iostream>
#else
#include "Arduino.h"
#endif

#define INCOMING_MQTT_MSG "IncomingMqttMsg"

void mainSetup(void);
void eventLoop(void);
void log(const char *);
#endif
