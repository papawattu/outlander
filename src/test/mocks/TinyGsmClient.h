class Stream
{

};
class TinyGsm
{
    public:
        TinyGsm(Stream&);
        void restart();
        void waitForNetwork();
        void gprsConnect(const char*,const char*, const char*);
};

class TinyGsmClient
{
    public:
        void init(TinyGsm*);
};

