package uz.pdp.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {

    @NotNull(message = "FISH bo'sh bo'lmasligi kerak !")
    private String fullName;

    @NotNull(message = "Username bo'sh bo'lmasligi kerak !")
    private String username;

    @NotNull(message = "Parol bo'sh bo'lmasligi kerak !")
    private String password;

    @NotNull(message = "Parol takrori bo'sh bo'lmasligi kerak !")
    private String prePassword;
}
