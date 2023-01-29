package ua.deliciousrestaurant.model.entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Cart {
    private Product product;
    private int quantity;
}
