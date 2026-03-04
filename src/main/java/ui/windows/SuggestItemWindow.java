package ui.windows;

import backend.services.ProductService;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import ui.UIController;

import java.util.List;

public class SuggestItemWindow extends BasicWindow {
    private final UIController ui;
    private final ProductService productService;

    public SuggestItemWindow(UIController ui, ProductService productService){
        super("Suggest an Item!");
        this.ui = ui;
        this.productService = productService;
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

        MenuItem nameQuestion = new MenuItem("What is the name of the item you would like to suggest?", this::NoOp);
        panel.addComponent(new Label(nameQuestion.name));
        TextBox firstResponse = new TextBox(new TerminalSize(35,1));
        panel.addComponent(firstResponse);

        newEmpty(panel, 2, 1);

        MenuItem seasonQuestion = new MenuItem("What season would this item be for? Type 1 for Winter, 2 for Fall, " +
                "\n3 for Spring, and 4 for Summer", this::NoOp);
        panel.addComponent(new Label(seasonQuestion.name));
        TextBox secondResponse = new TextBox(new TerminalSize(35,1));
        panel.addComponent(secondResponse);

        newEmpty(panel, 2, 1);

        Label feedback = new Label("");
        panel.addComponent(feedback);

        newEmpty(panel, 12, 7);

        Button subnitButton = new Button("Submit Responses", () ->{
            String nameRespo = firstResponse.getText().trim();
            String seasonRespo = secondResponse.getText().trim();

            if(nameRespo.isEmpty() || seasonRespo.isEmpty()){
                feedback.setText("Both Fields Must Be Filled In!");
            }

            productService.addProduct(nameRespo,seasonRespo);
            feedback.setText("Product Added!");
        });
        panel.addComponent(subnitButton);

        ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);
        alb.addItem("----Back----", () -> ui.closeWindow(this));

        return panel;
    }

    private void NoOp(){}
}
