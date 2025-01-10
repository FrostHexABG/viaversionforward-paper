package com.frosthex.viaversionforwardpaper;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public final class ViaVersionForwardPaperPlugin extends JavaPlugin implements PluginMessageListener, Listener, CommandExecutor {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "viaversion_forward_velocity:protocol_version", this);
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equals("viaversion_forward_velocity:protocol_version")) {
            return;
        }

        int protocolVersion = -1;

        try {
            protocolVersion = byteArrayToInt(message);
        } catch (BufferUnderflowException e) {
            e.printStackTrace();
        }

        this.getLogger().info("Parsed protocol version " + protocolVersion + " for " + player.getName() + " (" + player.getUniqueId() + ").");
        ViaVersionForwardDataManager.setPlayerProtocolVersion(player.getUniqueId(), protocolVersion);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        ViaVersionForwardDataManager.removePlayerProtocolVersion(e.getPlayer().getUniqueId());
    }

    private static byte[] intToByteArray(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(value);
        return buffer.array();
    }

    private static int byteArrayToInt(byte[] byteArray) {
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        return buffer.getInt();
    }

}
