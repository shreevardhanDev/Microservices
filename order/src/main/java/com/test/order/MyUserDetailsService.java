package com.test.order;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.UserDao;
import com.entities.Users;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User userDet = null;
		try {
			Users user = userDao.getUserForEmployeeHrmsId(userName);
			userDet = new User(user.getEmployeeHrmsId(), user.getPassword(),
					Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
		} catch (Exception ex) {
			throw new UsernameNotFoundException("Not found: " + userName);
		}

		return userDet;
	}

}
