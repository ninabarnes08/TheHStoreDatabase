package ui.windows;

import backend.services.ProductService;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import models.Product;
import ui.UIController;

import java.util.List;

public class EditSuggestionWindow extends BasicWindow {
    private final UIController ui;
    private final ProductService productService;
    private final Product product;


    public EditSuggestionWindow(UIController ui, ProductService productService, Product product){
        super("Edit an Item!");
        this.ui = ui;
        this.productService = productService;
        this.product = product;
        setHints(List.of(Hint.EXPANDED));
        setComponent(build());
    }

    private record MenuItem(String name, Runnable func) {
    }

    private void newEmpty(Panel panel, int col, int row){
        panel.addComponent(new EmptySpace(new TerminalSize(col, row)));
    }

    private Component build(){
        Panel panel = new Panel();
        panel.setLayoutManager(
                new LinearLayout(Direction.VERTICAL)
        );

        MenuItem nameQuestion = new  MenuItem("Rename this suggestion:", this::NoOp);
        panel.addComponent(new Label(nameQuestion.name));
        TextBox firstResponse = new TextBox(new TerminalSize(35,1));
        firstResponse.setText(product.name());
        panel.addComponent(firstResponse);

        newEmpty(panel, 2, 1);

        MenuItem seasonQuestion = new MenuItem("What season would this suggestion now be for? Type 1 for Winter, " +
                "2 for Fall, " +
                "\n3 for Spring, and 4 for Summer", this::NoOp);
        panel.addComponent(new Label(seasonQuestion.name));
        TextBox secondResponse = new TextBox(new TerminalSize(35,1));
        panel.addComponent(secondResponse);

        newEmpty(panel, 2, 1);

        Label feedback = new Label("");
        panel.addComponent(feedback);

        newEmpty(panel, 12, 7);

        Button submitButton = new Button("Submit Responses", () ->{
            String nameRespo = firstResponse.getText().trim();
            String seasonRespo = secondResponse.getText().trim();

            if(nameRespo.isEmpty() || seasonRespo.isEmpty()){
                feedback.setText("All Fields Must Be Filled In!");
            }

            int season = Integer.parseInt(seasonRespo);

            if (season < 1 || season > 4) {
                feedback.setText("Season must be between 1 and 4!");
            }

            productService.updateProduct(nameRespo, season, product.id());


            feedback.setText("Product Added!");
        });
        panel.addComponent(submitButton);

        ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);
        alb.addItem("----Back----", () -> ui.closeWindow(this));

        return panel;
    }

    private void NoOp(){};
}
