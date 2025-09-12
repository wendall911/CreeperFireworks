package creeperfireworks.platform;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.phys.Vec3;

import net.minecraftforge.network.PacketDistributor;

import creeperfireworks.network.CreeperFireworksNeoForgeNetwork;
import creeperfireworks.network.LaunchFirworksPacket;
import creeperfireworks.platform.services.IPlatform;

public class NeoForgePlatform implements IPlatform {

    @Override
    public void sendLaunchFireworksPacket(Creeper creeper) {
        Vec3 location = creeper.getEyePosition();
        PacketDistributor.TargetPoint targetPoint = new PacketDistributor.TargetPoint(location.x(), location.y(), location.z(), 300, creeper.level().dimension());

        CreeperFireworksNeoForgeNetwork.INSTANCE.send(
            PacketDistributor.TRACKING_ENTITY.with(() -> creeper),
            new LaunchFirworksPacket(location)
        );
    }

}
