package com.example.demo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

public class NettyServer {
    //监听端口
    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        NioEventLoopGroup mainGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup,workGroup)
                .option(ChannelOption.SO_BACKLOG, 128)//初始化连接大小
                .childOption(ChannelOption.SO_KEEPALIVE, true)//一直保持连接状态
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder",new StringDecoder());
                        pipeline.addLast("encoder",new StringEncoder());
                        pipeline.addLast(new IdleStateHandler(30,30,40, TimeUnit.SECONDS));
                        pipeline.addLast(new ServerUserDefinedIdleHandler());
                        pipeline.addLast(new ServerUserDefiendHandler());
                    }
                });

        System.out.println("服务启动");
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

        //监听关闭
        channelFuture.channel().closeFuture().sync();


        //关闭线程组
        mainGroup.shutdownGracefully();
        workGroup.shutdownGracefully();

    }

    public static void main(String[] args) throws InterruptedException {
        new NettyServer(7000).start();
    }
}
