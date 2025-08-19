package vn.iostar.repository;

import org.springframework.stereotype.Repository;

import vn.iostar.entity.ParcelType;
import vn.iostar.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.query.Param;
@Repository
public interface ParcelTypeRepository_M extends JpaRepository<ParcelType, Integer>{

	long count();  // Phương thức để đếm số lượng bản ghi
	@Query(value = "SELECT * FROM parcel_types\r\n"
			+ "  WHERE :weight >= min_weight AND :weight < max_weight", nativeQuery = true)
    ParcelType findParcelType(@Param("weight") Float weight);
}
