package com.springapp.mvc.Controller;

import com.springapp.mvc.Entity.Ingredient;
import com.springapp.mvc.Repository.IngredientRepository;
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
public class IngredientController {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @RequestMapping(value = {"/ingredient/getAll"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getAllIngredient() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (Ingredient ingredient : ingredientRepository.getAllIngredient()) {
                JSONObject object = new JSONObject();
                object.put("id", String.valueOf(ingredient.getId()));
                object.put("nameOfIngridient", ingredient.getNameOfIngredient());
                jsonArray.put(object);
            }
            jsonObject.put("array", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = {"/ingredient/getById/{id}"}, method = RequestMethod.GET, produces = {"text/html;charset=utf-8"})
    @ResponseBody
    public String getCategoryById(@PathVariable("id") int id) {
        Ingredient ingredient = ingredientRepository.getIngredientById(id);
        JSONObject object = new JSONObject();
        try {
            object.put("id", String.valueOf(ingredient.getId()));
            object.put("nameOfIngridient", ingredient.getNameOfIngredient());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

}
