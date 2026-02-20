package backend.services;

import backend.repositories.ProductsRepo;
import models.Product;

import java.util.List;

public class ProductService {
    private final ProductsRepo repo;
    public ProductService(ProductsRepo repo){
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.getProducts();
    }
}
