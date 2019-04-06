/**
 * Requirements met:
 *      This is the superclass for all StockItems.
 *      This class contains 2 abstract methods: getItemTypeDetails and getItemSummary.
 *      This class extends the interface Comparable
 *      This class has 1 public static property: itemCount.
 *      This class has 3 private properties: price, isOnSale, and itemId.
 *      This class has 2 protected properties: itemDescription, itemCategory.
 *      This class demonstrates polymorphism by overriding the 'toString()' and 'compareTo(...)' methods.
 *
 * Properties:
 *      public static int itemCount: this is the global count for all StockItem instances.
 *      private String name: this is the name of the StockItem.
 *      private double price: this is the price off the StockItem
 *      private boolean isOnSale: this is a boolean for whether or not the StockItem is on sale.
 *      private double itemId: this is the uniqueId for the StockItem.
 *      protected String itemDescription: this is the description for the StockItem.
 *      protected String itemCategory: this is the category the StockItem is associated with.
 *      public String toString(): overrides the Object's 'toString()' method and returns a combination of both the item summary and the item details.
 *
 * Methods:
 *      Each protected / private property has a getter and a setter following a naming convention such as:
 *          private Double price;
 *          public Double getPrice();
 *          public void setPrice(double price);
 *
 *      public int compareTo(StockItem o): Allows for sorting collections by item price.
 *      public String getItemTypeDetails(): this is an abstract method meant to be implemented by sub classes and return details specific to this item.
 *      public String getItemSummary(): this is an abstract method meant to be implemented by sub classes and return a higher level summary of the entire item
 *      public Pane drawInfoFillInNode(Main main, StockManager stockManager, boolean isAddWindow): This draws the pane specific for filling out all info on this particular item.
 */

package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class StockItem implements Comparable<StockItem>{
    public static int itemCount;
    private String name;
    private double price;
    private boolean isOnSale;
    private double itemId;
    protected String itemDescription;
    protected String itemCategory;

    public StockItem() {
        itemCount += 1;
    }

    public StockItem(String name, double price, boolean isOnSale, String itemDescription, String itemCategory) throws IllegalItemException {
        super();
        if (name == null || itemCategory == null || itemDescription == null) {
            throw new IllegalItemException("The name, itemCategory, and itemDescription must be set when creating a new StockItem.");
        }
        this.name = name;
        this.price = price;
        this.isOnSale = isOnSale;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public double getItemId() {
        return itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    @Override
    public String toString() {
        return getItemSummary() + getItemTypeDetails();
    }

    @Override
    public int compareTo(StockItem o) {
        return Double.compare(o.getPrice(), price);
    }


    public abstract String getItemTypeDetails();
    public abstract String getItemSummary();

    public Pane drawInfoFillInNode(Main main, StockManager stockManager, boolean isAddWindow) {
        VBox pane = new VBox();

        Text nameLabel = new Text("Name");
        TextField nameTextArea = new TextField();
        Text categoryLabel = new Text("Category");
        TextField categoryTextArea = new TextField();
        Text descriptionLabel = new Text("Description");
        TextArea itemDescriptionTextArea = new TextArea();
        Text priceLabel = new Text("Price");
        TextField itemPriceTextField = new TextField();

        Button addButton = new Button("Add");

        addButton.setOnAction(j ->
        {
            name = nameTextArea.getText();
            itemCategory = categoryTextArea.getText();
            itemDescription = itemDescriptionTextArea.getText();
            price = Double.parseDouble(itemPriceTextField.getText());

            if (isAddWindow) {
                stockManager.addItem(this);
                main.drawUnsortedItemsPane(stockManager);
            }
        });

        pane.getChildren().addAll(nameLabel, nameTextArea, categoryLabel, categoryTextArea, descriptionLabel, itemDescriptionTextArea, priceLabel, itemPriceTextField, addButton);

        return pane;
    }
}
