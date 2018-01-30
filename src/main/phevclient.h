#ifndef PHEVCLIENT_H_
#define PHEVCLIENT_H_

#include "../main/fifo.h"
#include "../main/message.h"

namespace 
{
    const size_t MAX_MESSAGES = 100;
}
typedef Fifo<Message,(size_t) MAX_MESSAGES> PHEVMessageQueue;
class PHEVClient
{
    public:
        PHEVClient(const PHEVMessageQueue&, const PHEVMessageQueue&);
        ~PHEVClient(void);
        void loop(void);
    private:
        PHEVMessageQueue _incomingQueue;
        PHEVMessageQueue _outgoingQueue;
};
#endif