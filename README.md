# Virtual Queue

## This project includes:
- Persistence with Reactive Couchbase
- Reactive services with Spring Webflux
- Messaging with Amqp
- One endpoint to test RSocket
    - You can test the endpoint with rsocket-cli like:  
        `$ rsocket-cli --route queue-stream-event tcp://localhost:7000`