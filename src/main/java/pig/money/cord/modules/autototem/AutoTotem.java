package pig.money.cord.modules.autototem;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;

public class AutoTotem extends Module
{
    public AutoTotem()
    {
        super("CD-AutoTotem", Category.Combat);
        this.listeners.add(new ListenerTick(this));
        this.setData(new AutoTotemData(this));
    }

    protected final Setting<Integer> health =
            register(new NumberSetting<>("Health", 16, 1, 36));
    protected final Setting<Integer> fallDistance =
            register(new NumberSetting<>("Fall Distance", 4, 1, 20));
    protected final Setting<Boolean> onlyTotem =
            register(new BooleanSetting("Only Totem", true));
    protected final Setting<Boolean> rightClickGapple =
            register(new BooleanSetting("Right Click Gapple", false));
}