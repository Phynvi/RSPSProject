package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.util.PunishmentManager;

public class IpMute implements Command {

	@Override
	public void execute(Player c, String command) {
	//if(c.getUsername().equalsIgnoreCase("chachi")) {
		//String args[] = command.split(" ");
		if(c.getStaffRights() >= 5) {
		String ipMuted = command.substring(7);
		String punishedPlayer = ipMuted.toLowerCase().replaceAll("_", " ");
		PunishmentManager.appendPunishment(punishedPlayer, 
				PunishmentManager.Punishments.ADDRESS_MUTE, true, ipMuted.toLowerCase());
	}
	}
}