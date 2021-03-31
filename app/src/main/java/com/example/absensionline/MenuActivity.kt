package com.example.absensionline

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import bean.AttendanceBean
import context.ApplicationContext
import db.DBAdapter
import java.util.*

class MenuActivity : Activity() {
    var addStudent: Button? = null
    var addFaculty: Button? = null
    var viewStudent: Button? = null
    var viewFaculty: Button? = null
    var logout: Button? = null
    var attendancePerStudent: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        addStudent = findViewById<View>(R.id.buttonaddstudent) as Button
        addFaculty = findViewById<View>(R.id.buttonaddfaculty) as Button
        viewStudent = findViewById<View>(R.id.buttonViewstudent) as Button
        viewFaculty = findViewById<View>(R.id.buttonviewfaculty) as Button
        logout = findViewById<View>(R.id.buttonlogout) as Button
        addStudent!!.setOnClickListener { // TODO Auto-generated method stub
            val intent = Intent(this@MenuActivity, AddStudentActivity::class.java)
            startActivity(intent)
        }
        addFaculty!!.setOnClickListener { // TODO Auto-generated method stub
            val intent = Intent(this@MenuActivity, AddLecturerActivity::class.java)
            startActivity(intent)
        }
        viewFaculty!!.setOnClickListener { // TODO Auto-generated method stub
            val intent = Intent(this@MenuActivity, ViewLecturerActivity::class.java)
            startActivity(intent)
        }
        viewStudent!!.setOnClickListener { // TODO Auto-generated method stub
            val intent = Intent(this@MenuActivity, ViewStudentActivity::class.java)
            startActivity(intent)
        }
        logout!!.setOnClickListener { // TODO Auto-generated method stub
            val intent = Intent(this@MenuActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        attendancePerStudent = findViewById<View>(R.id.attendancePerStudentButton) as Button
        attendancePerStudent!!.setOnClickListener {
            val dbAdapter = DBAdapter(this@MenuActivity)
            val attendanceBeanList: ArrayList<AttendanceBean> = dbAdapter.getAllAttendanceByStudent()
            (this@MenuActivity.applicationContext as ApplicationContext).setAttendanceBeanList(attendanceBeanList)
            val intent = Intent(this@MenuActivity, ViewAttendancePerStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
