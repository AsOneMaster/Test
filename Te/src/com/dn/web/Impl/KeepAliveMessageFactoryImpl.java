package com.dn.web.Impl;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 
 * @author Administrator
 *
 *心跳连接
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
	  private static final String HEARTBEATREQUEST = "000";//预设请求内容
	   private static final String HEARTBEATRESPONSE = "111";//预设应答内容，客户端在接收到预设请求内容回复给服务端一定也是这个内容
    @Override
    public boolean isRequest(IoSession ioSession, Object o) {
        System.out.print("服务端判断消息是否为请求包消息: "+o);
        if (o.equals(HEARTBEATREQUEST))
            return true;
        return false;
    }

    @Override
    public boolean isResponse(IoSession session, Object message) {
        System.out.print("服务端判断响应心跳包信息: " + message);
        if(message.equals(HEARTBEATRESPONSE))
            return true;
        return false;
    }

    @Override
    public Object getRequest(IoSession session) {
        System.out.print("服务端发送给客户端的心跳包消息: " + HEARTBEATREQUEST);
        return HEARTBEATREQUEST;
    }

    @Override
    public Object getResponse(IoSession session, Object request) {
        System.out.print("响应预设信息: " + HEARTBEATRESPONSE);
        return HEARTBEATRESPONSE;
//      return null;
    }

}
