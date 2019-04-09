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

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {

    Stage secondaryWindow = null;
    StockManager stockManager = new StockManager();
    ScrollPane itemsPane = new ScrollPane();
    ComboBox<String> sortOrder = new ComboBox<>();

    Image infoImage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        infoImage = new Image(new File("Icons/baseline_info_black_48dp.png").toURI().toString());

        stockManager = new StockManager();
        Pane rootPane = new Pane();

        VBox mainVerticalPanel = new VBox();

        ToolBar toolBar = new ToolBar();
        Button addButton = new Button("Add Item");
        addButton.setOnAction(i -> drawAddItemWindow(stockManager));

        sortOrder.getItems().addAll("Unsorted", "Most Expensive");
        sortOrder.setValue("Unsorted");
        sortOrder.setOnAction(i -> drawItemsPain(sortOrder.getValue()));


        toolBar.getItems().addAll(addButton, sortOrder);

        toolBar.prefWidthProperty().bind(mainVerticalPanel.widthProperty());

        mainVerticalPanel.getChildren().add(toolBar);
        mainVerticalPanel.prefHeightProperty().bind(rootPane.heightProperty());
        mainVerticalPanel.prefWidthProperty().bind(rootPane.widthProperty());

        rootPane.getChildren().add(mainVerticalPanel);

        drawItemsPain(sortOrder.getValue());

        mainVerticalPanel.getChildren().add(itemsPane);

        itemsPane.prefHeightProperty().bind(rootPane.heightProperty());

        Scene scene = new Scene(rootPane, 1000, 600);

        initHotKeys(scene);

        primaryStage.setTitle("Stock Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets up the standard hot keys for the scene provided.
     * @param scene The scene to set up the hot keys for.
     */
    public void initHotKeys(Scene scene){
        // Sets up the hot keys / short cuts
        scene.setOnKeyPressed(i -> {
            if (i.isControlDown() && i.getCode() == KeyCode.A){
                drawAddItemWindow(stockManager);
            }
        });
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
            itemsListPanel.getChildren().add(drawItem(stockItem));
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
            itemsListPanel.getChildren().add(drawItem(stockItem));
        }
    }

    public HBox drawItem(StockItem stockItem){
        HBox box = new HBox(20);
        box.getChildren().add(new Text(stockItem.getItemName()));

        //Button infoButton = new Button("Item Info");
        //infoButton.setOnAction(i -> drawItemSummaryWindow(stockItem));
        //box.getChildren().add(infoButton);

        ImageView iView = new ImageView(infoImage);
        iView.setFitHeight(25);
        iView.setFitWidth(25);

        ScaleTransition enterTransition = new ScaleTransition(Duration.millis(100), iView);
        enterTransition.setFromX(1);
        enterTransition.setFromY(1);
        enterTransition.setToX(1.25);
        enterTransition.setToY(1.25);
        enterTransition.setCycleCount(0);
        enterTransition.setAutoReverse(false);

        ScaleTransition exitTransition = new ScaleTransition(Duration.millis(100), iView);
        exitTransition.setFromX(1.25);
        exitTransition.setFromY(1.25);
        exitTransition.setToX(1);
        exitTransition.setToY(1);
        exitTransition.setCycleCount(0);
        exitTransition.setAutoReverse(false);

        iView.setOnMouseEntered(i -> enterTransition.play());
        iView.setOnMouseExited(i -> exitTransition.play());
        iView.setOnMousePressed(i -> exitTransition.play());
        iView.setOnMouseReleased(i -> {enterTransition.play(); drawItemSummaryWindow(stockItem);});

        box.getChildren().add(iView);

        Button removeButton = new Button("Remove Item");
        removeButton.setOnAction(i -> {
            stockManager.removeItem(stockItem);
            drawItemsPain(sortOrder.getValue());
        });
        box.getChildren().add(removeButton);

        return box;
    }
}
