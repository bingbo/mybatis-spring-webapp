package com.ibingbo.test;

import java.util.List;

/**
 * Created by bing on 17/9/11.
 */
public class User<T> {
    private Integer id;
    private String name;
    private String email;
    private T data;

    private List<Student> students;

    private Student[] studentArray;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public T getData() {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student[] getStudentArray() {
        return studentArray;
    }

    public void setStudentArray(Student[] studentArray) {
        this.studentArray = studentArray;
    }
}
