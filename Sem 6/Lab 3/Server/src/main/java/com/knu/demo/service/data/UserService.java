package com.knu.demo.service.data;

import com.knu.demo.entity.User;
import com.knu.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User update(User currentUser) {
        Optional<User> oldUser = userRepository.findByEmail(currentUser.getEmail());
        return oldUser.orElseGet(() -> userRepository.save(currentUser));
    }
}
