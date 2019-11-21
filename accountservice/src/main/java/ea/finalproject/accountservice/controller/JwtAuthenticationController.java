package ea.finalproject.accountservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import ea.finalproject.accountservice.config.JwtTokenUtil;
import ea.finalproject.accountservice.model.JwtRequest;
import ea.finalproject.accountservice.model.JwtResponse;
import ea.finalproject.accountservice.model.User;
import ea.finalproject.accountservice.model.UserDTO;
import ea.finalproject.accountservice.service.JwtUserDetailsService;
import ea.finalproject.accountservice.service.serviceimpl.TokenDecoderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class JwtAuthenticationController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private TokenDecoderService tokenDecoderService;


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

//	@CachePut(value = "users", key = "#user.username")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User saveUser(@RequestBody UserDTO user) throws Exception {
		return userDetailsService.save(user);

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Cacheable(value = "users", key = "#username")
	@GetMapping(value = "/account/{username}")
	public User getUser(@PathVariable String username) throws UnsupportedEncodingException, JsonProcessingException {
		log.info("getting user with username", username);
		return userDetailsService.getUser(username);
	}
	@CacheEvict(value = "users", allEntries=true)
	@GetMapping(value = "/account/delete/{id}")
	public void deleteUser(@PathVariable String id) throws UnsupportedEncodingException, JsonProcessingException {
		log.info("deleting user with id", id);
		 userDetailsService.deleteUser(id);
	}
}
