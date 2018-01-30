//#include "../main/phevclient.h"
#include "../main/fifo.h"
#include "../main/phevclient.h"
#include "gtest/gtest.h"

TEST(PHEVClientTest, shouldHandleIncomingMessage)
{
    PHEVMessageQueue input;
    PHEVMessageQueue output;
    uint8_t bytes[] {0,1,2,3,4};

    MessagePayload payload {bytes, sizeof(bytes)};
    Message m = Message(payload, (const char *) "Test");
    input.push(&m);
    PHEVClient sut(input,output);    
    sut.loop();
    
    //EXPECT_EQ(1,output.getCount());
}

