package com.frosthex.viaversionforwardpaper;

import java.util.HashMap;
import java.util.UUID;

public class ViaVersionForwardDataManager {

    private static HashMap<UUID, Integer> playerProtocolVersionMap = new HashMap<UUID, Integer>();

    public static void setPlayerProtocolVersion(UUID uuid, int protocolVersion) {
        playerProtocolVersionMap.put(uuid, protocolVersion);
    }

    public static int getPlayerProtocolVersion(UUID uuid) {
        return playerProtocolVersionMap.getOrDefault(uuid, -1);
    }

    public static void removePlayerProtocolVersion(UUID uuid) {
        playerProtocolVersionMap.remove(uuid);
    }

}
