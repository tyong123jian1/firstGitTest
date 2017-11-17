package com.jiejue.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by jianbin on 2017-10-10.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        try {
            ByteBuf buf = (ByteBuf) o;
//            while (buf.isReadable()){/**/
            System.out.println((char) buf.readableBytes());
            System.out.flush();
//            }
            channelHandlerContext.write(o);
            channelHandlerContext.flush();
        } finally {
            ReferenceCountUtil.release(o);
        }

    }


    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        throwable.printStackTrace();
        channelHandlerContext.close();
    }
}
