package com.studio.chat.command;

public class HeartBeatRequestPacket extends Packet{

	@Override
	public Byte getCommand() {
		// TODO Auto-generated method stub
		return Command.HEARTBEAT_REQUEST;
	}

}
