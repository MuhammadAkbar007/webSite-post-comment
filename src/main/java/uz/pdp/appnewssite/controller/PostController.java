package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService service;

    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody PostDto dto) {
        ApiResponse response = service.add(dto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response.getMessage());
    }

    @GetMapping
    public ResponseEntity<?> get() {
        ApiResponse response = service.get();
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response.getObject());
    }

    @PreAuthorize("hasAuthority('EDIT_POST')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody PostDto dto, @PathVariable Long id) {
        ApiResponse response = service.edit(id, dto);
        return ResponseEntity.status(response.isSuccess() ? 202 : 404).body(response.getMessage());
    }

    @PreAuthorize("hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 204 : 409).body(response.getMessage());
    }
}
