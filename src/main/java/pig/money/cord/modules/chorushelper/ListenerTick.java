package pig.money.cord.modules.chorushelper;

import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import pig.money.cord.api.Api;

final class ListenerTick extends ModuleListener<ChorusHelper, TickEvent>
{
    public ListenerTick(ChorusHelper module)
    {
        super(module, TickEvent.class);
    }

    @Override
    public void invoke(TickEvent event) {
        if (Api.nullCheck()) return;
        if (module.doDelay){
            if (module.timer.passed(module.delay.getValue() * 1000)){
                module.pos = null;
                module.timer.reset();
                module.doDelay = false;
            }
        }
    }
}