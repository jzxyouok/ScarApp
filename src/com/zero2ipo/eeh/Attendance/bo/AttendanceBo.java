package com.zero2ipo.eeh.Attendance.bo;

/**
 * 教师
 * Created by Administrator on 2016/2/24.
 */
public class AttendanceBo implements  java.io.Serializable {
    public int id;//主键
    public String cardNo;//卡号
    public String courseName;//课程
    public String classRoom;//上课教室
    public String schoolTime;//上课时间
    public String type;//类型 迟到，请假，早退，缺勤
    public String dayTime;//日期
    public String gradeName;//所在班级
    public String studentName;//学生姓名

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }


    public String getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
    }

    public String getType() {
        return type==null?"4":type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }
}
