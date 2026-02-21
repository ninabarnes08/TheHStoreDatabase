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

        MenuItem[] menu = {
                new MenuItem("Filter by Category", this :: NoOp, BorderLayout.Location.TOP), //class :: function ...
                                                                        // so like AllItemsWindow :: showStuff
                new MenuItem("View All HStore Items!", ui::showAllProductsPage, BorderLayout.Location.LEFT),
                new MenuItem("View our most popular items", this::NoOp, BorderLayout.Location.RIGHT),
                new MenuItem("Search for cheap prices!", this::NoOp, BorderLayout.Location.BOTTOM)
        };

        for (MenuItem mi : menu) {
            panel.addComponent(new Button(mi.name, mi.func), mi.location);
        }

        return panel;
    }

    private void NoOp() {
    }
}
