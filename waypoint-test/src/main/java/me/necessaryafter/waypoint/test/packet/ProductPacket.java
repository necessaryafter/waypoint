package me.necessaryafter.waypoint.test.packet;

import lombok.RequiredArgsConstructor;
import me.necessaryafter.waypoint.api.packet.WaypointPacket;
import me.necessaryafter.waypoint.test.data.Product;

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
