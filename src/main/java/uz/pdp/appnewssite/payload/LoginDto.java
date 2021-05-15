package uz.pdp.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull(message = "Username bo'sh bo'lmasligi kerak !")
    private String username;

    @NotNull(message = "Parol bo'sh bo'lmasligi kerak !")
    private String password;
}
