package sun.com.did.chat;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;
import sun.com.did.config.ChatConfig;
import sun.com.did.entity.Login;

@Component
public class UserHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChatConfig config=new ChatConfig();
    private  int num;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        num=config.getNum();
        num=num+1;
        Login message = new Gson().fromJson(msg.text(), Login.class);
        ChatConfig.name.put(num,message.getName());
        System.out.println("aaaaaaaaaa"+message.getName());
        config.setNum(num);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }
}
