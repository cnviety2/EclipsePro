package com.example.demo.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * Entity gửi request để login
 * */
public class RequestLoginEntity {

	private String username;
	private String password;
}
