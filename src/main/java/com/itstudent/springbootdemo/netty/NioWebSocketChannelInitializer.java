package com.itstudent.springbootdemo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @ClassName: NioWebSocketChannelInitializer
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/1/21 10:53
 * @Description: 配置Channel通道参数
 */

public class NioWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("logging",new LoggingHandler("DEBUG"));//设置log监听器，并且日志级别为debug，方便观察运行流程
        socketChannel.pipeline().addLast("http-codec",new HttpServerCodec());//设置解码器
        socketChannel.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
        socketChannel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());//用于大数据的分区传输
        socketChannel.pipeline().addLast("handler",new NioWebSocketHandler());//自定义的业务handler
    }
}
