package pig.money.cord.modules.example;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;

public class Example extends Module
{
    public Example()
    {
        super("CD-Example", Category.Movement);
        this.listeners.add(new ListenerTick(this));
        this.setData(new ExampleData(this));
    }

    protected void onTick() {
    }

}