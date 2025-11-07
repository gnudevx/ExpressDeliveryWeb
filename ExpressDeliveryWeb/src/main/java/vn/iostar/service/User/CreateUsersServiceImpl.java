package vn.iostar.service.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.iostar.entity.User;
import vn.iostar.repository.UserRepository;
import vn.iostar.service.ICreateUsersService;

import java.util.List;

@Service
public class CreateUsersServiceImpl implements ICreateUsersService {
    @Autowired
    UserRepository userRepository;
    //create password temp email service
    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationServiceImpl auth;

    //  default password = 1234
    @Override
    public User create(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        /*
             this is return password after create password send to mail user!
             step 1: after create password -> and it save password in clientSer
             step 2: save it in user and encoder!
        */
        String password = clientService.create(user);
        user.setPasswordHash(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }
    public String loginUser(String email, String password){
        User user = userRepository.findByEmail(email);
//            return(passwordEncoder.matches(password, user.getPasswordHash()));
        boolean result =  passwordEncoder.matches(password, user.getPasswordHash());
        if(result){
            System.out.println("ma token:" + auth.generateToken(user.getEmail(), user.getRole().getRoleName()));
            return auth.generateToken(user.getEmail(),user.getRole().getRoleName());
        }else {
            return null;
        }
    }
    public User getUser(String email){
        return userRepository.findByEmail(email);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
