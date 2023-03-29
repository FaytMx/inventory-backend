package cc.faytmx.springboot.app.inventory.services;

import org.springframework.http.ResponseEntity;

import cc.faytmx.springboot.app.inventory.model.Category;
import cc.faytmx.springboot.app.inventory.response.CategoryResponseRest;

public interface ICategoryService {
    public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	public ResponseEntity<CategoryResponseRest> save(Category category);
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
