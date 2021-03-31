package com.example.absensionline

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import bean.AttendanceBean
import bean.StudentBean
import context.ApplicationContext
import db.DBAdapter
import java.util.*

class ViewAttendanceByLecturerActivity : Activity() {
    var attendanceBeanList: ArrayList<AttendanceBean>? = null
    private var listView: ListView? = null
    private var listAdapter: ArrayAdapter<String>? = null
    var dbAdapter: DBAdapter = DBAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.__listview_main)
        listView = findViewById<View>(R.id.listview) as ListView
        val attendanceList = ArrayList<String>()
        attendanceList.add("Id | StudentName |  Status")
        attendanceBeanList = (this@ViewAttendanceByLecturerActivity.applicationContext as ApplicationContext).getAttendanceBeanList()
        for (attendanceBean in attendanceBeanList!!) {
            var users = ""
            users = if (attendanceBean.getAttendance_session_id() !== 0) {
                val dbAdapter = DBAdapter(this@ViewAttendanceByLecturerActivity)
                val studentBean: StudentBean = dbAdapter.getStudentById(attendanceBean.getAttendance_student_id())
                attendanceBean.getAttendance_student_id().toString() + ".     " + studentBean.getStudent_firstname() + "," + studentBean.getStudent_lastname() + "                  " + attendanceBean.getAttendance_status()
            } else {
                attendanceBean.getAttendance_status()
            }
            attendanceList.add(users)
            Log.d("users: ", users)
        }
        listAdapter = ArrayAdapter(this, R.layout.view_attendance_list, R.id.labelAttendance, attendanceList)
        listView!!.adapter = listAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
