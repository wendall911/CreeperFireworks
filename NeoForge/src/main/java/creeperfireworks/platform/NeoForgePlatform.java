package creeperfireworks.platform;

import net.minecraft.world.entity.monster.Creeper;

import net.neoforged.neoforge.network.PacketDistributor;

import org.joml.Vector3f;

import creeperfireworks.network.LaunchFireworksPacket;
import creeperfireworks.platform.services.IPlatform;

public class NeoForgePlatform implements IPlatform {

    @Override
    public void sendLaunchFireworksPacket(Creeper creeper) {
        Vector3f location = creeper.getEyePosition().toVector3f();

        PacketDistributor.sendToPlayersTrackingEntity(creeper, new LaunchFireworksPacket(location));
    }

}

