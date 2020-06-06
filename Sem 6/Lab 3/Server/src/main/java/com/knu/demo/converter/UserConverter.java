package com.knu.demo.converter;

import com.knu.demo.dto.UserDTO;
import com.knu.demo.entity.Booking;
import com.knu.demo.entity.Car;
import com.knu.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        if (user.getCar() != null) {
            userDTO.setCarId(user.getCar().getId());
        } else {
            userDTO.setCarId(null);
        }

        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO, Car car) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setCar(car);
        return user;
    }
}
