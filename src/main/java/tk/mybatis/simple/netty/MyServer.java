package tk.mybatis.simple.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MyServer {
	
	public static void main(String[] args) {
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new WebSocketChannellinitalizer());
			ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(8899)).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
			boosGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
