package vn.iostar.service_M;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.iostar.entity.PostOffice;


public interface IPostOfficeService_M {

	List<PostOffice> getAllPostOffices();

	List<String> getAllAddresses();

	List<String> getAllOptionAddress();

	

}
