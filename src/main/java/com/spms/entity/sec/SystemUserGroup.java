package com.spms.entity.sec;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "SystemUserGroup.findAll", query = "SELECT model FROM SystemUserGroup model"),
		@NamedQuery(name = "SystemUserGroup.findUserGroup", query = "SELECT model FROM SystemUserGroup model WHERE model.username = :username AND model.groupName = :groupName") })
@Table(name = "SYSTEMUSERGROUP")
public class SystemUserGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_NAME")
	private String username;

	@Id
	@Column(name = "GROUPNAME")
	private String groupName;

	public SystemUserGroup(){}
	
	public SystemUserGroup(String username, String groupName) {
		this.username = username;
		this.groupName = groupName;
	}

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
