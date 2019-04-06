/**
 * Requirements Met:
 *      This class has a generic method: 'public <T>ArrayList<T> getItemsByType(Class<T> type)'  It gets all items that are an instance of a particular type.
 *
 * Properties:
 *      private ArrayList<StockItem> stockItems: This is a collection of all stock items managed by this StockManager.
 *
 * Methods:
 *      public void addItem(StockItem item): this method adds a StockItem to 'stockItems'
 *      public void removeItem(StockItem item): removes the StockItem 'item' from 'stockItems'
 *      public ArrayList<StockItem> getAllItems(): returns the collection stockItems
 *      public StockItem getItem(double itemId): returns the StockItem with the matching id from 'stockItems'
 *      public ArrayList<StockItem> getItemsByName(String itemName): returns all StockItems with the matching name from 'stockItems'
 *      public ArrayList<StockItem> getItemsByCategory(String itemCategory): returns all StockItems in the specified category from 'stockItems'
 *      public ArrayList<StockItem> getMostExpensiveItems(): returns all StockItems sorted from most expensive to least expensive
 *      public ArrayList<StockItem> getCheapestItems(): returns all StockItems sorted from least expensive ot most expensive
 *      public ArrayList<StockItem> getMostExpensiveItemsByName(String itemName): returns all StockItems with the matching name from 'stockItems' sorted by most expensive to lest expensive
 *      public ArrayList<StockItem> getCheapestItemsByName(): returns all StockItems with the matching name from 'stockItems' sorted by least expensive to most expensive
 *      public ArrayList<StockItem> getMostExpensiveItemsByCategory(String itemCategory): returns all StockItems with the matching category from 'stockItems' sorted by most expensive to least expensive
 *      public ArrayList<StockItem> getCheapestItemsByCategory(String itemCategory: returns all StockItems with the matching category from 'stockItems' sorted by least expensive to most expensive
 *      public <T>ArrayList<T> getItemsByType(Class<T> type): returns a collection of all items that are an instance of the specified type 'T'
 *      public String generateReport(): generates a report of all items currently managed by this StockManager
 */

package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StockManager {

    private ArrayList<StockItem> stockItems = new ArrayList<>();

    public void addItem(StockItem item) {
        stockItems.add(item);
    }

    public void removeItem(StockItem item) {
        stockItems.remove(item);
    }

    public ArrayList<StockItem> getAllItems(){
        return stockItems;
    }

    public StockItem getItem(double itemId){
        for (var item:stockItems) {
            if (item.getItemId() == itemId){
                return item;
            }
        }

        return null;
    }

    public ArrayList<StockItem> getItemsByName(String itemName) {
        ArrayList<StockItem> items = new ArrayList<>();
        for (var item: stockItems) {
            if (item.getItemName().equals(itemName))
                items.add(item);
        }

        return items;
    }

    public ArrayList<StockItem> getItemsByCategory(String itemCategory) {
        ArrayList<StockItem> items = new ArrayList<>();
        for (var item: stockItems) {
            if (item.getItemName().equals(itemCategory))
                items.add(item);
        }

        return items;
    }

    public ArrayList<StockItem> getMostExpensiveItems() {
        ArrayList<StockItem> items = new ArrayList<>(stockItems);
        Collections.sort(items);
        return items;
    }

    public ArrayList<StockItem> getCheapestItems() {
        ArrayList<StockItem> items = new ArrayList<>(stockItems);
        Collections.sort(items);
        return items;
    }

    public ArrayList<StockItem> getMostExpensiveItemsByName(String itemName){
        ArrayList<StockItem> items = new ArrayList<>();

        for(var item: stockItems) {
            if (item.getItemName().equals(itemName))
                items.add(item);
        }
        Collections.sort(items);
        return items;
    }

    public ArrayList<StockItem> getCheapestItemsByName(String itemName) {
        ArrayList<StockItem> items = new ArrayList<>();

        for(var item: stockItems) {
            if (item.getItemName().equals(itemName))
                items.add(item);
        }
        Collections.sort(items);
        return items;
    }

    public ArrayList<StockItem> getMostExpensiveItemsByCategory(String itemCategory){
        ArrayList<StockItem> items = new ArrayList<>();

        for(var item: stockItems) {
            if (item.getItemCategory().equals(itemCategory))
                items.add(item);
        }
        Collections.sort(items);
        return items;
    }

    public ArrayList<StockItem> getCheapestItemsByCategory(String itemCategory){
        ArrayList<StockItem> items = new ArrayList<>();

        for(var item: stockItems) {
            if (item.getItemCategory().equals(itemCategory))
                items.add(item);
        }
        Collections.sort(items);
        return items;
    }

    public <T>ArrayList<T> getItemsByType(Class<T> type) {
        ArrayList<T> items = new ArrayList<>();

        for(var item: stockItems) {
            if (type.isInstance(item))
                items.add((T)item);
        }

        return items;
    }

    public String generateReport() {
        String report = "Stock Item Report\n";

        for (var item: stockItems) {
            report += item.toString() + "\n";
        }

        return report;
    }
}
