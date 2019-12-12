package com.lab.service;

import com.lab.dao.EntityDao;
import com.lab.entity.Product;
import com.lab.enums.DaoType;
import com.lab.factory.DaoFactory;

import java.util.List;

public class StorageService {
    private EntityDao<Product> productDao;

    public StorageService() {
        this.productDao = DaoFactory.getEntityDao(DaoType.PRODUCT);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public void updateProduct(int productId, int quantity) {
        Product product = productDao.getById(productId, true);
        product.setQuantity(quantity);
        productDao.update(product);
    }

    public void createProduct(String name, int price, int quantity) {
        productDao.create(new Product(name, price, quantity));
    }
}
