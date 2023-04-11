package cc.faytmx.springboot.app.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cc.faytmx.springboot.app.inventory.dao.ICategoryDao;
import cc.faytmx.springboot.app.inventory.dao.IProductDao;
import cc.faytmx.springboot.app.inventory.model.Category;
import cc.faytmx.springboot.app.inventory.model.Product;
import cc.faytmx.springboot.app.inventory.response.ProductResponseRest;

@Service
public class ProductServiceImpl implements IProductService {

    private ICategoryDao categoryDao;
    private IProductDao productDao;

    public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
        super();
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @Override
    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> products = new ArrayList<>();

        try {
            Optional<Category> category = categoryDao.findById(categoryId);

            if (category.isPresent()) {
                product.setCategory(category.get());
            } else {
                response.setMetadata("respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

            Product productSaved = productDao.save(product);

            if (productSaved != null) {
                products.add(productSaved);
                response.getProduct().setProducts(products);
                response.setMetadata("respuesta ok", "0", "Producto guardado");
            } else {
                response.setMetadata("respuesta nok", "-1", "Producto no guardado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Producto no guardado");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

}
