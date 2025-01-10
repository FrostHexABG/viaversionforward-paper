package com.frosthex.viaversionforwardpaper.api;

import com.frosthex.viaversionforwardpaper.ViaVersionForwardDataManager;

import java.util.HashMap;
import java.util.UUID;

public class ViaVersionForwardAPI {

    /**
     * Provides the players protocol version that has been received from ViaVersionForward on the proxy.
     * @param playerUuid
     * @return Returns the given players protocol version. Returns -1 if the players protocol version is unknown.
     */
    public static int getPlayerProtocolVersion(UUID playerUuid) {
        return ViaVersionForwardDataManager.getPlayerProtocolVersion(playerUuid);
    }

}
