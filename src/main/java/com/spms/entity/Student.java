package com.spms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

import com.spms.entity.sec.SystemUser;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
@NamedQueries({ @NamedQuery(name = "Student.findAll", query = "SELECT model FROM Student model")})
public class Student extends SystemUser implements Serializable {

  private static final long serialVersionUID = 1L;

  // Student: id (must be unique), name, surname, email address (must be validated), course (e.g.
  // Computer Science).

  private String course;
  
  public String getCourse() {
    return course;
  }
  public void setCourse(String course) {
    this.course = course;
  }
  
}
