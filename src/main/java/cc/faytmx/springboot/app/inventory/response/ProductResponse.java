package cc.faytmx.springboot.app.inventory.response;

import java.util.List;

import cc.faytmx.springboot.app.inventory.model.Product;
import lombok.Data;

@Data
public class ProductResponse {
    List<Product> products;
}
