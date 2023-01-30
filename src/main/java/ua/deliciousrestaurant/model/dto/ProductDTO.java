package ua.deliciousrestaurant.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private int idProduct;
    private int categoryId;
    private String categoryName;
    private String nameProduct;
    private int costProduct;
    private int weightProduct;
    private String imgProduct;
}
