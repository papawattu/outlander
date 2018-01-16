#include "events.h"

Event::Event(const char *name, const uint8_t *payload, const size_t size)
{
    strncpy(_name, name, MAX_EVENT_NAME_SIZE);
    _size = size;
    _payload = (uint8_t *)malloc(size);
    memcpy(_payload, payload, size);
}

Event::~Event()
{
    free(_payload);
}
char *Event::getName(void)
{
    return _name;
}
size_t Event::getPayloadSize(void)
{
    return _size;
}
uint8_t *Event::getPayload(uint8_t * payload)
{
    return _payload;
}
EventHandler::EventHandler(Stream &logger)
{
    log = &logger;
}
EventHandler::~EventHandler()
{
}
void EventHandler::addEventHandler(const char *name)
{
    log->print("Added new handler ");
    log->println(name);
    
}

void EventHandler::dispatchEvent(Event  * event)
{
    
    eventsFifo.push(event);
    
}