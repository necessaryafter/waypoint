# 🔊 Waypoint (UNDER DEVELOPMENT!!!)
### A server-to-server communication with redis pubsub.

This is a straightforward server-to-server communication system using Redis PubSub. It enables servers to efficiently communicate by sharing messages through Redis. 

## Features

- Real-time communication between servers.
- Asynchronous publishing messages.
- Easy-to-integrate with existing applications.
- Minimal configuration required.
- Utilizes Redis PubSub for efficient message distribution.

## Prerequisites for use
- Java 11 or above
- Redis

## Example of uses
Before starting to use Waypoint, it is necessary to initialize the connection with Redis. For this, we have our own method that will create an instance of Redis and an instance of Waypoint for you. Check it out below:

```java
        final Waypoint waypoint = WaypointBuilder.newBuilder()
                .setAuthCredentials(new AuthCredentialsBuilder()
                        .setAddress("127.0.0.1")
                        .setPort(6379)
                        .build())
                .setCurrentServer(ServerBuilder.newBuilder()
                        .setId("lobby-1")
                        .build())
                .build();
```

The communication works through packets created beforehand by the developer. Check out an example below on how to create a packet.
```java
@Data
public final class Product {

    private final String productName;
    private final int productId;

}
```

```java
@RequiredArgsConstructor
public final class ProductPacket extends WaypointPacket<Product> {

    private final Product product;

    @Override
    public void onReceive() {
        System.out.println("Mensagem recebida!");
        System.out.println(product.getProductName());
    }

    @Override
    public void onSend() {
        System.out.println("Mensagem enviada!");
    }
}

```

Currently, there are two ways for you to communicate between your servers, these ways are globally and specifically. As the name suggests, globally will communicate with all servers that use **Waypoint**, while using the specific method will only send to the server specified in the method. Check below.

**Globally**
```java
        final WaypointPacketService packetService = waypoint.getPacketService();
        final Product product = new Product("Camisa do Flamengo Versão 3", 1);

        packetService.publish(new ProductPacket(product));
```

**Specified**
```java
        final WaypointPacketService packetService = waypoint.getPacketService();
        final ServerRegistry serverRegistry = waypoint.getServerRegistry();
        final Product product = new Product("Camisa do Flamengo Versão 2", 2);

        final Server server = serverRegistry.findServerById("server-id");
        if(server == null) return;
        
        packetService.send(server, new ProductPacket(product));
```
> a null check for the Server is absolutely necessary, otherwise errors may occur.

## Importing project from Jitpack
Under development
