package com.school.model;

public class Timetable {
    private int timetableId;
    private String day;
    private String startTime;
    private String endTime;
    private String room;
    private Course course;
    private SchoolClass schoolClass;

    public Timetable() {
    }

    public Timetable(int timetableId, String day, String startTime, String endTime, String room, Course course) {
        this(timetableId, day, startTime, endTime, room, course, null);
    }

    public Timetable(int timetableId, String day, String startTime, String endTime, String room, Course course, SchoolClass schoolClass) {
        this.timetableId = timetableId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.course = course;
        this.schoolClass = schoolClass;
    }

    public int getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(int timetableId) {
        this.timetableId = timetableId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public boolean isConflict(Timetable other) {
        if (other == null) return false;
        return this.day != null && this.day.equalsIgnoreCase(other.day) &&
                this.room != null && this.room.equalsIgnoreCase(other.room) &&
                this.startTime != null && this.startTime.equalsIgnoreCase(other.startTime);
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timetableId=" + timetableId +
                ", day='" + day + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", room='" + room + '\'' +
                ", course=" + (course != null ? course.getCourseName() : "None") +
                ", schoolClass=" + (schoolClass != null ? schoolClass.getClassName() : "None") +
                '}';
    }
}

