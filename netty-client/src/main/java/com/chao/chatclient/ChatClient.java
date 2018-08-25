package com.chao.chatclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 简单聊天服务器-客户端
 *
 * @author waylau.com
 * @date 2015-2-26
 */
public class ChatClient {

    private final static Logger logger = LoggerFactory.getLogger(ChatClient.class);

    private final String host;
    private final int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    //通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChatClientInitializer());
            Channel channel = bootstrap.connect(host, port).sync().channel();
            ChatManager.setChannel(channel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.info("finally！{}", "ChatClient");
            //group.shutdownGracefully();
        }

    }

}
