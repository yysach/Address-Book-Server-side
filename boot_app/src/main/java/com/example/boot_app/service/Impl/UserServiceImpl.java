package com.example.boot_app.service.Impl;

import com.example.boot_app.Entity.User;
import com.example.boot_app.Repository.UserRepository;
import com.example.boot_app.data_transfer_object.RegisterUserdto;
import com.example.boot_app.data_transfer_object.Userdto;
import com.example.boot_app.helper.UserMapper;
import com.example.boot_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoderService encoderService;


    @Override
    public Userdto RegisterUser(RegisterUserdto registerUserdto) throws DataIntegrityViolationException {
    
        User user = new User();
        user.setEmail(registerUserdto.getEmail());
        user.setUsername(registerUserdto.getUsername());
        user.setPassword(encoderService.encodePass(registerUserdto.getPassword()));
        user.setRole("ROLE_USER");
        return userMapper.mapToUserDto(this.userRepository.save(user));
    }


    @Override
    public Userdto getUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.mapToUserDto(this.userRepository.findByUsername(username));
    }

    @Override
    public void changePassword(String email, String password) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        user.setPassword(encoderService.encodePass(password));
        this.userRepository.save(user);
    }


    @Override
    public boolean userIntegrity(String username, String email) throws UsernameNotFoundException {
        User user1 = this.userRepository.findByUsername(username);
        User user2 = this.userRepository.findByEmail(email);
        if(user1==null && user2==null){
            return true;
        }
        return false;
    }


    @Override
    public boolean resetPassword(String oldpassword, String newpassword, String username)
            throws UsernameNotFoundException {
                User user = this.userRepository.findByUsername(username);
                if(encoderService.crossMatchPassword(oldpassword, user.getPassword())){
                    user.setPassword(encoderService.encodePass(newpassword));
                    this.userRepository.save(user);
                    return true;
                }
        return false;
    }
    
}
