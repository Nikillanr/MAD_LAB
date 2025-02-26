package com.example.testlab4

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var chkPizza: CheckBox
    private lateinit var chkBurger: CheckBox
    private lateinit var chkPasta: CheckBox
    private lateinit var chkSandwich: CheckBox
    private lateinit var chkFries: CheckBox
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        chkPizza = findViewById(R.id.chkPizza)
        chkBurger = findViewById(R.id.chkBurger)
        chkPasta = findViewById(R.id.chkPasta)
        chkSandwich = findViewById(R.id.chkSandwich)
        chkFries = findViewById(R.id.chkFries)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Submit Button Click Listener
        btnSubmit.setOnClickListener {
            val orderList = mutableListOf<String>()
            var totalCost = 0

            if (chkPizza.isChecked) {
                orderList.add("Pizza - ₹200")
                totalCost += 200
            }
            if (chkBurger.isChecked) {
                orderList.add("Burger - ₹100")
                totalCost += 100
            }
            if (chkPasta.isChecked) {
                orderList.add("Pasta - ₹150")
                totalCost += 150
            }
            if (chkSandwich.isChecked) {
                orderList.add("Sandwich - ₹80")
                totalCost += 80
            }
            if (chkFries.isChecked) {
                orderList.add("Fries - ₹50")
                totalCost += 50
            }

            if (orderList.isEmpty()) {
                Toast.makeText(this, "Please select at least one item", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show confirmation dialog
            AlertDialog.Builder(this)
                .setTitle("Confirm Order")
                .setMessage("Are you sure you want to place this order?")
                .setPositiveButton("Yes") { _, _ ->
                    // Disable checkboxes
                    chkPizza.isEnabled = false
                    chkBurger.isEnabled = false
                    chkPasta.isEnabled = false
                    chkSandwich.isEnabled = false
                    chkFries.isEnabled = false
                    btnSubmit.isEnabled = false

                    // Pass data to new activity
                    val intent = Intent(this, OrderSummaryActivity::class.java)
                    intent.putStringArrayListExtra("orderList", ArrayList(orderList))
                    intent.putExtra("totalCost", totalCost)
                    startActivity(intent)
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}
