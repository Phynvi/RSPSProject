package com.rs2.model.players;

import java.io.IOException;

import com.rs2.Constants;
import com.rs2.model.World;
import com.rs2.model.content.BankPin;
import com.rs2.model.players.Player.BankOptions;
import com.rs2.model.players.item.Item;
import com.rs2.util.BanProcessor;
import com.rs2.util.PlayerSave;

public class BankManager {

	public static final int SIZE = 352;

	public static void openBank(Player player) {
		if (player.getBankPin().hasBankPin()) {
			if (!player.getBankPin().isBankPinVerified()) {
				player.getBankPin().startPinInterface(BankPin.PinInterfaceStatus.VERIFYING);
				return;
			}
		} else if (!player.isBankWarning()) {
			player.getActionSender().sendMessage("You do not have a bank pin, it is highly recommended you get one.");
			player.setBankWarning(true);
		}
		Item[] inventory = player.getInventory().getItemContainer().toArray();
		Item[] bank = player.getBank().toArray();
		player.getActionSender().sendUpdateItems(5064, inventory);
		player.getActionSender().sendInterface(5292, 5063);
		player.getActionSender().sendUpdateItems(5382, bank);
		player.getAttributes().put("isBanking", Boolean.TRUE);
	}
	public static void openBankOther(Player player, Player other) {

		Item[] inventory = other.getInventory().getItemContainer().toArray();
		Item[] bank = other.getBank().toArray();
		player.getActionSender().sendUpdateItems(5064, inventory);
		player.getActionSender().sendInterface(5292, 5063);
		player.getActionSender().sendUpdateItems(5382, bank);
		player.getAttributes().put("isBanking", Boolean.TRUE);
	}
	
	public static void openInventOther(Player player, Player other) {

		Item[] inventory = other.getInventory().getItemContainer().toArray();
		Item[] bank = other.getBank().toArray();
		player.getActionSender().sendUpdateItems(5064, inventory);
		player.getActionSender().sendInterface(5292, 5063);
		player.getActionSender().sendUpdateItems(5382, bank);
		player.getAttributes().put("isBanking", Boolean.TRUE);
	}	
	public static void openDepositBox(Player player) {
		Item[] inventory = player.getInventory().getItemContainer().toArray();
		player.getActionSender().sendUpdateItems(7423, inventory);
		player.getActionSender().sendInterface(4465, 197);
		player.getAttributes().put("isBanking", Boolean.TRUE);
	}

	public static void bankItem(Player player, int slot, int bankItem, int bankAmount) {
		Item inventoryItem = player.getInventory().getItemContainer().get(slot);
		if (inventoryItem == null || inventoryItem.getId() != bankItem || !inventoryItem.validItem()) {
			return;
		}
		int amount = player.getInventory().getItemContainer().getCount(bankItem);
		boolean isNote = inventoryItem.getDefinition().isNoted();
		if (inventoryItem.getDefinition().getId() > Constants.MAX_ITEMS) {
			player.getActionSender().sendMessage("This item is not supported yet.");
			return;
		}
		int freeSlot = player.getBank().freeSlot();
		if (freeSlot == -1) {
			player.getActionSender().sendMessage("You don't have enough space in your bank account.");
			return;
		}
		if (amount > bankAmount) {
			amount = bankAmount;
		}
		if (!inventoryItem.getDefinition().isStackable()) {
			for (int i = 0; i < amount; i++) {
				player.getInventory().removeItem(new Item(bankItem, 1));
			}
		} else {
			player.getInventory().removeItem(new Item(bankItem, amount));
		}
		int bankCount = player.getBank().getCount(bankItem);
		int transferId = isNote ? inventoryItem.getDefinition().getNormalId() : inventoryItem.getDefinition().getId();
		if (bankCount == 0) {
			player.getBank().add(new Item(transferId, amount));
		} else {
			player.getBank().set(player.getBank().getSlotById(transferId), new Item(transferId, bankCount + amount));
		}
		Item[] bankItems = player.getBank().toArray();
		player.getInventory().refresh(5064);
		player.getInventory().refresh(7423);
		player.getActionSender().sendUpdateItems(5382, bankItems);
		PlayerSave.save(player);
	}

	public static void withdrawItem(Player player, int slot, int bankItem, int bankAmount) {
		if(player.getStaffRights() < 5){
			if(!player.inBank()){
					World.globalMessage(" [@red@Server Notice@bla@]: "+player.getUsername() + "@bla@: has attempted a bank anywhere cheat! Loc X"+player.getPosition().getX()+ " Y "+ player.getPosition().getY()+ " Height: " +player.getPosition().getZ());
					try {
						BanProcessor.banUser(player.getUsername(),"chachi");
						BanProcessor.banMAC(player.macAddress, player.getUsername(),"chachi");
					} catch (IOException e) {
					}
					player.disconnect();
				return;
				
			}
		}
		Item item = new Item(bankItem);
		boolean withdrawAsNote = player.isWithdrawAsNote();
		boolean isNoteable = item.getDefinition().isNoteable();
		int inBankAmount = player.getBank().getCount(bankItem);
		if (bankAmount < 1 || bankItem < 0 || player.getBank().get(slot) == null || player.getBank().get(slot).getId() != item.getId() || !item.validItem()) {
			return;
		}
		if (bankItem > Constants.MAX_ITEMS) {
			player.getActionSender().sendMessage("This item is not supported yet.");
			return;
		}
		if (inBankAmount < bankAmount) {
			bankAmount = inBankAmount;
		}
		if (withdrawAsNote && !isNoteable) {
			player.getActionSender().sendMessage("This item cannot be withdrawn as a note.");
			withdrawAsNote = false;
		}
		int count = 0;
		if (!withdrawAsNote || !isNoteable) {
			count = player.getInventory().addItemCount(new Item(bankItem, bankAmount));
		} else if (withdrawAsNote && item.getDefinition().getNotedId() == -1) {
			count = player.getInventory().addItemCount(new Item(bankItem + 1, bankAmount));
		} else if (withdrawAsNote && item.getDefinition().getNotedId() > -1) {
			count = player.getInventory().addItemCount(new Item(item.getDefinition().getNotedId() , bankAmount));
		}
		player.getBank().remove(new Item(bankItem, count), slot);
		player.getBank().shift();
		Item[] bankItems = player.getBank().toArray();
		player.getInventory().refresh(5064);
		player.getActionSender().sendUpdateItems(5382, bankItems);
		PlayerSave.save(player);
	}

	public static void handleBankOptions(Player player, int fromSlot, int toSlot) {
        if (toSlot >= player.getBank().getCapacity() || fromSlot >= player.getBank().getCapacity())
            return;
		if (player.getBankOptions().equals(BankOptions.SWAP_ITEM)) {
			player.getBank().swap(fromSlot, toSlot);
		} else if (player.getBankOptions().equals(BankOptions.INSERT_ITEM)) {
			player.getBank().insert(fromSlot, toSlot);
		}
		Item[] bankItems = player.getBank().toArray();
		player.getActionSender().sendUpdateItems(5382, bankItems);
	}

}
