package pig.money.cord.modules.chorushelper;

import me.earth.earthhack.impl.event.events.render.Render3DEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;

final class ListenerRender extends ModuleListener<ChorusHelper, Render3DEvent>
{
    public ListenerRender(ChorusHelper module)
    {
        super(module, Render3DEvent.class);
    }

    @Override
    public void invoke(Render3DEvent event)
    {
        module.onRender3D();
    }

}