package com.jiejue.netty.protocol;

import java.io.*;

/**
 * Created by jianbin on 2017-10-13.
 */
public abstract class BaseMessage {

    protected Header header;

    public void parse(byte[] bytes) throws IOException {
        header = new Header(bytes);
        int length = bytes.length;
        if (length > 12) {
            DataInputStream dis = null;
            try {
                dis = new DataInputStream(new ByteArrayInputStream(bytes));
                dis.read(new byte[12]);
                byte[] body = new byte[length - 12];
                dis.read(body);
                dis.close();
                parseBody(body);
            } finally {
                if (dis != null) {
                    dis.close();
                }
            }
        }
    }

    public byte[] getBytes() throws IOException {
        byte[] headerBytes = header.getBytes();
        byte[] bodyBytes = getMsgBodyBytes();
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            dos.write(headerBytes);
            dos.write(bodyBytes);
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (dos != null) {
                dos.close();
            }
        }
        return bos.toByteArray();
    }

    public abstract void parseBody(byte[] bytes) throws IOException;

    public abstract byte[] getMsgBodyBytes() throws IOException;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
        System.out.println("total length:" + this.header.getLength());
    }
}
