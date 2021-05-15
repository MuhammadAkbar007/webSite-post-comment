package uz.pdp.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "FISH bo'sh bo'lmasligi kerak !")
    private String fullName;

    @NotNull(message = "Username bo'sh bo'lmasligi kerak !")
    private String username;

    @NotNull(message = "Parol bo'sh bo'lmasligi kerak !")
    private String password;

    @NotNull(message = "Lavozim bo'sh bo'lmasligi kerak !")
    private Integer lavozimId;
}
