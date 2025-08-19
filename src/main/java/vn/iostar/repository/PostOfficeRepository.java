package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.PostOffice;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Integer>{

}