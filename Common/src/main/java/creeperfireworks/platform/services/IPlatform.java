package creeperfireworks.platform.services;

import net.minecraft.world.entity.monster.Creeper;

public interface IPlatform {

    boolean isModLoaded(String name);

    boolean isPhysicalClient();

    void sendLaunchFireworksPacket(Creeper creeper);

}
