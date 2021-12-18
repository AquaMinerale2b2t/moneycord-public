package pig.money.cord.modules.feetplace;

import me.earth.earthhack.impl.event.events.misc.TickEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import pig.money.cord.api.Api;

import java.util.List;

final class ListenerTick extends ModuleListener<FeetPlace, TickEvent>
{
    public ListenerTick(FeetPlace module)
    {
        super(module, TickEvent.class);
    }


    @Override
    public void invoke(TickEvent event) {
        if (Api.nullCheck()) return;
        doFeetPlace();
    }

    private void doFeetPlace() {
        if ((mc.player.posY != module.y || mc.player.motionY > 0) && module.jumpDisable.getValue()) {
            module.disable();
            return;
        }
        if (!module.timer.passed(module.delay.getValue()) && module.didPlace) return;
        int offset = (mc.world.getBlockState(new BlockPos(mc.player.getPositionVector())).getBlock() == Blocks.ENDER_CHEST && mc.player.posY - Math.floor(mc.player.posY) > 0.5 ? 1 : 0);
        if (Api.getUnsafePositions(mc.player.getEntityBoundingBox(), offset, module.smart.getValue()).size() == 0) {
            if (module.disabling.getValue()) module.disable();
            return;
        }
        //Helping Block
        placeBlocks(Api.getUnsafePositions(mc.player.getEntityBoundingBox(), offset - 1, module.smart.getValue()));

        placeBlocks(Api.getUnsafePositions(mc.player.getEntityBoundingBox(), offset, module.smart.getValue()));
        module.placed = 0;
        module.timer.reset();
    }

    private void placeBlocks(List<BlockPos> blocks) {
        for (BlockPos bp : blocks) {
            if (module.placed >= module.bps.getValue()) return;
            int old = mc.player.inventory.currentItem;
            if (Api.swapToHotbarSlot(Api.findItem(BlockObsidian.class), false) == -1) {
                if (Api.swapToHotbarSlot(Api.findItem(BlockEnderChest.class), false) == -1) return;
            }
            switch (Api.isPlaceable(bp)) {
                case 0: {
                    Api.placeBlock(bp);
                    module.didPlace = true;
                    module.placed++;
                    break;
                }
                case -1: {
                    if (module.retry.getValue()) {
                        module.retriesCount.putIfAbsent(bp, 0);
                        if (module.retriesCount.get(bp) > module.retries.getValue()) break;
                        if (module.cleaner.getValue()) {
                            for (Entity e : mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(bp))) {
                                if (e instanceof EntityEnderCrystal)
                                    mc.player.connection.sendPacket(new CPacketUseEntity(e));
                            }
                        }
                        Api.placeBlock(bp);
                        module.retriesCount.replace(bp, module.retriesCount.get(bp) + 1);
                        module.placed++;
                    }
                    break;
                }
                case 1: {
                    break;
                }
            }
            if (mc.player.inventory.currentItem != old) {
                Api.swapToHotbarSlot(old, false);
            }
        }
    }
}