package com.itstudent.springbootdemo.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: MySocketServer
 * @author: wenqin.zhao
 * @createDate: 2020/9/10 15:17
 * @description: TODO
 */
@Slf4j
public class MySocketServer {

    private static ServerSocket serverSocket = null;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    private static boolean stop = false;
    /**
     * @description: Socket服务端监听
     * @param:
     * @return:
     * @author: wenqin.zhao
     * @createDate: 15:23 2020/9/10
     */
//    private static void listen(String hostName,Integer port){
//        Socket socket = null;
//        try {
//            serverSocket = new ServerSocket();
//            serverSocket.bind(new InetSocketAddress(hostName,port));
//            log.info("server listen on {}:{}",hostName,port);
//            socket = serverSocket.accept();
//            log.info("a client connected");
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//            String message = dis.readUTF();
//            System.out.println(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * @description: 自定义协议的实现
     * @param:
     * @return:
     * @author: wenqin.zhao
     * @createDate: 16:22 2020/9/10
     */
    public static void listen(String hostName, Integer port) {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(hostName, port));
            log.info("server listen on {}:{}", hostName, port);
            Socket socket = serverSocket.accept();
            log.info("a client connection");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (!stop) {
                //读取前字节流前两个字节
                short length = dis.readShort();
                byte[] message = new byte[length];
                dis.readFully(message);
                String decodeMessage = new String(message, Charset.forName("UTF-8"));
                log.info("解码字节:{}", decodeMessage);
                log.info("客户端消息：长度：{},内容：{}", length, decodeMessage);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        listen("localhost", 8000);
    }
}
