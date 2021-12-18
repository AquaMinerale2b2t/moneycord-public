package pig.money.cord.modules.phase;

import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import me.earth.earthhack.impl.util.minecraft.KeyBoardUtil;
import net.minecraft.network.play.client.CPacketPlayer;
import pig.money.cord.api.Api;

final class ListenerTick extends ModuleListener<Phase, TickEvent>
{
    public ListenerTick(Phase module)
    {
        super(module, TickEvent.class);
    }

    public void sendPacket(double x, double z){
        if (Api.nullCheck()) return;
        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX + x, mc.player.posY, mc.player.posZ + z, mc.player.onGround));
        if (mc.player.collidedHorizontally) {
            switch (module.mode.getValue()) {
                case UP:
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX + x, 7777, mc.player.posZ + z, mc.player.onGround));
                    break;
                case ZERO:
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX + x, 0, mc.player.posZ + z, mc.player.onGround));
                    break;
                case DOWN:
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX + x, -7777, mc.player.posZ + z, mc.player.onGround));
                    break;
                case NONE:
                    break;
            }
        }
    }

    @Override
    public void invoke(TickEvent event) {
        if (Api.nullCheck()) return;
        if (!KeyBoardUtil.isKeyDown(module.bindMovement) && module.movementCheck.getValue()) return;
        if (module.cancelMotion.getValue()){
            mc.player.motionX = 0;
            mc.player.motionZ = 0;
        }
        module.delay++;
        double forward[] = Api.forward(module.speed.getValue() / 100);
        if (mc.player.collidedHorizontally){
            for (int i = 0; i < module.factor.getValue(); i++){
                sendPacket(forward[0], forward[1]);
            }
        } else {
            if (module.moving.getValue()){
                for (int i = 0; i < module.factor.getValue(); i++){
                    sendPacket(forward[0], forward[1]);
                }
            }
            if (!Api.isMoving(mc.player) && module.walkBypass.getValue()){
                if (module.delay > module.walkDelay.getValue()){
                    System.out.println("Test!");
                    for (int i = 0; i < module.factor.getValue(); i++){
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, mc.player.onGround));
                        System.out.println(i);
                    }
                    mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY -1, mc.player.posZ, mc.player.onGround));
                    module.delay = 0;
                }
            }
        }
    }
}