package context;

import android.app.Application;

import java.util.ArrayList;

import bean.AttendanceBean;
import bean.AttendanceSessionBean;
import bean.LecturerBean;
import bean.StudentBean;

public class ApplicationContext extends Application {
    private LecturerBean lecturerBean;
    private AttendanceSessionBean attendanceSessionBean;
    private ArrayList<StudentBean> studentBeanList;
    private ArrayList<AttendanceBean> attendanceBeanList;

    public LecturerBean getLecturerBean() {
        return lecturerBean;
    }
    public void setLecturerBean(LecturerBean lecturerBean) {
        this.lecturerBean = lecturerBean;
    }
    public AttendanceSessionBean getAttendanceSessionBean() {
        return attendanceSessionBean;
    }
    public void setAttendanceSessionBean(AttendanceSessionBean attendanceSessionBean) {
        this.attendanceSessionBean = attendanceSessionBean;
    }
    public ArrayList<StudentBean> getStudentBeanList() {
        return studentBeanList;
    }
    public void setStudentBeanList(ArrayList<StudentBean> studentBeanList) {
        this.studentBeanList = studentBeanList;
    }
    public ArrayList<AttendanceBean> getAttendanceBeanList() {
        return attendanceBeanList;
    }
    public void setAttendanceBeanList(ArrayList<AttendanceBean> attendanceBeanList) {
        this.attendanceBeanList = attendanceBeanList;
    }



}
