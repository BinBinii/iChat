package com.studio.chat.socket;

import com.alibaba.fastjson.JSONObject;
import com.studio.chat.command.MessageRequestPacket;
import com.studio.chat.utils.SessionUtils;
import com.studio.common.model.pojo.TbUser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.nio.charset.Charset;

@Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>{

    public static MessageRequestHandler INSTANCE = new MessageRequestHandler();
	
	private MessageRequestHandler() {		
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
		// TODO Auto-generated method stub
		String message = "";
		Channel toUserChannel = SessionUtils.getChannel(messageRequestPacket.getToUserId());
		if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
			message = messageRequestPacket.getMessage(); 
		} else {
			message = "当前用户："+messageRequestPacket.getToUserId()+"不在线！";
			System.err.println(message);
		}
		TbUser toUser = SessionUtils.getUser(toUserChannel);
		String fileType = messageRequestPacket.getFileType();
		ByteBuf buf = getByteBuf(ctx, message, toUser, fileType);
		toUserChannel.writeAndFlush(new TextWebSocketFrame(buf));
	}
	
	public ByteBuf getByteBuf(ChannelHandlerContext ctx, String message, TbUser toUser, String fileType) {
		ByteBuf byteBuf = ctx.alloc().buffer();
		TbUser fromUser = SessionUtils.getUser(ctx.channel());
		JSONObject data = new JSONObject();
		data.put("type", 2);
		data.put("status", 200);
		JSONObject params = new JSONObject();
		params.put("message", message);
		params.put("fileType", fileType);
		params.put("fromUser", fromUser);
		params.put("toUser", toUser);
		data.put("params", params);
		byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
		byteBuf.writeBytes(bytes);
		return byteBuf;
	}
}
