package tk.mybatis.simple.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import tk.mybatis.simple.mapper.CountryMapperTest;
import tk.mybatis.simple.model.Country;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class action implements Runnable{

    private ChannelHandlerContext ctx;

    public action(ChannelHandlerContext ctx) {
        this.ctx=ctx;
    }

    public void run() {
        CountryMapperTest test = new CountryMapperTest();
        List<Country> result = test.find();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(result)));
    }
}

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    protected void channelRead0(final ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到消息:"+msg.text());
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Future futur = eventExecutors.submit(new action(ctx));
        futur.addListener(new GenericFutureListener() {
            public void operationComplete(Future future) throws Exception {
                System.out.println("完成");
            }
        });
        System.out.println("end");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("handlerAdded："+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println("handlerRemover:"+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("发生异常");
        ctx.close();
    }
}
