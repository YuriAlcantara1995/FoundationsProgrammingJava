package space.harbour.RealEstateSellingSystem.service;

import space.harbour.RealEstateSellingSystem.domain.User;
import space.harbour.RealEstateSellingSystem.dto.UserDto;

import java.util.List;

public interface UserServiceInterface {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    User findById(long id);

    List<UserDto> findAllUsers();
}