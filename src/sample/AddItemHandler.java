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
