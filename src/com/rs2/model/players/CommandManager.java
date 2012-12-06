package com.rs2.model.players;

import java.util.HashMap;
import java.util.Map;
import com.rs2.model.players.commands.*;
import com.rs2.model.players.Player;

public class CommandManager {

	private static Map<String, Command> commandMap = new HashMap<String, Command>();
	static {
		commandMap.put("sp", new PickUp());
		commandMap.put("players", new PlayersOnline());
		commandMap.put("mypos", new MyPosition());
		commandMap.put("tele", new Teleport());
		commandMap.put("max", new MaxSkillLevels());
		commandMap.put("mo", new MaxOtherSkillLevels());
		commandMap.put("sw", new SwitchMagicBooks());
		commandMap.put("specs", new Specs());
		commandMap.put("hi", new HeadIcon());
		commandMap.put("spawn", new NpcSpawn());
		commandMap.put("ispawn", new GlobalItemSpawn());
		commandMap.put("players", new Players());
		commandMap.put("news", new Yell());
		commandMap.put("bank", new Bank());
		commandMap.put("gfx", new Gfx());
		commandMap.put("emote", new Emote());
		commandMap.put("char", new Char());
		commandMap.put("meleemax", new Melee());
		commandMap.put("rangemax", new Range());
		commandMap.put("xteleto", new XTeleTo());
		commandMap.put("xteletome", new XTeleToMe());
		commandMap.put("empty", new Empty());
		commandMap.put("setlevel", new SetLevel());
		commandMap.put("setotherlevel", new SetOtherLevel());
		commandMap.put("kick", new Kick());
		commandMap.put("potions", new Potions());
		commandMap.put("melee", new MeleeGear());
		commandMap.put("mage", new MageGear());
		commandMap.put("range", new RangeGear());
		commandMap.put("changepass", new Password());
		commandMap.put("sd", new Sound());
		//commandMap.put("", new ());
		commandMap.put("ban", new Ban());
		commandMap.put("unban", new UnBan());
		commandMap.put("mute", new Mute());
		commandMap.put("unmute", new UnMute());
		commandMap.put("ipban", new IpBan());
		commandMap.put("unipban", new UnIpBan());
		commandMap.put("ipmute", new IpMute());
		commandMap.put("unipmute", new UnIpMute());
		commandMap.put("clearall", new ClearAll());
		commandMap.put("update", new Update());
		commandMap.put("runes", new Runes());
		commandMap.put("fr", new ForceRandom());
		commandMap.put("obank", new OBank());
		commandMap.put("oinv", new OInv());
		commandMap.put("sm", new SwitchOtherMagic());
		System.out.println("Loaded " + commandMap.size() + " commands.");	
	}
	
	public static void execute(Player player, String command) {
		String name = "";
		if (command.indexOf(' ') > -1) {
			name = command.substring(0, command.indexOf(' '));
		} else {
			name = command;
		}
		name = name.toLowerCase();
		if (commandMap.get(name) != null) {
			commandMap.get(name).execute(player, command);
		} else if (name.length() == 0) {
			player.getActionSender().sendMessage(
					"The command does not exist.");
		} else {
			player.getActionSender().sendMessage(
					"The command " + name + " does not exist.");
		}	
	
	
	}
}