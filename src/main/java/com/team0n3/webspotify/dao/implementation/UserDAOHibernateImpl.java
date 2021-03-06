
package com.team0n3.webspotify.dao.implementation;

import java.util.List;
import com.team0n3.webspotify.dao.UserDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import com.team0n3.webspotify.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class UserDAOHibernateImpl implements UserDAO{
    
  @Autowired
  private SessionFactory sessionFactory;
  
  @Value("${user.maxResult}")
  private int maxResults;
  public UserDAOHibernateImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void addUser(User user) {
    sessionFactory.getCurrentSession().persist(user);
  }

  @Override
  public User getUser(String username) {
    User user = (User)sessionFactory.getCurrentSession().get(User.class, username);
    
    if(user != null){
      Hibernate.initialize(user.getCreatedPlaylists());
      Hibernate.initialize(user.getFollowedPlaylists());
      Hibernate.initialize(user.getFollowedAlbums());
      Hibernate.initialize(user.getFollowedArtists());
      Hibernate.initialize(user.getFollowedSongs());
      Hibernate.initialize(user.getFollowing());
       Hibernate.initialize(user.getFollowers());
    }
    return user;
  }

  @Override
  public List<User> listUsers() {
    List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();
    return userList;
  }

  @Override
  public void updateUser(User user) {
    sessionFactory.getCurrentSession().merge(user);
  }

  @Override
  public void deleteUser(User user) {
    sessionFactory.getCurrentSession().delete(user);
  }

  @Override
  public User findByEmail(String email) {
    Criteria cr = sessionFactory.getCurrentSession().createCriteria(User.class);
    cr.add(Restrictions.eq("email", email));
    List results = cr.list();
    if(results.size()==0){
      return null;
    }
    User user = (User) results.get(0);
    return user;
  } 
  
  @Override
  public List<User> search(String keyword,boolean limit){
    Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
    c.add(Restrictions.like("username", "%"+keyword+"%"));
    if(limit){
      c.setMaxResults(maxResults);
    }
    return c.list();
  }
}
