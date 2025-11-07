package vn.iostar.service;

import java.util.List;

import vn.iostar.entity.Role;

public interface IRoleService {
	List<Role> findAll();
	
	List<Role> findExceptUserShipper();
	
	List<Role> findExceptUser();
	
	Role getById(Integer id);
	
	Role findByName(String name);
}
