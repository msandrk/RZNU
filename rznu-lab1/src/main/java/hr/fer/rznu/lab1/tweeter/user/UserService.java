package hr.fer.rznu.lab1.tweeter.user;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> users = new LinkedList<User>();
		userRepository.findAll().forEach(u -> users.add(u));
		return users;
	}

	public User getUser(Integer id) {
		if (id == null)
			return null;

		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return user.get();
	}

	public void addUser(User user) {

		if (user.getId() == null || userRepository.existsById(user.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		userRepository.save(user);
	}

	public void updateUser(Integer id, User user) {
		if (id == null || user.getId() == null || id != user.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else if(!userRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		userRepository.save(user);
	}

	public void deleteUser(Integer id) {
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);			
		}
		else if (!userRepository.existsById(id)){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		userRepository.deleteById(id);
	}
}
