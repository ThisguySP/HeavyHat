package org.minecraftsmp.heavyhat;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import org.bukkit.entity.Player;
import org.bukkit.entity.HumanEntity;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class HatDonListener implements Listener {
	private HeavyHat plugin;
	
	public HatDonListener(HeavyHat plugin) {
		this.plugin = plugin;
	}
	
	public void releaseListeners() {
		HandlerList.unregisterAll(this);
	}
	
	public boolean isArmorEmpty(PlayerInventory inv) {
		for (int i = 0; i < inv.getArmorContents().length; i++) {
			if (inv.getArmorContents()[i].getTypeId() != 0) {return false;}
		}
		return true;
	}
	
	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player) {
			if (isArmorEmpty(event.getWhoClicked().getInventory())) {
				((Player)event.getWhoClicked()).setAllowFlight(false);
				plugin.note(((Player)event.getWhoClicked()).getName() + " tried to put on armor while flying.");
			}
		}
	}
	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		if (!(
			isArmorEmpty(event.getPlayer().getInventory())
		)) {
			event.setCancelled(true);
			event.getPlayer().setAllowFlight(false);
			plugin.note(event.getPlayer().getName() + " tried to fly in armor.");
		}
	}
}
