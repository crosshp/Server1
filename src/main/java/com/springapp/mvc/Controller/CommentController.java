package com.springapp.mvc.Controller;

import com.springapp.mvc.Entity.Comment;
import com.springapp.mvc.Repository.CommentRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {
    private CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @RequestMapping(value = {"/comment/getAll"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getAllComment() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (Comment comment : commentRepository.getAllComment()) {
                JSONObject object = new JSONObject();
                object.put("id", String.valueOf(String.valueOf(comment.getId())));
                object.put("comment", comment.getComment());
                object.put("idDish", String.valueOf(comment.getIdDish()));
                jsonArray.put(object);
            }
            jsonObject.put("array", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    @RequestMapping(value = {"/comment/getById/{id}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getCommentById(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        Comment comment = commentRepository.getCommentById(id);
        try {
            jsonObject.put("id", String.valueOf(comment.getId()));
            jsonObject.put("comment", comment.getComment());
            jsonObject.put("idDish", String.valueOf(comment.getIdDish()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    @RequestMapping(value = {"/comment/getByDishId/{id}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getCommentByDishId(@PathVariable("id") int id) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<Object[]> comments = commentRepository.getCommentByDishId(id);
        try {
            for (Object[] tuple : comments) {
                JSONObject object = new JSONObject();
                object.put("id", tuple[0].toString());
                object.put("comment", tuple[1].toString());
                object.put("idDish", tuple[2].toString());
                jsonArray.put(object);
            }
            jsonObject.put("array", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    @RequestMapping(value = {"/comment/addNewComment/{id}/{comment}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getAllComment(@PathVariable("comment") String comment, @PathVariable("id") int id) {
        Comment newComment = new Comment(comment, id);
        commentRepository.addNewComment(newComment);
        return "Add new comment";
    }
}
