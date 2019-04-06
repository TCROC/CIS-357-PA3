/**
 * Requirements met:
 *      This is a subclass of NonConsumable
 *      This class has a protected properties 'color', 'memory', and 'graphicsCard'
 *      This class demonstrates polymorphism by overriding 'getItemTypeDetails()', 'getItemSummary()', and 'drawInfoFillInNode(...)'
 *
 * Properties:
 *      protected String processor: the type of processor this computer has, i.e. i7, i5, etc.
 *      protected String memory: the amount of memory in this computer, i.e. 1 TB HDD, 4 GB RAM
 *      protected String graphicsCard: the graphics card this computer has, i.e. NVIDIA GTX 1080
 *
 * Methods:
 *      public String getProcessor(): returns 'processor'
 *      public String setProcessor(String processor): sets 'processor'
 *      public String getMemory(): returns 'memory'
 *      public String setMemory(String memory): sets 'memory'
 *      public string getGraphicsCard(): returns 'graphicsCard'
 *      public string setGraphicsCard(String graphicsCard): sets 'graphicsCard'
 *      public String getItemTypeDetails(): returns the details for this particular item.
 *      public String getItemSummary(): returns the higher level summary of this particular item.
 *      public Pane drawInfoFillInNode(Main main, StockManager stockManager, boolean isAddWindow): draws the pane for filling out info for this item.
 *
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

public class Computer extends NonConsumable {

    protected String processor;
    protected String memory;
    protected String graphicsCard;

    public Computer() {}

    public Computer(String processor, String memory, String graphicsCard, String name, double price, boolean isOnSale, String itemDescription, String itemCategory) throws IllegalItemException {
        super(name, price, isOnSale, itemDescription, itemCategory);
        this.processor = processor;
        this.memory = memory;
        this.graphicsCard = graphicsCard;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    @Override
    public String getItemTypeDetails() {
        String details = "\nDetails:\n";
        details += "Processor: " + getProcessor() + "\n";
        details += "Memory: " + getMemory() + "\n";
        details += "Graphics Card: " + getGraphicsCard() + "\n";
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

        Text processorLabel = new Text("Processor");
        TextField processorTextField = new TextField();
        Text memoryLabel = new Text("Memory");
        TextField memoryTextField = new TextField();
        Text graphicsCardLabel = new Text("Graphics Card");
        TextField graphicsCardTextField = new TextField();

        Button addButton = (Button) pane.getChildren().get(pane.getChildren().size() - 1);
        EventHandler<ActionEvent> current = addButton.getOnAction();

        addButton.setOnAction(i ->
        {
            current.handle(i);
            processor = processorTextField.getText();
            memory = memoryTextField.getText();
            graphicsCard = graphicsCardTextField.getText();

            if (isAddWindow) {
                try {
                    stockManager.addItem(new Computer(processor, memory, graphicsCard, getItemName(), getPrice(), isOnSale(), itemDescription, itemCategory));
                } catch (IllegalItemException e) {

                }
                finally {
                    main.drawUnsortedItemsPane(stockManager);
                }
            }
        });

        // Do this to make sure the add button is always at the bottom.
        pane.getChildren().remove(addButton);

        pane.getChildren().addAll(processorLabel, processorTextField, memoryLabel, memoryTextField, graphicsCardLabel, graphicsCardTextField, addButton);

        return pane;
    }
}
