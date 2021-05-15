package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.UserDto;
import uz.pdp.appnewssite.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ApiResponse add(UserDto userDto) {
        return null;
    }
}
