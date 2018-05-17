package com.test.security;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.model.ApplicationUser;
import com.test.repo.ApplicationUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ApplicationUserRepository applicationUserRepository;

	
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    
	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			System.out.println("USER NOT FOUND");
			throw new UsernameNotFoundException(username);
		}else
			System.out.println("USER FOUND");
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
	}
}