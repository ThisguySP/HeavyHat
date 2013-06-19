package org.minecraftsmp.heavyhat;

import org.bukkit.plugin.java.JavaPlugin;

import org.minecraftsmp.heavyhat.HatDonListener;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class HeavyHat extends JavaPlugin {
	private boolean d;
	private HatDonListener HatDonListener;
	
	public void onEnable() {
		HatDonListener = new HatDonListener(this);
		getServer().getPluginManager().registerEvents(HatDonListener, this);
		if (!Files.exists(Paths.get("plugins/HeavyHat/config.yml"))) {
			this.saveDefaultConfig();
		}
		d = this.getConfig().getBoolean("debug");
		dnote("Enabled in debug mode.");
	}
	
	public void onDisable() {
		HatDonListener.releaseListeners();
	}
	
	
	
	public void note(String message) {
		getLogger().info(message);
	}
	
	public void dnote(String message) {
		if (d) {getLogger().info(message);}
	}
}
