package net.mdembree218.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class GrubHoeItem extends MiningToolItem {

    public GrubHoeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, toolMaterial, BlockTags.SHOVEL_MINEABLE, settings);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) return ActionResult.SUCCESS;

        BlockPos targetPos = context.getBlockPos();
        BlockState targetState = world.getBlockState(targetPos);
        PlayerEntity player = context.getPlayer();
        if (player == null) return ActionResult.PASS;

        if (targetState.isIn(BlockTags.SHOVEL_MINEABLE)) {
            Direction moveDirection = getMoveDirection(player);
            BlockPos newPos = targetPos.offset(moveDirection);

            if ((world.getBlockState(newPos).isAir() || targetState.isIn(BlockTags.REPLACEABLE_BY_TREES)) && !player.getBlockPos().equals(newPos) && !player.getBlockPos().up().equals(newPos)) {
                if (!isSurroundedByAir(world, newPos, moveDirection)) {
                    world.setBlockState(newPos, targetState);
                    world.setBlockState(targetPos, Blocks.AIR.getDefaultState());
                    return ActionResult.SUCCESS;
                } else {
                    player.sendMessage(Text.of("You cannot move the block there!"), true);
                }
            }
        }
        return ActionResult.PASS;
    }

    private Direction getMoveDirection(PlayerEntity player) {
        float pitch = player.getPitch(1.0F);
        if (pitch < -45.0F) return Direction.DOWN;
        if (pitch > 45.0F) return Direction.UP;
        return player.getHorizontalFacing().getOpposite();
    }

    private boolean isSurroundedByAir(World world, BlockPos newPos, Direction movingDirection) {
        return switch (movingDirection) {
            case NORTH -> world.getBlockState(newPos.north()).isAir() &&
                    world.getBlockState(newPos.east()).isAir() &&
                    world.getBlockState(newPos.west()).isAir() &&
                    world.getBlockState(newPos.up()).isAir() &&
                    world.getBlockState(newPos.down()).isAir();
            case EAST -> world.getBlockState(newPos.north()).isAir() &&
                    world.getBlockState(newPos.east()).isAir() &&
                    world.getBlockState(newPos.south()).isAir() &&
                    world.getBlockState(newPos.up()).isAir() &&
                    world.getBlockState(newPos.down()).isAir();
            case SOUTH -> world.getBlockState(newPos.east()).isAir() &&
                    world.getBlockState(newPos.south()).isAir() &&
                    world.getBlockState(newPos.west()).isAir() &&
                    world.getBlockState(newPos.up()).isAir() &&
                    world.getBlockState(newPos.down()).isAir();
            case WEST -> world.getBlockState(newPos.north()).isAir() &&
                    world.getBlockState(newPos.south()).isAir() &&
                    world.getBlockState(newPos.west()).isAir() &&
                    world.getBlockState(newPos.up()).isAir() &&
                    world.getBlockState(newPos.down()).isAir();
            case UP -> world.getBlockState(newPos.north()).isAir() &&
                    world.getBlockState(newPos.east()).isAir() &&
                    world.getBlockState(newPos.south()).isAir() &&
                    world.getBlockState(newPos.west()).isAir() &&
                    world.getBlockState(newPos.up()).isAir();
            case DOWN -> world.getBlockState(newPos.north()).isAir() &&
                    world.getBlockState(newPos.east()).isAir() &&
                    world.getBlockState(newPos.south()).isAir() &&
                    world.getBlockState(newPos.west()).isAir() &&
                    world.getBlockState(newPos.down()).isAir();
        };
    }
}