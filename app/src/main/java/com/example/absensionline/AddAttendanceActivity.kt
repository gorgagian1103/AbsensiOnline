package com.example.absensionline

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.Window
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import bean.AttendanceBean
import bean.StudentBean
import context.ApplicationContext
import db.DBAdapter
import java.util.*

class AddAttendanceActivity : Activity() {
    var studentBeanList: ArrayList<StudentBean>? = null
    private var listView: ListView? = null
    private var listAdapter: ArrayAdapter<String>? = null
    var sessionId = 0
    var status = "P"
    var attendanceSubmit: Button? = null
    var dbAdapter = DBAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.__listview_main)
        sessionId = intent.extras!!.getInt("sessionId")
        listView = findViewById<View>(R.id.listview) as ListView
        val studentList = ArrayList<String>()
        studentBeanList = (this@AddAttendanceActivity.applicationContext as ApplicationContext).studentBeanList
        for (studentBean in studentBeanList!!) {
            val users = studentBean.student_firstname.toString() + "," + studentBean.student_lastname
            studentList.add(users)
            Log.d("users: ", users)
        }
        listAdapter = ArrayAdapter(this, R.layout.add_student_attendance, R.id.labelA, studentList)
        listView!!.adapter = listAdapter
        listView!!.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            arg0.getChildAt(arg2).setBackgroundColor(Color.TRANSPARENT)
            //arg0.setBackgroundColor(234567);
            arg1.setBackgroundColor(334455)
            val studentBean = studentBeanList!![arg2]
            val dialog = Dialog(this@AddAttendanceActivity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) //...........
            dialog.setContentView(R.layout.test_layout)
            // set title and cancelable
            val radioGroup: RadioGroup
            val present: RadioButton
            val absent: RadioButton
            radioGroup = dialog.findViewById<View>(R.id.radioGroup) as RadioGroup
            present = dialog.findViewById<View>(R.id.PresentradioButton) as RadioButton
            absent = dialog.findViewById<View>(R.id.AbsentradioButton) as RadioButton
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.PresentradioButton) {
                    status = "P"
                } else if (checkedId == R.id.AbsentradioButton) {
                    status = "A"
                } else {
                }
            }
            attendanceSubmit = dialog.findViewById<View>(R.id.attendanceSubmitButton) as Button
            attendanceSubmit!!.setOnClickListener {
                val attendanceBean = AttendanceBean()
                attendanceBean.attendance_session_id = sessionId
                attendanceBean.attendance_student_id = studentBean.student_id
                attendanceBean.attendance_status = status
                val dbAdapter = DBAdapter(this@AddAttendanceActivity)
                dbAdapter.addNewAttendance(attendanceBean)
                dialog.dismiss()
            }
            dialog.setCancelable(true)
            dialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
