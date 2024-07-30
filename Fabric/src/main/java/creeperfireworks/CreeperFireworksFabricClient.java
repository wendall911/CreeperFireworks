package creeperfireworks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

import creeperfireworks.network.LaunchFireworksPacket;
import creeperfireworks.util.FireworksHelper;

public class CreeperFireworksFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(LaunchFireworksPacket.TYPE,
            ((payload, context) -> {
                Minecraft mc = Minecraft.getInstance();
                ClientLevel level = mc.level;

                mc.execute(() -> FireworksHelper.launchFireworks(level, payload.location()));
            })
        );
    }

}
