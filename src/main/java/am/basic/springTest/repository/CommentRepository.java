package am.basic.springTest.repository;

import am.basic.springTest.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> getByUserId(int userId);

    Comment getById(int id);

}
