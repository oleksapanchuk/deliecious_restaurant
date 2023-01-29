package ua.deliciousrestaurant.model.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Order {
    private int orderId;
    private int clientId;
    private int statusId;
    private int orderTotalPrice;
    private String addressDelivery;
    private String date;
    private boolean isOrderLiked;
    private List<Cart> orderProducts;
}
