package com.cxy.server;

import com.cxy.thrift.Hello;
import com.cxy.thrift.impl.HelloThrift;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class MultiplexedServerMain {

    public static void main(String[] args) {
        try {
            System.out.println("多接口服务，服务端开启....");
            TMultiplexedProcessor processor = new TMultiplexedProcessor();
            processor.registerProcessor("HelloServer", new Hello.Processor<Hello.Iface>(new HelloThrift()));

            // 简单的单线程服务模型
            TServerSocket serverTransport = new TServerSocket(8091);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(processor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
