package pig.money.cord.modules.antiLog4j;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;

public class AntiLog4j extends Module {
    public AntiLog4j() {
        super("CD-AntiLog4j", Category.Client);
        this.setData(new AntiLog4jData(this));
        this.listeners.add(new ListenerPacket(this));
    }
}