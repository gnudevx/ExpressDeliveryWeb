package vn.iostar.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.iostar.entity.Product;
import vn.iostar.repository.ProductRepository;
@Service
public class ProductServiceImpl implements IProductService{
	@Autowired
	ProductRepository productRepo;

	public ProductServiceImpl(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productRepo.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepo.findById(id);
	}

	@Override
	public Page<Product> getAll(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 10);
		return productRepo.findAll(pageable);
	}
	
}
