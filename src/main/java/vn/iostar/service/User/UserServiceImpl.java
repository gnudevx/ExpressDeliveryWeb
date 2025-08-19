package vn.iostar.service.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iostar.entity.User;
import vn.iostar.repository.UserRepository;
import vn.iostar.service.IUserService;
@Service
public class UserServiceImpl implements IUserService_dung{
    @Autowired
    UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	
	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	
	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}
    
}