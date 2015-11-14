package com.springapp.mvc.Controller;

import com.springapp.mvc.Entity.Comment;
import com.springapp.mvc.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {
    private CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @RequestMapping(value = {"/comment/getAll"}, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ArrayList<String>> getAllComment() {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        for (Comment comment : commentRepository.getAllComment()) {
            ArrayList<String> records = new ArrayList<String>();
            records.add(String.valueOf(String.valueOf(comment.getId())));
            records.add(comment.getComment());
            records.add(String.valueOf(comment.getDish().getId()));
            list.add(records);
        }
        return list;
    }

    @RequestMapping(value = {"/comment/getById/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getCommentById(@PathVariable("id") int id) {
        Comment comment = commentRepository.getCommentById(id);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(String.valueOf(comment.getId()));
        arrayList.add(comment.getComment());
        arrayList.add(String.valueOf(comment.getDish().getId()));
        return arrayList;
    }


    @RequestMapping(value = {"/comment/getByDishId/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ArrayList<String>> getCommentByDishId(@PathVariable("id") int id) {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        List<Object[]> comments = commentRepository.getCommentByDishId(id);
        for(Object[] tuple : comments) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(tuple[0].toString());
            arrayList.add(tuple[1].toString());
            arrayList.add(tuple[2].toString());
            arrayLists.add(arrayList);
        }
        return arrayLists;
    }
}
