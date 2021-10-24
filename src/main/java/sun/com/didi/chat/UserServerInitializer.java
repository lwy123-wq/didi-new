package sun.com.didi.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

@Component
public class UserServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        //添加块处理器
        pipeline.addLast(new ChunkedWriteHandler());

        pipeline.addLast(new HttpObjectAggregator(8192));

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定义handler,处理业务逻辑
        //TODO
        pipeline.addLast(new UserHandler());
    }
}
