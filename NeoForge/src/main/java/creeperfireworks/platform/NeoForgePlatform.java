package creeperfireworks.platform;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.Vec3;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.network.PacketDistributor;

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
        Vec3 location = creeper.getEyePosition();

        PacketDistributor.TRACKING_ENTITY.with(creeper).send(new LaunchFireworksPacket(location));
    }

}

