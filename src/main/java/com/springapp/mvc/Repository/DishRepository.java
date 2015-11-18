package com.springapp.mvc.Repository;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.springapp.mvc.Entity.Dish;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.*;

@Repository
@Transactional
public class DishRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Dish> getAllDish() {
        return this.sessionFactory.getCurrentSession().createQuery("from Dish").list();
    }

    public Dish getDishById(int id) {
        return (Dish) sessionFactory.getCurrentSession().get(Dish.class, id);
    }

    public List<Dish> getRandom20Dishes() {
        List<Integer> setForIndex = new ArrayList<Integer>();
        while (setForIndex.size() < 20) {
            setForIndex.add((int) (Math.random() * 1371) + 1);
        }
        Iterator iterator = setForIndex.iterator();
        List<Dish> dishSet = new ArrayList<Dish>();
        while (iterator.hasNext()) {
            dishSet.add(getDishById((Integer) iterator.next()));
        }
        return dishSet;
    }

    public String getRandom20DishesJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<Dish> dishSet = getRandom20Dishes();
        Iterator iterator = dishSet.iterator();
        try {
            while (iterator.hasNext()) {
                Dish dish = (Dish) iterator.next();
                JSONObject object = new JSONObject();
                object.put("idDish", String.valueOf(dish.getId()));
                object.put("nameOfDish", dish.getNameOfDish());
                object.put("href", dish.getHref());
                object.put("img", dish.getImg());
                object.put("steps", dish.getSteps());
                object.put("ingridients", dish.getIngredients());
                jsonArray.put(object);
            }
            jsonObject.put("array", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public String getAllDishesJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<Dish> dishSet = getAllDish();
        Iterator iterator = dishSet.iterator();
        try {
            while (iterator.hasNext()) {
                Dish dish = (Dish) iterator.next();
                JSONObject object = new JSONObject();
                object.put("idDish", String.valueOf(dish.getId()));
                object.put("nameOfDish", dish.getNameOfDish());
                object.put("href", dish.getHref());
                object.put("img", dish.getImg());
                object.put("steps", dish.getSteps());
                object.put("ingridients", dish.getIngredients());
                jsonArray.put(object);
                jsonObject.put("array", jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public String getDishByIngredientsJSON(String ingredients) {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/cook";
        String USER = "root";
        String PASS = "root";
        Connection conn = null;
        Statement stmt = null;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "select cook.dish.* from cook.dish,cook.dishingridient,cook.ingridient \n" +
                    "where cook.dish.idDish = dishingridient.idDish \n" +
                    "and dishingridient.idIngridient = ingridient.idIngridient\n" +
                    "and ingridient.idIngridient in(" + ingredients +
                    ") \n group by cook.dish.idDish\n" +
                    "order by cook.dish.countOfIngredient";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                JSONObject object = new JSONObject();
                object.put("idDish",String.valueOf(rs.getInt("idDish")));
                object.put("nameOfDish", rs.getString("nameOfDish"));
                object.put("href", rs.getString("href"));
                object.put("img", rs.getString("img"));
                object.put("steps", rs.getString("steps"));
                object.put("ingridients", rs.getString("ingridients"));
                jsonArray.put(object);
            }
            jsonObject.put("array",jsonArray);
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return jsonObject.toString();
    }

}
