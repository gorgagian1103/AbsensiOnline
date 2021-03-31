package com.example.absensionline

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import bean.StudentBean
import db.DBAdapter
import java.util.*

class ViewStudentByBranchYear : Activity() {
    var studentBeanList: ArrayList<StudentBean>? = null
    private var listView: ListView? = null
    private var listAdapter: ArrayAdapter<String>? = null
    var branch: String? = null
    var year: String? = null
    var dbAdapter: DBAdapter = DBAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.__listview_main)
        listView = findViewById<View>(R.id.listview) as ListView
        val studentList = ArrayList<String>()
        branch = intent.extras!!.getString("branch")
        year = intent.extras!!.getString("year")
        studentBeanList = dbAdapter.getAllStudentByBranchYear(branch, year)
        for (studentBean in studentBeanList!!) {
            val users: String = studentBean.getStudent_firstname().toString() + "," + studentBean.getStudent_lastname()
            studentList.add(users)
            Log.d("users: ", users)
        }
        listAdapter = ArrayAdapter(this, R.layout.view_student_list, R.id.label, studentList)
        listView!!.adapter = listAdapter
        listView!!.onItemLongClickListener = OnItemLongClickListener { arg0, arg1, position, arg3 ->
            val alertDialogBuilder = AlertDialog.Builder(this@ViewStudentByBranchYear)
            alertDialogBuilder.setTitle(title.toString() + "decision")
            alertDialogBuilder.setMessage("Are you sure?")
            alertDialogBuilder.setPositiveButton("Yes") { dialog, id ->
                studentList.removeAt(position)
                listAdapter!!.notifyDataSetChanged()
                listAdapter!!.notifyDataSetInvalidated()
                dbAdapter.deleteStudent(studentBeanList!![position].getStudent_id())
                studentBeanList = dbAdapter.getAllStudentByBranchYear(branch, year)
                for (studentBean in studentBeanList!!) {
                    val users = """ FirstName: ${studentBean.getStudent_firstname().toString()}
Lastname:${studentBean.getStudent_lastname()}"""
                    studentList.add(users)
                    Log.d("users: ", users)
                }
            }
            alertDialogBuilder.setNegativeButton("No") { dialog, id -> // cancel the alert box and put a Toast to the user
                dialog.cancel()
                Toast.makeText(applicationContext, "You choose cancel",
                        Toast.LENGTH_LONG).show()
            }
            val alertDialog = alertDialogBuilder.create()
            // show alert
            alertDialog.show()
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
