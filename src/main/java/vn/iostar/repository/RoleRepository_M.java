package vn.iostar.repository;

import org.springframework.stereotype.Repository;

//import vn.iostar.entity.ParcelType;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository_M extends JpaRepository<Role, Integer> {

	long count(); // Phương thức để đếm số lượng bản ghi

}
