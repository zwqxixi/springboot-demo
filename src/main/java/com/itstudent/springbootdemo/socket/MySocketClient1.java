package com.itstudent.springbootdemo.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @className: MySocketClient0
 * @author: wenqin.zhao
 * @createDate: 2020/9/10 15:33
 * @description: TODO
 */
@Slf4j
public class MySocketClient1 {

//    public static void send(String message){
//        Socket socket = null;
//        try {
//            socket = new Socket("localhost",8000);
//            //获取输出流用于客户端向服务端发送数据
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//            dos.writeUTF(message);
//            log.info("client1 send data:{}",message);
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void send(String message){
        Socket socket = null;
        try {
            socket = new Socket("localhost",8000);
            //获取输出流用于客户端向服务端发送数据
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            byte[] messageBytes = message.getBytes(Charset.forName("UTF-8"));
            int length = messageBytes.length;
            dos.writeShort(length);
            dos.write(messageBytes);
            log.info("client1 send data,长度：{},内容：{}",length,messageBytes);
            dos.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        send("client1====message");
    }
}
