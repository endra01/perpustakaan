package com.sinaukoding.perpustakaan.service;

import com.sinaukoding.perpustakaan.common.RestResult;
import com.sinaukoding.perpustakaan.common.StatusCode;
import com.sinaukoding.perpustakaan.dao.BaseDAO;
import com.sinaukoding.perpustakaan.dao.UserDAO;
import com.sinaukoding.perpustakaan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDAO dao;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected BaseDAO<User> getDAO() {
        return dao;
    }

    @Transactional
    public User register(User param, User.Role role){
        User reference = dao.findOne(new User(param.getUsername()));
        if (reference != null){
            return null;
        }else {
            param.setRole(role);
            param.setPassword(BCrypt.hashpw(param.getPassword(), BCrypt.gensalt()));
            dao.save(param);
            return param;
        }
    }

    @Transactional
    public RestResult login(User param){
        RestResult result = new RestResult(StatusCode.PASSWORD_OR_USER_NOT_REGISTERED);
        User curentUser = dao.findOne(param);
        if (curentUser != null){
            return result;
        }else if(curentUser.getPassword() != null && BCrypt.checkpw(param.getPassword(), curentUser.getPassword())) {
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(curentUser.getUsername(), curentUser.getPassword(), new ArrayList<>());
            curentUser.setToken(jwtTokenService.generateToken(userDetails));
            result.setData(curentUser);
            result.setStatus(StatusCode.LOGIN_SUCCESS);
        }else{
            result.setStatus(StatusCode.LOGIN_FAILED);
        }
        return result;
    }
}
