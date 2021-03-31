package com.example.absensionline

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import bean.AttendanceBean
import bean.AttendanceSessionBean
import bean.LecturerBean
import bean.StudentBean
import context.ApplicationContext
import db.DBAdapter
import java.util.*

class AddAttandanceSessionActivity<AddAttandanceActivity> : Activity() {
    private var date: ImageButton? = null
    private var cal: Calendar? = null
    private var day = 0
    private var month = 0
    private var dyear = 0
    private var dateEditText: EditText? = null
    var submit: Button? = null
    var viewAttendance: Button? = null
    var viewTotalAttendance: Button? = null
    var spinnerbranch: Spinner? = null
    var spinneryear: Spinner? = null
    var spinnerSubject: Spinner? = null
    var branch = "INFORMATIKA"
    var year = "2018"
    var subject = "Probstat"
    private val branchString = arrayOf("INFORMATIKA")
    private val yearString = arrayOf("2017", "2018", "2019", "2020")
    private val subject2017String = arrayOf("KP","TA","PBA","ETHICAL HACKING","KETEKNOWIRAAN" ,"TA 2", "KAPITA SELEKTA", "UI/UX DESIGN", "ANDAL", "PC")
    private val subject2018String = arrayOf("PAM","APPL","PPMPL","CERTAN","PABWE" ,"Probstat","ENGLISH 3","MACHINE LEARNING", "MPPL", "SISTEM PARALEL DAN TERDISTRIBUSI")
    private val subject2019String = arrayOf("ATI", "Probstat","BASDAT","SISDIG","LOGIN","IMK","JARKOM", "ALSTRUDAT", "AOK", "PBO", "ALJALI")
    private val subject2020String = arrayOf("DELCHA", "INDIG","MADAS 1", "ENGLISH 1", "MADAS 2", "FISDAS 1", "PENULISAN KARYA ILMIAH", "MATDIS", "FISDAS 2", "PDR", "RPL","DASPRO")
    private val subjectFinal = arrayOf("KP","TA","PBA","ETHICAL HACKING","KETEKNOWIRAAN" ,"TA 2", "KAPITA SELEKTA", "UI/UX DESIGN", "ANDAL", "PC","PAM","APPL","PPMPL","CERTAN","PABWE" ,"Probstat","ENGLISH 3","MACHINE LEARNING", "MPPL", "SISTEM PARALEL DAN TERDISTRIBUSI","ATI", "Probstat","BASDAT","SISDIG","LOGIN","IMK","JARKOM", "ALSTRUDAT", "AOK", "PBO", "ALJALI","DELCHA", "INDIG","MADAS 1", "ENGLISH 1", "MADAS 2", "FISDAS 1", "PENULISAN KARYA ILMIAH", "MATDIS", "FISDAS 2", "PDR", "RPL","DASPRO")
    var attendanceSessionBean: AttendanceSessionBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_attandance)

        //Assume subject will be SE
        //subjectFinal = subjectSEString;
        spinnerbranch = findViewById<View>(R.id.spinner1) as Spinner
        spinneryear = findViewById<View>(R.id.spinneryear) as Spinner
        spinnerSubject = findViewById<View>(R.id.spinnerSE) as Spinner
        val adapter_branch = ArrayAdapter(this, android.R.layout.simple_spinner_item, branchString)
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerbranch!!.adapter = adapter_branch
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

        ///......................spinner2
        val adapter_year = ArrayAdapter(this, android.R.layout.simple_spinner_item, yearString)
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinneryear!!.adapter = adapter_year
        spinneryear!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>, view: View,
                                        arg2: Int, arg3: Long) {
                // TODO Auto-generated method stub
                (arg0.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                year = spinneryear!!.selectedItem as String
                Toast.makeText(applicationContext, "year:$year", Toast.LENGTH_SHORT).show()

                /*if(year.equalsIgnoreCase("se"))
				{
					subjectFinal = subjectSEString;
				}
				else if(year.equalsIgnoreCase("te"))
				{
					subjectFinal = subjectTEString;
				}
				else if(year.equalsIgnoreCase("be"))
				{
					subjectFinal = subjectBEString;
				}*/
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }
        val adapter_subject = ArrayAdapter(this, android.R.layout.simple_spinner_item, subjectFinal)
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSubject!!.adapter = adapter_subject
        spinnerSubject!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>, view: View,
                                        arg2: Int, arg3: Long) {
                // TODO Auto-generated method stub
                (arg0.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                subject = spinnerSubject!!.selectedItem as String
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }
        val date = findViewById<View>(R.id.DateImageButton) as ImageButton
        val cal = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val dyear = cal.get(Calendar.YEAR)
        dateEditText = findViewById<View>(R.id.DateEditText) as EditText
        date!!.setOnClickListener { showDialog(0) }
        submit = findViewById<View>(R.id.buttonsubmit) as Button
        submit!!.setOnClickListener {
            val attendanceSessionBean = AttendanceSessionBean()
            val bean: LecturerBean = (this@AddAttandanceSessionActivity.applicationContext as ApplicationContext).getLecturerBean()
            attendanceSessionBean.setAttendance_session_lecturer_id(bean.getLecturer_id())
            attendanceSessionBean.setAttendance_session_department(branch)
            attendanceSessionBean.setAttendance_session_class(year)
            attendanceSessionBean.setAttendance_session_date(dateEditText!!.text.toString())
            attendanceSessionBean.setAttendance_session_subject(subject)
            val dbAdapter = DBAdapter(this@AddAttandanceSessionActivity)
            val sessionId: Int = dbAdapter.addAttendanceSession(attendanceSessionBean)
            val studentBeanList: ArrayList<StudentBean> = dbAdapter.getAllStudentByBranchYear(branch, year)
            (this@AddAttandanceSessionActivity.applicationContext as ApplicationContext).setStudentBeanList(studentBeanList)
            val intent = Intent(this@AddAttandanceSessionActivity, AddAttendanceActivity::class.java)
            intent.putExtra("sessionId", sessionId)
            startActivity(intent)
        }
        viewAttendance = findViewById<View>(R.id.viewAttendancebutton) as Button
        viewAttendance!!.setOnClickListener {
            val attendanceSessionBean = AttendanceSessionBean()
            val bean: LecturerBean = (this@AddAttandanceSessionActivity.applicationContext as ApplicationContext).getLecturerBean()
            attendanceSessionBean.setAttendance_session_lecturer_id(bean.getLecturer_id())
            attendanceSessionBean.setAttendance_session_department(branch)
            attendanceSessionBean.setAttendance_session_class(year)
            attendanceSessionBean.setAttendance_session_date(dateEditText!!.text.toString())
            attendanceSessionBean.setAttendance_session_subject(subject)
            val dbAdapter = DBAdapter(this@AddAttandanceSessionActivity)
            val attendanceBeanList: ArrayList<AttendanceBean> = dbAdapter.getAttendanceBySessionID(attendanceSessionBean)
            (this@AddAttandanceSessionActivity.applicationContext as ApplicationContext).setAttendanceBeanList(attendanceBeanList)
            val intent = Intent(this@AddAttandanceSessionActivity, ViewAttendanceByLecturerActivity::class.java)
            startActivity(intent)
        }
        viewTotalAttendance = findViewById<View>(R.id.viewTotalAttendanceButton) as Button
        viewTotalAttendance!!.setOnClickListener {
            val attendanceSessionBean = AttendanceSessionBean()
            val bean: LecturerBean = (this@AddAttandanceSessionActivity.applicationContext as ApplicationContext).getLecturerBean()
            attendanceSessionBean.setAttendance_session_lecturer_id(bean.getLecturer_id())
            attendanceSessionBean.setAttendance_session_department(branch)
            attendanceSessionBean.setAttendance_session_class(year)
            attendanceSessionBean.setAttendance_session_subject(subject)
            val dbAdapter = DBAdapter(this@AddAttandanceSessionActivity)
            val attendanceBeanList: ArrayList<AttendanceBean> = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean)
            (this@AddAttandanceSessionActivity.applicationContext as ApplicationContext).setAttendanceBeanList(attendanceBeanList)
            val intent = Intent(this@AddAttandanceSessionActivity, ViewAttendanceByLecturerActivity::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("")
    override fun onCreateDialog(id: Int): Dialog {
        return DatePickerDialog(this, datePickerListener, dyear, month, day)
    }

    private val datePickerListener = OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
        dateEditText!!.setText(selectedDay.toString() + " / " + (selectedMonth + 1) + " / "
                + selectedYear)
    }
}
