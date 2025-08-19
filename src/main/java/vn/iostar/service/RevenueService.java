package vn.iostar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.iostar.model.RevenueDTO;
import vn.iostar.repository.RevenueRepository;

@Service
public class RevenueService implements IRevenueService {

	@Autowired
	RevenueRepository revenueRepository;

	public RevenueService(RevenueRepository revenueRepository) {
		this.revenueRepository = revenueRepository;
	}

	@Override
	public List<RevenueDTO> findOfficeRevenue() {
		List<Object[]> results = revenueRepository.findOfficeRevenue();
	    
	    List<RevenueDTO> revenueDTOList = new ArrayList<>();
	    
	    for (Object[] result : results) {
	        int officeId = (Integer) result[0]; // office_id
	        String address = (String) result[1];
	        long dayly = ((Number) result[2]).longValue(); // Dayly
	        long monthly = ((Number) result[3]).longValue(); // Monthly
	        long quarterly = ((Number) result[4]).longValue(); // Quarterly
	        long yearly = ((Number) result[5]).longValue(); // Yearly
	        
	        // Add the RevenueDTO to the list
	        revenueDTOList.add(new RevenueDTO(officeId, address, dayly, monthly, quarterly, yearly));
	    }
	    
	    return revenueDTOList;
	}

	@Override
	public Page<RevenueDTO> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
        List<RevenueDTO> allRevenue = findOfficeRevenue();
        int start = Math.min((pageNo - 1) * 10, allRevenue.size());
        int end = Math.min(start + 10, allRevenue.size());
        List<RevenueDTO> pageContent = allRevenue.subList(start, end);
        return new PageImpl<RevenueDTO>(pageContent, pageable, allRevenue.size());
    }
}
