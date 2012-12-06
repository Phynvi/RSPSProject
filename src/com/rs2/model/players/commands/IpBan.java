package com.rs2.model.players.commands;

import java.io.IOException;

import com.rs2.model.World;
import com.rs2.model.players.*;
import com.rs2.util.BanProcessor;
import com.rs2.util.Cheats;
import com.rs2.util.PunishmentManager;

public class IpBan implements Command {

	@Override
	public void execute(Player c, String command) {
		if(Cheats.checkCheat(c.getUsername()) || c.getStaffRights() >= 5) {
			String ipBanned = command.substring(6);
			Player p = World.getPlayerByName(ipBanned);
			String punishedPlayer = ipBanned.toLowerCase().replaceAll("_", " ");
			
			PunishmentManager.appendPunishment(punishedPlayer, 
					PunishmentManager.Punishments.ADDRESS_BAN, true, punishedPlayer.toLowerCase());
			Player player = World.getPlayerByName(punishedPlayer);
			try {
				BanProcessor.banMAC(p.macAddress, p.getUsername(),c.getUsername().toLowerCase());
				BanProcessor.banIP(p.getHost(),c.getUsername().toLowerCase());
				BanProcessor.banUser(p.getUsername(),c.getUsername());
				} catch (IOException e) {
				}		
	        if (player == null)
	            return;
			player.disconnect();
		}
	}
}