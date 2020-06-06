package com.knu.demo.service.data;

import com.knu.demo.entity.User;
import com.knu.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;

    @Transactional
    public User save(User currentUser) {
        Optional<User> oldUser = userRepository.findByEmail(currentUser.getEmail());
        return oldUser.orElseGet(() -> userRepository.save(currentUser));
    }
}
