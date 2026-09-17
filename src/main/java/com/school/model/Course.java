package com.school.model;

public class Course {
    private int courseId;
    private String courseName;
    private String description;
    private int credit;
    private Department department;
    private Teacher teacher;

    public Course() {
    }

    public Course(
            int courseId, 
            String courseName, 
            String description, 
            int credit, 
            Department department, 
            Teacher teacher
    ) 
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.credit = credit;
        this.department = department;
        this.teacher = teacher;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", credit=" + credit +
                ", department=" + department +
                ", teacher=" + teacher +
                '}';
    }
}