# Virtual Queue
The purpose of this PoC is to create a full reactive flow
to sent notification to clients in real time when entities have been
created, updated or deleted.

## This project includes:
- Persistence with Reactive Couchbase
- Reactive services with Spring Webflux
- Messaging with Amqp
- Realtime events with SSE
- One endpoint to test RSocket
    - You can test the endpoint with rsocket-cli like:  
        `$ rsocket-cli --route queue-stream-event tcp://localhost:7000`
        
- Architecture Diagram:
    ![Image of Yaktocat](docs/architecture_diagram.png)