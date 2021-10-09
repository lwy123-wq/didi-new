package sun.com.did.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.springframework.stereotype.Component;

@Component
public class BinaryHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
        System.out.println("服务器接收到二进制消息");
        ByteBuf content = msg.content();
        content.markReaderIndex();
        int flag = content.readInt();
        content.resetReaderIndex();
        ByteBuf byteBuf = Unpooled.directBuffer(msg.content().capacity());
        byteBuf.writeBytes(msg.content());
        ctx.writeAndFlush(new BinaryWebSocketFrame(byteBuf));
    }
}
