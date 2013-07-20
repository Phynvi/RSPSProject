package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.Position;
import com.rs2.model.World;

public class XTeleTo implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getStaffRights() > 4){
			if (command.length() > 8) {
				String name = command.substring(8);
				for (Player p : World.getPlayers()) {
					if(p == null)
						continue;
					if(p.getUsername().equalsIgnoreCase(name)){
						c.getActionSender().sendMessage("@red@You have teleported to "+p.getUsername()+".");
						c.teleport(new Position(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ()));					
					}
				}
			}
		}
	}
}