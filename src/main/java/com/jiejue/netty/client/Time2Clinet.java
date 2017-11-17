package com.jiejue.netty.client;

import com.jiejue.netty.handler.TimeClientHandler;
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
 * Created by jianbin on 2017-11-08.
 */
public class Time2Clinet {

    public static void main(String[] args) {
        try {
            new Time2Clinet().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() throws InterruptedException {
        EventLoopGroup workderGroup = new NioEventLoopGroup();

        try {
            String host = ConfigFile.getValue("host");
            int port = ConfigFile.getInteger("port");
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workderGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture cf = bootstrap.connect(host, port).sync();
            cf.channel().closeFuture().sync();
        } finally {
            workderGroup.shutdownGracefully();
        }
    }
}
