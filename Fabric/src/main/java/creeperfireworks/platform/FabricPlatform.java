package creeperfireworks.platform;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Creeper;

import creeperfireworks.network.LaunchFireworksPacket;
import creeperfireworks.platform.services.IPlatform;

public class FabricPlatform implements IPlatform {

    @Override
    public boolean isModLoaded(String name) {
        return FabricLoader.getInstance().isModLoaded(name);
    }

	@Override
    public boolean isPhysicalClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    @Override
    public void sendLaunchFireworksPacket(Creeper creeper) {
        for (ServerPlayer sp : PlayerLookup.tracking(creeper)) {
            ServerPlayNetworking.send(sp, new LaunchFireworksPacket(creeper.getEyePosition().toVector3f()));
        }
    }

}

