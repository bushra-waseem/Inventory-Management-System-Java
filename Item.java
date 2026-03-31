package semesterproject;

public class Item {           //Yeh class ek item ki information store karti hai
    private String itemName;  //Private ka matlab:Yeh boxes locked hain. Directly access nahi ho sakte.
    private int itemQuantity;   //step 1 variable
    private double itemPrice;

    public Item(String itemName, int itemQuantity, double itemPrice) {  //Jab tum naya item banana chahti ho, yeh constructor use hoga.
        this.itemName = itemName;   //step 2 constructor
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {   //Agar tumhe item ka naam chahiye, toh getter use karo.
        return itemName;             // step 3 getter
    }

    public void setItemName(String itemName) {  //Agar tumhe item ki value change karni ho, toh setter use karo.
        this.itemName = itemName;              // step 4 setter
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {    //Item ko achi tarah se dikhana screen pe.
        return itemName + " | Qty: " + itemQuantity + " | Price: $" + itemPrice; //step 5 to string
    }
}