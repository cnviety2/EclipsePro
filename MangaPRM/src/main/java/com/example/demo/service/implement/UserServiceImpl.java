package com.example.demo.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user.UserEntity;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	private static Logger log = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public UserEntity getUser(String username) {
		try {
			return repo.getUser(username);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<String> getAllUsername() {
		try {
			return repo.getAllUserName();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<String>();
	}

	@Override
	public boolean isAdmin(String username) {
		try {
			UserEntity user = repo.getUser(username);
			return user.isAdmin();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}

	@Override
	public String getFullname(String username) {
		try {
			return repo.getUser(username).getFullname();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public UserEntity login(String username, String password) {
		try {
			if (username == null || username.isEmpty() || password == null || password.isEmpty())
				return null;
			return repo.login(username, password);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean addUser(UserEntity user) {
		if (!checkUser(user))
			return false;
		try {
			if (!repo.existsById(user.getUsername())) {
				repo.save(user);
				return true;
			}
			return false;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}

	@Override
	public UserEntity testAUser(String username) {
		return repo.findById(username).orElse(null);
	}

	@Override
	public boolean updateAUser(UserEntity user) {
		if (!checkUser(user))
			return false;
		try {
			if (repo.existsById(user.getUsername())) {
				repo.save(user);
				return true;
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}

	/*
	 * Check data của user,nếu pass các đk trả về true
	 */
	private boolean checkUser(UserEntity user) {
		if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null
				|| user.getPassword().isEmpty())
			return false;
		return true;
	}

}
