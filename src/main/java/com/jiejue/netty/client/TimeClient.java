package com.jiejue.netty.client;

import com.jiejue.netty.handler.TimeClientHandler;
import com.jiejue.netty.protocol.ImMessage0;
import com.jiejue.netty.protocol.TestConnecMsg;
import com.jiejue.netty.protocol.TestMsgEncoder;
import com.jiejue.netty.util.CheckSumGenerator;
import com.jiejue.netty.util.ConfigFile;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by jianbin on 2017-10-10.
 */
public class TimeClient {

    public static void main(String[] arg) throws InterruptedException {
        String[] args = new String[2];
        args[0] = ConfigFile.getValue("host");
        args[1] = ConfigFile.getValue("port");
        runClient(args);
    }

    public static void runClient(String[] args) throws InterruptedException {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new TestMsgEncoder(), new TimeClientHandler());
                }
            });
            ChannelFuture future = bootstrap.connect(host, port).sync();

            String username = "test";
            String password = "test@@1u99!x908";
            Long timeStamp = System.currentTimeMillis();
            int version = 1;
            String checksum = CheckSumGenerator.genCheckSum(username, password, timeStamp);
            TestConnecMsg msg = new TestConnecMsg(username, checksum, version, timeStamp);
//            ImMessage imMessage = new ImMessage("zhangsan", "你好1");
            ImMessage0 imMessage0 = new ImMessage0("张三", (short) 2, 22, "你好");
            future.channel().write(msg);
//            future.channel().write(imMessage);
            future.channel().write(imMessage0);
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }
}
