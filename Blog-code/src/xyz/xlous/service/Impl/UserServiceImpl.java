package xyz.xlous.service.Impl;

import xyz.xlous.dao.Impl.UserDaoImpl;
import xyz.xlous.dao.UserDao;
import xyz.xlous.pojo.User;
import xyz.xlous.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userdao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return userdao.getUsers();
    }

    @Override
    public User login(User user) {
        return userdao.findUserByMobileAndPassword(user.getUserTel(),user.getPassword());
    }

    @Override
    public boolean addUser(User user) {
        return userdao.add(user);
    }

    @Override
    public void deleteUser(String mobile) {
        userdao.delete(mobile);
    }

    @Override
    public boolean searchUserByMobile(String mobile) {
        return userdao.searchUserByMobile(mobile);
    }

    @Override
    public boolean findUserByUsername(String username) {
        return userdao.findUserByUsername(username);
    }
}
