package com.teamandroidangel.iamangelauditor.menuorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//This app displays an order form to order coffee.

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This method is called when the plus button is clicked.

    public void increment (View view) {
        if (quantity == 100){
            Toast.makeText(this, "You cannot have more than 100 of coffees", Toast.LENGTH_LONG).show();

            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    //This method is called when the minus button is clicked.

    public void decrement (View view) {

        if (quantity == 0){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_LONG).show();

            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    // Order button
    public void submitOrder(View view) {
        EditText edittext = (EditText) findViewById(R.id.edit_text_name);
        String nameField = edittext.getText().toString();

        CheckBox checkBoxWhippedCream = (CheckBox)findViewById(R.id.whippedCreamId);
        boolean hasWhippedCream = checkBoxWhippedCream.isChecked();

        CheckBox checkBoxChocolate = (CheckBox)findViewById(R.id.chocolateId);
        boolean hasChocolate = checkBoxChocolate.isChecked();

        CheckBox checkBoxVanillaSyrup = (CheckBox)findViewById(R.id.vanillaSyrupId);
        boolean hasVanillaSyrup = checkBoxVanillaSyrup.isChecked();

        CheckBox checkBoxMilkPowder = (CheckBox)findViewById(R.id.milkPowder);
        boolean hasMilkPowder = checkBoxMilkPowder.isChecked();

        CheckBox checkBoxStrawberry = (CheckBox)findViewById(R.id.strawberrySyrupId);
        boolean hasStrawberrySyrup = checkBoxStrawberry.isChecked();

        int price = calculatePrice(hasWhippedCream, hasMilkPowder, hasStrawberrySyrup, hasVanillaSyrup, hasChocolate);
        String priceMessage = createOrderSummary
                (nameField, price, hasWhippedCream, hasMilkPowder, hasStrawberrySyrup, hasVanillaSyrup, hasChocolate);

        displayMessage(priceMessage);

    }

    // Text Summary Order
    private String createOrderSummary
    (String nameField, int price,
     boolean hasWhippedCream,
     boolean hasMilkPowder,
     boolean hasStrawberrySyrup,
     boolean hasVanillaSyrup,
     boolean hasChocolate){
        String priceMessage = "Name: " + nameField;
        priceMessage += "\nAdd Whipped Cream?: " + hasWhippedCream;
        priceMessage += "\nAdd Dairy Cream?: " + hasStrawberrySyrup;
        priceMessage += "\nAdd Vanilla Syrup?: " + hasVanillaSyrup;
        priceMessage += "\nAdd Soy Milk?: " + hasMilkPowder;
        priceMessage += "\nAdd Chocolate?: " + hasChocolate;
        priceMessage  += "\nQuantity: " + quantity;
        priceMessage  += "\nTotal Amount: ₱ " + price;
        priceMessage  += "\nThank You!!!";
        return priceMessage;
    }

    //Calculates the price of the order.
    //    @return total price

    private int calculatePrice
            (boolean addWhippedCream,
             boolean addStrawberrySyrup,
             boolean addVanillaSyrup,
             boolean addChocolate,
             boolean addMilkPowder) {

        //basePrice is the price of one coffee
        int basePrice = 80;

        if (addWhippedCream){
            basePrice = basePrice + 10;
        }
        if (addStrawberrySyrup){
            basePrice = basePrice + 10;
        }
        if (addVanillaSyrup){
            basePrice = basePrice + 10;
        }
        if (addChocolate){
            basePrice = basePrice + 10;
        }
        if (addMilkPowder){
            basePrice = basePrice + 10;
        }
        return quantity * basePrice;
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantityTextView);
        quantityTextView.setText(number + " pcs.");
    }

    //This method displays the given text on the screen.

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
