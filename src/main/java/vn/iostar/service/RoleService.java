//package vn.iostar.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import vn.iostar.entity.PostOffice;
//import vn.iostar.entity.Role;
//import vn.iostar.repository.RoleRepository;
//
//@Service
//public class RoleService implements IRoleService{
//	@Autowired
//	RoleRepository roleRepository;
//
//	public RoleService(RoleRepository roleRepository) {
//		this.roleRepository = roleRepository;
//	}
//
//	@Override
//	public List<Role> findAll() {
//		return roleRepository.findAll();
//	}
//
//	@Override
//	public List<Role> findExceptUserShipper() {
//		return roleRepository.findExceptUserShipper();
//	}
//
//	@Override
//	public Role getById(Integer id) {
//		return roleRepository.getById(id);
//	}
//
//	@Override
//	public List<Role> findExceptUser() {
//		return roleRepository.findExceptUser();
//	}
//
//	@Override
//	public Role findByName(String name) {
//		return roleRepository.findByName(name);
//	}
//}
