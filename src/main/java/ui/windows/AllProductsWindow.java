package ui.windows;

import backend.services.PriceService;
import backend.services.ProductService;
import com.googlecode.lanterna.gui2.*;
import models.Price;
import models.Product;
import ui.UIController;
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

        List<Product> products = productService.getAllProducts();
        //List<Price> prices = priceService.getAllPrices();

        ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);

        for (Product p : products) {
            alb.addItem(p.name(), () -> System.out.println(p.id()));
        }

//        for (Price d : prices){
//            alb.addItem(String.valueOf(d.price()), () -> System.out.println(d.products_id()));
//        }
        alb.addItem("----View Item Prices (In Order)----", ui::showViewPriceWindow);
        alb.addItem("----Back----", () -> ui.closeWindow(this));

        return panel;
    }

}
