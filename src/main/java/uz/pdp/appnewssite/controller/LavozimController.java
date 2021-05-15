package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.aop.HuquqniTekshirish;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LavozimDto;
import uz.pdp.appnewssite.service.LavozimService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {

    @Autowired
    LavozimService lavozimService;

    @PreAuthorize("hasAnyAuthority('ADD_LAVOZIM')")
    @PostMapping
    public HttpEntity<?> addLavozim(@Valid @RequestBody LavozimDto lavozimDto){
        ApiResponse apiResponse = lavozimService.add(lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse.getMessage());
    }

    @HuquqniTekshirish(huquq = "EDIT_LAVOZIM")
//    @PreAuthorize("hasAnyAuthority('EDIT_LAVOZIM')")
    @PutMapping("/{id}")
    public HttpEntity<?> editLavozim(@Valid @RequestBody LavozimDto lavozimDto, @PathVariable Long id){
        ApiResponse apiResponse = lavozimService.edit(id, lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse.getMessage());
    }
}
