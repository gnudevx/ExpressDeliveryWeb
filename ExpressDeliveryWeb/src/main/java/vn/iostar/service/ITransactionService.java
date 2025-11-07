package vn.iostar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import vn.iostar.model.TransactionDTO;
import vn.iostar.model.TransactionDetailDTO;

public interface ITransactionService {
	List<TransactionDTO> findTransaction();
	
	Page<TransactionDTO> getAll(Integer pageNo);
	
	Page<TransactionDetailDTO> getOffice(Integer pageNo, Integer id);
	
	List<TransactionDetailDTO> findTransactionByIdStartOffice(@Param("id") int id);
}
