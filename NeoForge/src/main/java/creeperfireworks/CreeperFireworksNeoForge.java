package creeperfireworks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import creeperfireworks.network.CreeperFireworksNeoForgeNetwork;
import creeperfireworks.network.LaunchFireworksPacket;

@Mod(CreeperFireworks.MODID)
public class CreeperFireworksNeoForge {

    public CreeperFireworksNeoForge(IEventBus eventBus) {
        CreeperFireworks.init();
        eventBus.addListener(this::registerPayloadHandler);
    }

    private void registerPayloadHandler(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(CreeperFireworks.MODID).versioned("1.0");

        registrar.playToClient(LaunchFireworksPacket.TYPE, LaunchFireworksPacket.STREAM_CODEC,
            CreeperFireworksNeoForgeNetwork.getInstance()::handleFireworksPacket);
    }

}

