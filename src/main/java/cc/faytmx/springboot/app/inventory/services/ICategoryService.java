package cc.faytmx.springboot.app.inventory.services;

import org.springframework.http.ResponseEntity;

import cc.faytmx.springboot.app.inventory.response.CategoryResponseRest;

public interface ICategoryService {
    public ResponseEntity<CategoryResponseRest> search();
    public ResponseEntity<CategoryResponseRest> searchById(Long id);
}
