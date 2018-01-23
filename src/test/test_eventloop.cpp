
#include "../eventloop.h"
#include "gtest/gtest.h"

TEST(EventLoopTest, shouldBootStrap)
{
    EventLoop eventLoop;
    eventLoop.begin();
}
