package backend.repositories;

import models.Price;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PricesRepo {
    private final Connection conn;
    public PricesRepo(Connection conn){
        this.conn = conn;
    }
    public List<Price> getPrices(){
        List<Price> priceList = new ArrayList<>();
        try(PreparedStatement sql = conn.prepareStatement("SELECT products_id, price FROM pricing")){
            try(ResultSet rs = sql.executeQuery()){
                while(rs.next()) {
                    priceList.add(new Price(rs.getInt("products_id"), rs.getInt("price")));
                  }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return priceList;
    }
}
