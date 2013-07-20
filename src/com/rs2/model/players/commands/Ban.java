package com.rs2.model.players.commands;

import java.io.IOException;

import com.rs2.model.World;
import com.rs2.model.players.*;
import com.rs2.util.BanProcessor;
import com.rs2.util.PunishmentManager;

public class Ban implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getStaffRights() >= 5) {
			String banned = command.substring(4);
			Player p = World.getPlayerByName(banned);

			try {
				BanProcessor.banUser(p.getUsername(),c.getUsername());
				p.disconnect();
				} catch (IOException e) {
				}		
		}
	}
}