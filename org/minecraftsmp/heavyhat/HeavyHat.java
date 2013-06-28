package org.minecraftsmp.heavyhat;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.entity.Player;

import org.minecraftsmp.heavyhat.HatDonListener;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class HeavyHat extends JavaPlugin {
	private boolean d, n, i;
	private String inflight, preflight;
	private HatDonListener hatlistener;
	
	public void onEnable() {
		hatlistener = new HatDonListener(this);
		getServer().getPluginManager().registerEvents(hatlistener, this);
		if (!Files.exists(Paths.get("plugins/HeavyHat/config.yml"))) {
			this.saveDefaultConfig();
		}
		d = this.getConfig().getBoolean("verbosity.debug");
		n = this.getConfig().getBoolean("verbosity.normal");
		i = this.getConfig().getBoolean("verbosity.ingame");
		preflight = this.getConfig().getString("lang.preflight").replace("&", "§");
		inflight = this.getConfig().getString("lang.inflight").replace("&", "§");
		dnote("Enabled debug messages.");
		if (!n) {getLogger().info("Normal-level console messages supressed.");}
	}
	
	public void onDisable() {
		hatlistener.releaseListeners();
	}
	
	
	
	public String tooHeavyToTakeOff() {
		return preflight;
	}
	
	public String tooHeavyToStayAloft() {
		return inflight;
	}
	
	public void msg(Player player, String message) {
		player.sendRawMessage(message);
	}
	
	public void note(String message) {
		if (n) {getLogger().info(message);}
	}
	
	public void dnote(String message) {
		if (d) {getLogger().info(message);}
	}
}
