package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository repository;

    public ApiResponse add(PostDto dto) {
        if (repository.existsByTitleAndUrl(dto.getTitle(), dto.getUrl()))
            return new ApiResponse("Such post already exists !", false);
        repository.save(new Post(dto.getTitle(), dto.getText(), dto.getUrl()));
        return new ApiResponse("New post successfully added !", true);
    }

    public ApiResponse get() {
        List<Post> all = repository.findAll();
        if (all.isEmpty()) return new ApiResponse("No post!", false, "No post!");
        return new ApiResponse("Have", true, all);
    }

    public ApiResponse edit(Long id, PostDto dto) {
        Optional<Post> optionalPost = repository.findById(id);
        if (!optionalPost.isPresent()) return new ApiResponse("Post not found !", false);
        Post post = optionalPost.get();
        post.setTitle(dto.getTitle());
        post.setText(dto.getText());
        post.setUrl(dto.getUrl());
        repository.save(post);
        return new ApiResponse("Post successfully edited !", true);
    }

    public ApiResponse delete(Long id) {
        try {
            repository.deleteById(id);
            return new ApiResponse("Post successfully deleted !", true);
        } catch (Exception e) {
            return new ApiResponse("Error while deleting !", false);
        }
    }
}
