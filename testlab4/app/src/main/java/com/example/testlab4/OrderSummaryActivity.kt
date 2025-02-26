package com.example.testlab4

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderSummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        val tvItems: TextView = findViewById(R.id.tvItems)
        val tvTotal: TextView = findViewById(R.id.tvTotal)

        // Retrieve data from intent
        val orderList = intent.getStringArrayListExtra("orderList") ?: arrayListOf()
        val totalCost = intent.getIntExtra("totalCost", 0)

        // Display the ordered items
        val orderSummary = orderList.joinToString("\n")
        tvItems.text = orderSummary

        // Display total cost
        tvTotal.text = "Total Cost: ₹$totalCost"
    }
}
