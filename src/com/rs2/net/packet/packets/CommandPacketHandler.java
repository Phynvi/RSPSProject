package com.rs2.net.packet.packets;

import com.rs2.model.players.CommandManager;
import com.rs2.model.players.Player;
import com.rs2.net.packet.Packet;
import com.rs2.net.packet.PacketManager.PacketHandler;

public class CommandPacketHandler implements PacketHandler {

	public static final int COMMAND = 103;

	@Override
	public void handlePacket(Player player, Packet packet) {
		switch (packet.getOpcode()) {
			case COMMAND:
				String playerCommand = packet.getIn().readString();
				CommandManager.execute(player, playerCommand);
			break;
		}

	}
}
