package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {

	@Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username")
	UserEntity getUser(@Param("username") String username);

	@Query(value = "SELECT u.username FROM UserEntity u")
	List<String> getAllUserName();

	@Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
	UserEntity login(@Param("username") String username, @Param("password") String password);

}
