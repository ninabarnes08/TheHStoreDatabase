import backend.Database;
import backend.repositories.PricesRepo;
import backend.repositories.ProductsRepo;
import backend.repositories.QuestionRepo;
import backend.services.PriceService;
import backend.services.ProductService;
import backend.services.QuestionService;
import models.Price;
import ui.Gui;
import ui.UIController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
Initializes everything
UI
    GUI (Window management wrapper) -> UIController (Navigation) -> Windows (What we see)
Backend
    Service (used in UIController) -> Repositories (Queries the db) -> DB (Handles the DB connection)
 */
public class App {
    public static void run() throws SQLException {
        String url = "jdbc:sqlite:db";
        try (
                Database db = new Database(url);
        ) {
            // database
            db.connect();
            Connection conn = db.getConnection();

            // Repositories
            ProductsRepo productsRepo = new ProductsRepo(conn);
            PricesRepo pricesRepo = new PricesRepo(conn);

            // Services
            ProductService productService = new ProductService(productsRepo);
            PriceService priceService = new PriceService(pricesRepo);


            // GUI
            Gui gui = new Gui();
            gui.start();
            UIController ui = new UIController(gui, productService, priceService);
            ui.showMainMenu();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
