#ifndef _FIFO_H_
#define _FIFO_H_

#include <stdlib.h>
#include <stdint.h>
#include <string.h>
template <class T,size_t N>
class Fifo
{
  public:
    Fifo()
    {
        buffer = (uint8_t *) malloc(sizeof(T) * N);
        count = 0;
        first = 0;
        last = 0;
        size = N;
    }

    ~Fifo()
    {
        free(buffer);
    }
    void push(const T* value)
    {
        if(count == size) {
            return;
        }
        int idx = last * sizeof(T);
        memcpy((void *) &buffer[idx], (void *) value, (size_t) sizeof(T)); 
        last = (last + 1) % sizeof(T);
        count ++; 
    }
    int pop(T *t)
    {
        if(count == 0) return -1;
        int idx = first * sizeof(T);
        memcpy(t, &buffer[idx], (size_t) sizeof(T));
        first = (first + 1) % sizeof(T);
        count --;
        return sizeof(T);
    }
    size_t getSize() 
    {
        return size;
    }
    uint16_t getCount()
    {
        return count;
    }
  private:
    uint8_t * buffer;
    size_t size;
    uint16_t count;
    uint16_t first;
    uint16_t last;
};

#endif