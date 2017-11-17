package com.jiejue.netty.protocol;

import java.io.IOException;

/**
 * Created by jianbin on 2017-10-13.
 */
public interface Message {
    void parse(byte[] bytes) throws IOException;

    byte[] getBytes() throws IOException;

    void setHeader(Header header);

    Header getHeader();
}
