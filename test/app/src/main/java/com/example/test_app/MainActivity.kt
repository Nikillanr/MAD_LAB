package com.example.test_app

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.test_app.R
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerSource: Spinner
    private lateinit var spinnerDestination: Spinner
    private lateinit var btnDatePicker: Button
    private lateinit var tvSelectedDate: TextView
    private lateinit var toggleTicketType: ToggleButton
    private lateinit var btnSubmit: Button
    private lateinit var btnReset: Button

    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Views
        spinnerSource = findViewById(R.id.spinnerSource)
        spinnerDestination = findViewById(R.id.spinnerDestination)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        toggleTicketType = findViewById(R.id.toggleTicketType)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnReset = findViewById(R.id.btnReset)

        // Populate Spinners with City Names
        val cities = arrayOf("Mumbai", "Delhi", "Bangalore", "Chennai", "Hyderabad", "Kolkata")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities)
        spinnerSource.adapter = adapter
        spinnerDestination.adapter = adapter

        // Date Picker
        btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = "Selected Date: $selectedDate"
            }, year, month, day)

            datePickerDialog.show()
        }

        // Submit Button Click
        btnSubmit.setOnClickListener {
            val source = spinnerSource.selectedItem.toString()
            val destination = spinnerDestination.selectedItem.toString()
            val ticketType = if (toggleTicketType.isChecked) "Round Trip" else "One Way"

            if (selectedDate.isEmpty()) {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Pass data to new activity
            val intent = Intent(this, TicketDetailsActivity::class.java)
            intent.putExtra("source", source)
            intent.putExtra("destination", destination)
            intent.putExtra("date", selectedDate)
            intent.putExtra("ticketType", ticketType)
            startActivity(intent)
        }

        // Reset Button Click
        btnReset.setOnClickListener {
            spinnerSource.setSelection(0)
            spinnerDestination.setSelection(0)
            toggleTicketType.isChecked = false
            tvSelectedDate.text = "No date selected"
            selectedDate = ""
        }
    }
}
