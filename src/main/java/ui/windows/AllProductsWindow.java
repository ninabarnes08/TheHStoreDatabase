package ui.windows;

import backend.services.PriceService;
import backend.services.ProductService;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import models.Price;
import models.Product;
import ui.UIController;

import javax.swing.*;
import java.util.List;

public class AllProductsWindow extends BasicWindow {
    private final UIController ui;
    private final ProductService productService;
   // private final PriceService priceService;

    public AllProductsWindow(UIController ui, ProductService productService) {
        super("All Products");
        this.ui = ui;
        this.productService = productService;
        setHints(List.of(Hint.CENTERED));
        setComponent(build());
    }


    private record MenuItem(String name, Runnable func) {
    }

    private Component build() {
        Panel panel = new Panel();
        panel.setLayoutManager(
                new LinearLayout(Direction.VERTICAL)
        );
        Panel productsPanel = new Panel().setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        panel.addComponent(productsPanel);

        List<Product> products = productService.getAllProducts();



        ActionListBox alb = new ActionListBox();
        ActionListBox deleteAlb = new ActionListBox();
        productsPanel.addComponent(deleteAlb);
        productsPanel.addComponent(alb);


        for (Product p : products) {
            deleteAlb.addItem("X (id " + String.valueOf(p.id()) + ")", () -> {
                MessageDialogButton res =
                        ui.showConfirmationDialog("Delete Record", "Are you sure you want to delete this suggestion?\n",
                                MessageDialogButton.Cancel, MessageDialogButton.OK);
                if(res == MessageDialogButton.OK){
                    productService.deleteProduct(p.id());
                    ui.showWindow(new AllProductsWindow(ui, productService));
                    //panel.removeAllComponents();
                }
            });
            alb.addItem(p.name() + " (" + String.valueOf(p.id()) + ") ", () -> System.out.println(p.id()));
        }


        alb.addItem("----Suggest a New Item!----", ui::showSuggestionWindow);
        alb.addItem("----View Item Prices (In Order)----", ui::showViewPriceWindow);
        alb.addItem("----Back----", () -> ui.closeWindow(this));

        return panel;
    }
    private void drawPanel(Panel panel){
        panel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
    }

}
