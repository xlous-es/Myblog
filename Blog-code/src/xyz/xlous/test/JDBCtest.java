package xyz.xlous.test;

import org.junit.Test;
import xyz.xlous.utils.JDBCUitls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCtest {

    @Test
    public void jdbctest() throws SQLException {
//        System.out.println(JDBCUitls.getDataSource());
//        System.out.println(JDBCUitls.getConnection());

        Connection conn = JDBCUitls.getConnection();
        String sql = "select * from gg_user";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("user_id"));
            System.out.println(rs.getString("user_name"));
            System.out.println(rs.getString("user_password"));
            System.out.println(rs.getString("user_email"));
            System.out.println("============================");
        }


    }
}
