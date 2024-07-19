package it.marco.digrigoli.services.interfaces;

import java.util.Optional;

import it.marco.digrigoli.entities.User;
import it.marco.digrigoli.entities.dto.UserLoginResponseDTO;

public interface IUserService {

	public Optional<User> loadUserByName(String username);
	
	public Optional<User> findByEmail(String email);
	
	public UserLoginResponseDTO issueToken(String email);
	
	public User register(User user);
	
	public Iterable<User> getAll();
	
	public Optional<User> getById(Long id);
	
	public String hashPassword(String password);
	
	public User save(User user);

	String generateRandomSpecialCharacters(int length);
	
}
