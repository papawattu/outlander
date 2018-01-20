
#include "../main.h"
#include "gtest/gtest.h"


TEST(MainTest, Bob) {
  log("Hello");
}

int main(int argc, char **argv) {
  ::testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}
