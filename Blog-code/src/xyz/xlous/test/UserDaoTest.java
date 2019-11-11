package xyz.xlous.test;

import org.junit.Test;
import xyz.xlous.dao.Impl.UserDaoImpl;
import xyz.xlous.dao.UserDao;
import xyz.xlous.pojo.User;

public class UserDaoTest {

    /**
     * UserDaoImpl：返回用户信息
     */
    @Test
    public void getUserInfoTest(){
        UserDaoImpl userdao = new UserDaoImpl();
        User user = userdao.getUserInfo(1001);
        System.out.println(user.getUserId());
        System.out.println(user.getUserTel());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getRegTime());
        System.out.println(user.getLastTimeLogin());
    }

    @Test
    public void addTest(){
        UserDao userdao = new UserDaoImpl();
        User user = new User();
        user.setUserName("123");
        user.setUserTel("15988815910");
        user.setPassword("123456");
        boolean bool = userdao.add(user);
        System.out.println(bool);
    }


}
