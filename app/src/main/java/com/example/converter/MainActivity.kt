package com.example.converter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.lang.Exception

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var arr = arrayOf<String?>("Mile", "Km", "Cm")
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var exchange: ImageView
    private lateinit var firstText: EditText
    private lateinit var secondText: EditText

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        spinner1 = findViewById(R.id.spinner)
        exchange = findViewById(R.id.imageView)
        spinner2 = findViewById(R.id.spinner2)
        firstText = findViewById(R.id.firstText)
        secondText = findViewById(R.id.secondText)


//        ----------------------- this is for spinner 1 -------------------------
        spinner1.onItemSelectedListener = this
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this, android.R.layout.simple_spinner_item, arr
        )
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = ad

//        -------------------------- this is for spinner 2 --------------------------
        spinner2.onItemSelectedListener = this
        val ad2: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this, android.R.layout.simple_spinner_dropdown_item, arr
        )

        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = ad2


        secondText.isEnabled = false

//-------------------------------- converter logic ------------------

        firstText.addTextChangedListener { charSequence ->
            if (firstText.text.length < 10 && !firstText.text.isEmpty()) {
                var result =
                    if (spinner1.selectedItem == "Mile" && firstText.text.toString().toInt() >= 0) {
                        if (spinner2.selectedItem == "Km") {
                            firstText.text.toString().toDouble() * 1.60934
                        } else if (spinner2.selectedItem == "Cm") {
                            firstText.text.toString().toLong() * 160934
                        } else {
                            firstText.text.toString().toLong()
                        }
                    } else if (spinner1.selectedItem == "Km" && firstText.text.toString()
                            .toInt() >= 0
                    ) {
                        if (spinner2.selectedItem == "Mile") {
                            firstText.text.toString().toDouble() * 0.621371
                        } else if (spinner2.selectedItem == "Cm") {
                            firstText.text.toString().toLong() * 100000
                        } else {
                            firstText.text.toString().toLong()
                        }
                    } else {
                        if (spinner2.selectedItem == "Mile") {
                            firstText.text.toString().toDouble() * 6.2137e-6
                        } else if (spinner2.selectedItem == "Km") {
                            firstText.text.toString().toLong() * 1e-5
                        } else {
                            firstText.text.toString().toLong()
                        }
                    }
                secondText.setText(result.toString())
            } else {
                Toast.makeText(this, "Maximum digit (10) reached", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}