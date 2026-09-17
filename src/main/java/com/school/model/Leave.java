package com.school.model;

/**
 * Leave
 * -----
 * Represents a leave request. The requester can be EITHER a Student
 * OR a Teacher (never both) — exactly one of the two fields will be set.
 *
 * Use isTeacherLeave() / isStudentLeave() to check which one applies,
 * and getRequesterName() to display whichever one is set without
 * needing to null-check both yourself every time.
 */
public class Leave {
    private int leaveId;
    private String reason;
    private String startDate;
    private String endDate;
    private String status;
    private Student student;
    private Teacher teacher;
    private SchoolClass schoolClass;

    public Leave() {
        this.status = "Pending";
    }

    /** Leave requested by a Student, optionally tied to a SchoolClass. */
    public Leave(int leaveId, String reason, String startDate, String endDate, String status,
                 Student student, SchoolClass schoolClass) {
        this.leaveId = leaveId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status != null ? status : "Pending";
        this.student = student;
        this.schoolClass = schoolClass;
    }

    /** Convenience: Student leave without a SchoolClass. */
    public Leave(int leaveId, String reason, String startDate, String endDate, String status, Student student) {
        this(leaveId, reason, startDate, endDate, status, student, null);
    }

    /** Leave requested by a Teacher. */
    public Leave(int leaveId, String reason, String startDate, String endDate, String status, Teacher teacher) {
        this.leaveId = leaveId;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status != null ? status : "Pending";
        this.teacher = teacher;
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

    /** Setting a Student as requester clears any Teacher requester. */
    public void setStudent(Student student) {
        this.student = student;
        if (student != null) {
            this.teacher = null;
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }

    /** Setting a Teacher as requester clears any Student requester. */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        if (teacher != null) {
            this.student = null;
        }
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public boolean isStudentLeave() {
        return student != null;
    }

    public boolean isTeacherLeave() {
        return teacher != null;
    }

    /** Returns the requester's name regardless of whether it's a Student or Teacher. */
    public String getRequesterName() {
        if (student != null) return student.getStudentName();
        if (teacher != null) return teacher.getTeacherName();
        return "None";
    }

    /** Returns "Student" or "Teacher" depending on who requested this leave. */
    public String getRequesterType() {
        if (student != null) return "Student";
        if (teacher != null) return "Teacher";
        return "Unknown";
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
                ", requesterType='" + getRequesterType() + '\'' +
                ", requester=" + getRequesterName() +
                ", schoolClass=" + (schoolClass != null ? schoolClass.getClassName() : "None") +
                '}';
    }
}

