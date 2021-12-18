package pig.money.cord.api;

import me.earth.earthhack.api.util.interfaces.Globals;
import me.earth.earthhack.impl.managers.Managers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import pig.money.cord.managers.PacketManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Api implements Globals {

    public static boolean nullCheck(){
        return mc.player == null || mc.world == null;
    }

    //TODO: ITEMUTIL

    public static int getItemSlot(Item input) {
        if (input == mc.player.getHeldItemOffhand().getItem()) return -1;
        for (int i = 36; i >= 0; i--) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if (item == input) {
                if (i < 9) {
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }

    public static void swapToOffhandSlot(int slot) {
        if (slot == -1) return;
        mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
        mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
        mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
        mc.playerController.updateController();
    }

    public static int swapToHotbarSlot(int slot, boolean silent){
        if (mc.player.inventory.currentItem == slot || slot < 0 || slot > 8) return slot;
        PacketManager.getInstance().addLast(new CPacketHeldItemChange(slot));
        if (!silent) mc.player.inventory.currentItem = slot;
        mc.playerController.updateController();
        return slot;
    }

    public static int findItem(Block... blockIn) {
        List<Block> list = Arrays.stream(blockIn).collect(Collectors.toList());
        for (int i = 0; i < 9; ++i) {
            ItemStack stack = mc.player.inventory.getStackInSlot(i);
            if (stack == ItemStack.EMPTY || !(stack.getItem() instanceof ItemBlock) || (!list.contains((( ItemBlock ) stack.getItem()).getBlock())))
                continue;
            return i;
        }
        return -1;
    }

    public static int findItem(Class clazz) {
        for (int i = 0; i < 9; ++i) {
            ItemStack stack = mc.player.inventory.getStackInSlot(i);
            if (stack == ItemStack.EMPTY) continue;
            if (clazz.isInstance(stack.getItem())) {
                return i;
            }
            if (!(stack.getItem() instanceof ItemBlock) || !clazz.isInstance((( ItemBlock ) stack.getItem()).getBlock()))
                continue;
            return i;
        }
        return -1;
    }

    //TODO: BLOCK UTIL

    private static boolean unshift = false;

    public static int isPlaceable(BlockPos bp) {
        if (mc.world == null || bp == null) return 1;
        if (!mc.world.getBlockState(bp).getMaterial().isReplaceable()) return 1;
        for (Entity e : mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(bp))) {
            if (e instanceof EntityXPOrb || e instanceof EntityItem) continue;
            if (e instanceof EntityPlayer) return 1;
            return -1;
        }
        return 0;
    }

    public static List<BlockPos> getUnsafePositions(AxisAlignedBB vector, int level, boolean smart) {
        List<BlockPos> blocks = new ArrayList<>();
        if(!smart) {
            getLevels(level).stream().map(vec -> new BlockPos(vector.getCenter().add(vec.x, vec.y, vec.z))).filter(block -> mc.world.getBlockState(block).getMaterial().isReplaceable()).forEach(blocks::add);
        } else {
            int maxX = ( int ) Math.floor(vector.maxX),
                    minX = ( int ) Math.floor(vector.minX),
                    maxZ = ( int ) Math.floor(vector.maxZ),
                    minZ = ( int ) Math.floor(vector.minZ);
            Arrays.asList(new BlockPos(maxX, vector.getCenter().y, maxZ), new BlockPos(maxX, vector.getCenter().y, minZ), new BlockPos(minX, vector.getCenter().y, maxZ), new BlockPos(minX, vector.getCenter().y, minZ)).forEach(bp -> {
                if (!mc.world.getBlockState(bp).getMaterial().isReplaceable()) return;
                getLevels(level).stream().map(vec -> bp.add(vec.x, vec.y, vec.z)).filter(block -> mc.world.getBlockState(block).getMaterial().isReplaceable()).forEach(blocks::add);
            });
        }
        return blocks;
    }

    public static List<Vec3d> getLevels(int y) {
        return Arrays.asList(new Vec3d(-1, y, 0), new Vec3d(1, y, 0), new Vec3d(0, y, 1), new Vec3d(0, y, -1));
    }

    public static boolean placeBlock(BlockPos pos) {
        Block block = mc.world.getBlockState(pos).getBlock();
        EnumFacing direction = calcSide(pos);
        if (direction == null) {
            return false;
        }
        boolean activated = block.onBlockActivated(mc.world, pos, mc.world.getBlockState(pos), mc.player, EnumHand.MAIN_HAND, direction, 0.0f, 0.0f, 0.0f);
        if (activated) {
            mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));
        }
        mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(pos.offset(direction), direction.getOpposite(), EnumHand.MAIN_HAND, 0.5f, 0.5f, 0.5f));
        mc.player.connection.sendPacket(new CPacketAnimation(EnumHand.MAIN_HAND));
        if (activated || unshift) {
            mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            unshift = false;
        }
        mc.playerController.updateController();
        return true;
    }

    public static EnumFacing calcSide(BlockPos pos) {
        for (EnumFacing side : EnumFacing.values()) {
            IBlockState offsetState = mc.world.getBlockState(pos.offset(side));
            boolean activated = offsetState.getBlock().onBlockActivated(mc.world, pos, offsetState, mc.player, EnumHand.MAIN_HAND, side, 0.0f, 0.0f, 0.0f);
            if (activated) {
                mc.getConnection().sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SNEAKING));
                unshift = true;
            }
            if (!offsetState.getBlock().canCollideCheck(offsetState, false) || offsetState.getMaterial().isReplaceable())
                continue;
            return side;
        }
        return null;
    }

    //TODO: ENTITY UTIL

    public static double[] forward(double d) {
        float f = mc.player.movementInput.moveForward;
        float f2 = mc.player.movementInput.moveStrafe;
        float f3 = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += ( float ) (f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += ( float ) (f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = ( double ) f * d * d3 + ( double ) f2 * d * d2;
        double d5 = ( double ) f * d * d2 - ( double ) f2 * d * d3;
        return new double[] { d4, d5 };
    }

    public static boolean isntValid(final EntityPlayer entity, final double range) {
        return mc.player.getDistance(entity) > range || entity == mc.player || entity.getHealth() <= 0.0f || entity.isDead || Managers.FRIENDS.contains(entity.getName());
    }

    public static boolean isMoving(EntityLivingBase entity) {
        return entity.moveStrafing != 0 || entity.moveForward != 0;
    }

    public static float getHealth(EntityLivingBase player) {
        return player.getHealth() + player.getAbsorptionAmount();
    }

    public static EntityPlayer getTarget(final float range) {
        EntityPlayer currentTarget = null;
        for (int size = mc.world.playerEntities.size(), i = 0; i < size; ++i) {
            final EntityPlayer player = mc.world.playerEntities.get(i);
            if (!isntValid(player, range)) {
                if (currentTarget == null) {
                    currentTarget = player;
                } else if (mc.player.getDistanceSq(player) < mc.player.getDistanceSq(currentTarget)) {
                    currentTarget = player;
                }
            }
        }
        return currentTarget;
    }



}
