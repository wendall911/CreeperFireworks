package creeperfireworks.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FireworkParticles;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.phys.Vec3;

import creeperfireworks.CreeperFireworks;
import creeperfireworks.config.ConfigHandler;

public class FireworksHelper {

    public static void launchFireworks(ClientLevel level, Vec3 location) {
        if (showFireworks()) {
            ParticleEngine particleEngine = Minecraft.getInstance().particleEngine;

            particleEngine.add(
                new FireworkParticles.Starter(
                    level,
                    location.x(),
                    location.y() + ConfigHandler.Client.getFireworksHeight(),
                    location.z(),
                    0, 0, 0,
                    particleEngine,
                    getConfiguredFirework()
                )
            );
        }
    }

    private static CompoundTag getConfiguredFirework() {
        CompoundTag holderTag = new CompoundTag();
        CompoundTag fireworkTag = new CompoundTag();
        ListTag holderList = new ListTag();

        fireworkTag.putIntArray("Colors", ConfigHandler.Client.getColorsList());
        fireworkTag.putBoolean("Flicker", ConfigHandler.Client.fireworksFlicker());
        fireworkTag.putByte("Type", (byte) ConfigHandler.Client.getFireworksShape());
        holderList.add(fireworkTag);
        holderTag.put("Explosions", holderList);

        return holderTag;
    }

    private static boolean showFireworks() {
        int fireworksChance = ConfigHandler.Client.fireworksChance();

        return fireworksChance > 0 && CreeperFireworks.RANDOM.nextInt(100) <= fireworksChance;
    }

}
