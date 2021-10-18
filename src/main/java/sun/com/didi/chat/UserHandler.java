package sun.com.didi.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import sun.com.didi.config.ChatConfig;

public class UserHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChatConfig config=new ChatConfig();
    private  int num;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("连接————————————————");
        num=config.getNum();
        num=num+1;
        /*Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        Login message =gson.fromJson(msg.text(),Login.class);*/
        JSONObject message = JSON.parseObject(msg.text());
        String username=message.getString("username");
        System.out.println(username);
        ChatConfig.name.put(num,username);
        config.setNum(num);
    }

}
