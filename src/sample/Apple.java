/**
 * Requirements Met:
 *      This is a subclass of Consumable.
 *      This class has a protected property 'color'
 *      This class demonstrates polymorphism by overriding 'getItemTypeDetails()', 'getItemSummary()', and 'drawInfoFillInNode(...)'
 *
 * Properties:
 *      protected String color: the color of this particular apple.
 *
 * Methods:
 *      public String getColor(): returns 'color' i.e. the color of this apple.
 *      public void setColor(): sets 'color' i.e. the color of this apple.
 *      public String getItemTypeDetails(): returns the details for this particular item.
 *      public String getItemSummary(): returns the higher level summary of this particular item.
 *      public Pane drawInfoFillInNode(Main main, StockManager stockManager, boolean isAddWindow): draws the pane for filling out info for this item.
 */

package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Apple extends Consumable {

    protected String color;

    public Apple() {

    }

    public Apple(String color, String expirationDate, double calorieCount, String name, double price, boolean isOnSale, String itemDescription, String itemCategory) throws IllegalItemException {
        super(expirationDate, calorieCount, name, price, isOnSale, itemDescription, itemCategory);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getItemTypeDetails() {

        String details = super.getItemTypeDetails();
        details += "Color: " + getColor() + "\n";
        return details;
    }

    @Override
    public String getItemSummary() {
        String summary = "";
        summary += "Name: " + getItemName() + "\n";
        summary += "Category: " + getItemCategory() + "\n";
        summary += "Description: " + getItemDescription() + "\n";
        summary += "Price: " + getPrice() + "\n";

        return summary;
    }

    @Override
    public Pane drawInfoFillInNode(Main main, StockManager stockManager, boolean isAddWindow) {
        VBox pane = (VBox) super.drawInfoFillInNode(main, stockManager, false);

        Text colorLabel = new Text("Color");
        TextField colorTextField = new TextField();

        Button addButton = (Button) pane.getChildren().get(pane.getChildren().size() - 1);

        EventHandler<ActionEvent> current = addButton.getOnAction();

        addButton.setOnAction(i ->
        {
            current.handle(i);
            color = colorTextField.getText();

            if (isAddWindow) {
                try {
                    stockManager.addItem(new Apple(color, getExpirationDate(), getCalorieCount(), getItemName(), getPrice(), isOnSale(), itemDescription, itemCategory));
                }
                catch (IllegalItemException e){

                    }
                    finally {
                    main.drawUnsortedItemsPane(stockManager);
                }
            }
        });

        // Do this to make sure the add button is always at the bottom.
        pane.getChildren().remove(addButton);

        pane.getChildren().addAll(colorLabel, colorTextField, addButton);

        return pane;
    }
}
