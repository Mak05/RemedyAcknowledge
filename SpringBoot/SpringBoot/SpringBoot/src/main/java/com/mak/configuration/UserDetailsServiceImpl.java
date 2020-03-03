package com.mak.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mak.dao.UsersDao;
import com.mak.model.Roles;
import com.mak.model.Users;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

		@Autowired
	    private UsersDao userRepository;

	    @Transactional
	    @SuppressWarnings("unused") 
	    public UserDetails loadUserByUsername(String userName) {
	        Users user = userRepository.findByUserName(userName);
	        if (user == null) throw new UsernameNotFoundException(userName);

	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        for (Roles role : user.getRole()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(userName));
	        }

	        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
	    }

}
