package am.basic.springTest.controller;

import am.basic.springTest.model.Comment;
import am.basic.springTest.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class TestControllerV2 {

    @Autowired
    private CommentRepository commentRepository;


    @GetMapping(value = "/comments",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllComments(){
        List<Comment> comments = commentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(comments);
     }


    @GetMapping(value = "/comments/byId")
    public ResponseEntity getAllComments(@RequestParam int id){
        Comment comment = commentRepository.getById(id);
        if (comment == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }


    @PostMapping("/comments")
    public ResponseEntity add(@RequestBody Comment comment){
        commentRepository.save(comment);
       // return new ResponseEntity(comment,HttpStatus.OK);
        return ResponseEntity
                .status(200)
                .body(comment);
    }

    @PostMapping("/comments/form")
    public ResponseEntity add(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam int userId){
        Comment comment = new Comment();
        comment.setName(name);
        comment.setDescription(description);
        comment.setUserId(userId);
        commentRepository.save(comment);
        return ResponseEntity.status(200).body(comment);
    }
}
