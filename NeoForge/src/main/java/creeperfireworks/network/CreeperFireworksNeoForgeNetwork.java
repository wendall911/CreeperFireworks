package creeperfireworks.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

import net.neoforged.neoforge.network.handling.IPayloadContext;

import creeperfireworks.util.FireworksHelper;

public class CreeperFireworksNeoForgeNetwork {

    public static final CreeperFireworksNeoForgeNetwork INSTANCE = new CreeperFireworksNeoForgeNetwork();

    public static CreeperFireworksNeoForgeNetwork getInstance() {
        return INSTANCE;
    }

    public void handleFireworksPacket(LaunchFireworksPacket msg, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;

            if (level != null) {
                FireworksHelper.launchFireworks(level, msg.location());
            }
        });
    }

}
