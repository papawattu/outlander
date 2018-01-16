#ifndef MESSAGE_HANDLER_H
#define MESSAGE_HANDLER_H

#include <stdlib.h>
#include <stdint.h>
#include <string.h>

typedef struct Message Message;

    
struct Message
{
    uint8_t *payload;
    size_t length;
    enum MessageType {
        INCOMING_MSG, 
        OUTGOING_MSG
    } type;
    Message *next;
};

struct messageQueue 
{
    int numberOfMessages;
    Message *head;
    Message *tail;
} messageQueue;

void (*incomingFuncPtr)(Message *);
void (*outgoingFuncPtr)(Message *);

void messageHandler(void (*incomingHandler)(Message *), void (*outgoingHander)(Message *))
{
    messageQueue.numberOfMessages = 0;
    incomingFuncPtr = incomingHandler;
    outgoingFuncPtr = outgoingHander;
}

void incomingHandler(Message * message) 
{
    
}
void pushMessage(Message * message)
{
    messageQueue.tail->next = message;
    messageQueue.tail = message; 
}
Message * copyMessage(Message * source) 
{
    Message * target = (Message *) malloc(sizeof(Message));
    memcpy(target,source, sizeof(Message));
    uint8_t * payload = (uint8_t *) malloc(source->length);
    memcpy(payload,source->payload,source->length);
    target->payload = payload;
    return target;
}
Message* popMessage(Message message)
{
    if(messageQueue.numberOfMessages > 0) {
        Message * message = copyMessage(messageQueue.head);
        messageQueue.head = message->next;
        return message;
    } else {
        return NULL;
    }
}
void publish(Message *message)
{
#ifdef _DEBUG
    Serial.print(message->type == message->type.INCOMING_MSG ? "Incoming " : "Outgoing ");
    Serial.print("Message ");
    int i;
    for (i = 0; i < message->length; i++)
    {
        Serial.print(message->payload[i], HEX);
    }
    Serial.println("");
#endif

    pushMessage(message);
}
#endif