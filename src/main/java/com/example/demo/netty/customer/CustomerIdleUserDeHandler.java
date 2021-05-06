package com.example.demo.netty.customer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomerIdleUserDeHandler extends ChannelInboundHandlerAdapter {


    private CustomerServer tcpClient;

    public CustomerIdleUserDeHandler(CustomerServer tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Successfully established a connection to the server.");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("@@@@@@@@@@@@@@Try to reconnect to the server ");

        final EventLoop eventLoop = ctx.channel().eventLoop();
        AtomicBoolean fla = new AtomicBoolean(true);
        eventLoop.schedule(() -> {
            System.out.println("Reconnecting ...");
            while (fla.get()) {
                try {
                    tcpClient.start();
                    fla.set(false);
                } catch (Exception e) {
                    fla.set(true);
                }
            }

        }, 5, TimeUnit.MILLISECONDS);
        ctx.fireChannelInactive();
    }


}
