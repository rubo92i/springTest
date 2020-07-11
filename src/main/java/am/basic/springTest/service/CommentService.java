package am.basic.springTest.service;

import am.basic.springTest.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getByUserId(int userId) ;

    void add(Comment comment) ;

    void delete(int id) ;

    List<Comment> search(Comment sample);

}
