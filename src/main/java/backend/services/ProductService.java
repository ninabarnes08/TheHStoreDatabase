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

    public void addProduct(String name, String season){
        repo.addNewProduct(name, season);
    }

    public void deleteProduct(int id){
        repo.deleteProduct(id);
    }

    public void updateProduct(String sugName, int seasonId, int id){
        repo.updateProduct(sugName, seasonId, id);
    }
}
