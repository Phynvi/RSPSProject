package com.rs2.model.content.questing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.rs2.model.players.Player;

/**
 * By Mikey` of Rune-Server - Credit to Killamess for the most of the
 * openQuestGuide method.
 */
public class Questing {

	private Player player;

	public Questing(Player player) {
		this.player = player;
	}

	public Object[][] questData = {
	// questName, currentStage, finishedStage, questPoints
		{"Black Knights' Fortress", 0, 6, 3},//0
		{"Cook's Assistant", 0, 6, 1},//1
		{"Demon Slayer", 0, 6, 3},//2
		{"Doric's Quest", 0, 6, 1},//3
		{"Dragon Slayer", 0, 6, 2},//4
		{"Ernest The Chicken", 0, 6, 4},//5
		{"Goblin Diplomacy", 0, 6, 5},//6
		{"Imp Catcher", 0, 6, 1},//7
		{"The Knight's Sword", 0, 6, 1},//8
		{"Pirate's Treasure", 0, 6, 1},//9
		{"Prince Ali Rescue", 0, 6, 3},//10
		{"The Restless Ghost", 0, 6, 1},//11
		{"Romeo & Juliet", 0, 6, 5},//12
		{"Rune Mysteries Quest", 0, 6, 1},//13
		{"Sheep Shearer", 0, 6, 1},//14
		{"Shield Of Arrav", 0, 6, 1},//15
		{"Vampire Slayer", 0, 6, 1},//16
		{"Witch's Potion", 0, 6, 1},//17
		{"Between a Rock....", 0, 6, 2},//18
		{"Big Chompy Bird Hunting", 0, 6, 2},//19
		{"Biohazard", 0, 6, 3},//20
		{"Cabin Fever", 0, 6, 2},//21
		{"Clock Tower", 0, 6, 1},//22
		{"Creature of Fenkenstrain", 0, 6, 2},//23
		{"Death Plateau", 0, 6, 1},//24
		{"Desert Treasure", 0, 6, 3},//25
		{"Devious Minds", 0, 6, 1},
		{"Digsite Quest", 0, 6, 2},
		{"Druidic Ritual", 0, 6, 4},
		{"Dwarf Cannon", 0, 6, 1},
		{"Eadgar's Ruse", 0, 6, 2},
		{"Elemental Workshop", 0, 6, 1},
		{"Enakhra's Lament", 0, 6, 2},
		{"A Fariy Tale Pt.1", 0, 6, 2},
		{"Family Crest", 0, 6, 1},
		{"The Feud", 0, 6, 1},
		{"Fight Arena", 0, 6, 2},
		{"Fishing Contest", 0, 6, 1},
		{"Forgettable Tale...", 0, 6, 2},
		{"The Fremennik Trials", 0, 6, 3},//39
		{"Garden of Tranquillity", 0, 6, 2},
		{"Gertrude's Cat", 0, 6, 1},
		{"Ghosts Ahoy", 0, 6, 2},
		{"The Giant Dwarf", 0, 6, 2},
		{"The Golem", 0, 6, 1},
		{"The Grand Tree", 0, 6, 5},
		{"The Hand in the Sand", 0, 6, 1},
		{"Haunted Mine", 0, 6, 2},
		{"Hazeel Cult", 0, 6, 1},
		{"Heroes Quest", 0, 6, 1},//49
		{"Holy Grail", 0, 6, 2},
		{"Horror from the Deep", 0, 6, 2},
		{"Icthlarin's Little Helper", 0, 6, 2},
		{"In Aid of the Myreque", 0, 6, 2},
		{"In Search of the Myreque", 0, 6, 2},
		{"Jungle Potion", 0, 6, 1},
		{"Legends Quest", 0, 6, 4},//56
		{"Lost City", 0, 6, 3},//57
		{"The Lost Tribe", 0, 6, 1},
		{"Making History", 0, 6, 3},
		{"Merlin's Crystal", 0, 6, 6},
		{"Monkey Madness", 0, 6, 3},//61
		{"Monk's Friend", 0, 6, 1},
		{"Mountain Daughter", 0, 6, 2},
		{"Mourning's Ends Part 1", 0, 6, 2},
		{"Mourning's Ends Part 2", 0, 6, 2},
		{"Murder Mystery", 0, 6, 3},
		{"Nature Spirit", 0, 6, 2},
		{"Observatory Quest", 0, 6, 2},
		{"One Small Favour", 0, 6, 2},
		{"Plauge City", 0, 6, 1},
		{"Priest in Peril", 0, 6, 1},
		{"Rag and Bone Man", 0, 6, 2},
		{"Rat Catchers", 0, 6, 2},
		{"Recipe for Disaster", 0, 6, 10},
		{"Revruitment Drive", 0, 6, 1},
		{"Regicide", 0, 6, 3},
		{"Roving Elves", 0, 6, 1},
		{"Rum Deal", 0, 6, 2},
		{"Scorpion Catcher", 0, 6, 1},
		{"Sea Slug Quest", 0, 6, 1},
		{"Shades of Mort'ton", 0, 6, 3},
		{"Shadow of the Storm", 0, 6, 1},
		{"Sheep Herder", 0, 6, 4},
		{"Shilo Village", 0, 6, 2},
		{"A Soul's Bane", 0, 6, 1},
		{"Spirits of the Elid", 0, 6, 2},
		{"Swan Song", 0, 6, 2},
		{"Tai Bwo Wannai Trio", 0, 6, 2},
		{"A Tail of Two Cats", 0, 6, 2},
		{"Tears of Guthix", 0, 6, 1},
		{"Temple of Ikov", 0, 6, 1},
		{"Throne of Miscellania", 0, 6, 1},
		{"The Tourist Trap", 0, 6, 2},
		{"Tree Gnome Village", 0, 6, 2},
		{"Tribal Totem", 0, 6, 1},
		{"Troll Romance", 0, 6, 2},
		{"Troll Stronghold", 0, 6, 1},
		{"Underground Pass", 0, 6, 5},
		{"Wanted!", 0, 6, 1},
		{"Watch Tower", 0, 6, 4},
		{"Waterfall Quest", 0, 6, 1},
		{"Witch's House", 0, 6, 4},
		{"Zogre Flesh Eaters", 0, 6, 1}	
	};

