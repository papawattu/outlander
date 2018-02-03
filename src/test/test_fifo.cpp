
#include "../main/fifo.h"
#include "gtest/gtest.h"

struct Simple
{
  int val;
  Simple(int x = 0) : val(x) {}
  //Simple(const Simple& s) : val(s.val) {}
  //inline bool operator==(const Simple& lhs, const Simple& rhs) { return lhs.val == rhs.val; }
};

TEST(FifoTest, shouldBeEmpty)
{
  Fifo<Simple,10> queue;
  EXPECT_EQ(0,queue.getCount());
}

TEST(FifoTest, shouldHandleEmptyQueue)
{
  Fifo<Simple,10> queue;
  ASSERT_ANY_THROW(queue.pop());
}
TEST(FifoTest, shouldAddToQueue)
{
  Fifo<Simple,10> queue;
  Simple tmp(1);
  queue.push(tmp);
  EXPECT_EQ(1,queue.getCount());
}
TEST(FifoTest, shouldHandleFullQueue)
{
  Fifo<Simple,2> queue;
  Simple tmp(4);
  queue.push(tmp);
  queue.push(tmp);
  queue.push(tmp);
  EXPECT_EQ(2,queue.getCount());
}
TEST(FifoTest, shouldAddAndRemoveFromQueue)
{
  Fifo<Simple,10> queue;
  Simple tmp(666);
  Simple tmp2;
  queue.push(tmp);
  EXPECT_EQ(1,queue.getCount());
  tmp2 = queue.pop();
  EXPECT_EQ(0,queue.getCount());
  EXPECT_EQ(666,tmp2.val);
}
TEST(FifoTest, shouldAddMoreAndRemoveFromQueue)
{
  Fifo<Simple,10> queue;
  Simple tmp(666);
  Simple tmp2(1000);
  Simple out;
  queue.push(tmp);
  queue.push(tmp2);
  EXPECT_EQ(2,queue.getCount());
  out = queue.pop();
  EXPECT_EQ(1,queue.getCount());
  EXPECT_EQ(666,out.val);
  out = queue.pop();
  EXPECT_EQ(1000,out.val);
}
TEST(FifoTest, shouldRollOverQueue)
{
  Fifo<Simple,4> queue;
  Simple tmp(0),tmp1(1),tmp2(2),tmp3(3),tmp4(4);
  Simple out;
  queue.push(tmp);
  queue.push(tmp1);
  queue.push(tmp2);
  queue.push(tmp3);
  out = queue.pop();
  EXPECT_EQ(0,out.val);
  queue.push(tmp4);
  out = queue.pop();
  EXPECT_EQ(3,queue.getCount());
  EXPECT_EQ(1,out.val); 
} 