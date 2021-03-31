package com.example.absensionline

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import bean.StudentBean
import db.DBAdapter

class AddStudentActivity : Activity() {
    var registerButton: Button? = null
    var textFirstName: EditText? = null
    var textLastName: EditText? = null
    var textcontact: EditText? = null
    var textaddress: EditText? = null
    var spinnerbranch: Spinner? = null
    var spinneryear: Spinner? = null
    var userrole: String? = null
    var branch: String? = null
    var year: String? = null
    private val branchString = arrayOf("INFORMATIKA")
    private val yearString = arrayOf("2017", "2018", "2019", "2020")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addstudent)
        spinnerbranch = findViewById<View>(R.id.spinnerdept) as Spinner
        spinneryear = findViewById<View>(R.id.spinneryear) as Spinner
        textFirstName = findViewById<View>(R.id.editTextFirstName) as EditText
        textLastName = findViewById<View>(R.id.editTextLastName) as EditText
        textcontact = findViewById<View>(R.id.editTextPhone) as EditText
        textaddress = findViewById<View>(R.id.editTextaddr) as EditText
        registerButton = findViewById<View>(R.id.RegisterButton) as Button
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
        registerButton!!.setOnClickListener { // TODO Auto-generated method stub
            //......................................validation
            val first_name = textFirstName!!.text.toString()
            val last_name = textLastName!!.text.toString()
            val phone_no = textcontact!!.text.toString()
            val address = textaddress!!.text.toString()
            if (TextUtils.isEmpty(first_name)) {
                textFirstName!!.error = "please enter firstname"
            } else if (TextUtils.isEmpty(last_name)) {
                textLastName!!.error = "please enter lastname"
            } else if (TextUtils.isEmpty(phone_no)) {
                textcontact!!.error = "please enter phoneno"
            } else if (TextUtils.isEmpty(address)) {
                textaddress!!.error = "enter address"
            } else {
                val studentBean = StudentBean()
                studentBean.setStudent_firstname(first_name)
                studentBean.setStudent_lastname(last_name)
                studentBean.setStudent_mobilenumber(phone_no)
                studentBean.setStudent_address(address)
                studentBean.setStudent_department(branch)
                studentBean.setStudent_class(year)
                val dbAdapter = DBAdapter(this@AddStudentActivity)
                dbAdapter.addStudent(studentBean)
                val intent = Intent(this@AddStudentActivity, MenuActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "student added successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
