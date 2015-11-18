package com.springapp.mvc.Repository;

import com.springapp.mvc.Entity.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Comment> getAllComment() {
        return this.sessionFactory.getCurrentSession().createQuery("from Comment").list();
    }

    public Comment getCommentById(int id) {
        return (Comment) sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    public List<Object[]> getCommentByDishId(int id) {
        return (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery("select comment.id,comment.comment,comment.idDish FROM cook.comment,cook.dish where comment.idDish = dish.idDish" +
                " and dish.idDish = " + id + ";").list();
    }

    public void addNewComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }
}
