package com.dn.web.Impl;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 
 * @author Administrator
 *
 *��������
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
	  private static final String HEARTBEATREQUEST = "000";//Ԥ����������
	   private static final String HEARTBEATRESPONSE = "111";//Ԥ��Ӧ�����ݣ��ͻ����ڽ��յ�Ԥ���������ݻظ��������һ��Ҳ���������
    @Override
    public boolean isRequest(IoSession ioSession, Object o) {
        System.out.print("������ж���Ϣ�Ƿ�Ϊ�������Ϣ: "+o);
        if (o.equals(HEARTBEATREQUEST))
            return true;
        return false;
    }

    @Override
    public boolean isResponse(IoSession session, Object message) {
        System.out.print("������ж���Ӧ��������Ϣ: " + message);
        if(message.equals(HEARTBEATRESPONSE))
            return true;
        return false;
    }

    @Override
    public Object getRequest(IoSession session) {
        System.out.print("����˷��͸��ͻ��˵���������Ϣ: " + HEARTBEATREQUEST);
        return HEARTBEATREQUEST;
    }

    @Override
    public Object getResponse(IoSession session, Object request) {
        System.out.print("��ӦԤ����Ϣ: " + HEARTBEATRESPONSE);
        return HEARTBEATRESPONSE;
//      return null;
    }

}
