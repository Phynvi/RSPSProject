package com.rs2.model.content.tutorialisland;

import com.rs2.model.Position;
import com.rs2.model.content.dialogue.Dialogues;
import com.rs2.model.players.Player;
import com.rs2.model.players.item.Item;
import com.rs2.model.players.item.ItemDefinition;


public class NewComersSide {

	private Player player;

	public NewComersSide(Player player) {
		this.player = player;
	}

	private int tutorialIslandStage = 99;
	private int progressValue = 1;

	public void startTutorialIsland() {
		if (tutorialIslandStage == 0) {
			player.getActionSender().hideAllSideBars();
			player.getActionSender().sendInterface(3559);
			setTutorialIslandStage(1, true);
			player.getBank().add(new Item(995, 25));
		} else {
			updateInterface(true);
		}
	}

	public boolean sendDialogue() {
		StagesLoader stagesLoader = StagesLoader.forId(tutorialIslandStage);
		if (stagesLoader == null)
			return false;
		if (stagesLoader.getDialogueId() == 0) {
			player.getDialogue().sendStatement("Follow the instructions to continue!");
			player.setClickId(0);
			return true;
		}
		Dialogues.startDialogue(player, stagesLoader.getDialogueId());
		return true;

	}

	public boolean sendGiveItemsInstructor() {
		StagesLoader stagesLoader = StagesLoader.forId(tutorialIslandStage);
		if (stagesLoader == null || stagesLoader.getTutItemsInvolved() == null)
			return false;
		for (Item item : stagesLoader.getTutItemsInvolved()) {
			if (item == null)
				continue;
			if (player.getInventory().playerHasItem(item.getId(), item.getCount())) {
				player.getDialogue().sendStatement("Follow the instructions to continue!");
				player.setClickId(0);
				return true;
			}
		}
		switch (stagesLoader.getTutItemsInvolved().length) {
			case 1 :
				player.getInventory().addItem(stagesLoader.getTutItemsInvolved()[0]);
				player.getDialogue().sendGiveItemNpc(stagesLoader.getTutorName() + " gives you " + (stagesLoader.getTutItemsInvolved()[0].getCount() > 1 ? "some" : "a") + " " + ItemDefinition.forId(stagesLoader.getTutItemsInvolved()[0].getId()).getName() + "!", stagesLoader.getTutItemsInvolved()[0]);
				player.setClickId(0);
				break;
			case 2 :
				player.getInventory().addItem(stagesLoader.getTutItemsInvolved()[0]);
				player.getInventory().addItem(stagesLoader.getTutItemsInvolved()[1]);
				player.getDialogue().sendGiveItemNpc(stagesLoader.getTutorName() + " gives you " + (stagesLoader.getTutItemsInvolved()[0].getCount() > 1 ? "some" : "a") + " " + ItemDefinition.forId(stagesLoader.getTutItemsInvolved()[0].getId()).getName(), "and " + (stagesLoader.getTutItemsInvolved()[1].getCount() > 1 ? "some" : "a") + " " + ItemDefinition.forId(stagesLoader.getTutItemsInvolved()[1].getId()).getName() + "!", stagesLoader.getTutItemsInvolved()[0], stagesLoader.getTutItemsInvolved()[1]);
				player.setClickId(0);
				break;
		}
		return true;
	}

	public void addStarterItems() {
		player.getInventory().getItemContainer().clear();
		player.getEquipment().getItemContainer().clear();
		player.getInventory().refresh();
		player.getEquipment().refresh();
		Item[] starterItems = {new Item(1351), new Item(590), new Item(303), new Item(316, 25), new Item(1925), new Item(1931), new Item(2309), new Item(1265), new Item(1205), new Item(1277), new Item(1171), new Item(841), new Item(882, 10), new Item(556, 10), new Item(558, 10), new Item(555, 10), new Item(557, 10), new Item(559, 10)};
		for (Item item : starterItems)
			player.getInventory().addItem(item);
		/*	player.getInventory().addItem(new Item(995, 100000));
			player.getInventory().addItem(new Item(841, 1));
			player.getInventory().addItem(new Item(882, 50));			// Starting Items
			player.getInventory().addItem(new Item(554, 50));
			player.getInventory().addItem(new Item(555, 50));
			player.getInventory().addItem(new Item(556, 50));
			player.getInventory().addItem(new Item(557, 50));
			player.getInventory().addItem(new Item(558, 50));
			player.getInventory().addItem(new Item(563, 50));
			player.getInventory().addItem(new Item(380, 100));
			player.getInventory().addItem(new Item(1321, 1));
			player.getInventory().addItem(new Item(1325, 1));
			player.getInventory().addItem(new Item(1712, 1));	*/
	}

	public boolean handleObjectClicking(int objectId, Position objectPosition) {
		if (!isInTutorialIslandStage())
			return false;
		TutorialObjects tutorialObjects = TutorialObjects.forId(tutorialIslandStage);
		if (tutorialObjects == null)
			return false;
		for (int i = 0; i < tutorialObjects.getObjectId().length; i++) {
			if (objectId == tutorialObjects.getObjectId()[i] && objectPosition.equals(tutorialObjects.getObjectPosition()[i]))
				tutorialObjects.applyObjectClicking(player);
		}
		return false;

	}

	public void updateInterface(boolean send) {
		StagesLoader stagesLoader = StagesLoader.forId(tutorialIslandStage);
		if (stagesLoader == null) {
			player.getActionSender().removeInterfaces();
			return;
		}
		if (tutorialIslandStage >= 43)
			player.getEquipment().sendWeaponInterface();

		stagesLoader.sendInterfaces(player, send);
		if (isInTutorialIslandStage())
			sendProgressInterface();
	}

	public void sendProgressInterface() {
		player.getActionSender().sendConfig(406, progressValue);
		player.getActionSender().sendFrame171(1, 12224);
		player.getActionSender().sendFrame171(1, 12225);
		player.getActionSender().sendFrame171(1, 12226);
		player.getActionSender().sendFrame171(1, 12227);
		player.getActionSender().sendFrame171(0, 12161);
		player.getActionSender().sendString("% Done", 12224);
		player.getActionSender().sendWalkableInterface(8680);

	}

	public void setTutorialIslandStage(int tutorialIslandStage, boolean update) {
		this.tutorialIslandStage = tutorialIslandStage;
		if (update)
			updateInterface(update);
	}

	public void setProgressValue(int progressValue) {
		this.progressValue = progressValue;
	}

	public int getTutorialIslandStage() {
		return tutorialIslandStage;
	}

	public int getProgressValue() {
		return progressValue;
	}

	public boolean isInTutorialIslandStage() {
		return false;//tutorialIslandStage < 100;
	}
}
