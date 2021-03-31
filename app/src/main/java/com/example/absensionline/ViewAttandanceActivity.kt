package com.example.absensionline

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class ViewAttandanceActivity : Activity() {
    var spinnerbranch: Spinner? = null
    var spinneryear: Spinner? = null
    var userrole: String? = null
    var branch: String? = null
    var year: String? = null
    private val branchString = arrayOf("INFORMATIKA")
    private val yearString = arrayOf("2017", "2018", "2019", "2020")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewstudent)
        spinnerbranch = findViewById<View>(R.id.spinnerbranchView) as Spinner
        spinneryear = findViewById<View>(R.id.spinneryearView) as Spinner
        spinnerbranch!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>, view: View,
                                        arg2: Int, arg3: Long) {
                // TODO Auto-generated method stub
                (arg0.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                branch = spinnerbranch!!.selectedItem as String
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }
        val adapter_branch = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, branchString)
        adapter_branch
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerbranch!!.adapter = adapter_branch

        ///......................spinner2
        spinneryear!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>, view: View,
                                        arg2: Int, arg3: Long) {
                // TODO Auto-generated method stub
                (arg0.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                year = spinneryear!!.selectedItem as String
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }
        val adapter_year = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, yearString)
        adapter_year
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinneryear!!.adapter = adapter_year
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
