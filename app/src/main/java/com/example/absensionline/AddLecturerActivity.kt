package com.example.absensionline

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import bean.LecturerBean
import db.DBAdapter

class AddLecturerActivity : Activity() {
    var registerButton: Button? = null
    var textFirstName: EditText? = null
    var textLastName: EditText? = null
    var textemail: EditText? = null
    var textcontact: EditText? = null
    var textaddress: EditText? = null
    var textusername: EditText? = null
    var textpassword: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addlecturer)
        textFirstName = findViewById<View>(R.id.editTextFirstName) as EditText
        textLastName = findViewById<View>(R.id.editTextLastName) as EditText
        textcontact = findViewById<View>(R.id.editTextPhone) as EditText
        textaddress = findViewById<View>(R.id.editTextaddr) as EditText
        textusername = findViewById<View>(R.id.editTextUserName) as EditText
        textpassword = findViewById<View>(R.id.editTextPassword) as EditText
        registerButton = findViewById<View>(R.id.RegisterButton) as Button
        registerButton!!.setOnClickListener { // TODO Auto-generated method stub
            val first_name = textFirstName!!.text.toString()
            val last_name = textLastName!!.text.toString()
            val phone_no = textcontact!!.text.toString()
            val address = textaddress!!.text.toString()
            val userName = textusername!!.text.toString()
            val passWord = textpassword!!.text.toString()
            if (TextUtils.isEmpty(first_name)) {
                textFirstName!!.error = "please enter firstname"
            } else if (TextUtils.isEmpty(last_name)) {
                textLastName!!.error = "please enter lastname"
            } else if (TextUtils.isEmpty(phone_no)) {
                textcontact!!.error = "please enter phoneno"
            } else if (TextUtils.isEmpty(address)) {
                textaddress!!.error = "enter address"
            } else if (TextUtils.isEmpty(userName)) {
                textcontact!!.error = "please enter username"
            } else if (TextUtils.isEmpty(passWord)) {
                textaddress!!.error = "enter password"
            } else {
                val lecturerBean = LecturerBean()
                lecturerBean.setLecturer_firstname(first_name)
                lecturerBean.setLecturer_lastname(last_name)
                lecturerBean.setLecturer_mobilenumber(phone_no)
                lecturerBean.setLecturer_address(address)
                lecturerBean.setLecturer_username(userName)
                lecturerBean.setLecturer_password(passWord)
                val dbAdapter = DBAdapter(this@AddLecturerActivity)
                dbAdapter.addLecturer(lecturerBean)
                val intent = Intent(this@AddLecturerActivity, MenuActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Dosen Telah Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
