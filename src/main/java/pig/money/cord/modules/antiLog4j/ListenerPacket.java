package pig.money.cord.modules.antiLog4j;

import me.earth.earthhack.impl.event.events.network.PacketEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import net.minecraft.network.play.server.SPacketChat;
import pig.money.cord.api.Api;

final class ListenerPacket extends
        ModuleListener<AntiLog4j, PacketEvent.Receive<SPacketChat>>
{
    public ListenerPacket(AntiLog4j module)
    {
        super(module, PacketEvent.Receive.class, SPacketChat.class);
    }

    @Override
    public void invoke(PacketEvent.Receive<SPacketChat > event) {
        if (Api.nullCheck()) return;
        String text = ((SPacketChat)event.getPacket()).getChatComponent().getUnformattedText();
        if (text.contains("${")) {
            System.out.println("Potential RCE Exploit Blocking");
            event.setCancelled(true);
        }
    }
}