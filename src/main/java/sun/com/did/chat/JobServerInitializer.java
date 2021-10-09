package sun.com.did.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

@Component
public class JobServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //使用http的编码器和解码器
        pipeline.addLast(new HttpServerCodec());
        //添加块处理器
        pipeline.addLast(new ChunkedWriteHandler());

        pipeline.addLast(new HttpObjectAggregator(8192));

        // webSocket 数据压缩扩展，当添加这个的时候WebSocketServerProtocolHandler的第三个参数需要设置成true
		pipeline.addLast(new WebSocketServerCompressionHandler());

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定义handler,处理业务逻辑
        //TODO
        pipeline.addLast(new JobHandler());

        pipeline.addLast(new BinaryHandler());
    }
}
