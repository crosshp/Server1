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

@Controller
public class CategoryController {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    @RequestMapping(value = { "/category/getAll"},method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ArrayList<String>> getAllCategory() {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        for(Category category: categoryRepository.getAllCategory()) {
            ArrayList<String> records = new ArrayList<String>();
            records.add(String.valueOf(category.getId()));
            records.add(category.getCategory());
            list.add(records);
        }
        return list;
    }

    @RequestMapping(value = { "/category/getById/{id}"},method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getCategoryById(@PathVariable("id") int id) {
        Category category = categoryRepository.getCategory(id);
        ArrayList<String> list = new ArrayList<String>();
        list.add(String.valueOf(category.getId()));
        list.add(category.getCategory());
        return list;
    }
}
