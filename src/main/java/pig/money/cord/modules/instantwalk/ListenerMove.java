package pig.money.cord.modules.instantwalk;

import me.earth.earthhack.api.cache.ModuleCache;
import me.earth.earthhack.impl.event.events.movement.MoveEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import me.earth.earthhack.impl.modules.Caches;
import me.earth.earthhack.impl.modules.movement.packetfly.PacketFly;
import me.earth.earthhack.impl.modules.player.freecam.Freecam;
import me.earth.earthhack.impl.modules.player.ncptweaks.NCPTweaks;
import me.earth.earthhack.impl.util.minecraft.MovementUtil;

final class ListenerMove extends ModuleListener<InstantWalk, MoveEvent> {
    private static final ModuleCache<PacketFly> PACKET_FLY = Caches.getModule(PacketFly.class);
    private static final ModuleCache<Freecam> FREECAM = Caches.getModule(Freecam.class);
    private static final ModuleCache<NCPTweaks> NCP_TWEAKS = Caches.getModule(NCPTweaks.class);

    public ListenerMove(InstantWalk module) {
        super(module, MoveEvent.class);
    }

    @Override
    public void invoke(MoveEvent event) {
        if (PACKET_FLY.isEnabled() || FREECAM.isEnabled() || NCP_TWEAKS.isEnabled() && NCP_TWEAKS.get().isSpeedStopped()) {return;}
        if (mc.player.isElytraFlying()) return;
        if (mc.player.isInWater() || mc.player.isInLava()) return;
        if (module.autoStep.getValue()) mc.player.stepHeight = module.stepHeight.getValue();
        MovementUtil.strafe(event, module.customSpeed.getValue() == 0 ? MovementUtil.getSpeed(module.slow.getValue()) : (module.customSpeed.getValue() / 5));
    }
}