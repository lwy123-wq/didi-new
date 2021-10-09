package sun.com.did.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;
import sun.com.did.config.ChatConfig;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class JobHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static ChatConfig config=new ChatConfig();
    private static int num=0;
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        num++;
        System.out.println("接收到客户端的消息:"+msg.text());
        String txtMsg="["+ChatConfig.map.get(num)+"]["+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))+ "] ==> " + msg.text();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(txtMsg));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //ChatConfig.map.put(num,ctx.channel().id());
        Channel channel = ctx.channel();
        System.out.println(ctx.channel().remoteAddress()+"连接到服务器");
        channelGroup.add(channel);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("服务器发生了异常:"+ cause);
    }

}
