#ifndef _EVENTLOOP_H_
#define _EVENTLOOP_H_

class EventLoop
{
    public:
        EventLoop(void);
        ~EventLoop(void);
        void begin(void);
        void loop(void);
};
#endif
