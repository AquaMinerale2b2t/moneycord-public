package pig.money.cord.modules.chorushelper;

import me.earth.earthhack.impl.event.events.network.PacketEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.math.BlockPos;
import pig.money.cord.api.Api;

final class ListenerPacket extends
        ModuleListener<ChorusHelper, PacketEvent.Receive<SPacketSoundEffect>>
{
    public ListenerPacket(ChorusHelper module)
    {
        super(module, PacketEvent.Receive.class, SPacketSoundEffect .class);
    }

    @Override
    public void invoke(PacketEvent.Receive<SPacketSoundEffect > event) {
        if (Api.nullCheck()) return;
        final SPacketSoundEffect packet = ( SPacketSoundEffect ) event.getPacket();
        if (packet.getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT) {
            module.doDelay = false;
            module.timer.reset();
            module.doDelay = true;
            module.pos = new BlockPos(packet.getX(), packet.getY(), packet.getZ());
        }
    }

}