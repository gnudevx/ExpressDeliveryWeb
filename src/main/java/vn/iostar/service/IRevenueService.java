package vn.iostar.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.iostar.model.RevenueDTO;

public interface IRevenueService {
	List<RevenueDTO> findOfficeRevenue();
	
	Page<RevenueDTO> getAll(Integer pageNo);
}
