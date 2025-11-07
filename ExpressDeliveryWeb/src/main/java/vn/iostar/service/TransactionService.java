//package vn.iostar.service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import vn.iostar.model.TransactionDTO;
//import vn.iostar.model.TransactionDetailDTO;
//import vn.iostar.repository.TransactionRepository;
//
//@Service
//public class TransactionService implements ITransactionService {
//
//	@Autowired
//	TransactionRepository transactionRepository;
//
//	public TransactionService(TransactionRepository transactionRepository) {
//		this.transactionRepository = transactionRepository;
//	}
//
//	@Override
//	public List<TransactionDTO> findTransaction() {
//		List<Object[]> results = transactionRepository.findTransaction();
//
//		List<TransactionDTO> transactionDTOList = new ArrayList<>();
//
//		for (Object[] result : results) {
//			int officeId = (Integer) result[0]; // office_id
//			String address = (String) result[1];
//			long dayly = ((Number) result[2]).longValue(); // Dayly
//			long monthly = ((Number) result[3]).longValue(); // Monthly
//			long quarterly = ((Number) result[4]).longValue(); // Quarterly
//			long yearly = ((Number) result[5]).longValue(); // Yearly
//
//			// Add the RevenueDTO to the list
//			transactionDTOList.add(new TransactionDTO(officeId, address, dayly, monthly, quarterly, yearly));
//		}
//
//		return transactionDTOList;
//	}
//
//	@Override
//	public Page<TransactionDTO> getAll(Integer pageNo) {
//		Pageable pageable = PageRequest.of(pageNo - 1, 10);
//		List<TransactionDTO> allTransaction = findTransaction();
//		int start = Math.min((pageNo - 1) * 10, allTransaction.size());
//		int end = Math.min(start + 10, allTransaction.size());
//		List<TransactionDTO> pageContent = allTransaction.subList(start, end);
//		return new PageImpl<TransactionDTO>(pageContent, pageable, allTransaction.size());
//	}
//
//	@Override
//	public Page<TransactionDetailDTO> getOffice(Integer pageNo, Integer id) {
//		Pageable pageable = PageRequest.of(pageNo - 1, 10);
//		List<TransactionDetailDTO> officeTransaction = findTransactionByIdStartOffice(id);
//		int start = Math.min((pageNo - 1) * 10, officeTransaction.size());
//		int end = Math.min(start + 10, officeTransaction.size());
//		List<TransactionDetailDTO> pageContent = officeTransaction.subList(start, end);
//		return new PageImpl<TransactionDetailDTO>(pageContent, pageable, officeTransaction.size());
//	}
//
//	@Override
//	public List<TransactionDetailDTO> findTransactionByIdStartOffice(int id) {
//		List<Object[]> results = transactionRepository.findTransactionByIdStartOffice(id);
//
//		List<TransactionDetailDTO> list = new ArrayList<>();
//
//		for (Object[] result : results) {
//			int parcelId = (Integer) result[1];
//			int userId = (Integer) result[2];
//			String paymentName = (String) result[0];
//			LocalDateTime date = (LocalDateTime) result[3];
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//			String createDate = date.format(formatter);
//			LocalDateTime completeDate = (LocalDateTime) result[4];
//			int shippingFee = (Integer) result[5];
//
//			list.add(new TransactionDetailDTO(parcelId, userId, paymentName, createDate, completeDate, shippingFee));
//		}
//
//		return list;
//	}
//}
