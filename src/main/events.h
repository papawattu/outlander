#ifndef _EVENTS_H_
#define _EVENTS_H_
#include "fifo.h"
#include <string.h>
#include <stdint.h>

#define MAX_EVENT_NAME_SIZE 100
#define MAX_NUM_Q_EVENTS 100


class Event
{
  protected:
    char _name[MAX_EVENT_NAME_SIZE];
    uint8_t *_payload;
    size_t _size;

  public:
    Event(const char *, const uint8_t *, const size_t);
    ~Event();
    char *getName(void);
    size_t getPayloadSize(void);
    uint8_t *getPayload(uint8_t *);
};

class EventHandler
{
  protected:
  public:
    EventHandler();
    ~EventHandler();
    int queuedEvents(void);
    void addEventHandler(const char *);
    void dispatchEvent(Event *);
    void handleEvent(void);

  private:
    Fifo<Event,MAX_NUM_Q_EVENTS> eventsFifo;
};
class EventLoop
{
  public:
    EventLoop(void);
    ~EventLoop(void);
    void addListener(void (*)(Event&));
    void loop();
};
#endif