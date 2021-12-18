package pig.money.cord.modules.vclip;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import pig.money.cord.modules.Notify;

public class Vclip extends Module
{
    public Vclip()
    {
        super("CD-Vclip", Category.Movement);
        this.listeners.add(new ListenerTick(this));
        this.setData(new VclipData(this));
    }

    @Override
    public void onEnable(){
        Notify.enable("CD-Vclip");
    }
    @Override
    public void onDisable(){
        Notify.disable("CD-Vclip");
    }

    protected final Setting<Integer> values =
            register(new NumberSetting<>("Value", 10, -15, 15));
    protected final Setting<Boolean> ccBypass =
            register(new BooleanSetting("CC-Bypass", false));
}