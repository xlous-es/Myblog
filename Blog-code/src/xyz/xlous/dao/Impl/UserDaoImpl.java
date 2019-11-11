package xyz.xlous.dao.Impl;

import xyz.xlous.dao.UserDao;
import xyz.xlous.pojo.User;
import xyz.xlous.utils.JDBCUitls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    /**
     * 根据用户id（用户的唯一标识），返回用户信息
     *
     * @param userId
     * @return
     */
    public User getUserInfo(int userId) {
        User user = new User();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUitls.getConnection();
            String sql = "select userid,usertel,username,regtime,lasttimelogin from `user` where userid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("userid"));
                user.setUserTel(rs.getString("usertel"));
                user.setUserName(rs.getString("username"));
                user.setRegTime(rs.getTimestamp("regtime"));
                user.setLastTimeLogin(rs.getTimestamp("lasttimelogin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUitls.close(rs, pstmt, conn);
        }
        return user;
    }

    // 更新用户信息
    public User updateUserInfo() {
        return null;
    }

    /**
     * 返回用户列表
     *
     * @return
     */
    @Override
    public List<User> getUsers() {
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> userlist = new ArrayList<User>();
        try {
            conn = JDBCUitls.getConnection();
            String sql = "select * from user";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("userid"));
                user.setUserTel(rs.getString("usertel"));
                user.setUserName(rs.getString("username"));
                user.setRegTime(rs.getTimestamp("regtime"));
                user.setLastTimeLogin(rs.getTimestamp("lasttimelogin"));
                userlist.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUitls.close(rs, pstmt, conn);
        }
        return userlist;
    }

    @Override
    public boolean add(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = JDBCUitls.getConnection();
            String sql = "insert user(mobile,username,password) values(?,?,?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user.getUserTel());
            pstmt.setString(2,user.getUserName());
            pstmt.setString(3,user.getPassword());
            pstmt.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUitls.close(rs,pstmt,conn);
            return flag;
        }
    }

    @Override
    public void delete(String mobile) {

    }

    @Override
    public User findUserByMobileAndPassword(String mobile, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = new User();
        try {
            conn = JDBCUitls.getConnection();
            String sql = "select * from user where mobile=? and password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,mobile);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            while (rs.next()){
                user.setUserId(rs.getInt("userid"));
                user.setUserTel(rs.getString("mobile"));
                user.setUserName(rs.getString("username"));
                user.setRegTime(rs.getTimestamp("regtime"));
                user.setLastTimeLogin(rs.getTimestamp("lasttimelogin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUitls.close(rs,pstmt,conn);
            return user;
        }
    }

    @Override
    public boolean searchUserByMobile(String mobile) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = JDBCUitls.getConnection();
            String sql = "select * from user where mobile=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,mobile);
            rs = pstmt.executeQuery();
            if (rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUitls.close(rs,pstmt,conn);
            return flag;
        }
    }

    @Override
    public boolean findUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = JDBCUitls.getConnection();
            String sql = "select * from user where username=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            rs = pstmt.executeQuery();
            if (rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUitls.close(rs,pstmt,conn);
            return flag;
        }
    }
}
