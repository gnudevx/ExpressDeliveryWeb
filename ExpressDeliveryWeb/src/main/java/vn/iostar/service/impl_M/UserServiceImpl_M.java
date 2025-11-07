package vn.iostar.service.impl_M;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.entity.User;
import vn.iostar.repository.UserRepositoryAdmin_M;
import vn.iostar.service_M.IUserService_M;

import java.util.List;

@Service
public class UserServiceImpl_M implements IUserService_M {

    @Autowired
    private UserRepositoryAdmin_M userRepository;

    @Override
    public List<User> findShippers() {
        // Giả sử Role của Shipper có ID là 2, bạn cần query danh sách này.
        return userRepository.findByRoleRoleId(3);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
