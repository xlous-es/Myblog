package xyz.xlous.service;

import xyz.xlous.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param user
     * @return
     */
    public abstract User login(User user);

    /**
     * 添加用户
     * @param user
     */
    public abstract boolean addUser(User user);

    /**
     * 根据登录手机号删除用户
     * @param mobile
     */
    public abstract void deleteUser(String mobile);

    /**
     * 根据手机号查询用户是否已存在
     * @param mobile
     * @return
     */
    public abstract boolean searchUserByMobile(String mobile);

    /**
     * 根据用户名查询用户是否已存在
     * @param username
     * @return
     */
    public abstract boolean findUserByUsername(String username);

}
