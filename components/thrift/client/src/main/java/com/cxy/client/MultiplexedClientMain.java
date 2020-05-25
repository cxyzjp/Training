package com.cxy.client;

import com.cxy.thrift.Hello;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class MultiplexedClientMain {

    public static void main(String[] args) {
        System.out.println("多接口服务，客户端启动....");
        TTransport transport = null;
        try {
            transport = new TSocket("localhost", 8091, 30000);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);

            // 调用HelloServer服务接口
            TMultiplexedProtocol helloServer = new TMultiplexedProtocol(protocol,"HelloServer");
            Hello.Client client = new Hello.Client(helloServer);

            transport.open();
            System.out.println(client.helloString("hi"));
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}
