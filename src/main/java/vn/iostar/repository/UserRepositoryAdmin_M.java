package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iostar.entity.User;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepositoryAdmin_M extends JpaRepository<User, Integer>{

	// Lấy danh sách shipper (giả sử Role của Shipper có ID là 2)
    List<User> findByRoleRoleId(Integer roleId);

}
