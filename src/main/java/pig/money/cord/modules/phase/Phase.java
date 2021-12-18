package pig.money.cord.modules.phase;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BindSetting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.EnumSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import me.earth.earthhack.api.util.bind.Bind;
import me.earth.earthhack.impl.util.text.ChatUtil;
import pig.money.cord.modules.Notify;

public class Phase extends Module
{
    public Phase()
    {
        super("CD-PhaseWalk", Category.Movement);
        this.listeners.add(new ListenerTick(this));
        this.setData(new PhaseData(this));
    }


    public enum Mode
    {
        UP,
        ZERO,
        DOWN,
        NONE
    }

    protected final Setting<Mode> mode =
            register(new EnumSetting<>("Mode", Mode.ZERO));

    int delay;

    @Override
    public void onEnable(){
        delay = 0;
        Notify.enable("CD-Phase Walk");
    }
    @Override
    public void onDisable(){
        delay = 0;
        Notify.disable("CD-Phase Walk");
    }

    @Override
    public String getDisplayInfo() {
        return mode.getValue().name();
    }

    protected final Setting<Boolean> movementCheck =
            register(new BooleanSetting("Movement Check", true));
    protected final Setting<Bind> bindMovement =
            register(new BindSetting("SwitchBind", Bind.none()));
    protected final Setting<Float> speed =
            register(new NumberSetting<>("Speed", 0.1F, 0.01F, 5F));
    protected final Setting<Integer> factor =
            register(new NumberSetting<>("Factor", 2, 1, 5));
    protected final Setting<Integer> walkDelay =
            register(new NumberSetting<>("Delay", 2, 1, 5));
    protected final Setting<Boolean> cancelMotion =
            register(new BooleanSetting("Cancel Motion", true));
    protected final Setting<Boolean> moving =
            register(new BooleanSetting("Moving", false));
    protected final Setting<Boolean> walkBypass =
            register(new BooleanSetting("WalkBypass", true));
}