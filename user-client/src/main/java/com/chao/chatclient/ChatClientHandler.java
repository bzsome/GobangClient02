package com.chao.chatclient;

import com.chao.chatclient.message.MessageManager;
import com.chao.domain.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端 message
 *
 * @author waylau.com
 * @date 2015-2-26
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<MyMessage> {

    private final static Logger logger = LoggerFactory.getLogger(ChatClientHandler.class);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        logger.info("已掉线！");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyMessage myMessage) throws Exception {
        logger.info("收到消息：{}", myMessage.getMsg_text());
        MessageManager.handlerMessage(myMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info("异常，关闭连接", ctx.channel().remoteAddress());
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
