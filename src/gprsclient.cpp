#include "gprsclient.h"

GPRSClient::GPRSClient(Stream& serialAT, const char* apn, const char* user, const char* pass) 
{
    TinyGsm modem(serialAT);
    init(&modem);
    modem.restart();
    modem.waitForNetwork();
    modem.gprsConnect(apn, user, pass);
}
GPRSClient::~GPRSClient(void)
{

}