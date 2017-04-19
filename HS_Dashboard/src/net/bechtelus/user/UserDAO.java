package net.bechtelus.user;


import java.util.List;

import javax.ejb.Remote;

import net.bechtelus.common.DAOException;
import net.bechtelus.user.User;


public interface  UserDAO  {
	
public User findUserById(long user_tk) throws DAOException;
  
public User findUserByEmail(String email) throws DAOException;

public User findUserByUserID(String user_id) throws DAOException;

public List<User> getAllActiveUsers() throws DAOException;
  
 
   
}