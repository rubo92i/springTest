package am.basic.springTest.service.impl;


import am.basic.springTest.model.Comment;
import am.basic.springTest.repository.CommentRepository;
import am.basic.springTest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {





    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> getByUserId(int userId) {
        return commentRepository.getByUserId(userId);
    }


    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }


    @Override
    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> search(Comment sample) {
        return commentRepository.findAll(Example.of(sample));
    }



}
