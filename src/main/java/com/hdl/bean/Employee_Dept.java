package com.hdl.bean;

public class Employee_Dept {
    private Integer id;
    private  String lastname;
    private String gender;
    private String email;
    //每个员工都有一个部门
    private Dept dept;

    public Employee_Dept(Integer id, String lastname, String gender, String email, Dept dept) {
        this.id = id;
        this.lastname = lastname;
        this.gender = gender;
        this.email = email;
        this.dept = dept;
    }

    public Employee_Dept() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }


    @Override
    public String toString() {
        return "Employee_Dept{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                '}';
    }


}
