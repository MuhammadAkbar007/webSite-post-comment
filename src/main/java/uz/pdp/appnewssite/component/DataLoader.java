package uz.pdp.appnewssite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.entity.enums.Huquq;
import uz.pdp.appnewssite.repository.LavozimRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.appnewssite.entity.enums.Huquq.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) {
        if (initialMode.equals("always")) {
            Huquq[] huquqs = Huquq.values();
            Lavozim admin = lavozimRepository.save(new Lavozim(
                    AppConstants.ADMIN, Arrays.asList(huquqs), "Sistema egasi"));
            Lavozim user = lavozimRepository.save(new Lavozim(
                    AppConstants.USER, Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT), "Tizim foydalanuvchisi"));

            userRepository.save(new User(
                    "Admin Adminov", "admin", passwordEncoder.encode("admin123"), admin, true));
            userRepository.save(new User(
                    "User Userov", "user", passwordEncoder.encode("user123"), user, true));
        }
    }
}
