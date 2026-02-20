package backend.repositories;

import models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsRepo {
    private final Connection conn;
    public ProductsRepo(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getProducts(){
        List<Product> productList = new ArrayList<>();
        try(PreparedStatement sql = conn.prepareStatement("SELECT id, name FROM products")){
            try(ResultSet rs = sql.executeQuery()){
                while(rs.next()){
                    productList.add(new Product(rs.getInt("id"),rs.getString("name")
                    ,rs.getInt("category_id"),rs.getInt("season_id")));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return productList;
    }
}