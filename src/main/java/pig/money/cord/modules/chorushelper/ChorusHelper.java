package pig.money.cord.modules.chorushelper;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.*;
import me.earth.earthhack.impl.util.render.Interpolation;
import me.earth.earthhack.impl.util.render.RenderUtil;
import net.minecraft.util.math.BlockPos;
import pig.money.cord.api.Api;
import pig.money.cord.modules.Notify;
import pig.money.cord.api.Timer;

import java.awt.*;

public class ChorusHelper extends Module
{
    public ChorusHelper()
    {
        super("CD-ChorusPredict", Category.Movement);
        this.listeners.add(new ListenerTick(this));
        this.listeners.add(new ListenerRender(this));
        this.listeners.add(new ListenerPacket(this));
        this.setData(new ChorusHelperData(this));
    }
    protected final Setting<Color> playersColor =
            register(new ColorSetting("Color", new Color(94, 255, 255, 165)));
    protected final Setting<Integer> delay =
            register(new NumberSetting<>("Delay", 10, 1, 25));


    Timer timer = new Timer();

    public BlockPos pos;
    public boolean doDelay = false;

    public void setNull(){
        timer.reset();
        doDelay = false;
        pos = null;
    }

    @Override
    public void onEnable(){
        setNull();
        Notify.enable("CD-ChorusPredict");
    }

    @Override
    public void onDisable(){
        setNull();
        Notify.disable("CD-ChorusPredict");
    }

    protected void onRender3D(){
        if (Api.nullCheck() || pos == null) return;
        RenderUtil.drawBox(Interpolation.interpolatePos(pos, 0.9f), playersColor.getValue());
    }
}