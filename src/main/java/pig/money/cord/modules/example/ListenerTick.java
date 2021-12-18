package pig.money.cord.modules.example;

import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import pig.money.cord.api.Api;

final class ListenerTick extends ModuleListener<Example, TickEvent>
{
    public ListenerTick(Example module)
    {
        super(module, TickEvent.class);
    }

    @Override
    public void invoke(TickEvent event) {
        if (Api.nullCheck()) return;
        module.onTick();
    }
}