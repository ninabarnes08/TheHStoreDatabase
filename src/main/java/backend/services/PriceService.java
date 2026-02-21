package backend.services;

import backend.repositories.PricesRepo;
import backend.repositories.ProductsRepo;
import models.Price;

import java.util.List;

public class PriceService {
    private final PricesRepo repo;
    public PriceService(PricesRepo repo){
        this.repo = repo;
    }

    public List<Price> getAllPrices(){
        return repo.getPrices();
    }
}
