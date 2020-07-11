package am.basic.springTest.controller;

import am.basic.springTest.model.Comment;
import am.basic.springTest.model.User;
import am.basic.springTest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static am.basic.springTest.util.constants.Messages.INTERNAL_ERROR_MESSAGE;
import static am.basic.springTest.util.constants.Pages.COMMENT_PAGE;
import static am.basic.springTest.util.constants.ParameterKeys.MESSAGE_ATTRIBUTE_KEY;

@Controller
@RequestMapping("/secure")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    //@RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ModelAndView getCommentPage(@SessionAttribute("user") User user) {
        try {
            List<Comment> comments = commentService.getByUserId(user.getId());
            return new ModelAndView(COMMENT_PAGE, "comments", comments);
        } catch (RuntimeException exception) {
            return new ModelAndView(COMMENT_PAGE, MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
        }
    }

    @PostMapping("/comments/add")
    //@RequestMapping(value = "/comments/add", method = RequestMethod.POST)
    public ModelAndView add(@SessionAttribute("user") User user, @RequestParam String name, @RequestParam String description) {
        try {
            Comment comment = new Comment();
            comment.setName(name);
            comment.setDescription(description);
            comment.setUserId(user.getId());
            commentService.add(comment);

            return getCommentPage(user);
        } catch (RuntimeException exception) {
            return new ModelAndView(COMMENT_PAGE, MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
        }
    }


    @PostMapping("/comments/edit")
    //@RequestMapping(value = "/comments/delete", method = RequestMethod.POST)
    public ModelAndView delete(@SessionAttribute("user") User user,
                               @RequestParam Integer id,
                               @RequestParam String name,
                               @RequestParam String description,
                               @RequestParam String submit) {
        try {
            if (submit.equalsIgnoreCase("DELETE")){
                commentService.delete(id);
            }else {
                Comment comment = new Comment();
                comment.setUserId(user.getId());
                comment.setId(id);
                comment.setName(name);
                comment.setDescription(description);
                commentService.add(comment);
            }

            return getCommentPage(user);
        } catch (RuntimeException exception) {
            return new ModelAndView(COMMENT_PAGE, MESSAGE_ATTRIBUTE_KEY, INTERNAL_ERROR_MESSAGE);
        }
    }




}
