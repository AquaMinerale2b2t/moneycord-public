package pig.money.cord.modules.fastfall;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import pig.money.cord.modules.Notify;

public class FastFall extends Module
{
    public FastFall()
    {
        super("CD-FastFall", Category.Movement);
        this.listeners.add(new ListenerTick(this));
        this.setData(new FastFallData(this));
    }

    protected final Setting<Integer> speed =
            register(new NumberSetting<>("speed", 10, 1, 15));

    @Override
    public void onEnable(){
        Notify.enable("CD-FastFall");
    }

    @Override
    public void onDisable(){
        Notify.disable("CD-FastFall");
    }

}