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
	 * ��ʼ�����ݿ�����
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
    * ��ѯ����
    * @param sql sql���
    * @return ��ѯ���Ľ����
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
    * �������
    * @param sql sql���
    * @return ������
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
	    	System.out.println("�����Զ��ύװ̬��"+autoCommit);
	    	if(autoCommit) {
	    		//�ر��Զ��ύ
	    		con.setAutoCommit(false);
	    	}
	    	pre = con.prepareStatement(sql);
	    	for(int i = 0; i < dNum.size(); i++) {
	    		pre.setInt(1, dNum.get(i));
	    		pre.addBatch();
	    	}
	    	count = pre.executeBatch();
	    	//���˳�����ڴ��ύ
			con.commit();
			//�ָ�ԭ�������ύ״̬
			con.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("DB updateAll SQLError");
		} catch (Exception e) {
			if(con!=null) {
				try {
					//�ع�
					con.rollback();
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					System.out.println("DB updateAll rollback Error");
				}
			}
			System.out.println("DB updateAll Error");
		} 
		return count;
	}

   /**
    * �ر���Դ
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

