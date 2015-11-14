package com.springapp.mvc.Repository;

import com.springapp.mvc.Entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Category> getAllCategory() {
        return this.sessionFactory.getCurrentSession().createQuery("from Category").list();
    }

    public Category getCategory(int id) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
    }
}
