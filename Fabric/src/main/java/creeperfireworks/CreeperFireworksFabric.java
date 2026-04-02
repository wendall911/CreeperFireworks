package creeperfireworks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

import creeperfireworks.network.LaunchFireworksPacket;

public class CreeperFireworksFabric implements ModInitializer {

	@Override
    public void onInitialize() {
        PayloadTypeRegistry.clientboundPlay().register(LaunchFireworksPacket.TYPE, LaunchFireworksPacket.STREAM_CODEC);
    }

}

