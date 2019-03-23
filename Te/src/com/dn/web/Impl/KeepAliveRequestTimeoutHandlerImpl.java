package com.dn.web.Impl;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

public class  KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler{
	   
	@Override
    public void keepAliveRequestTimedOut(KeepAliveFilter keepAliveFilter, IoSession ioSession) throws Exception {
     System.out.print("客户端下线了一个");
    	ioSession.close(true);
       
    }
}
