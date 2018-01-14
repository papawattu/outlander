#ifndef MQTT_H
#define MQTT_H

#include <PubSubClient.h>

const char *broker = "jenkins.wattu.com";

PubSubClient mqtt;

void setupMqtt(Client &client, void (*callback)(char*, uint8_t*, unsigned int))
{
    mqtt.setClient(client);
    mqtt.setServer(broker, 1883);
    mqtt.setCallback(callback);
}

void subscribe(const char *topic)
{
  mqtt.subscribe(topic);
}
boolean mqttConnect(const char * topic)
{
  Serial.print("Connecting to ");
  Serial.print(broker);
  if (!mqtt.connect(topic))
  {
    Serial.println(" fail");
    return false;
  }
  Serial.println(" OK");
  return mqtt.connected();
}
boolean mqttConnected() {
    return mqtt.connected();
}
void mqttLoop() 
{
    mqtt.loop();
}
void mqttPublish(const char * topic, const char * message) 
{
    mqtt.publish(topic,message);
}
void mqttPublish(const char * topic, const unsigned char * message, int length) 
{
    mqtt.publish(topic,message,length);
}
#endif