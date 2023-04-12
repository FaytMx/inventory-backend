package cc.faytmx.springboot.app.inventory.services;

import org.springframework.http.ResponseEntity;

import cc.faytmx.springboot.app.inventory.model.Product;
import cc.faytmx.springboot.app.inventory.response.ProductResponseRest;

public interface IProductService {
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);

    public ResponseEntity<ProductResponseRest> searchById(Long id);

    public ResponseEntity<ProductResponseRest> searchByName(String name);
    // public ResponseEntity<ProductResponseRest> searchById(Long id);

    public ResponseEntity<ProductResponseRest> deleteById(Long id);

    public ResponseEntity<ProductResponseRest> search();

}
