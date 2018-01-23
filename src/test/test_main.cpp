
#include "gtest/gtest.h"
#include "test_fifo.cpp"
#include "test_eventloop.cpp"


int main(int argc, char **argv) {
  ::testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}
