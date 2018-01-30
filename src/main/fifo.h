#ifndef _FIFO_H_
#define _FIFO_H_

#include <stdlib.h>
#include <stdint.h>
#include <string.h>
template <typename T,size_t N>
class Fifo
{
  public:
    Fifo() : count(0), first(0), last(0) {}

    ~Fifo() {}
    void push(const T* value)
    {
        if(count == N) {
            return;
        }
        int idx = last;
        memcpy((void *) &buffer[idx], (void *) value, (size_t) sizeof(T)); 
        last = (last + 1) % (N - 1);
        count ++; 
    }
    int pop(T *t)
    {
        if(count == 0) return -1;
        int idx = first;
        t = &buffer[idx];
        first = (first + 1) % (N - 1);
        count --;
        return sizeof(T);
    }
    auto pop(void)
    {
        auto idx = first * sizeof(T);
        first = (first + 1) % (N - 1);
        count --;
        return buffer[idx];
    }
    size_t getSize() 
    {
        return N;
    }
    uint16_t getCount()
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