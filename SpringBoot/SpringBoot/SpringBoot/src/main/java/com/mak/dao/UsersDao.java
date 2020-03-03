package com.mak.dao;

import org.springframework.data.repository.CrudRepository;

import com.mak.model.Users;

public interface UsersDao extends CrudRepository<Users, Integer>{
	Users findByUserName(String userName);
}
