package com.springapp.mvc.Controller;

import com.springapp.mvc.Entity.Comment;
import com.springapp.mvc.Entity.Dish;
import com.springapp.mvc.Repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
public class DishController {
    private DishRepository dishRepository;

    @Autowired
    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @RequestMapping(value = {"/dish/random"}, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<List<String>> getRandomDishes() {
        return dishRepository.getRandom20DishesJSON();
    }

    @RequestMapping(value = {"/dish/getAll"}, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<List<String>> getAllDishes() {
        return dishRepository.getAllDishesJSON();
    }

    @RequestMapping(value = {"/dish/commentDishById/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ArrayList<String>> printWelcome2(@PathVariable("id") int id) {
        Dish dish = dishRepository.getDishById(id);
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        for (Comment comment : dish.getComments()) {
            ArrayList<String> records = new ArrayList<String>();
            records.add(String.valueOf(String.valueOf(comment.getId())));
            records.add(comment.getComment());
            records.add(String.valueOf(comment.getDish().getId()));
            list.add(records);
        }
        return list;
    }

    @RequestMapping(value = {"/dish/getByIngredients/{ingredients}"}, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ArrayList<String>> getRandomDishes(@PathVariable("ingredients") String ingredients) {
        return dishRepository.getDishByIngredientsJSON(ingredients);
    }
}
