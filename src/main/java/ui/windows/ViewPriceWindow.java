package ui.windows;

import backend.services.PriceService;
import backend.services.QuestionService;
import com.googlecode.lanterna.gui2.*;
import models.Price;
import models.Question;
import ui.UIController;

import java.util.List;

public class ViewPriceWindow extends BasicWindow {

    private final UIController ui;
    private final PriceService service;

    public ViewPriceWindow(UIController ui, PriceService service) {
        super("All Questions");
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

        List<Price> prices = service.getAllPrices();

        ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);

        for (Price x : prices) {
            alb.addItem(x.products_id() + ". $" + String.valueOf(x.price()) + ".00", () -> System.out.println(x.products_id()));
        }
        alb.addItem("-----Back-----", () -> ui.closeWindow(this));

        return panel;
    }
}
