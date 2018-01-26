#ifndef GPRSCLIENT_H_
#define GPRSCLIENT_H_

#define TINY_GSM_MODEM_SIM800
#define TINY_GSM_RX_BUFFER 31

#include <TinyGsmClient.h>

class GPRSClient : public TinyGsmClient
{
    public:
        GPRSClient(Stream&, const char*, const char*, const char*);
        ~GPRSClient(void);

};
#endif
