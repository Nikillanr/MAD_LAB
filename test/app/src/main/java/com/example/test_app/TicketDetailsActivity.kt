package com.example.test_app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test_app.R

class TicketDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_details)

        val tvDetails: TextView = findViewById(R.id.tvDetails)

        // Retrieve data
        val source = intent.getStringExtra("source")
        val destination = intent.getStringExtra("destination")
        val date = intent.getStringExtra("date")
        val ticketType = intent.getStringExtra("ticketType")

        val details = "Source: $source\nDestination: $destination\nDate: $date\nTicket Type: $ticketType"
        tvDetails.text = details
    }
}
