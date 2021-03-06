package com.lezijie.note.dao;

import com.lezijie.note.po.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User queryUserByName(String userName){
        String sql="select * from tb_user where uname=?";

        List<Object> params=new ArrayList<>();
        params.add(userName);
        User user=(User) BaseDao.queryRow(sql,params,User.class);

        return user;
    }

    /*public User queryUserByName02(String userName) {
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
    }*/
}
