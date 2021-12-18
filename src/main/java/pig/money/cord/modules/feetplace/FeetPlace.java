package pig.money.cord.modules.feetplace;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import net.minecraft.util.math.BlockPos;
import pig.money.cord.modules.Notify;
import pig.money.cord.api.Timer;

import java.util.HashMap;

public class FeetPlace extends Module
{
    public FeetPlace()
    {
        super("CD-FeetPlace", Category.Combat);
        this.listeners.add(new ListenerTick(this));
        this.setData(new FeetPlaceData(this));
    }

    public final Timer timer = new Timer();
    public int placed;
    public boolean didPlace;
    public double y;
    public HashMap<BlockPos, Integer> retriesCount = new HashMap<>();

    @Override
    public void onEnable(){
        placed = 0;
        y = mc.player.posY;
        didPlace = false;
        timer.reset();
        retriesCount.clear();
        Notify.enable("CD-FeetPlace");
    }

    @Override
    public void onDisable(){
        placed = 0;
        y = mc.player.posY;
        didPlace = false;
        timer.reset();
        retriesCount.clear();
        Notify.disable("CD-FeetPlace");
    }

    protected final Setting<Integer> delay =
            register(new NumberSetting<>("Delay", 15, 1, 250));
    protected final Setting<Integer> bps =
            register(new NumberSetting<>("BPS", 12, 1, 20));
    protected final Setting<Boolean> retry =
            register(new BooleanSetting("Retry", true));
    protected final Setting<Integer> retries =
            register(new NumberSetting<>("Retries", 10, 1, 15));
    protected final Setting<Boolean> cleaner =
            register(new BooleanSetting("Cleaner", true));
    protected final Setting<Boolean> jumpDisable =
            register(new BooleanSetting("Jump Disable", true));
    protected final Setting<Boolean> disabling =
            register(new BooleanSetting("Disable", false));
    protected final Setting<Boolean> smart =
            register(new BooleanSetting("Smart", false));
}