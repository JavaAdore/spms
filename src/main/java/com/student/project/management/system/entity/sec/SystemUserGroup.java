package com.student.project.management.system.entity.sec;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "SYSTEMUSERGROUP")
public class SystemUserGroup implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "USER_NAME")
  private String username;

  @Id
  @Column(name = "GROUPNAME")
  private String groupName;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
}
