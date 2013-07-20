package com.rs2.model.players.commands;

import com.rs2.model.content.randomevents.SpawnEvent;
import com.rs2.model.content.randomevents.TalkToEvent;
import com.rs2.model.content.randomevents.SpawnEvent.RandomNpc;
import com.rs2.model.content.randomevents.TalkToEvent.TalkToNpc;
import com.rs2.model.players.*;
import com.rs2.util.Misc;
import com.rs2.util.PunishmentManager;

public class ForceRandom implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
		switch(Misc.random(2)) {
		case 1 :
			TalkToEvent.spawnNpc(c, TalkToNpc.JEKYLL);
			break;
		case 2 :
			TalkToEvent.spawnNpc(c, TalkToNpc.RICK);
			break;	
	}
		}
	}
}