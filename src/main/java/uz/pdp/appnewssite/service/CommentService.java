package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.repository.CommentRepository;
import uz.pdp.appnewssite.repository.PostRepository;
import uz.pdp.appnewssite.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository repository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    public ApiResponse add(CommentDto dto) {
        Optional<Post> optionalPost = postRepository.findById(dto.getPostId());
        if (!optionalPost.isPresent()) return new ApiResponse("Post not found !", false);
        if (repository.existsByPostAndText(optionalPost.get(), dto.getText()))
            return new ApiResponse("Such comment in this post already exists !", false);
        repository.save(new Comment(dto.getText(), optionalPost.get()));
        return new ApiResponse("New comment successfully added !", true);
    }

    public ApiResponse get() {
        List<Comment> all = repository.findAll();
        if (all.isEmpty()) return new ApiResponse("No", false, "No comments in this post !");
        return new ApiResponse("Yes", true, all);
    }

    public ApiResponse edit(Long id, CommentDto dto, HttpServletRequest request) {
        Optional<Comment> optionalComment = repository.findById(id);
        if (!optionalComment.isPresent()) return new ApiResponse("Comment not found !", false);
        Comment comment = optionalComment.get();
        User createdUser = comment.getCreatedBy();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (createdUser != user) return new ApiResponse("This is not your comment !", false);
        comment.setText(dto.getText());
        repository.save(comment);
        return new ApiResponse("Comment successfully edited !", true);
    }

    public ApiResponse delete(Long id) {
        try {
            repository.deleteById(id);
            return new ApiResponse("Comment successfully deleted !", true);
        } catch (Exception e) {
            return new ApiResponse("Error while deleting !", false);
        }
    }

    public ApiResponse deleteMyComment(Long id) {
        try {
            Optional<Comment> optionalComment = repository.findById(id);
            if (!optionalComment.isPresent()) return new ApiResponse("Comment not found !", false);
            Comment comment = optionalComment.get();
            User createdUser = comment.getCreatedBy();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (createdUser != user) return new ApiResponse("This is not your comment !", false);
            repository.deleteById(id);
            return new ApiResponse("Comment successfully deleted !", true);
        } catch (Exception e) {
            return new ApiResponse("Error while deleting !", false);
        }
    }
}
