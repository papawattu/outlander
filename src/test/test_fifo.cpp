
#include "../main/fifo.h"
#include "gtest/gtest.h"

TEST(FifoTest, shouldBeEmpty)
{
  Fifo<int,10> queue;
  EXPECT_EQ(0,queue.getCount());
}
TEST(FifoTest, shouldHandleEmptyQueue)
{
  Fifo<int,10> queue;
  int tmp;
  queue.pop(&tmp);
  EXPECT_EQ(0,queue.getCount());
}
TEST(FifoTest, shouldAddToQueue)
{
  Fifo<int,10> queue;
  int tmp;
  queue.push(&tmp);
  EXPECT_EQ(1,queue.getCount());
}
TEST(FifoTest, shouldHandleFullQueue)
{
  Fifo<int,1> queue;
  int tmp;
  queue.push(&tmp);
  queue.push(&tmp);
  EXPECT_EQ(1,queue.getCount());
}
TEST(FifoTest, shouldAddAndRemoveFromQueue)
{
  Fifo<int,10> queue;
  int tmp = 666;
  int tmp2 = 0;
  queue.push(&tmp);
  EXPECT_EQ(1,queue.getCount());
  queue.pop(&tmp2);
  EXPECT_EQ(0,queue.getCount());
  EXPECT_EQ(666,tmp2);
}
TEST(FifoTest, shouldRollOverQueue)
{
  Fifo<int,3> queue;
  int tmp = 0;
  int tmp2 = 0;
  queue.push(&tmp);
  tmp++;
  queue.push(&tmp);
  tmp++;
  queue.push(&tmp);
  tmp++;
  queue.push(&tmp);
  tmp++;
  queue.pop(&tmp2);
  EXPECT_EQ(0,tmp2);
  queue.push(&tmp);
  queue.pop(&tmp2);
  EXPECT_EQ(2,queue.getCount());
  EXPECT_EQ(1,tmp2);
}
