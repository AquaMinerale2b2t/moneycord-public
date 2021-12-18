package pig.money.cord.modules.fastfall;

import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import pig.money.cord.api.Api;

final class ListenerTick extends ModuleListener<FastFall, TickEvent>
{
    public ListenerTick(FastFall module)
    {
        super(module, TickEvent.class);
    }

    @Override
    public void invoke(TickEvent event) {
        if (Api.nullCheck()) return;
        if (mc.player.onGround) mc.player.motionY -= module.speed.getValue() / 5;
    }
}