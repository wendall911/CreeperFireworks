package creeperfireworks.platform.services;

import net.minecraft.world.entity.monster.Creeper;

public interface IPlatform {

    void sendLaunchFireworksPacket(Creeper creeper);

}
