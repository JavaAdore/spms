package com.spms.entity.sec;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "SYSTEMUSER")
@Inheritance(strategy = InheritanceType.JOINED)
public class SystemUser implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  private String id;
  
  private String name;
  
  private String surname;

  @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*", message = "Invalid Email.")
  @Column(name = "USER_NAME")
  private String email;

  @Column(name = "USERPASSWORD")
  private String password;
  
  @ManyToOne
  @JoinColumn(name = "GROUP_ID")
  private SystemRole role;
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getSurname() {
    return surname;
  }
  
  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public SystemRole getRole() {
    return role;
  }

  public void setRole(SystemRole role) {
    this.role = role;
  }
  
  public SystemUser() {}

}
