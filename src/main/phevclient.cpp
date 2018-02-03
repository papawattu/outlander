#include "phevclient.h"

PHEVClient::PHEVClient(const PHEVMessageQueue& incoming, const PHEVMessageQueue& outgoing) : _incomingQueue(incoming), _outgoingQueue(outgoing) 
{

}
PHEVClient::~PHEVClient(void)
{

}
void PHEVClient::loop(void)
{
    //_outgoingQueue.push(_incomingQueue.pop());
}