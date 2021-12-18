package pig.money.cord.modules.autototem;

import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
import pig.money.cord.api.Api;

final class ListenerTick extends ModuleListener<AutoTotem, TickEvent>
{
    public ListenerTick(AutoTotem module)
    {
        super(module, TickEvent.class);
    }

    @Override
    public void invoke(TickEvent event) {
        if (Api.nullCheck()) return;

        float hp = mc.player.getHealth() + mc.player.getAbsorptionAmount();

        if (hp > module.health.getValue() && !(mc.player.fallDistance >= module.fallDistance.getValue()) && !module.onlyTotem.getValue()) {
            Item heldItem = mc.player.getHeldItemMainhand().getItem();
            if (module.rightClickGapple.getValue() && mc.gameSettings.keyBindUseItem.isKeyDown() && (heldItem instanceof ItemSword || heldItem instanceof ItemPickaxe) && mc.currentScreen == null) {
                Api.swapToOffhandSlot(Api.getItemSlot(Items.GOLDEN_APPLE));
            } else {
                Api.swapToOffhandSlot(Api.getItemSlot(Items.END_CRYSTAL));
            }
        } else {
            Api.swapToOffhandSlot(Api.getItemSlot(Items.TOTEM_OF_UNDYING));
        }
    }
}