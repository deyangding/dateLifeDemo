package com.example.demo.netty.customer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CustomerServer {
    //属性
    private String host;
    private int port;

    public CustomerServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup customer = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(customer)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {

                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new CustomerIdleUserDeHandler(CustomerServer.this));
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast(new UserDefined1Handler());
            }
        });

        ChannelFuture channelFuture =bootstrap.connect(host, port).sync();
        Channel channel = channelFuture.channel();
        System.out.println();
        System.out.println("channel.localAddress()"+ channel.localAddress());
        System.out.println("channel.remoteAddress()"+channel.remoteAddress());
        System.out.println();

       /* //客户端需要输入信息，创建一个扫描器
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = "scanner.nextLine()";
            //通过 channel 发送到服务器端
            channel.writeAndFlush(msg + "\r\n");
        }*/
        String msg = "我";
        channel.writeAndFlush(msg + "\r\n");
       while (channel.isActive()){

           //通过 channel 发送到服务器端

           Thread.sleep(1000);
//           channel.writeAndFlush(msg + "\r\n");
       }
        customer.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        new CustomerServer("127.0.0.1", 7000).start();
    }
}
