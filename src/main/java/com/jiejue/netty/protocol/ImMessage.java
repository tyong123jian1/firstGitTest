package com.jiejue.netty.protocol;

import java.io.*;

/**
 * Created by jianbin on 2017-11-08.
 */
public class ImMessage extends BaseMessage implements TestMessage {
    private String name; //12
    private String word; //12

    public ImMessage(String name, String word) {
        this.name = name;
        this.word = word;
        setHeader(new Header(0x6004, 2, 12 + 12 + 12));
    }

    public void setBodyLength(int bodyLength) {

    }

    public int getBodyLength() {
        return 0;
    }

    public void parseBody(byte[] bytes) throws IOException {

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        byte[] temp = new byte[12];

        dis.read(temp);
        name = new String(temp);

        dis.read(temp);
        word = new String(temp);
        dis.close();
    }

    public byte[] getMsgBodyBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        byte[] temp = new byte[12];
        byte[] nameBytes = name.getBytes("UTF-8");
        System.arraycopy(nameBytes, 0, temp, 0, nameBytes.length > 12 ? 12 : nameBytes.length);
        dos.write(temp);
        byte[] wordBytes = word.getBytes("UTF-8");
        System.arraycopy(wordBytes, 0, temp, 0, wordBytes.length > 12 ? 12 : wordBytes.length);
        dos.write(temp);
        bos.close();
        dos.close();

        return bos.toByteArray();
    }
}
