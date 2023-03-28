package cc.faytmx.springboot.app.inventory.response;

import java.util.List;

import cc.faytmx.springboot.app.inventory.model.Category;
import lombok.Data;

@Data
public class CategoryResponse {
    private List<Category> category;

}
