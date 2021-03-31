package com.example.absensionline

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import bean.AttendanceBean
import bean.StudentBean
import context.ApplicationContext
import db.DBAdapter
import java.util.*

class ViewAttendancePerStudentActivity : Activity() {
    var attendanceBeanList: ArrayList<AttendanceBean>? = null
    private var listView: ListView? = null
    private var listAdapter: ArrayAdapter<String>? = null
    var dbAdapter: DBAdapter = DBAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.__listview_main)
        listView = findViewById<View>(R.id.listview) as ListView
        val attendanceList = ArrayList<String>()
        attendanceList.add("Present Count Per Student")
        attendanceBeanList = (this@ViewAttendancePerStudentActivity.applicationContext as ApplicationContext).getAttendanceBeanList()
        for (attendanceBean in attendanceBeanList!!) {
            var users = ""
            val dbAdapter = DBAdapter(this@ViewAttendancePerStudentActivity)
            val studentBean: StudentBean = dbAdapter.getStudentById(attendanceBean.getAttendance_student_id())
            users = attendanceBean.getAttendance_student_id().toString() + ".     " + studentBean.getStudent_firstname() + "," + studentBean.getStudent_lastname() + "                  " + attendanceBean.getAttendance_session_id()
            attendanceList.add(users)
        }
        listAdapter = ArrayAdapter(this, R.layout.view_attendance_list_per_student, R.id.labelAttendancePerStudent, attendanceList)
        listView!!.adapter = listAdapter

        /*listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {



				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewAttendanceByFacultyActivity.this);

				alertDialogBuilder.setTitle(getTitle()+"decision");
				alertDialogBuilder.setMessage("Are you sure?");

				alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						facultyList.remove(position);
						listAdapter.notifyDataSetChanged();
						listAdapter.notifyDataSetInvalidated();

						dbAdapter.deleteFaculty(facultyBeanList.get(position).getFaculty_id());
						facultyBeanList=dbAdapter.getAllFaculty();

						for(FacultyBean facultyBean : facultyBeanList)
						{
							String users = " FirstName: " + facultyBean.getFaculty_firstname()+"\nLastname:"+facultyBean.getFaculty_lastname();
							facultyList.add(users);
							Log.d("users: ", users);

						}

					}

				});
				alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// cancel the alert box and put a Toast to the user
						dialog.cancel();
						Toast.makeText(getApplicationContext(), "You choose cancel",
								Toast.LENGTH_LONG).show();
					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();
				// show alert
				alertDialog.show();





				return false;
			}
		});
*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
