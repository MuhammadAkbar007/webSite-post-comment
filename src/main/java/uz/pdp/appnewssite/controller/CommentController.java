package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService service;

    @PreAuthorize("hasAuthority('ADD_COMMENT')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CommentDto dto) {
        ApiResponse response = service.add(dto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response.getMessage());
    }

    @GetMapping
    public ResponseEntity<?> get() {
        ApiResponse response = service.get();
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response.getObject());
    }

    @PreAuthorize("hasAuthority('EDIT_COMMENT')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody CommentDto dto, @PathVariable Long id, HttpServletRequest request) {
        ApiResponse response = service.edit(id, dto, request);
        return ResponseEntity.status(response.isSuccess() ? 202 : 404).body(response.getMessage());
    }

    @PreAuthorize("hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 204 : 409).body(response.getMessage());
    }

    @PreAuthorize("hasAuthority('DELETE_MY_COMMENT')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, HttpServletRequest request) {
        ApiResponse response = service.deleteMyComment(id, request);
        return ResponseEntity.status(response.isSuccess() ? 204 : 409).body(response.getMessage());
    }
}
