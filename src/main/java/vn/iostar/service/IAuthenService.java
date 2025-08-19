package vn.iostar.service;

public interface IAuthenService {
    String generateToken(String userName,String roles);
}
