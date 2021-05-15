package uz.pdp.appnewssite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByPostAndText(Post post, String text);
}
