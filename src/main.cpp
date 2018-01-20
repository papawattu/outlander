#include "events.h"
#include "main.h"

EventHandler eventHandler;

#ifndef ARDUINO
void log(const char * message) 
{
    std::cout << message;
}
/* int main() {
    mainSetup();
    for(;;)
    {
	eventLoop();
    }
} 
 */
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
