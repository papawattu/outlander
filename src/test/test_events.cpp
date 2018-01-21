
#include "../events.h"
#include "gtest/gtest.h"

#define EVENT_ID "1"

TEST(EventsTest, shouldNotHaveQueuedEventsAtInit)
{
  EventHandler eventHandler;
  EXPECT_EQ(0,eventHandler.queuedEvents());
}

TEST(EventsTest, shouldQueueEvent)
{
  EventHandler eventHandler;
  const char payload[] = "Hello";
  Event event(EVENT_ID,(uint8_t *) &payload, sizeof(payload));
  EXPECT_EQ(0,eventHandler.queuedEvents());
  eventHandler.dispatchEvent(&event);
  EXPECT_EQ(1,eventHandler.queuedEvents());
}

TEST(EventsTest, shouldDeQueueEvent)
{
  EventHandler eventHandler;
  const char payload[] = "Hello";
  Event event(EVENT_ID,(uint8_t *) &payload, sizeof(payload));
  eventHandler.dispatchEvent(&event);
  eventHandler.handleEvent();
  EXPECT_EQ(0,eventHandler.queuedEvents());
}
TEST(EventsTest, shouldCallHandler)
{
  EventHandler eventHandler;
  const char payload[] = "Hello";
  Event event(EVENT_ID,(uint8_t *) &payload, sizeof(payload));
  eventHandler.dispatchEvent(&event);
  eventHandler.handleEvent();

}

