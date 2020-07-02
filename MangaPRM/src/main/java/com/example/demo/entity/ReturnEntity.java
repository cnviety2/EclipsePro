package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Class bao bên ngoài data để trả về
 */
public class ReturnEntity {
	private String message;
	private Object data;

	public ReturnEntity(Object data) {
		message = "OK";
		this.data = data;
	}
	
	public ReturnEntity(String message) {
		this.message = message;
		this.data = null;
	}
	
	public ReturnEntity() {
		message = "OK";
	}
	
	

}