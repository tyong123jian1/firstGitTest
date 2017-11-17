package com.jiejue.netty.protocol;

import java.io.*;

/**
 * @version V1.0
 * @Title: TextMessage.java
 * @Description: 文本消息
 * @date 2016年9月7日 上午11:22:35
 */
public class ImMessage0 extends BaseMessage implements TestMessage {

    String fromName;    //10
    short type;            //2
    long toId;            //8
    int textLength;        //4
    String textMsg;        //textLength

    public final static short PERSONAL = 1;
    public final static short GROUP = 2;

    public ImMessage0() {
    }

    public ImMessage0(String fromName, short type, long toId, String textMsg) {
        setHeader(new Header(IMMESSAGE, 2, 12 + 10 + 2 + 8 + 4 + textMsg.getBytes().length));
        this.fromName = fromName;
        this.type = type;
        this.toId = toId;
        this.textLength = textMsg.getBytes().length;
        this.textMsg = textMsg;

    }


    @Override
    public void parseBody(byte[] bodyBytes) throws IOException {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(bodyBytes));
            byte[] temp = new byte[10];
            dis.read(temp);
            fromName = new String(temp).trim();
            type = dis.readShort();
            toId = dis.readLong();
            textLength = dis.readInt();
            temp = new byte[textLength];
            dis.read(temp);
            textMsg = new String(temp);
        } finally {
            if (dis != null) {
                dis.close();
            }
        }
    }

    @Override
    public byte[] getMsgBodyBytes() throws IOException {
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            byte[] temp = new byte[10];
            byte[] fnb = fromName.getBytes("UTF-8");
            System.arraycopy(fnb, 0, temp, 0, fnb.length > 10 ? 10 : fnb.length);
            dos.write(temp);
            dos.writeShort(type);
            dos.writeLong(toId);
            textLength = textMsg.getBytes().length;
            dos.writeInt(textLength);
            dos.write(textMsg.getBytes("UTF-8"));
        } finally {
            if (dos != null)
                dos.close();
            if (bos != null)
                bos.close();
        }
        return bos.toByteArray();
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public int getTextLength() {
        return textLength;
    }

    public void setTextLength(int textLength) {
        this.textLength = textLength;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public void setBodyLength(int bodyLength) {

    }

    public int getBodyLength() {
        return 0;
    }

}
