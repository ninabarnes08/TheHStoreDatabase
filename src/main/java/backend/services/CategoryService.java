package backend.services;


import backend.repositories.CategoryRepo;
import models.Category;

import java.util.List;

public class CategoryService {
    private final CategoryRepo repo;
    public CategoryService(CategoryRepo repo){
        this.repo = repo;
    }
    public List<Category> getAllCategories(){
        return repo.getCategories();
    }
    public List<Category> filterAllChoices(String filterOne, String filterTwo){

        return null;
    }
}
