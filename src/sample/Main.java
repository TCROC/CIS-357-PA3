/**
 * Note: This class is used for displaying the StockManager.  To see how the requirements are met, please refer to the other classes.
 *
 * Properties:
 * default Stage secondaryWindow: this any window drawn besides the main window.
 * default StockManager stockManager: this keeps track of all StockItems and has sorting / getting methods for the items.
 * default ScrollPane itemsPane: this is a scroll pane for the items so that the window has scrolling capabilities.
 *
 * Methods:
 * public void drawItemsPain (String sortOrder): Calls the correct method to draw the items based on the sort order passed in. i.e. Unsorted, Cheapest, Most Expensive, etc.
 * public void drawAddItemWindow(StockManager stockManager): Draws the window for adding items to the StockManager and adds them as well.
 * public void drawItemSummaryWindow(StockItem stockItem): Draws the window which outputs the summary for this particular item.
 * public void drawUnsortedItemsPane(StockManager stockManager): Draws the items currently in the stock manager in the order they exist in the list.
 * public void drawMostExpensiveItemsPane(StockManager stockManager): Draws items sorted in the order from most expensive to least expensive.
 *
 */

package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    Stage secondaryWindow = null;
    StockManager stockManager = new StockManager();
    ScrollPane itemsPane = new ScrollPane();

    @Override
    public void start(Stage primaryStage) throws Exception{
        stockManager = new StockManager();
        Pane rootPane = new Pane();

        VBox mainVerticalPanel = new VBox();

        ToolBar toolBar = new ToolBar();
        Button addButton = new Button("Add Item");
        addButton.setOnAction(i -> drawAddItemWindow(stockManager));

        ComboBox<String> sortOrder = new ComboBox<>();
        sortOrder.getItems().addAll("Unsorted", "Most Expensive");
        sortOrder.setValue("Unsorted");
        sortOrder.setOnAction(i -> drawItemsPain(sortOrder.getValue()));


        toolBar.getItems().addAll(addButton, sortOrder);

        toolBar.prefWidthProperty().bind(mainVerticalPanel.widthProperty());

        mainVerticalPanel.getChildren().add(toolBar);
        mainVerticalPanel.prefHeightProperty().bind(rootPane.heightProperty());
        mainVerticalPanel.prefWidthProperty().bind(rootPane.widthProperty());

        rootPane.getChildren().add(mainVerticalPanel);

        drawUnsortedItemsPane(stockManager);

        mainVerticalPanel.getChildren().add(itemsPane);

        itemsPane.prefHeightProperty().bind(rootPane.heightProperty());

        Scene scene = new Scene(rootPane, 1000, 600);
        primaryStage.setTitle("Stock Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void drawItemsPain(String sortOrder){
        switch (sortOrder){
            case "Unsorted":
                drawUnsortedItemsPane(stockManager);
                break;
            case "Most Expensive":
                drawMostExpensiveItemsPane(stockManager);
                break;
        }
    }

    public void drawAddItemWindow(StockManager stockManager){
        if (secondaryWindow != null)
            secondaryWindow.close();

        ComboBox<String> comboBox = new ComboBox<>();

        ScrollPane scrollPane = new ScrollPane();

        VBox mainPane = new VBox();

        mainPane.getChildren().add(comboBox);

        comboBox.getItems().addAll(
                "Apple",
                "Computer"
        );

        comboBox.setValue("Select Item");

        scrollPane.prefHeightProperty().bind(mainPane.heightProperty());
        scrollPane.prefWidthProperty().bind(mainPane.widthProperty());

        comboBox.valueProperty().addListener(i -> {
            //fieldFillOutPane.getChildren().clear();
            switch (comboBox.getValue()){
                case "Apple":
                    scrollPane.setContent(new Apple().drawInfoFillInNode(this, stockManager, true));
                    break;
                case "Computer":
                    scrollPane.setContent(new Computer().drawInfoFillInNode(this, stockManager, true));
                    break;
            }
        });

        mainPane.getChildren().add(scrollPane);

        secondaryWindow = new Stage();
        secondaryWindow.setTitle("Add Item");
        secondaryWindow.setScene(new Scene(mainPane, 450, 450));
        secondaryWindow.show();
    }

    public void drawItemSummaryWindow(StockItem stockItem) {

        if (secondaryWindow != null)
            secondaryWindow.close();

        Pane pane = new Pane();
        Text info = new Text(stockItem.toString());

        pane.getChildren().add(info);

        secondaryWindow = new Stage();
        secondaryWindow.setTitle("Item Summary: " + stockItem.getItemName());
        secondaryWindow.setScene(new Scene(pane, 450, 450));
        secondaryWindow.show();
    }

    public void drawUnsortedItemsPane(StockManager stockManager) {
        VBox itemsListPanel = new VBox();

        itemsListPanel.setSpacing(10);
        itemsListPanel.setPadding(new Insets(10));
        itemsPane.setContent(itemsListPanel);
        itemsPane.setPannable(true);

        itemsPane.setPrefWidth(500);

        for (var stockItem: stockManager.getAllItems()) {
            HBox box = new HBox(20);
            box.getChildren().add(new Text(stockItem.getItemName()));
            Button infoButton = new Button("Item Info");
            infoButton.setOnAction(i -> drawItemSummaryWindow(stockItem));
            box.getChildren().add(infoButton);
            Button removeButton = new Button("Remove Item");
            removeButton.setOnAction(i -> {
                stockManager.removeItem(stockItem);
                drawUnsortedItemsPane(stockManager);
            });
            box.getChildren().add(removeButton);
            itemsListPanel.getChildren().add(box);
        }
    }

    public void drawMostExpensiveItemsPane(StockManager stockManager) {
        VBox itemsListPanel = new VBox();

        itemsListPanel.setSpacing(10);
        itemsListPanel.setPadding(new Insets(10));
        itemsPane.setContent(itemsListPanel);
        itemsPane.setPannable(true);

        itemsPane.setPrefWidth(500);

        for (var stockItem: stockManager.getMostExpensiveItems()) {
            HBox box = new HBox(20);
            box.getChildren().add(new Text(stockItem.getItemName()));
            Button infoButton = new Button("Item Info");
            infoButton.setOnAction(i -> drawItemSummaryWindow(stockItem));
            box.getChildren().add(infoButton);
            Button removeButton = new Button("Remove Item");
            removeButton.setOnAction(i -> {
                stockManager.removeItem(stockItem);
                drawUnsortedItemsPane(stockManager);
            });
            box.getChildren().add(removeButton);
            itemsListPanel.getChildren().add(box);
        }
    }
}
