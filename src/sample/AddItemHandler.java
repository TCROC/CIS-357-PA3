/**
 *
 * Properties:
 * private Main main: a reference to the main class for displaying the add item window when the button is pressed.
 *
 * Methods:
 * public void handle(Main main): This method overrides the handle method in EventHandler<>. It handles ActionEvents when the AddItem button is pressed.
 */

package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddItemHandler implements EventHandler<ActionEvent> {

    private Main main;

    public AddItemHandler(Main main){
        this.main = main;
    }

    @Override
    public void handle(ActionEvent event) {
        main.drawAddItemWindow(main.stockManager);
    }
}
