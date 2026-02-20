package ui.windows;

import backend.services.ProductService;
import com.googlecode.lanterna.gui2.*;
import models.Product;
import ui.UIController;
import java.util.List;

public class AllProductsWindow extends BasicWindow {
    private final UIController ui;
    private final ProductService service;

    public AllProductsWindow(UIController ui, ProductService service) {
        super("All Products"); //come back and see what this should be if anything
        this.ui = ui;
        this.service = service;
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

        List<Product> products = service.getAllProducts();

        ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);

        for (Product p : products) {
            alb.addItem(p.name(), () -> System.out.println(p.id()));
        }
        alb.addItem("Back", () -> ui.closeWindow(this));

        return panel;
    }

}
