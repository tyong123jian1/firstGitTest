package com.jiejue.netty.protocol;

import java.io.*;

/**
 * Created by jianbin on 2017-11-03.
 */
public class TestConnecMsg extends BaseMessage implements TestMessage {
    private String userName; //10
    private long timestamp;  //8
    private String checkSum; //32

    public TestConnecMsg() {
    }

    public TestConnecMsg(String userName, String checkSum, int version, long timestamp) {
        this.userName = userName;
        this.checkSum = checkSum;
        this.timestamp = timestamp;
        setHeader(new Header(CONNECT, 1, 12 + 10 + 8 + 32));
    }

    public void parseBody(byte[] bytes) throws IOException {

        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            byte[] temp = new byte[10];
            dis.read(temp);
            this.userName = new String(temp);
            this.timestamp = dis.readLong();
            temp = new byte[32];
            dis.read(temp);
            this.checkSum = new String(temp);
        } finally {
            if (dis != null) {
                dis.close();
            }
        }

    }

    public byte[] getMsgBodyBytes() throws IOException {
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            byte[] temp = new byte[10];
            byte[] unb = userName.getBytes();
            if (unb.length == 10) {
                temp = unb;
            } else {
                System.arraycopy(unb, 0, temp, 0, unb.length > 10 ? 10 : unb.length);
            }
            dos.write(temp);
            dos.writeLong(timestamp);
            temp = new byte[32];
            byte[] csb = checkSum.getBytes();
            if (csb.length == 32) {
                temp = csb;
            } else {
                System.arraycopy(csb, 0, temp, 0, csb.length > 32 ? 32 : csb.length);
            }
            dos.write(temp);

        } finally {
            if (dos != null)
                dos.close();
            if (bos != null)
                bos.close();
        }
        return bos.toByteArray();
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Connect [userName=" + userName + ", timestamp=" + timestamp + ", checkSum=" + checkSum + "]";
    }

    public void setBodyLength(int bodyLength) {

    }

    public int getBodyLength() {
        return 0;
    }
}
