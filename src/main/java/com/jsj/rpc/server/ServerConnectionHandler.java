package com.jsj.rpc.server;

import com.jsj.rpc.protocol.*;
import com.jsj.rpc.util.MessageUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConnectionHandler extends SimpleChannelInboundHandler<Message> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerConnectionHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        Header header = message.getHeader();
        //若是心跳请求则直接返回，否则交给下一handler处理
        if (MessageTypeEnum.HEART_BEAT_REQUEST.getValue() == header.messageType()) {
            LOGGER.debug("服务端收到心跳请求，channel:" + channelHandlerContext.channel());
            channelHandlerContext.writeAndFlush(MessageUtil.createHeartBeatResponseMessage());
        } else {
            channelHandlerContext.fireChannelRead(message);
        }
    }
}
