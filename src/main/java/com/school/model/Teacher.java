package com.school.model;

public class Teacher {
    private int teacherId;
    private String teacherName;
    private String email;
    private String phone;
    private double salary;
    private Department department;

    public Teacher() {
    }

    public Teacher(int teacherId, String teacherName, String email, String phone, double salary, Department department) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void assignToDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", department=" + (department != null ? department.getName() : "None") +
                '}';
    }
}

