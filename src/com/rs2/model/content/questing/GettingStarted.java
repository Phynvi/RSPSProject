package com.rs2.model.content.questing;

import com.rs2.model.players.Player;

/**
 * By Mikey` of Rune-Server
 */
public class GettingStarted {

	public static void sendDialogue(final Player player, int dialogueId) {
		switch (dialogueId) {
			case 1 :
				if (player.getQuesting().getQuestStage(0) == 0) {
					sendDialogue(player, 2);
				}
				if (player.getQuesting().getQuestStage(0) == 1) {
					sendDialogue(player, 4);
				}
				if (player.getQuesting().getQuestStage(0) == 2) {
					sendDialogue(player, 4);
				}
				break;
			case 2 :
				player.getDialogue().sendNpcChat("Hello, " + player.getUsername() + ".", "What can I help you with?", 591);
				player.getDialogue().setNextChatId(3);
				break;
			case 3 :
				player.getDialogue().sendOption("Tell me about the server.", "Nothing, just admiring you.");
				//player.getDialogue().setOptionId(2);
				player.getDialogue().setNextChatId(0);
				break;
			case 4 :
				if (player.getQuesting().getQuestStage(0) == 2) {
					player.getDialogue().sendPlayerChat("Tell me about the server again.", 591);
					player.getDialogue().setNextChatId(9);
				} else {
					player.getQuesting().setQuestStage(0, 1);
					player.getDialogue().sendPlayerChat("Tell me about the server.", 591);
					player.getDialogue().setNextChatId(9);
				}
				break;
			case 5 :
				player.getDialogue().sendPlayerChat("Nothing, just admiring you.", 591);
				player.getDialogue().setNextChatId(6);
				break;
			case 6 :
				player.getDialogue().sendNpcChat("Well.. that's.. odd..", 591);
				player.getDialogue().setNextChatId(7);
				break;
			case 7 :
				player.getDialogue().sendPlayerChat("Yes..", "Yes, it is.", 605);
				player.getDialogue().setNextChatId(8);
				break;
			case 8 :
				player.getDialogue().sendStatement("He backs away slowly.");
				player.getDialogue().setNextChatId(0);
				break;
			case 9 :
				//player.getQuesting().executeTimedDialogue(3, 10, 4);
				break;
			case 10 :
				player.getDialogue().sendTimedNpcChat("This source is based off Azure (RuneSource).", "It has been modified by Mikey` of Rune-Server.", 591);
				player.getDialogue().setNextChatId(11);
				break;
			case 11 :
				player.getDialogue().sendTimedNpcChat("There's no way to test content simply by playing.", "Search classes and spawn items to test content.", 591);
				player.getDialogue().setNextChatId(12);
				break;
			case 12 :
				player.getDialogue().sendTimedNpcChat("If you have any questions, please contact Mikey` at", "metallic_mike@yahoo.com (on MSN).", 591);
				player.getDialogue().setNextChatId(13);
				break;
			case 13 :
				player.getDialogue().sendPlayerChat("Thanks!", 591);
				if (player.getQuesting().getQuestStage(0) == 2) {
					player.getDialogue().setNextChatId(0);
				} else {
					player.getDialogue().setNextChatId(14);
				}
				break;
			case 14 :
				player.getQuesting().completeQuest(0, "", "", "", "", "",0);
				player.getDialogue().setNextChatId(0);
				break;
		}
	}

}
