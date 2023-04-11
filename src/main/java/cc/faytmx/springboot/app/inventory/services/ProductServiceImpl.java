package cc.faytmx.springboot.app.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.faytmx.springboot.app.inventory.dao.ICategoryDao;
import cc.faytmx.springboot.app.inventory.dao.IProductDao;
import cc.faytmx.springboot.app.inventory.model.Category;
import cc.faytmx.springboot.app.inventory.model.Product;
import cc.faytmx.springboot.app.inventory.response.ProductResponseRest;
import cc.faytmx.springboot.app.inventory.util.Util;

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
    @Transactional
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ProductResponseRest> searchById(Long id) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> products = new ArrayList<>();

        try {
            Optional<Product> product = productDao.findById(id);

            if (product.isPresent()) {

                byte[] imageDescompressed = Util.decompressZLib(product.get().getPicture());
                product.get().setPicture(imageDescompressed);
                products.add(product.get());
                response.getProduct().setProducts(products);
                response.setMetadata("respuesta ok", "0", "producto encontrado");
            } else {
                response.setMetadata("respuesta nok", "-1", "producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Error al consultar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseRest> searchByName(String name) {
        ProductResponseRest response = new ProductResponseRest();
        List<Product> products = new ArrayList<>();
        List<Product> listAux = new ArrayList<>();

        try {
            listAux = productDao.findByNameContainingIgnoreCase(name);

            if (listAux.size() > 0) {

                listAux.stream().forEach(product -> {
                    byte[] imageDescompressed = Util.decompressZLib(product.getPicture());
                    product.setPicture(imageDescompressed);
                    products.add(product);
                });

                response.getProduct().setProducts(products);
                response.setMetadata("respuesta ok", "0", "productos encontrados");
            } else {
                response.setMetadata("respuesta nok", "-1", "producto no encontrado");
                return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("respuesta nok", "-1", "Error al consultar producto");
            return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
    }

}
