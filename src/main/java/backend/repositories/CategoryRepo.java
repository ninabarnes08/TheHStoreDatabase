package backend.repositories;

import models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepo {
    private final Connection conn;
    public CategoryRepo(Connection conn){
        this.conn = conn;
    }
    public List<Category> getCategories(){
        List<Category> categoryList = new ArrayList<>();
        try(PreparedStatement sql = conn.prepareStatement("SELECT id, category FROM categories")){
            try(ResultSet rs = sql.executeQuery()){
                while(rs.next()){
                    categoryList.add(new Category(rs.getInt("id"), rs.getString("category")));
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    public List<Category> filterCategories(String one, String two){
        List<Category> filteredCats = new ArrayList<>();
//        try{
//            String sqlStatement = "SELECT name AS product_name FROM products" +
//                    "INNER JOIN categories ON products.category_id = categories.id" +
//                    "WHERE categories.name LIKE " + two +
//                    "INNER JOIN seasons ON products.season_id = seasons.id" +
//                    "WHERE seasons.name LIKE " + two;
//            PreparedStatement sql = conn.prepareStatement(sqlStatement);
//            sql.setString();
//        }
//        public static void numberPerCategory(Connection conn, String category){
//            try {
//                String sqlStatement = "SELECT category.name, COUNT(*) as film_count FROM film " +
//                        "INNER JOIN film_category ON film.film_id = film_category.film_id " +
//                        "INNER JOIN category ON film_category.category_id = category.category_id " +
//                        "WHERE category.name LIKE ? " +
//                        "GROUP BY category.name;";
//                PreparedStatement sql = conn.prepareStatement(sqlStatement);
//                sql.setString(1, category);
//                try(ResultSet result =sql.executeQuery()){
//                    while(result.next()){
//                        String categoryName = result.getString("name");
//                        int count = result.getInt("film_count");
//                        System.out.println("%s: %d".formatted(categoryName, count));
//                    }
//                }
//            } catch (SQLException e) {
//                System.out.println("Ruh-Roh - connection to DB was unsuccessful");
//                throw new RuntimeException(e);
//            }
//
//        }

        /*catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        return null;
    }
}
