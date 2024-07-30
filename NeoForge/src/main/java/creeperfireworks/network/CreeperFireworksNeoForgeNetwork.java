package creeperfireworks.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import creeperfireworks.util.FireworksHelper;

public class CreeperFireworksNeoForgeNetwork {

    public static final CreeperFireworksNeoForgeNetwork INSTANCE = new CreeperFireworksNeoForgeNetwork();

    public static CreeperFireworksNeoForgeNetwork getInstance() {
        return INSTANCE;
    }

    public void handleFireworksPacket(LaunchFireworksPacket msg, PlayPayloadContext ctx) {
        ctx.workHandler().submitAsync(() -> {
            ClientLevel level = Minecraft.getInstance().level;

            if (level != null) {
                FireworksHelper.launchFireworks(level, msg.location());
            }
        });
    }

}
