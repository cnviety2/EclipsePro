package com.example.demo.entity.user;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
/**
 * Entity để gửi request register
 * */
public class RequestRegisterEntity {

	private String username;
	private String password;
}
