package vn.iostar.service_M;

import java.util.List;

import vn.iostar.entity.User;

public interface IUserService_M {

	void deleteUserById(Integer id);

	void saveUser(User user);

	List<User> findAll();

	User findById(Integer id);

	List<User> findShippers();

	

}
