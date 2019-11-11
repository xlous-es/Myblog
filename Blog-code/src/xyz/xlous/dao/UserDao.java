package xyz.xlous.dao;

import xyz.xlous.pojo.User;

import java.util.List;

/**
 * 用户操作的DAO
 */
public interface UserDao {

    // 返回用户列表
    public List<User> getUsers();
    // 添加用户
    public abstract boolean add(User user);
    // 删除用户
    public abstract void delete(String mobile);
    // 查询用户是否存在
    public abstract boolean searchUserByMobile(String mobile);
    // 根据用户名查询用户
    public abstract boolean findUserByUsername(String username);
    // 登录
    public abstract User findUserByMobileAndPassword(String mobile,String password);


}
