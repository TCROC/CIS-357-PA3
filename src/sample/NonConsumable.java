/**
 * Requirements MetL
 *      This is a subclass of StockItem
 */

package sample;

public abstract class NonConsumable extends StockItem {

    public NonConsumable(){}

    public NonConsumable(String name, double price, boolean isOnSale, String itemDescription, String itemCategory) throws IllegalItemException {
        super(name, price, isOnSale, itemDescription, itemCategory);
    }

}
