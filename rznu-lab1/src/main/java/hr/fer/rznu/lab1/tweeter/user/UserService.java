package hr.fer.rznu.lab1.tweeter.user;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		List<User> users = new LinkedList<User>();
		userRepository.findAll().forEach(u -> users.add(u));
		return users;
	}

	public User getUser(Integer id) {
		if(id == null) return null;
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			return null;
		}
		return user.get();
	}

	public void addUser(User user) {
		
		if(user.getId() == null || userRepository.findById(user.getId()).isPresent()) {
				return;
		}
	
		userRepository.save(user);
	}

	public void updateUser(Integer id, User user) {
		if(id == null || user.getId() == null) return;
		if(userRepository.findById(id).isEmpty()) return;
		try{
			userRepository.save(user);
		} catch(IllegalArgumentException e) {
			// ignore
		}
	}

	public void deleteUser(Integer id) {
		if(id == null) return;
		userRepository.deleteById(id);		
	}
}
