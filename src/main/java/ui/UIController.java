package ui;

import backend.services.PriceService;
import backend.services.ProductService;
import backend.services.QuestionService;
import com.googlecode.lanterna.gui2.Window;
import ui.windows.AllProductsWindow;
import ui.windows.AllQuestionsWindow;
import ui.windows.MainWindow;
import ui.windows.ViewPriceWindow;

/*
Handles navigation
 */
public class UIController {
    private final Gui gui;
    private final ProductService productService;
    private final PriceService priceService;

        public UIController(Gui gui, ProductService productService, PriceService priceService){
        this.gui = gui;
        this.priceService = priceService;
        this.productService = productService;
    }

//    public UIController(Gui gui, ProductService productService){
//        this.gui = gui;
//        this.productService = productService;
//        priceService = null;
//    }
//
//    public UIController(Gui gui, PriceService priceService){
//        this.gui = gui;
//        this.priceService = priceService;
//        productService = null;
//    }

    public void showMainMenu(){
        gui.show(new MainWindow(this));
    }

    public void showAllProductsPage(){
        gui.show(new AllProductsWindow(this, productService));
    }

    public void showViewPriceWindow(){
        gui.show(new ViewPriceWindow(this, priceService));
    }

    public void closeWindow(Window window) {
        window.close();
    }

    public void closeApp() {
        gui.close();
    }
}
