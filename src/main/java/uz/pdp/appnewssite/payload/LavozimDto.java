package uz.pdp.appnewssite.payload;

import lombok.Data;
import uz.pdp.appnewssite.entity.enums.Huquq;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class LavozimDto {
    @NotBlank // probelni ham o'tkazib yubormasligi uchun
    private String name;

    private String description;

    @NotEmpty
    private List<Huquq> huquqList;
}
