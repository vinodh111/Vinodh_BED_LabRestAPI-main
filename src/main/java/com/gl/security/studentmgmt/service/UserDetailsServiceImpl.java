package com.gl.security.studentmgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gl.security.studentmgmt.entity.User;
import com.gl.security.studentmgmt.repository.UserRepository;
import com.gl.security.studentmgmt.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.getUserByUsername(username);
		if(user == null)
		{
			throw new UsernameNotFoundException("User Could not be found");
		}
		
		return new MyUserDetails(user);
	}
	
	

}
