package com.example.sbwmcum.pojo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import xyz.morphia.annotations.Entity;

@Entity(value = "employees", noClassnameStored = true)
public class Employee {

    @Id
    private ObjectId _id;

    private String empName;
    private String gender;
    private String mobile;

    public Employee(ObjectId id, String empName, String gender, String mobile) {
        this._id = id;
        this.empName = empName;
        this.gender = gender;
        this.mobile = mobile;
    }

    public Employee() {

    }

    public ObjectId getId() {
        return _id;
    }
    public void setId(ObjectId id) {
        this._id = id;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + _id +
                ", empName='" + empName + '\'' +
                ", gender='" + gender + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
