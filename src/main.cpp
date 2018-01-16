#include "events.h"
#include "main.h"

EventHandler eventHandler;

void mainSetup() 
{

}
void eventLoop()
{
  //delay(1000);
  const char payload[] = "Hello";
  Event event(INCOMING_MQTT_MSG,(uint8_t *) &payload, sizeof(payload));
  eventHandler.dispatchEvent(&event);
  //log(esp_get_free_heap_size());

}

#ifndef ARDUINO
void main() {
    cout << "Hello";
}
#endif