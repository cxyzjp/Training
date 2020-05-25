package com.cxy.server;

import com.cxy.thrift.Hello;
import com.cxy.thrift.impl.HelloThrift;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class ServerMain {

    public static void main(String[] args) {
        try {
            System.out.println("服务端启动....");
            //1.创建TProcessor
            TProcessor tprocessor = new Hello.Processor<Hello.Iface>(new HelloThrift());
            //2.创建TServerSocket,非阻塞式Socket,用于服务器端
            TServerSocket serverTransport = new TServerSocket(8091);
            //3.创建TProtocol
            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(factory);
            //4.创建TServer,传入需要的参数
            TServer server = new TSimpleServer(tArgs);
            //5.启动server
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
