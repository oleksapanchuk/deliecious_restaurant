package ua.deliciousrestaurant.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int idProduct;
    private String categoryName;
    private String nameProduct;
    private int costProduct;
    private int weightProduct;
    private String imgProduct;
}
