package ui.windows;

import backend.services.CategoryService;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import models.Category;
import ui.UIController;

import java.util.List;

import static javax.swing.text.StyleConstants.Alignment;
import static javax.swing.text.StyleConstants.setComponent;

public class FilterByCategoryWindow extends BasicWindow{
    private final UIController ui;
    private final CategoryService categoryService;

    public FilterByCategoryWindow(UIController ui, CategoryService categoryService){
        super("Filter By Category");
        this.ui = ui;
        this.categoryService = categoryService;
        setHints(List.of(Hint.FULL_SCREEN));
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

        List<Category> categories = categoryService.getAllCategories();

        MenuItem[] menu = {
                new MenuItem("Please type if you are looking to filter by \nSeason, Clothing Article, or Inventory", this::NoOp),
        };

        for (MenuItem mi : menu) {
            panel.addComponent(new Label(mi.name));
        }

        TextBox firstResponse = new TextBox(new TerminalSize(35,1));
        panel.addComponent(firstResponse);

        newEmpty(panel, 2, 1);

        MenuItem x = new MenuItem("What specifically are you looking for within the filter you selected?", this::NoOp);
        panel.addComponent(new Label(x.name));

        TextBox secondResponse = new TextBox(new TerminalSize(35,1));
        panel.addComponent(secondResponse);

        newEmpty(panel, 2, 1);

        Label feedback = new Label("");
        panel.addComponent(feedback);

        newEmpty(panel, 12, 7);
        Button submitButton = new Button("Submit Responses", () -> {
            String respoOne = firstResponse.getText().trim();
            String respoTwo = secondResponse.getText().trim();

            if(respoOne.isEmpty() || respoTwo.isEmpty()){
                feedback.setText("Both Fields Must Be Filled In!");
            }

            List<Category> results = categoryService.filterAllChoices(respoOne,respoTwo); //gotta add this
            if(results.isEmpty()){
                feedback.setText("No Results Found, Sorry!");
            } else {
                feedback.setText("Found" + results.size() + " results!");
            }
        });
        panel.addComponent(submitButton);







       /*
       NOTE TO SELF: FIX THIS WHEN YOU ARE ABLE; FOR NOW, WORK W TWO TEXTBOXES TO FIT THE REQUIREMENTS
        ComboBox<String> options = new ComboBox<>("Seasons", "Clothing Article", "Item Stock");
        panel.addComponent(options);
        System.out.println(options.isDropDownFocused());
        */

        /*ActionListBox alb = new ActionListBox();
        panel.addComponent(alb);

        for (Category c : categories){
            alb.addItem(c.category(), () -> System.out.println(c.id()));
        } */
        return panel;
    }

    private void NoOp() {}
}
