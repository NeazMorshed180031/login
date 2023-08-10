package com.example.login;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.login.entities.Authority;
import com.example.login.entities.User;
import com.example.login.repository.UserDetailsRepository;

@SpringBootApplication
public class LoginApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@PostConstruct
	protected void init() {

		List<Authority> authorityList=new ArrayList<>();

		authorityList.add(createAuthority("USER","User role"));
		//authorityList.add(createAuthority("ADMIN","Admin role"));

		User user=new User();

		user.setUserName("admin");

                System.gc();
		user.setPassword(passwordEncoder.encode("admin@123"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);
                System.gc();
		userDetailsRepository.save(user);



	}


	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		System.gc();
		return authority;
	}



}
