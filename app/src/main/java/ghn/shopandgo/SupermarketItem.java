package ghn.shopandgo;

/**
 * Created by bukbukbukh on 12/22/16.
 */

public class SupermarketItem {

    private int ID;
    private String itemName;
    private double itemPrice;
    private byte[] itemPicture;
    private String itemDescription;
    private int itemQuantity;

    public SupermarketItem(int ID) {
        this.ID = ID;
    }


    public int getID() {
        return ID;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public byte[] getItemPicture() {
        return itemPicture;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemPicture(byte[] itemPicture) {
        this.itemPicture = itemPicture;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }


}