	private static final int[] QUEST_IDS = {// Same order as questData
	7332, 7333, 7334, 7336, 7383, 7339, 7338, 7340, 7346, 7341, 7342, 7337, 7343, 7335, 7344, 7345, 7347, 7348,12772,673,7352,17510,7353,12129,
	8438,12852,15841,7354,7355,7356,8679,7459,16149,6987,7357,12836,7358,7359,14169,10115,14604,7360,12282,13577,12839,7361,16128,11857,
	7362,7363,7364,10135,4508,18517,11907,7365,7366,7367,13389,15487,7368,11132,7369,12389,13974,6027,7370,8137,7371,12345,7372,8115,18684,
	15499,18306,668,8576,12139,14912,7373,7374,8969,15352,7375,7376,15098,15592,249,1740,15235,3278,7378,6518,7379,7380,7381,11858,191,9927,
	6024,7349,7350,7351,13356,191
	
	};

	private static final int[] QUEST_BUTTON_IDS = {// Same order as QUEST_IDS
													// and questData
	28164, 28165, 28166, 28168, 28215, 28171, 28170, 28172, 28178, 28173, 28174, 28169, 28175, 28167, 28176, 27177, 28179, 28180,49228,2161,28184,
	68102,28185,47097,32246,50052,61225,28186,28187,28188,33231,29035,63021,27075,28189,50036,28191,55089,39131,57012,28192,47250,53009,50039,28193,
	63000,46081,28194,28195,28196,39151,17156,72085,46131,28197,28198,28199,52077,60127,28200,43124,28201,48101,54150,23139,28202,31201,28203,48057,
	28204,31179,72252,60139,71130,2156,33128,47107,58064,28205,28206,35009,59248,28207,28208,58250,60232,249,6204,59131,12206,28210,25118,28211,
	28212,28213,46082,38199,23136,28181,28182,28183,52044
	
			
	
	
	
	};

	private boolean questDialogueTicking = false;

	public void updateQuestList() {
		player.getActionSender().sendString("Quests", 663);
		for (int i = 0; i < QUEST_IDS.length; i++) {
			if (i < questData.length) {
				if (questData[i][1] == questData[i][2]) {
					player.getActionSender().sendString("@gre@" + questData[i][0], QUEST_IDS[i]);
				} else if ((Integer) questData[i][1] > 0) {
					player.getActionSender().sendString("@yel@" + questData[i][0], QUEST_IDS[i]);
				} else {
					player.getActionSender().sendString("" + questData[i][0], QUEST_IDS[i]);
				}
			} else {
				player.getActionSender().sendString("", QUEST_IDS[i]);
			}
		}
	}
	public void finishQuestList() {
		player.getActionSender().sendString("Quests", 663);
		for (int i = 0; i < QUEST_IDS.length; i++) {
			//player.getQuesting().setQuestStage(i, 6);
			if (i < questData.length) {
				if (questData[i][1] == questData[i][2]) {
					player.getActionSender().sendString("@gre@" + questData[i][0], QUEST_IDS[i]);
					player.getQuesting().setQuestStage(i, 6);
				} else if ((Integer) questData[i][1] > 0) {
					player.getActionSender().sendString("@yel@" + questData[i][0], QUEST_IDS[i]);
					player.getQuesting().setQuestStage(i, 6);
				} else {
					player.getActionSender().sendString("" + questData[i][0], QUEST_IDS[i]);
					player.getQuesting().setQuestStage(i, 6);
				}
			} else {
				player.getActionSender().sendString("", QUEST_IDS[i]);
			}
		}
	}
	public void openQuestList() {
		player.getActionSender().sendString("Quests", 663);
		for (int i = 0; i < QUEST_IDS.length; i++) {
			//player.getQuesting().setQuestStage(i, 6);
			if (i < questData.length) {
				if (questData[i][1] == questData[i][2]) {
					player.getActionSender().sendString("@gre@" + questData[i][0], QUEST_IDS[i]);
					player.getQuesting().setQuestStage(i, 0);
				} else if ((Integer) questData[i][1] > 0) {
					player.getActionSender().sendString("@yel@" + questData[i][0], QUEST_IDS[i]);
					player.getQuesting().setQuestStage(i, 0);
				} else {
					player.getActionSender().sendString("" + questData[i][0], QUEST_IDS[i]);
					player.getQuesting().setQuestStage(i, 0);
				}
			} else {
				player.getActionSender().sendString("", QUEST_IDS[i]);
			}
		}
	}	
	public void clickQuestGuide(int buttonId) {
		for (int i = 0; i < questData.length; i++) {
			if (buttonId == QUEST_BUTTON_IDS[i]) {
				openQuestGuide((String) questData[i][0], (Integer) questData[i][1]);
			}
		}
	}

