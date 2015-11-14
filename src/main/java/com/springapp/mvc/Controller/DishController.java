package com.springapp.mvc.Controller;

import com.springapp.mvc.Entity.Category;
import com.springapp.mvc.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Controller
public class DishController {
    private CategoryRepository categoryRepository;

    @Autowired
    public DishController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @RequestMapping(value = { "/edit/{string}"},method = RequestMethod.GET)
     @ResponseBody
     public ArrayList<Set<String>> printWelcome1(@PathVariable("string") String id) {
        Set<String> records = new HashSet<String>();
        for(Category category: categoryRepository.getAllCategory()) {
            records.add(category.getCategory());
        }
        Set<String> records1 = new HashSet<String>();
        for(Category category: categoryRepository.getAllCategory()) {
            records.add(category.getCategory());
        }
        ArrayList<Set<String>> ser = new ArrayList<Set<String>>();
        ser.add(records);
        ser.add(records1);
        return ser;
    }

    @RequestMapping(value = { "/id/{string}"},method = RequestMethod.GET)
    @ResponseBody
    public String printWelcome2(@PathVariable("string") int id) {
        Category category = categoryRepository.getCategory(id);
        return ((Integer)category.getId()).toString()+"  "+category.getCategory();
    }
}
