package com.ksvsofttech.product.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.entities.UserMst;
import com.ksvsofttech.product.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		UserMst user = userRepository.findByLoginId(loginId);
		if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
		return new UserDetailsImpl(user);
	}
}
