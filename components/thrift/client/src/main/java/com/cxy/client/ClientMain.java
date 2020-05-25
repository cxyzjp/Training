package com.cxy.client;

import com.cxy.thrift.Hello;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ClientMain {

    public static void main(String[] args) {
        System.out.println("客户端启动....");
        TTransport transport = null;
        try {
            //1.创建TTransport
            transport = new TSocket("localhost", 8091, 30000);
            //2.创建TProtocol  协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            //3.创建Client
            Hello.Client client = new Hello.Client(protocol);
            //4.打开transport
            transport.open();
            //5.调用Client响应方法
            String result = client.helloString("hi");
            System.out.println(result);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}
