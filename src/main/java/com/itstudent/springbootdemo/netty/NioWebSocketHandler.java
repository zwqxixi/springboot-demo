package com.itstudent.springbootdemo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @ClassName: NioWebSocketHandler
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/1/21 10:57
 * @Description: 自定义的业务Handler
 */

public class NioWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private final String FAVICON_ICO = "/favicon.ico";
    // 读取客户端请求，向客户端响应的方法，所以这里要构造响应返回给客户端。
    //当服务器接收到一条消息的时候被调用
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        if (o instanceof HttpRequest) {
            io.netty.handler.codec.http.HttpRequest httpRequest = (HttpRequest) o;
            URI uri = new URI(httpRequest.uri());
            System.out.println("请求方法: " + httpRequest.method() + ", 请求path: " + uri.getPath());
            if (FAVICON_ICO.equals(uri.getPath())) {
                System.out.println("请求/favicon.ico");
                return;
            }
            // BytBuf：构造给客户端的响应内容, 制定好编码
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            // 接下构造响应对象
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            // 调用flush才会将内容真正返回给客户端
            System.out.println("响应给客户端对象: " + response);
            ctx.writeAndFlush(response);
            ctx.channel().closeFuture();
        }
    }
}
