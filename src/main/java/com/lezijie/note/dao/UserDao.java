package com.lezijie.note.dao;

import com.lezijie.note.po.User;
import com.lezijie.note.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public User queryUserByName(String userName) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "select * from tb_user where uname=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUname(userName);
                user.setHead(resultSet.getString("head"));
                user.setMood(resultSet.getString("mood"));
                user.setNick(resultSet.getString("nick"));
                user.setUpwd(resultSet.getString("upwd"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(resultSet, preparedStatement, connection);
        }
        return user;
    }
}
