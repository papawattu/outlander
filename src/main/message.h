#ifndef MESSAGE_H_
#define MESSAGE_H_

typedef const char* Topic;
struct MessagePayload
{
    MessagePayload() {}
    MessagePayload(uint8_t * p, const size_t s) : payload(p), size(s) {}
    MessagePayload(const MessagePayload& mp) : size(mp.size) 
    { 
        payload = (uint8_t *) malloc(mp.size);
        memcpy(payload,mp.payload,mp.size);
    }
    uint8_t * payload;
    size_t size;
     
}; 

struct Message
{
    Message(void) {}
    Message(MessagePayload p, Topic t = "") : topic(t),  payload(p) {}
    Topic topic;
    MessagePayload payload;

};
#endif