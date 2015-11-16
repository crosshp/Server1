package com.springapp.mvc.Controller;

import com.springapp.mvc.Entity.Category;
import com.springapp.mvc.Entity.Comment;
import com.springapp.mvc.Repository.CategoryRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @RequestMapping(value = {"/category/getAll"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getAllCategory() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (Category category : categoryRepository.getAllCategory()) {
                JSONObject object = new JSONObject();
                object.put("id", String.valueOf(category.getId()));
                object.put("category", category.getCategory());
                jsonArray.put(object);
            }
            jsonObject.put("array", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = {"/category/getById/{id}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getCategoryById(@PathVariable("id") int id) {
        Category category = categoryRepository.getCategory(id);
        JSONObject object = new JSONObject();
        try {
            object.put("id", String.valueOf(category.getId()));
            object.put("category", category.getCategory());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    @RequestMapping(value = {"/category/getDishCategoryById/{id}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getDishCategoryById(@PathVariable("id") int id) {
        return categoryRepository.getDishCategoryByIdJSON(id);
    }

    @RequestMapping(value = {"/category/getDishByCategory/{category}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getDishByCategory(@PathVariable("category") int category) {
        return categoryRepository.getDishByCategoryJSON(category);
    }
}
