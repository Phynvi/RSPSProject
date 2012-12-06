package com.rs2.util;

import java.io.IOException;

import com.rs2.model.content.skills.magic.Spell;
import com.rs2.model.content.skills.magic.SpellBook;
import com.rs2.model.players.item.Item;
import com.rs2.model.players.item.ItemDefinition;

/**
 *
 */
public class SpellChecker {

	public static void main(String[] args) throws IOException {
		ItemDefinition.init();
		for (Spell spell : SpellBook.MODERN.getSpells().values()) {
			System.out.print(spell.name() + " [");
			for (Item item : spell.getRunesRequired()) {
				System.out.print(ItemDefinition.forId(item.getId()).getName() + "=" + item.getCount() + ", ");
			}
			System.out.println("]");
		}
	}
}
