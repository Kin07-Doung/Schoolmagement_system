package com.school.model;

public class Department {
    private int id;
    private String name;
    private String code;
    private String location;
    private int budget;
    private int managerId;
    private int IsActive;
    private String createdDate;

    public Department() {
    }

    public Department(
        int id, 
        String name, 
        String code, 
        String location, 
        int budget, 
        int managerId, 
        int IsActive, 
        String createdDate
    ) 
    {
        this.id = id;
        this.name = name;
        this.code = code;
        this.location = location;
        this.budget = budget;
        this.managerId = managerId;
        this.IsActive = IsActive;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int IsActive) {
        this.IsActive = IsActive;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", location='" + location + '\'' +
                ", budget=" + budget +
                ", managerId=" + managerId +
                ", IsActive=" + IsActive +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}