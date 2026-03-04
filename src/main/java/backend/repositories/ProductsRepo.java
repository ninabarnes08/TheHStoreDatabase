package backend.repositories;

import models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

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
                    productList.add(new Product(rs.getInt("id"),rs.getString("name")));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return productList;
    }

    public void addNewProduct(String name, String season){
        int seasonNumber = parseInt(season);
        String sqlStatement = "INSERT INTO products (name, category_id, season_id) VALUES" +
                " (?, ?, ?)" ;
        try(PreparedStatement sql = conn.prepareStatement(sqlStatement)){
            sql.setString(1, name + " (Suggested)");
            sql.setInt(2, 0);
            sql.setInt(3, seasonNumber);
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert product: " + e.getMessage());
            throw new RuntimeException("Database error during product insert", e);
        }
    }
}