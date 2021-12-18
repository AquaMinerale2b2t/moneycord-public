package pig.money.cord.modules.vclip;

import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import me.earth.earthhack.impl.util.text.ChatUtil;
import net.minecraft.network.play.client.CPacketPlayer;
import pig.money.cord.api.Api;

final class ListenerTick extends ModuleListener<Vclip, TickEvent>
{
    public ListenerTick(Vclip module)
    {
        super(module, TickEvent.class);
    }

    @Override
    public void invoke(TickEvent event) {
        if (Api.nullCheck()) return;

        if (Api.nullCheck() || !mc.player.onGround){
            module.disable();
            return;
        }


        if (!module.ccBypass.getValue()){
            mc.player.setPosition(mc.player.posX, mc.player.posY + module.values.getValue(), mc.player.posZ);
        } else {
            if (mc.player.posY < 3){
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY - 0.01, mc.player.posZ,mc.player.onGround));
                for (int i = 0; i < 2; i++)
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, -1, mc.player.posZ,mc.player.onGround));

            } else {
                ChatUtil.sendMessage("this exploit working only if your Y position is 1 or 2");
                module.disable();
                return;
            }
        }
        module.disable();
    }
}