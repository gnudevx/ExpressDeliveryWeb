package vn.iostar.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iostar.entity.User;
import vn.iostar.model.UserRoleDTO;
import vn.iostar.repository.UserRepository;

@Service
public class UserService implements IUserService {
	@Autowired
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public long countNewCustomerDay() {
		return userRepository.countNewCustomerDay();
	}

	@Override
	public long countNewCustomerMonth() {
		return userRepository.countNewCustomerMonth();
	}

	@Override
	public long countNewCustomerYear() {
		return userRepository.countNewCustomerYear();
	}

	@Override
	public long countTotalCustomer() {
		return userRepository.countTotalCustomer();
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
	public List<Long> getMonthlyTotal() {
		return userRepository.getMonthlyTotal();
	}

	@Override
	public List<Long> getQuarterlyTotal() {
		return userRepository.getQuarterlyTotal();
	}

	@Override
	public List<User> findCustomer() {
		return userRepository.findCustomer();
	}

	@Override
	public Page<User> getCustomer(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
		List<User> allCustomer = findCustomer();
		int start = Math.min((pageNo - 1) * 10, allCustomer.size());
		int end = Math.min(start + 10, allCustomer.size());
		List<User> pageContent = allCustomer.subList(start, end);
		return new PageImpl<User>(pageContent, pageable, allCustomer.size());
	}

	@Override
	public User getById(int id) {
		return userRepository.getById(id);
	}

	@Override
	public List<UserRoleDTO> findAllEmployee() {
		List<Object[]> results = userRepository.findAllEmployee();

		List<UserRoleDTO> list = new ArrayList<>();

		for (Object[] result : results) {
			int userId = (Integer) result[0];
			String email = (String) result[1];
			String password = (String) result[2];
			String fullname = (String) result[3];
			String phone = (String) result[4]; 
		    
			String name = (String) result[6];
			String status = (String) result[7];
			Integer officeId = null;
			if ((Integer) result[8] != null) {
				officeId = (Integer) result[8];
			}
			
			list.add(new UserRoleDTO(userId, email, password, fullname, phone,
					name, status, officeId));
		}
		return list;
	}

	@Override
	public Page<UserRoleDTO> getAllEmployee(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
        List<UserRoleDTO> list = findAllEmployee();
        int start = Math.min((pageNo - 1) * 10, list.size());
        int end = Math.min(start + 10, list.size());
        List<UserRoleDTO> pageContent = list.subList(start, end);
        return new PageImpl<UserRoleDTO>(pageContent, pageable, list.size());
	}

	@Override
	public List<UserRoleDTO> findByRoleOffice(String role, Integer office) {
		List<Object[]> results = userRepository.findByRoleOffice(role, office);

		List<UserRoleDTO> list = new ArrayList<>();

		for (Object[] result : results) {
			int userId = (Integer) result[0];
			String email = (String) result[1];
			String password = (String) result[2];
			String fullname = (String) result[3];
			String phone = (String) result[4];
			String name = (String) result[6];
			String status = (String) result[7];
			Integer officeId = null;
			if ((Integer) result[8] != null) {
				officeId = (Integer) result[8];
			}
			
			list.add(new UserRoleDTO(userId, email, password, fullname, phone,
					name, status, officeId));
		}
		return list;
	}

	@Override
	public Page<UserRoleDTO> getAllByRoleOffice(String role, Integer officeId, Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
        List<UserRoleDTO> list = findByRoleOffice(role, officeId);
        int start = Math.min((pageNo - 1) * 10, list.size());
        int end = Math.min(start + 10, list.size());
        List<UserRoleDTO> pageContent = list.subList(start, end);
        return new PageImpl<UserRoleDTO>(pageContent, pageable, list.size());
	}

	@Override
	public <S extends User> S save(S entity) {
		return userRepository.save(entity);
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

}
