package com.rs2.net.packet.packets;

import com.rs2.model.players.Player;
import com.rs2.net.packet.Packet;
import com.rs2.net.packet.PacketManager.PacketHandler;


public class IdleLogoutPacketHandler implements PacketHandler {

	public static final int IDLELOGOUT = 202;

	@Override
	public void handlePacket(Player player, Packet packet) {
		if(player.getStaffRights() < 5){
			if (player.getPjTimer().completed() && player.getInCombatTick().completed()){
				player.disconnect();
			}
		}
	}
}