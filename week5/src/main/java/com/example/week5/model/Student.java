package com.example.week5.model;

import java.util.Objects;

public class Student {
  private String studentID;
  private String name;
  private String password;
  private String email;


  public Student() {
  }

  public Student(String studentID, String name, String password, String email) {
    this.studentID = studentID;
    this.name = name;
    this.password = password;
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(studentID, student.studentID) &&
        Objects.equals(name, student.name) &&
        Objects.equals(password, student.password) &&
        Objects.equals(email, student.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentID, name, password, email);
  }

  @Override
  public String toString() {
    return "Student{" +
        "studentID='" + studentID + '\'' +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  public String getStudentID() {
    return studentID;
  }

  public void setStudentID(String studentID) {
    this.studentID = studentID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
