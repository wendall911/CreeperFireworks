package creeperfireworks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;

import creeperfireworks.network.CreeperFireworksNeoForgeNetwork;
import creeperfireworks.network.LaunchFireworksPacket;

@Mod(CreeperFireworks.MODID)
public class CreeperFireworksNeoForge {

    public CreeperFireworksNeoForge(IEventBus eventBus) {
        CreeperFireworks.init();
        eventBus.addListener(this::registerPayloadHandler);
    }

    private void registerPayloadHandler(final RegisterPayloadHandlerEvent event) {
        event.registrar(CreeperFireworks.MODID).play(LaunchFireworksPacket.ID, LaunchFireworksPacket::new,
            handler -> handler.client(CreeperFireworksNeoForgeNetwork.getInstance()::handleFireworksPacket));
    }

}

