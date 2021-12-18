package pig.money.cord.modules.donkeyhelper;

import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.init.Items;
import pig.money.cord.api.Api;

final class ListenerTick extends ModuleListener<DonkeyHelper, TickEvent>
{
    public ListenerTick(DonkeyHelper module)
    {
        super(module, TickEvent.class);
    }

    @Override
    public void invoke(TickEvent event) {
        if (Api.nullCheck()) return;
        if (module.placeChectBypass.getValue()) {
            if (Api.findItem(BlockChest.class) == -1) return;
            if (mc.player.getHeldItemMainhand().getItem() != Items.AIR && mc.objectMouseOver.entityHit instanceof EntityDonkey && mc.gameSettings.keyBindUseItem.isKeyDown()) {
                if (module.timer.passed(1)) {
                    Api.swapToHotbarSlot(Api.findItem(BlockChest.class), false);
                    module.timer.reset();
                }
            } else return;
        }
    }
}