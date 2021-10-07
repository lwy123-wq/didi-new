package sun.com.did.config;

import io.netty.channel.ChannelId;

import java.util.concurrent.ConcurrentHashMap;

public class ChatConfig {
    public static ConcurrentHashMap<Integer, ChannelId> map = new ConcurrentHashMap<Integer, ChannelId>();
    public int num=0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
