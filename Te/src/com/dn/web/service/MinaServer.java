package com.dn.web.service;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dn.web.Impl.KeepAliveMessageFactoryImpl;
import com.dn.web.Impl.KeepAliveRequestTimeoutHandlerImpl;

import net.sf.json.JSONObject;

public class MinaServer {
    /** 30���ʱ */
    private static final int IDELTIMEOUT = 30;
    /** 15�뷢��һ�������� */
    private static final int HEARTBEATRATE = 15;
    /** ���������� */
  
	private static final Logger log = LoggerFactory.getLogger(MinaServer.class);
	public static Map<String, IoSession> usersMap; 
	static int num;
	static ArrayList<String> info= new ArrayList<String>();
	public static void main(String[] args) {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", 
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		  //������⿪ʼ
        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();

		KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();
		KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,
					IdleStatus.BOTH_IDLE, heartBeatHandler);
        //heartBeat.setRequestTimeout(IDELTIMEOUT);
		   //�����Ƿ�forward����һ��filter
        heartBeat.setForwardEvent(true);
        //��������Ƶ��
        heartBeat.setRequestInterval(HEARTBEATRATE);
        //����ʧ�ܴ���handler
        
        acceptor.getFilterChain().addLast("heartbeat", heartBeat);
      //����������
		acceptor.setHandler(new ServerHandler());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		try {
			acceptor.bind(new InetSocketAddress(9898));
			 usersMap=new HashMap<String, IoSession>();
			System.out.println("mina server listen at port	9898.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

    private static class ServerHandler extends IoHandlerAdapter{
    	@Override
    	public void sessionCreated(IoSession session) throws Exception {
    		super.sessionCreated(session);
    	}
    	
    	@Override
    	public void sessionOpened(IoSession session) throws Exception {
    		super.sessionOpened(session);
			System.out.println("one client connected.");
			log.info("one client connected.");
    	}
    	

		@SuppressWarnings("deprecation")
		@Override
    	public void messageReceived(IoSession session, Object message)
    			throws Exception {
    			String str = message.toString();
    	
    				JSONObject lObject = new JSONObject();
        			JSONObject jsonOb = JSONObject.fromObject(str);
        			
        			System.out.println(jsonOb.toString()); 
        	        /*System.out.println("Message From " + info[0]);      
        	        System.out.println("Message To " + info[1]);*/
        		     if(jsonOb.getString("client").equals("0")){
         	        	MinaServer.usersMap.put(jsonOb.getString("name"), session);
         	        	System.out.println("�û���"+jsonOb.getString("name")+"���ѵ�¼");
         	        	num=usersMap.size();
         	        	
         	        	System.out.println(num);
         	        	info.add(jsonOb.getString("name"));
         	        }
        		     if(jsonOb.getString("client").equals("yes")) {
        	        	System.out.println("�յ������Ϣ");
    	    	      
    	    	         	if(num==1){
    	    	         		MinaServer.usersMap.get(info.get(0).toString( )).write("����δ����");
    	        	        	System.out.println("server :����δ���ߣ�");
    	        	        } else {
    	        	        	
    							    for(int i=0;i<num;i++) {
    			        	        	
    			        	        	if(jsonOb.getString("Userid").equals(info.get(i))) {
    			        	        		continue;
    			        	        	}else {
    			        	        		System.out.println("sos!!!!!!!!----"+jsonOb.getString("firstSend"));
    			        	        	
    										
    			        	        		lObject.put("firstSend", jsonOb.getString("firstSend"));
    			        	        		
    			        	        		lObject.put("lo",jsonOb.getDouble("lo"));
    				        	        	lObject.put("la",jsonOb.getDouble("la"));
    				        	        	lObject.put("safe", "no");
    				        	        	lObject.put("situation", "���ĺ�����Ҫ�����ػ�!");
    				        	        	MinaServer.usersMap.get(info.get(i).toString()).write(lObject.toString());
    									}
    							    
    							}
    		        	    
    	    	        }	    	        
        	        }
        		     if(jsonOb.getString("client").equals("no")) {
         	        	System.out.println("�ػ����");   	
     	        	        	    for(int i=0;i<num;i++) {
     			        	        	
     			        	        	if(jsonOb.getString("Userid").equals(info.get(i))) {
     			        	        		continue;
     			        	        	}else {
     			        	        		System.out.println("safe");
     			        	        		lObject.put("firstSend", "no");
     			        	        		lObject.put("safe","yes");
     			        	        		lObject.put("situation", "���ĺ��Ѱ�ȫ����Ŀ�ĵ�!");
     				        	        	MinaServer.usersMap.get(info.get(i).toString()).write(lObject.toString());
     									}
     							    }	
     	        	        		
     							

     		        	    
     	    	        }	        
        		     if(jsonOb.getString("client").equals("event")) {
        		 	    for(int i=0;i<num;i++) {
		        	    
		        	        	if(jsonOb.getString("Userid").equals(info.get(i))) {
		        	        		
		        	        		continue;
		        	        	}else {
		        	        		System.out.println("Ⱥ����");
		        	        		 lObject.put("event","event");
		        	                 lObject.put("eventMsg",jsonOb.getString("eventMsg"));
			        	        	MinaServer.usersMap.get(info.get(i).toString()).write(lObject.toString());
								}
						    }	
        		     }
    			
    	        
    	        
    	      
    	       
    	        //���ܿͻ����ַ���"quit"�رյ�ǰ�Ự����  
    	        if(str.trim().equalsIgnoreCase("quit")){  
    	            session.close();
    	        }  
    	}
    	
    	@Override
    	public void messageSent(IoSession session, Object message)
    			throws Exception {
    		super.messageSent(session, message);
    	}
    	
        public boolean isJson(String content) {
            try {
                JSONObject.fromObject(content);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
    

}
