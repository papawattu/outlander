FROM gcc:latest
COPY . /usr/src/outlander
WORKDIR /usr/src/outlander
RUN git clone https://github.com/google/googletest.git lib/googletest
RUN make clean
RUN make 
CMD ["./bin/tests"]
