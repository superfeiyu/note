package com.lezijie.note.dao;

import com.lezijie.note.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {

    /**
     * 通用 添加，修改，删除操作
     *
     * @param sql
     * @param params
     * @return
     */
    public static int executeUpdate(String sql, List<Object> params) {
        int row = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            row = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(null, preparedStatement, connection);
        }
        return row;
    }

    /**
     * 查询一条记录
     *
     * @param sql
     * @param params
     * @return
     */
    public static Object findSingleValue(String sql, List<Object> params) {
        Object object = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                object = resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(resultSet, preparedStatement, connection);
        }
        return resultSet;
    }


    /**
     * 查询集合 （JavaBean中字段与数据库表字段对应）
     *
     * @param sql
     * @param params
     * @param cls
     * @return
     */
    public static List queryRows(String sql, List<Object> params, Class cls) {
        List list = new ArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int filedNum = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Object object = cls.newInstance();
                for (int i = 1; i < filedNum; i++) {
                    String columName = resultSetMetaData.getColumnLabel(i);
                    Field field = cls.getDeclaredField(columName);
                    String setMethod = "set" + columName.substring(0, 1).toUpperCase() + columName.substring(1);
                    Method method = cls.getDeclaredMethod(setMethod, field.getType());
                    Object value = resultSet.getObject(columName);
                    method.invoke(object, value);
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(resultSet, preparedStatement, connection);
        }
        return list;
    }

    /**
     * 查询一条记录
     *
     * @param sql
     * @param params
     * @param cls
     * @return
     */
    public static Object queryRow(String sql, List<Object> params, Class cls) {
        List list = queryRows(sql, params, cls);
        Object object = null;
        if (list != null && list.size() > 0) {
            object = list.get(0);
        }
        return object;
    }
}
