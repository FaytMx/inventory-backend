package cc.faytmx.springboot.app.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import cc.faytmx.springboot.app.inventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {

}
