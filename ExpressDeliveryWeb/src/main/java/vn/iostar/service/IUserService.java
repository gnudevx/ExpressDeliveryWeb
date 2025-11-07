package vn.iostar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.iostar.entity.User;
import vn.iostar.model.UserRoleDTO;

public interface IUserService {
	
    long countNewCustomerDay();
	
	long countNewCustomerMonth();
	
	long countNewCustomerYear();
	
	long countTotalCustomer();
	
	boolean existsByEmail(String email);
	
    User findByEmail(String email);
    
    List<Long> getMonthlyTotal();
    
    List<Long> getQuarterlyTotal();
    
    List<User> findCustomer();
    
    Page<User> getCustomer(Integer pageNo);
    
    User getById(int id);
    
    List<UserRoleDTO> findAllEmployee();
    
    Page<UserRoleDTO> getAllEmployee(Integer pageNo);
    
    List<UserRoleDTO> findByRoleOffice(String role, Integer office);
    
    Page<UserRoleDTO> getAllByRoleOffice(String role, Integer officeId, Integer pageNo);
    
    <S extends User> S save(S entity);
    
    void deleteById(Integer id);
    
    Optional<User> findById(Integer id);
}
