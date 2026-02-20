package ui.windows;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import ui.UIController;

import java.util.List;

public class MainWindow extends BasicWindow {

    private final UIController ui;

    public MainWindow(UIController ui, String title) {
        super(title);
        this.ui = ui;
        setHints(List.of(Window.Hint.CENTERED, Hint.EXPANDED, Hint.NO_POST_RENDERING));
        setComponent(build());
    }

    public MainWindow(UIController ui) {
        this(ui, "Main Menu");
    }

    private record MenuItem(String name, Runnable func, BorderLayout.Location location) {
    }

    private Component build() {
        Panel panel = new Panel();
        panel.setLayoutManager(
                new BorderLayout()
        );

//        MenuItem[] menu = {
//                new MenuItem("Search Questions by Tag", this::NoOp),
//                new MenuItem("Add New Question", this::NoOp),
//                new MenuItem("View All Questions", ui::showAllQuestionsPage),
//                new MenuItem("Exit", ui::closeApp)
//        };

        //lines 41-67 need to be fixed/updated.. but for the sake of moving on
        MenuItem[] menu = {
                new MenuItem("Filter by Category", this :: NoOp, BorderLayout.Location.TOP), //class :: function ...
                                                                        // so like AllItemsWindow :: showStuff
                new MenuItem("View All HStore Items!", ui::showAllProductsPage, BorderLayout.Location.LEFT),
                new MenuItem("View our most popular items", this::NoOp, BorderLayout.Location.RIGHT),
                new MenuItem("Search for cheap prices!", this::NoOp, BorderLayout.Location.BOTTOM)
        };

        for (MenuItem mi : menu) {
            panel.addComponent(new Button(mi.name, mi.func), mi.location);
//
//            panel.addComponent(new Button("Filter by Category", () -> {
//                System.out.println("Struggling huh?");
//            }), BorderLayout.Location.TOP);
//
//            panel.addComponent(new Button("View All HStore Items!", () -> {
//                System.out.println("tapped the button vo=r!");
//            }), BorderLayout.Location.LEFT);
//
//            panel.addComponent(new Button("View our most popular items!", () -> {
//                System.out.println("popular items estan aqui");
//            }), BorderLayout.Location.RIGHT);
//
//            panel.addComponent(new Button("Search for cheap prices!", () -> {
//                System.out.println("bro there's not anything cheap here bffr");
//            }), BorderLayout.Location.BOTTOM);

        }

        return panel;
    }

    private void NoOp() {
    }
}
