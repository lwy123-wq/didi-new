package sun.com.didi.config;

import io.netty.channel.ChannelId;

import java.util.concurrent.ConcurrentHashMap;

public class ChatConfig {
    public static ConcurrentHashMap<Integer, ChannelId> map = new ConcurrentHashMap<Integer, ChannelId>();
    public static ConcurrentHashMap<Integer, String> name = new ConcurrentHashMap<Integer, String>();
    public int num=0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
