package ea.finalproject.accountservice.service;
import ea.finalproject.accountservice.model.User;
import ea.finalproject.accountservice.model.UserDTO;
import ea.finalproject.accountservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public User save(UserDTO user) {
	//	if(userRepository.findByUsername(user.getUsername())==null) {
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setCity(user.getCity());
			newUser.setState(user.getState());
			newUser.setCountry(user.getCountry());
			newUser.setZipCode(user.getZipCode());
			newUser.setPhoneNumber(user.getPhoneNumber());
			newUser.setRole("USER_ROLE");
			newUser.setEmail(user.getEmail());
			return userRepository.save(newUser);
//		}
//		else{
//			throw new UsernameNotFoundException("User with " + user.getUsername() + " already registered");
//		}
	}
	public User getUser(String username){
		return userRepository.findByUsername(username);
	}

	public void deleteUser(String id){
		 userRepository.deleteById(id);
	}
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}

	public User save(User user) {
		return  userRepository.save(user);
	}
}
