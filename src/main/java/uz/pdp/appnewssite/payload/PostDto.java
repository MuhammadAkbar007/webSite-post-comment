package uz.pdp.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostDto {
    @NotNull(message = "Sarlavha bo'sh bo'lmasligi kerak !")
    private String title;

    @NotNull(message = "Matn bo'sh bo'lmasligi kerak !")
    private String text;

    @NotNull(message = "Url manzil bo'sh bo'lmasligi kerak !")
    private String url;
}
