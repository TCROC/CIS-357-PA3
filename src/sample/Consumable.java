/**
 * Requirements met:
 *      This class is a sub class of StockItem.
 *      This class has polymorphism by overriding 'public String getItemTypeDetails()' and 'public Pane drawInfoFillInNode(...)'
 *
 * Properties:
 *      private String expirationDate: the data at which this item will expire.
 *      private double calorieCount: the amount of calories are in this item.
 *
 * Methods:
 *      public String getExpirationDate(): returns the 'expirationDate' for this item.
 *      public void setExpirationDate(String expirationDate): sets the 'expirationDate' for this item.
 *      public double getCalorieCount(): gets the 'calorieCount' for this item.
 *      public void setCalorieCount(double calorieCount): sets the 'calorieCount' for this item.
 *      public String getItemTypeDetails(): returns the type details specific to this item.
 *      public Pane drawInfoFillInNode(Main main, StockManager stockManager, boolean isAddWindow): draws the pane for filling in info for this particular item.
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

public abstract class Consumable extends StockItem {

    private String expirationDate;
    private double calorieCount;

    public Consumable() {

    }

    public Consumable(String expirationDate, double calorieCount, String name, double price, boolean isOnSale, String itemDescription, String itemCategory) throws IllegalItemException {
        super(name, price, isOnSale, itemDescription, itemCategory);
        this.expirationDate = expirationDate;
        this.calorieCount = calorieCount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(double calorieCount) {
        this.calorieCount = calorieCount;
    }

    @Override
    public String getItemTypeDetails() {
        String details = "\nDetails:\n";
        details += "Expiration Date: " + expirationDate + "\n";
        details += "Calorie Count: " + calorieCount + "\n";
        return details;
    }

    @Override
    public Pane drawInfoFillInNode(Main main, StockManager stockManager, boolean isAddWindow) {
        VBox pane = (VBox) super.drawInfoFillInNode(main, stockManager, false);

        Text expirationDateLabel = new Text("Expiration Date");
        TextField dateTextField = new TextField();
        Text calorieCountLabel = new Text("Calorie Count");
        TextField categoryTextArea = new TextField();

        Button addButton = (Button) pane.getChildren().get(pane.getChildren().size() - 1);

        EventHandler<ActionEvent> current = addButton.getOnAction();

        addButton.setOnAction(i ->
        {
            current.handle(i);
            expirationDate = dateTextField.getText();
            calorieCount = Double.parseDouble(categoryTextArea.getText());

            if (isAddWindow) {
                stockManager.addItem(this);
                main.drawUnsortedItemsPane(stockManager);
            }
        });

        // Do this to make sure the add button is always at the bottom.
        pane.getChildren().remove(addButton);

        pane.getChildren().addAll(expirationDateLabel, dateTextField, calorieCountLabel, categoryTextArea, addButton);

        return pane;
    }
}
