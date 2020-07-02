package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ReturnEntity;
import com.example.demo.entity.user.RequestLoginEntity;
import com.example.demo.entity.user.RequestRegisterEntity;
import com.example.demo.entity.user.RequestUpdateEntity;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	private static Logger log = LogManager.getLogger(UserController.class);

	@GetMapping("/{username}")
	public Object test(@PathVariable("username") String username) {
		return new ResponseEntity<UserEntity>(service.testAUser(username), HttpStatus.OK);
	}

	@PostMapping("/test")
	public Object asd(@RequestBody UserEntity user) {
		service.addUser(user);
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}

	@GetMapping("/all")
	public Object getEverything() {
		return new ResponseEntity<ReturnEntity>(new ReturnEntity(service.getAllUsername()), HttpStatus.OK);
	}

	/**
	 * Gửi lên 2 attr username,password {"username" ="", "password" = "" }
	 */
	@PostMapping("/login")
	@CrossOrigin
	public Object login(@RequestBody RequestLoginEntity loginInfo) {
		try {
			UserEntity user = service.login(loginInfo.getUsername().trim(), loginInfo.getPassword().trim());
			if (user != null) {
				return new ResponseEntity<ReturnEntity>(new ReturnEntity(user), HttpStatus.OK);
			}
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Access denied"), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Access denied"), HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/register")
	@CrossOrigin
	public Object register(@RequestBody RequestRegisterEntity registerInfo) {

		try {
			UserEntity user = new UserEntity(registerInfo.getUsername().trim(), registerInfo.getPassword().trim());
			if (service.addUser(user)) {
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("OK"), HttpStatus.OK);
			}
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Register denied"), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Register denied"), HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/update")
	@CrossOrigin
	public Object update(@RequestBody RequestUpdateEntity updateInfo) {
		try {
			UserEntity user = new UserEntity(updateInfo.getUsername().trim(), updateInfo.getPassword().trim(),
					updateInfo.getFullname().trim());
			if (service.updateAUser(user)) {
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("OK"), HttpStatus.OK);
			}
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Update denied"), HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Update denied"), HttpStatus.UNAUTHORIZED);

	}

}
