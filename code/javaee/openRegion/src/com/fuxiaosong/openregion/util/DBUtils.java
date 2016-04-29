package com.fuxiaosong.openregion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DBUtils {
	//数据库驱动类名称 
//    private static String DRIVER = "com.mysql.jdbc.Driver";  
//    //连接字符串 
//    private static String URL = "jdbc:mysql://localhost:3306/region";  
//    //用户名 
//    private static String USERNAME = "root";  
//    //密码 
//    private static String PASSWORD = "556975";  
    //数据库驱动类名称 
    private static String DRIVER = null;  
    //连接字符串 
    private static String URL = null;  
    //用户名 
    private static String USERNAME = null;  
    //密码 
    private static String PASSWORD = null;  
  
    //创建数据库连接对象 
    private Connection connnection = null;  
    //创建PreparedStatement对象 
    private PreparedStatement preparedStatement = null;  
    //创建结果集对象 
    private ResultSet resultSet = null;  
  
    
    /**
     * 静态块,用于加载数据库驱动
     */
    static {  
        try {  
            
            
            PropertiesUtils propertiesUtils = new PropertiesUtils("/jdbc.properties");
            DRIVER = propertiesUtils.getProperty("jdbc.driver");
            URL = propertiesUtils.getProperty("jdbc.url");
            USERNAME = propertiesUtils.getProperty("jdbc.username");
            PASSWORD = propertiesUtils.getProperty("jdbc.password");
            
            propertiesUtils = null;
            
            // 加载数据库驱动程序  
            System.out.println("加载数据库驱动...");
            Class.forName(DRIVER); 
            
            
        } catch (ClassNotFoundException e) {  
            System.out.println("加载驱动错误: " + e.getMessage());  
        }
    }  
  
    /** 
     * 获取结果集，并将结果放在List中 
     *  
     * @param sql SQL语句 
     * @return List 结果List
     */  
    public ArrayList<Object> excuteQuery(String sql, Object[] params) { 
    	//打印SQL语句日志
    	writeExecutedSqlStatement(sql , params);
        // 执行SQL获得结果集  
        ResultSet rs = executeQueryRS(sql, params);  
        // 创建ResultSetMetaData对象  
        ResultSetMetaData rsmd = null;  
        // 结果集列数  
        int columnCount = 0;  
        try {  
            rsmd = rs.getMetaData();  
            // 获得结果集列数  
            columnCount = rsmd.getColumnCount();  
        } catch (SQLException e) {  
            System.out.println("数据库执行时异常： " + e.getMessage()); 
        }  
  
        // 创建List  
        ArrayList<Object> list = new ArrayList<Object>();  
  
        try {  
            // 将ResultSet的结果保存到List中  
            while (rs.next()) {  
                Map<String, Object> map = new HashMap<String, Object>();  
                for (int i = 1; i <= columnCount; i++) {  
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));  
                }  
                list.add(map);  
            }  
        } catch (SQLException e) {  
            System.out.println("数据库执行时异常： " + e.getMessage()); 
        } finally {  
            // 关闭所有资源  
            closeAll();  
        }  
        return list;  
    }  
    
    /** 
     * 建立数据库连接 
     * 
     * @return 数据库连接 
     */  
    private Connection getConnection() {  
        try {  
            // 获取连接  
            connnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);  
        } catch (SQLException e) {  
            System.out.println("获取数据库连接异常: " + e.getMessage());  
        }  
        return connnection;  
    }
  
    /** 
     * SQL 查询将查询结果直接放入ResultSet中 
     * 
     * @param sql SQL语句 
     * @param params 参数数组，若没有参数则为null 
     * @return 结果集 
     */  
    private ResultSet executeQueryRS(String sql, Object[] params) {
    	//打印SQL语句日志
    	writeExecutedSqlStatement(sql , params);
        try {  
            // 获得连接  
            connnection = this.getConnection();  
            // 调用SQL  
            preparedStatement = connnection.prepareStatement(sql);  
            // 参数赋值  
            if (params != null) {  
                for (int i = 0; i < params.length; i++) {  
                    preparedStatement.setObject(i + 1, params[i]);  
                }  
            }  
            // 执行  
            resultSet = preparedStatement.executeQuery();  
  
        } catch (SQLException e) {  
            System.out.println("数据库执行时异常： " + e.getMessage()); 
        }   
        return resultSet;  
    }  
      
    /** 
     * 关闭所有资源 
     */  
    private void closeAll() {  
        // 关闭结果集对象  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
        // 关闭PreparedStatement对象  
        if (preparedStatement != null) {  
            try {  
                preparedStatement.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
          
        // 关闭Connection 对象  
        if (connnection != null) {  
            try {  
                connnection.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }     
    }  
    
    /**
     * 打印SQL语句日志
     * 
     * @param sql SQL语句
     * @param params SQL语句的参数
     */
    private void writeExecutedSqlStatement(String sql , Object[] params){
    	if (params != null) {
	        for (Object ob : params) {
	        	//替换参数
	            String repStr; 
	            if (ob instanceof String){
	                repStr= "'" + ob.toString() + "'";
	            }else {
	                repStr = ob == null ? "" : ob.toString();
	            }
	            repStr = repStr.replace("\\", "\\\\");
	            repStr = repStr.replace("$", "\\$");
	            //替换完成
	            sql = sql.replaceFirst("\\?", repStr);
	        }
    	}
    	//打印日志
		System.out.println("SQL Statement : " + sql);
    }
}
