package com.bezkoder.spring.jwt.mongodb.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(String id) {
		return userRepository.findById(id).orElse(null);
	}

	public User createUser(User userType) {
		return userRepository.save(userType);
	}

	public User updateUser(String id, User updatedUser) {
		return userRepository.findById(id)
				.map(existingUser -> {
					existingUser.setFirstName(updatedUser.getFirstName());
					existingUser.setLastName(updatedUser.getLastName());
					// Update other fields as needed
					return userRepository.save(existingUser);
				})
				.orElse(null); // Handle the case when the user with the given id is not found
	}

	public void deleteUser(String id) {
		userRepository.deleteById(id);
	}
}
