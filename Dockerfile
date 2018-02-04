FROM gcc:latest
RUN apt-get update \
    && apt-get install --yes --no-install-recommends cmake
RUN apt-get clean  \
    && rm -rf /var/lib/apt/lists/*
COPY . /usr/src/outlander
WORKDIR /usr/src/outlander
RUN git clone https://github.com/google/googletest.git lib/googletest
RUN git clone https://github.com/eclipse/paho.mqtt.c.git lib/paho.mqtt.c
RUN git clone https://github.com/eclipse/paho.mqtt.cpp lib/paho.mqtt.cpp
WORKDIR /usr/src/outlander/lib/paho.mqtt.c
RUN make
RUN make install
WORKDIR /usr/src/outlander/lib/paho.mqtt.cpp
RUN mkdir build
WORKDIR /usr/src/outlander/lib/paho.mqtt.cpp/build/
RUN cmake -DPAHO_BUILD_DOCUMENTATION=FALSE -DPAHO_BUILD_SAMPLES=FALSE -DPAHO_MQTT_C_PATH=../../paho.mqtt.c ..
RUN make
WORKDIR /usr/src/outlander
RUN make clean
RUN make 
CMD ["./bin/tests"]
