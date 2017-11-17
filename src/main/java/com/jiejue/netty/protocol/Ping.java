package com.jiejue.netty.protocol;

import java.io.IOException;

/**
 * Created by jianbin on 2017-10-20.
 */
public class Ping extends BaseMessage implements Message {


    public void parseBody(byte[] bytes) throws IOException {

    }

    public byte[] getMsgBodyBytes() throws IOException {
        return new byte[0];
    }
}
