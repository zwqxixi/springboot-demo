package com.itstudent.springbootdemo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: NioWebSocketServer
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/1/21 9:42
 * @Description: NettyServer端启动类
 */

@Slf4j
public class NioWebSocketServer {

    private void init(){
        log.info("开始启动Netty服务器");
        //new 事件循环组
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //Reactor模式三种（两个关键组成 Reactor:负载监听和轮询事件  Handler:负责调用相应的逻辑处理事件
        //   1.单个Reactor 单个线程
        //   2.单个Reactor 多个线程
        //   3.主从Reactor(MainReactor:负责客户端的连接请求 并将请求转发给SubReactor:负责IO数据读写)
        // ）
        //bossGroup目的是获取客户端连接，连接接收到之后再将连接转发给workerGroup去处理
        try {
            serverBootstrap.group(boss,work)
                           .channel(NioServerSocketChannel.class)//建立serverSocketChannel 绑定服务端
                           .childHandler(new NioWebSocketChannelInitializer());//创建ChannelInitializeer实例，通过ChannelPipeline初始化处理器链
            Channel channel = serverBootstrap.bind(8081).sync().channel();
            log.info("Netty服务器启动成功");
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("运行出错："+e);
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
            log.info("Netty服务器已关闭");
        }
    }

    public static void main(String[] args) {
        new NioWebSocketServer().init();
    }
}
