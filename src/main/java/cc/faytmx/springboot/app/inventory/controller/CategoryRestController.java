package cc.faytmx.springboot.app.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.faytmx.springboot.app.inventory.model.Category;
import cc.faytmx.springboot.app.inventory.response.CategoryResponseRest;
import cc.faytmx.springboot.app.inventory.services.ICategoryService;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * Search all categories
     * 
     * @return
     */
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchCategories() {
        ResponseEntity<CategoryResponseRest> response = categoryService.search();
        return response;
    }

    /**
     * Search category by id
     * 
     * @param id
     * @return
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoryById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.searchById(id);
        return response;
    }

    /**
     * Save category
     * 
     * @param category
     * @return
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category) {
        ResponseEntity<CategoryResponseRest> response = categoryService.save(category);
        return response;
    }

    /**
     * Update category by id
     * 
     * @param category
     * @param id
     * @return
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.update(category, id);
        return response;
    }

    /**
     * Delete category by id
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> deleteCategory(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = categoryService.deleteById(id);
        return response;
    }
}
