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
import bean.LecturerBean
import db.DBAdapter
import java.util.*

class ViewLecturerActivity : Activity() {
    var lecturerBeanList: ArrayList<LecturerBean>? = null
    private var listView: ListView? = null
    private var listAdapter: ArrayAdapter<String>? = null
    var dbAdapter: DBAdapter = DBAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.__listview_main)
        listView = findViewById<View>(R.id.listview) as ListView
        val lecturerList = ArrayList<String>()
        lecturerBeanList = dbAdapter.getAllLecturer()
        for (lecturerBean in lecturerBeanList!!) {
            val users = """ FirstName: ${lecturerBean.getLecturer_firstname().toString()}
Lastname:${lecturerBean.getLecturer_lastname()}"""
            lecturerList.add(users)
            Log.d("users: ", users)
        }
        listAdapter = ArrayAdapter(this, R.layout.view_lecturer_list, R.id.labelF, lecturerList)
        listView!!.adapter = listAdapter
        listView!!.onItemLongClickListener = OnItemLongClickListener { arg0, arg1, position, arg3 ->
            val alertDialogBuilder = AlertDialog.Builder(this@ViewLecturerActivity)
            alertDialogBuilder.setTitle(title.toString() + "decision")
            alertDialogBuilder.setMessage("Apakah Anda Yakin?")
            alertDialogBuilder.setPositiveButton("Ya") { dialog, id ->
                lecturerList.removeAt(position)
                listAdapter!!.notifyDataSetChanged()
                listAdapter!!.notifyDataSetInvalidated()
                dbAdapter.deleteLecturer(lecturerBeanList!![position].getLecturer_id())
                lecturerBeanList = dbAdapter.getAllLecturer()
                for (lecturerBean in lecturerBeanList!!) {
                    val users = """ FirstName: ${lecturerBean.getLecturer_firstname().toString()}
Lastname:${lecturerBean.getLecturer_lastname()}"""
                    lecturerList.add(users)
                    Log.d("users: ", users)
                }
            }
            alertDialogBuilder.setNegativeButton("No") { dialog, id ->
                dialog.cancel()
                Toast.makeText(applicationContext, "Anda Memilih Batalkan",
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
