package pig.money.cord.modules.donkeyhelper;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import pig.money.cord.modules.Notify;
import pig.money.cord.api.Timer;

public class DonkeyHelper extends Module {
    public DonkeyHelper() {
        super("CD-DonkeyHelper", Category.Movement);
        this.listeners.add(new ListenerTick(this));
        this.setData(new DonkeyHelperData(this));
    }

    protected final Setting<Boolean> placeChectBypass =
            register(new BooleanSetting("PlaceChestBypass", true));


    public void setNull(){
        timer.reset();
    }

    @Override
    public void onEnable(){
        setNull();
        Notify.enable("CD-DonkeyHelper");
    }

    @Override
    public void onDisable(){
        setNull();
        Notify.disable("CD-DonkeyHelper");
    }

    Timer timer = new Timer();

}