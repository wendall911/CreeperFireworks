package creeperfireworks.platform;

import net.minecraft.world.entity.monster.Creeper;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.network.PacketDistributor;

import org.joml.Vector3f;

import creeperfireworks.network.LaunchFireworksPacket;
import creeperfireworks.platform.services.IPlatform;

public class NeoForgePlatform implements IPlatform {

    @Override
    public boolean isModLoaded(String name) {
        return ModList.get().isLoaded(name);
    }

    @Override
    public boolean isPhysicalClient() {
        return FMLLoader.getDist() == Dist.CLIENT;
    }

    @Override
    public void sendLaunchFireworksPacket(Creeper creeper) {
        Vector3f location = creeper.getEyePosition().toVector3f();

        PacketDistributor.sendToPlayersTrackingEntity(creeper, new LaunchFireworksPacket(location));
    }

}

