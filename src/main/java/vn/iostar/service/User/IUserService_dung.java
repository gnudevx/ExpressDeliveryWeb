package vn.iostar.service.User;

import vn.iostar.entity.User;

public interface IUserService_dung {

	boolean existsById(Integer id);

	User findByEmail(String email);

	boolean existsByEmail(String email);

}
