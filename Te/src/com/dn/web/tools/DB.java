package com.dn.web.tools;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB {
	static String url="jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=true";
	static Connection con=null;
	static  PreparedStatement pre=null;
	static ResultSet result=null;
	static int num=0;
	/**
	 * 初始化数据库连接
	 */
   public static void init(){
	   try {
		   
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,"root","123456");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   /**
    * 查询操作
    * @param sql sql语句
    * @return 查询到的结果集
    */
   public static ResultSet executeQuery(String sql,Object ...o){
	   init(); 
	   try {
		pre = con.prepareStatement(sql);
		for(int i=0;i<o.length;i++){
			pre.setObject(i+1, o[i]);
		}
		
		result=pre.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return result;
   }
   
   /**
    * 插入操作
    * @param sql sql语句
    * @return 插入数
    */
   public static int  executeUpdate(String sql,Object ...o){
	   init(); 
	   try {
		pre = con.prepareStatement(sql);
		for(int i=0;i<o.length;i++){
			pre.setObject(i+1, o[i]);
		}
		
		num=pre.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return num;
   }
   public static int[] updateAll(String sql, List<Integer> dNum) {
		init();
		int[] count = null;
		try {
			boolean autoCommit = con.getAutoCommit();
	    	System.out.println("事务自动提交装态："+autoCommit);
	    	if(autoCommit) {
	    		//关闭自动提交
	    		con.setAutoCommit(false);
	    	}
	    	pre = con.prepareStatement(sql);
	    	for(int i = 0; i < dNum.size(); i++) {
	    		pre.setInt(1, dNum.get(i));
	    		pre.addBatch();
	    	}
	    	count = pre.executeBatch();
	    	//如果顺利，在此提交
			con.commit();
			//恢复原有事务提交状态
			con.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("DB updateAll SQLError");
		} catch (Exception e) {
			if(con!=null) {
				try {
					//回滚
					con.rollback();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					System.out.println("DB updateAll rollback Error");
				}
			}
			System.out.println("DB updateAll Error");
		} 
		return count;
	}

   /**
    * 关闭资源
    */
   public static void close(){
	   try {
		con.close();
		pre.close();
		result.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
   }
}

