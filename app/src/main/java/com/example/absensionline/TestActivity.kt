package com.example.absensionline

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import bean.AttendanceSessionBean
import db.DBAdapter
import java.util.*

class TestActivity : Activity() {
    var submit: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_main)
        submit = findViewById<View>(R.id.button1) as Button
        submit!!.setOnClickListener {
            val dbAdapter = DBAdapter(this@TestActivity)
            val attendanceSessionBean = AttendanceSessionBean()
            attendanceSessionBean.setAttendance_session_lecturer_id(1)
            attendanceSessionBean.setAttendance_session_department("INFORMATIKA")
            attendanceSessionBean.setAttendance_session_class("Probstat")
            attendanceSessionBean.setAttendance_session_date("06/04/2016")
            attendanceSessionBean.setAttendance_session_subject("DataBase")
            dbAdapter.addAttendanceSession(attendanceSessionBean)
            Log.d("add", "inserted")


            val attendanceSessionBeanList: ArrayList<AttendanceSessionBean> = dbAdapter.getAllAttendanceSession()
            for (sessionBean in attendanceSessionBeanList) {
                Log.d("for", "in for loop")
                val aid: Int = sessionBean.getAttendance_session_id()
                val fid: Int = sessionBean.getAttendance_session_lecturer_id()
                val sclass: String = sessionBean.getAttendance_session_class()
                val dept: String = sessionBean.getAttendance_session_department()
                val date: String = sessionBean.getAttendance_session_date()
                val sub: String = sessionBean.getAttendance_session_subject()
                Log.d("id", aid.toString() + "")
                Log.d("fid", fid.toString() + "")
                Log.d("sclass", sclass)
                Log.d("dept", dept)
                Log.d("date", date)
                Log.d("sub", sub)
            }
        }
    }
}
