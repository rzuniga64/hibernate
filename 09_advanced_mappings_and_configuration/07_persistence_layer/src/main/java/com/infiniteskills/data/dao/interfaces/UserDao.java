package com.infiniteskills.data.dao.interfaces;

import com.infiniteskills.data.entities.User;

import java.util.List;

public interface UserDao extends Dao<User,Long>{

	public List<User> findByFirstName(String firstName);
}
