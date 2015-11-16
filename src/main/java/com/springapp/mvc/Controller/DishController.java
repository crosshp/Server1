package com.springapp.mvc.Controller;

import com.springapp.mvc.Entity.Comment;
import com.springapp.mvc.Entity.Dish;
import com.springapp.mvc.Repository.DishRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class DishController {
    private DishRepository dishRepository;

    @Autowired
    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @RequestMapping(value = {"/dish/random"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getRandomDishes() {
        return dishRepository.getRandom20DishesJSON();
    }

    @RequestMapping(value = {"/dish/getAll"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getAllDishes() {
        return dishRepository.getAllDishesJSON();
    }

    @RequestMapping(value = {"/dish/commentDishById/{id}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String commentDishById(@PathVariable("id") int id) {
        Dish dish = dishRepository.getDishById(id);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (Comment comment : dish.getComments()) {
                JSONObject object = new JSONObject();
                object.put("id", String.valueOf(comment.getId()));
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

    @RequestMapping(value = {"/dish/getByIngredients/{ingredients}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getRandomDishes(@PathVariable("ingredients") String ingredients) {
        return dishRepository.getDishByIngredientsJSON(ingredients);
    }
}
