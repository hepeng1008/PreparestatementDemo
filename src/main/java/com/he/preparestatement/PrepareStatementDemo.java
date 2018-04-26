package com.he.preparestatement;

import java.sql.*;

public class PrepareStatementDemo {


    public static void main(String[] args) {

        //数据库连接
        Connection connection = null;

        //预编译的Statement，使用预编译的Statement提高数据库性能
        PreparedStatement preparedStatement = null;

        //结果集
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");

            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?&useServerPrepStmts=true&cachePrepStmts=true","root","root");

            //定义SQL语句
            String sql  = "select * from user where name = ?";

            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);

            //设置参数，第一个参数为sql语句中参数的序号(从1开始)，第二个参数为设置的参数值
            preparedStatement.setString(1,"aaa");

            //向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();

            preparedStatement.close();
            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, "bbb");
            resultSet =  preparedStatement.executeQuery();
            //遍历查询结果集
            while(resultSet.next()){
                System.out.println(resultSet.getString("id")+"  "+resultSet.getString("name"));
            }
            resultSet.close();
            preparedStatement.close();

            System.out.println("#############################");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
