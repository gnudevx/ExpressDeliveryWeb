package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iostar.entity.PostOffice;
import vn.iostar.entity.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Integer>{

}