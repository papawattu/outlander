FROM gcc:latest
COPY . /usr/src/outlander
WORKDIR /usr/src/outlander
RUN make clean
RUN make 
CMD ["./bin/tests"]
