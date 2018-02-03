#ifndef _FIFO_H_
#define _FIFO_H_

#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <iostream>

template <typename T,size_t N>
class Fifo
{
  public:
    Fifo() : count(0), first(0), last(0) 
    {
        static_assert(N > 1);
    }

    ~Fifo() {}
    void push(T& value)
    {
        if(count < N) {
            buffer[last] = value;
            last = (last + 1) % N;
            count ++; 
        }
    }
    T pop(void)
    {
        if(count == 0)  throw std::underflow_error("No entries");
        T t = buffer[first];
        first = (first + 1) % N;
        count --;
        return T(t); 
    }
    auto getSize() 
    {
        return N;
    }
    auto getCount()
    {
        return count;
    }
  private:
    T buffer[N];
    uint16_t count;
    uint16_t first;
    uint16_t last;
};

#endif