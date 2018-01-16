#include "events.h"
#include "main.h"

EventHandler eventHandler;

#ifndef ARDUINO
void log(char * message) 
{
    std::cout << message;
}
int main() {
    log("Hello");
} 
#else
void log(const char * message)
{
    Serial.println(message);
}
#endif
void mainSetup() 
{
    log("Hello");
}
void eventLoop()
{
  //delay(1000);
  const char payload[] = "Hello";
  Event event(INCOMING_MQTT_MSG,(uint8_t *) &payload, sizeof(payload));
  eventHandler.dispatchEvent(&event);
  log("Created event");

}
