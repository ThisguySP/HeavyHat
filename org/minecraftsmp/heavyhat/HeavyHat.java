package org.minecraftsmp.heavyhat;

import org.bukkit.plugin.java.JavaPlugin;

import org.minecraftsmp.heavyhat.HatDonListener;

public final class HeavyHat extends JavaPlugin {
	
	private HatDonListener HatDonListener;
	public void onEnable() {
		HatDonListener = new HatDonListener();
		getServer().getPluginManager().registerEvents(HatDonListener, this);
	}
	
	public void onDisable() {
	}
}
