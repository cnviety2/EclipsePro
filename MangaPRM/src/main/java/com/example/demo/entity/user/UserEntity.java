package com.example.demo.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`User`")
//@JsonIgnoreProperties(value = "password")
public class UserEntity {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "password")
	private String password;

	@Column(name = "isAdmin")
	private boolean isAdmin;

	public UserEntity(String username, String password) {
		this.username = username;
		this.password = password;
		this.fullname = "";
		this.isAdmin = false;
	}

	public UserEntity(String username, String password, String fullname) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.isAdmin = false;
	}
}
