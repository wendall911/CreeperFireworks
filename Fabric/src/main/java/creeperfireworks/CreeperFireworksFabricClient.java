package creeperfireworks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;

import creeperfireworks.util.FireworksHelper;

public class CreeperFireworksFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(CreeperFireworksFabric.LAUNCH_FIREWORKS,
            ((client, handler, buf, responseSender) -> {
                ClientLevel level = Minecraft.getInstance().level;
                Vec3 location = new Vec3(buf.readVector3f());

                client.execute(() -> FireworksHelper.launchFireworks(level, location));
            })
        );
    }

}
