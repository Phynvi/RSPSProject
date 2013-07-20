package com.rs2.model.players.commands;

import com.rs2.model.content.skills.magic.SpellBook;
import com.rs2.model.players.*;

public class SwitchMagicBooks implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
		if(c.inWild()){
			c.getActionSender().sendMessage("Please use this commmand out of the wild.");
			return;
		}		
			if (command.length() > 3) {
				String[] commands = command.split(" ");
				int magics = Integer.parseInt(commands[1]);
				switch (magics) {
					case 1:
						c.disableAutoCast();
						c.setAutoCasting(false);
						c.setAutoSpell(null);
						c.getEquipment().refresh();
						c.getActionSender().sendMessage("You switch to modern magic.");
						c.getActionSender().sendSidebarInterface(6, 1151);
						c.magicBookType = SpellBook.MODERN;
					break;
					case 2:
						c.disableAutoCast();
						c.setAutoCasting(false);
						c.setAutoSpell(null);
						c.getEquipment().refresh();
						c.getActionSender().sendMessage("You switch to ancient magic.");
						c.getActionSender().sendSidebarInterface(6, 12855);
						c.magicBookType = c.getMagicBookType().ANCIENT;	
					break;
					
					default:
						c.disableAutoCast();
						c.setAutoCasting(false);
						c.setAutoSpell(null);
						c.getEquipment().refresh();
						c.getActionSender().sendMessage("You switch to modern magic.");
						c.getActionSender().sendSidebarInterface(6, 1151);
						c.magicBookType = c.getMagicBookType().MODERN;
					break;
				}
			} else {
				c.getActionSender().sendMessage("Usage: ::sw 1 for normal ::sw 2 for ancients.");
			}			
		}
	}
}