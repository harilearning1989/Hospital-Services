package com.hosp.login.services;

import com.hosp.login.models.User;
import com.hosp.login.models.UserDetailsImpl;
import com.hosp.login.repos.UserRepository;
import com.web.demo.exception.UserNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
        return this;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotExistsException("User Not Found with Username=" + username));
        return UserDetailsImpl.build(user);
    }

}
