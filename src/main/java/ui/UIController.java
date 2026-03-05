package ui;

import backend.services.CategoryService;
import backend.services.PriceService;
import backend.services.ProductService;
import backend.services.QuestionService;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.FileDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import ui.windows.*;

/*
Handles navigation
 */
public class UIController {
    private final Gui gui;
    private final ProductService productService;
    private final PriceService priceService;
    private final CategoryService categoryService;

        public UIController(Gui gui, ProductService productService, PriceService priceService,
                            CategoryService categoryService){
        this.gui = gui;
        this.priceService = priceService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    public MessageDialogButton showConfirmationDialog(String title, String message, MessageDialogButton... buttons) {
        return gui.showConfirmationDialog(title, message, buttons);
    }

    public void showMainMenu(){
        gui.show(new MainWindow(this));
    }

    public void showWindow(BasicWindow window){
            gui.show(window);
    }

    public void showFilterByCategoryPage(){
            gui.show(new FilterByCategoryWindow(this, categoryService));
    }

    public void showAllProductsPage(){
        gui.show(new AllProductsWindow(this, productService));
    }

    public void showSuggestionWindow(){gui.show(new SuggestItemWindow(this, productService));}

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
