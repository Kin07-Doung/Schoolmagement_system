package com.school.model;

public class SchoolClass {
    private int classId;
    private String className;
    private String room;
    private int capacity;
    private Course course;
    private Teacher teacher;
    private Generation generation;

    public SchoolClass() {
    }

    public SchoolClass(int classId, String className, String room, int capacity, Course course, Teacher teacher, Generation generation) {
        this.classId = classId;
        this.className = className;
        this.room = room;
        this.capacity = capacity;
        this.course = course;
        this.teacher = teacher;
        this.generation = generation;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public boolean isFull(int currentStudentCount) {
        return currentStudentCount >= capacity;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", room='" + room + '\'' +
                ", capacity=" + capacity +
                ", course=" + course +
                ", teacher=" + teacher +
                ", generation=" + generation +
                '}';
    }
}