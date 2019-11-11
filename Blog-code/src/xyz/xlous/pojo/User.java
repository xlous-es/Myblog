package xyz.xlous.pojo;

import java.sql.Timestamp;

public class User {
   private int userId;
   private String userTel;
   private String userName;
   private String password;
   private Timestamp regTime;
   private Timestamp lastTimeLogin;

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public String getUserTel() {
      return userTel;
   }

   public void setUserTel(String userTel) {
      this.userTel = userTel;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Timestamp getRegTime() {
      return regTime;
   }

   public void setRegTime(Timestamp regTime) {
      this.regTime = regTime;
   }

   public Timestamp getLastTimeLogin() {
      return lastTimeLogin;
   }

   public void setLastTimeLogin(Timestamp lastTimeLogin) {
      this.lastTimeLogin = lastTimeLogin;
   }

   @Override
   public String toString() {
      return "User{" +
              "userId=" + userId +
              ", userTel='" + userTel + '\'' +
              ", userName='" + userName + '\'' +
              ", password='" + password + '\'' +
              ", regTime=" + regTime +
              ", lastTimeLogin=" + lastTimeLogin +
              '}';
   }
}
