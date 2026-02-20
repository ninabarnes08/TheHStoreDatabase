package ui;

import backend.services.ProductService;
import backend.services.QuestionService;
import com.googlecode.lanterna.gui2.Window;
import ui.windows.AllProductsWindow;
import ui.windows.AllQuestionsWindow;
import ui.windows.MainWindow;

/*
Handles navigation
 */
public class UIController {
    private final Gui gui;
    private final ProductService productService;

    public UIController(Gui gui, ProductService productService){
        this.gui = gui;
        this.productService = productService;
    }

    public void showMainMenu(){
        gui.show(new MainWindow(this));
    }

    public void showAllProductsPage(){
        gui.show(new AllProductsWindow(this, productService));
    }

    public void closeWindow(Window window) {
        window.close();
    }

    public void closeApp() {
        gui.close();
    }
}
