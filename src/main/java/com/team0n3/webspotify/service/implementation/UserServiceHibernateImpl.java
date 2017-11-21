/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.UserDAO;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.UserService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author JSCHA
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceHibernateImpl implements UserService{
    private final static Logger LOGGER = Logger.getLogger("UserService");
    @Autowired
    private UserDAO userDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public User login(String username, String password) {
        User user= userDao.getUser(username);
        if(user==null){
            return null;
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceHibernateImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.update(user.getSalt());
        md.update(password.getBytes());
        byte hashedPass[] = md.digest();
        if(!Arrays.equals(user.getPassword(), hashedPass)){
            return null;
        }
        return user;
    }
    
    @Transactional(readOnly = false)
    @Override
    public User signup(String username, String password, String email) {
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[12];
        MessageDigest md = null;
        if(null!=userDao.getUser(username)){
            System.out.println("broken name");
            return null;
        }
       try{
            InternetAddress internetAddress = new InternetAddress(email);//Create new internet address with the given email
            internetAddress.validate(); //will error is invalid
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        if(userDao.findByEmail(email)!=null){
            System.out.println("broken email");
            return null;
        }
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceHibernateImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        random.nextBytes(salt);
        md.update(salt);
        md.update(password.getBytes());
        byte hashedPass[]=md.digest();
        User user= new User(username, email, hashedPass, salt);
        userDao.addUser(user);
        return user;
    }
    
}
