package com.lezijie.note.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(in);
            Class.forName(properties.getProperty("jdbcName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库链接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        String dbUrl = properties.getProperty("dbUrl");
        String dbName = properties.getProperty("dbName");
        String dbPwd = properties.getProperty("dbPwd");

        try {
            connection = DriverManager.getConnection(dbUrl, dbName, dbPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭资源
     * @param resultSet
     * @param preparedStatement
     * @param connection
     * @return
     */
    public static void closeConnection(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