	public void clearQuestGuide() {
		for (int i = 0; i < 100; i++) {
			player.getActionSender().sendString("", 8144 + i);
		}
	}

	public void openQuestGuide(String directory, int stage) {
		boolean endOfFile = false;
		String line = "";
		BufferedReader questFile = null;
		try {
			questFile = new BufferedReader(new FileReader("./data/content/questing/" + directory + "/" + stage + ".txt"));
			int lineId = 8144;
			while (!endOfFile) {
				line = questFile.readLine();
				if (!line.equals("[END]") && !line.startsWith("//")) {
					if (lineId == 8146) {
						lineId += 1;
					}
					player.getActionSender().sendString(line, lineId);
					lineId++;
				} else if (line.equals("[END]")) {
					endOfFile = true;
				}
			}
			questFile.close();
			player.getActionSender().sendInterface(8134);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void completeQuest(int questId, String reward1, String reward2, String reward3, String reward4, String reward5, int itemId) {
		player.getActionSender().sendInterface(12140);
		player.getActionSender().sendString("You've completed '" + (String) questData[questId][0] + "'!", 12144);
		player.getActionSender().sendString(questData[questId][3] + " quest point(s)!", 12150);
		player.getActionSender().sendString(reward1, 12151);
		player.getActionSender().sendString(reward2, 12152);
		player.getActionSender().sendString(reward3, 12153);
		player.getActionSender().sendString(reward4, 12154);
		player.getActionSender().sendString(reward5, 12155);
		player.getActionSender().sendItemOnInterface(12145, 250, itemId);
		player.setQuestPoints(player.getQuestPoints() + (Integer) questData[questId][3]);
		player.getActionSender().sendString("QP: " + player.getQuestPoints(), 3985);
		player.getActionSender().sendString("" + player.getQuestPoints(), 12147);
		player.getActionSender().sendString("@gre@" + questData[questId][0], QUEST_IDS[questId]);
		setQuestStage(questId, (Integer) questData[questId][2]);
	}

	/*public void executeTimedDialogue(int tickCount, int startId, final int amount) {
		player.getDialogue().setNextDialogue(startId);
		player.getDialogue().sendDialogue(player.getClickId(), player.getDialogue().getNextDialogue());
		World.submit(new Tick(10) {
			int currentAmount = 1;
			@Override
			public void execute() {
				player.getDialogue().sendDialogue(player.getClickId(), player.getDialogue().getNextDialogue());
				currentAmount++;
				if (currentAmount == amount) {
					stop();
					if (player.getDialogue().getNextDialogue() == 0) {
						player.getActionSender().removeInterfaces();
					}
				}
			}
		});
	}*/

	public void setQuestStage(int questId, int newQuestStage) {
		questData[questId][1] = newQuestStage;
		if (questData[questId][1] == questData[questId][2]) {
			player.getActionSender().sendString("@gre@" + questData[questId][0], QUEST_IDS[questId]);
		} else if ((Integer) questData[questId][1] > 0) {
			player.getActionSender().sendString("@yel@" + questData[questId][0], QUEST_IDS[questId]);
		} else {
			player.getActionSender().sendString("" + questData[questId][0], QUEST_IDS[questId]);
		}
	}

	public int getQuestStage(int questId) {
		return (Integer) questData[questId][1];
	}

	public void setQuestDialogueTicking(boolean questDialogueTicking) {
		this.questDialogueTicking = questDialogueTicking;
	}

	public boolean isQuestDialogueTicking() {
		return questDialogueTicking;
	}

}