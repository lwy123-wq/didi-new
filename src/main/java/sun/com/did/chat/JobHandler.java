package sun.com.did.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import sun.com.did.config.ChatConfig;

public class JobHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private ChatConfig config=new ChatConfig();
    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("接收到客户端的消息:"+msg.text());
        //String txtMsg="["+ChatConfig.map.get()


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        int num=config.getNum();
        num=num+1;
        config.setNum(num);
        ChatConfig.map.put(num,ctx.channel().id());
        Channel channel = ctx.channel();
        System.out.println(ctx.channel().remoteAddress()+"连接到服务器");
        channelGroup.add(channel);

    }
}
