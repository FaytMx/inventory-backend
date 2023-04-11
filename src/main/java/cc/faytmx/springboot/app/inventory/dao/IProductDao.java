package cc.faytmx.springboot.app.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import cc.faytmx.springboot.app.inventory.model.Product;

public interface IProductDao extends CrudRepository<Product, Long> {
    
}
