package com.jiejue.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by jianbin on 2017-10-10.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        try {
            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println("recive time:" + new Date(currentTimeMillis));
            ctx.close();
        } finally {
            buf.release();
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
       /* final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        String username = "test";
        String password="test@@1u99!x908";
        Long timeStamp = System.currentTimeMillis();
        int version = 1;
//        String checksum = CheckSumGenerator.genCheckSum(username, password, timeStamp);
//        TestConnecMsg msg = new TestConnecMsg(username, checksum, version, timeStamp);
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)

        f.addListener( ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE); // (4)*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
