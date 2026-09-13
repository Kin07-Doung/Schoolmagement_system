package com.school.model;

public class Leave {
    private int leaveId;
    private String reason;
    private String startDate;
    private String endDate;
    private String status;
    private Student student;
    private SchoolClass schoolClass;

    public Leave() {
        this.status = "Pending";
    }

    public Leave(int leaveId, String reason, String startDate, String endDate, String status, Student student) {
        this(leaveId, reason, startDate, endDate, status, student, null);
    }

    public Leave(int leaveId, String reason, String startDate, String endDate, String status, Student student, SchoolClass schoolClass) {
        this.leaveId = leaveId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status != null ? status : "Pending";
        this.student = student;
        this.schoolClass = schoolClass;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void approve() {
        this.status = "Approved";
    }

    public void reject() {
        this.status = "Rejected";
    }

    @Override
    public String toString() {
        return "Leave{" +
                "leaveId=" + leaveId +
                ", reason='" + reason + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", student=" + (student != null ? student.getStudentName() : "None") +
                ", schoolClass=" + (schoolClass != null ? schoolClass.getClassName() : "None") +
                '}';
    }
}

