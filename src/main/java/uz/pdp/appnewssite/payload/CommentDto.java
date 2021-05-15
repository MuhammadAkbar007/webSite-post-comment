package uz.pdp.appnewssite.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    @NotNull(message = "Matn bo'sh bo'lmasligi kerak !")
    private String text;

    @NotNull(message = "Post tanlanmagan bo'lmasligi kerak !")
    private Long postId;
}
