package com.jiejue.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.internal.StringUtil;

/**
 * Created by jianbin on 2017-11-06.
 */
public class TestMsgEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof TestMessage) {
            byte[] bytes = ((TestMessage) msg).getBytes();
            System.out.println("length:" + bytes.length + ", encodign msg:" + StringUtil.toHexString(bytes));
            ByteBuf bb = ctx.alloc().buffer(bytes.length);
            bb.writeBytes(bytes);
            ctx.writeAndFlush(bb, promise);
        } else {
            super.write(ctx, msg, promise);

        }
    }
}
