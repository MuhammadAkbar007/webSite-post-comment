package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Lavozim;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.repository.LavozimRepository;

@Service
public class LavozimService {
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse add(LavozimDto lavozimDto) {
        if (lavozimRepository.existsByName(lavozimDto.getName()))
            return new ApiResponse("Bunday lavozim mavjud !", false);
        Lavozim lavozim = new Lavozim(lavozimDto.getName(), lavozimDto.getHuquqList(), lavozimDto.getDescription());
        lavozimRepository.save(lavozim);
        return new ApiResponse("Lavozim muvaffaqqiyatli saqlandi !", true);
    }

    public ApiResponse edit(Long id, LavozimDto lavozimDto) {
        return new ApiResponse("Keldik", true);
    }
}
