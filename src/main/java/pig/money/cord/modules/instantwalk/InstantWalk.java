package pig.money.cord.modules.instantwalk;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import pig.money.cord.modules.Notify;

public class InstantWalk extends Module
{
    public InstantWalk()
    {
        super("CD-InstantWalk", Category.Movement);
        this.listeners.add(new ListenerMove(this));
        this.setData(new InstantWalkData(this));
    }

    protected final Setting<Boolean> slow =
            register(new BooleanSetting("Slow", false));
    protected final Setting<Boolean> autoStep =
            register(new BooleanSetting("Auto Step", true));
    protected final Setting<Float> customSpeed =
            register(new NumberSetting<>("customSpeed", 3.0f,0.0f,6.0f));
    protected final Setting<Integer> stepHeight =
            register(new NumberSetting<>("Step Height", 2,0,2));

    public void onEnable(){
        Notify.enable("CD-InstantWalk");
        mc.player.stepHeight = 0.6f;
    }
    public void onDisable(){
        Notify.disable("CD-InstantWalk");
        mc.player.stepHeight = 0.6f;
    }
}