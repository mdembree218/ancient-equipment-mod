package net.mdembree218;

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
        super(toolMaterial, BlockTags.SHOVEL_MINEABLE, attackDamage, attackSpeed, settings.maxCount(1));
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
                if (!isSurroundedByAir(world, newPos)) {
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

    //    private boolean isSurroundedByAir(World world, BlockPos newPos, Direction movingDirection) {
//        return switch (movingDirection) {
//            case NORTH -> world.getBlockState(newPos.north()).isAir() &&
//                    world.getBlockState(newPos.east()).isAir() &&
//                    world.getBlockState(newPos.west()).isAir() &&
//                    world.getBlockState(newPos.up()).isAir() &&
//                    world.getBlockState(newPos.down()).isAir() &&
//                    world.getBlockState(newPos.north().down()).isAir() &&
//                    world.getBlockState(newPos.north().up()).isAir() &&
//                    world.getBlockState(newPos.east().down()).isAir() &&
//                    world.getBlockState(newPos.east().up()).isAir() &&
//                    world.getBlockState(newPos.west().down()).isAir() &&
//                    world.getBlockState(newPos.west().up()).isAir() &&
//                    world.getBlockState(newPos.south().down()).isAir() &&
//                    world.getBlockState(newPos.south().up()).isAir();
//            case EAST -> world.getBlockState(newPos.north()).isAir() &&
//                    world.getBlockState(newPos.east()).isAir() &&
//                    world.getBlockState(newPos.south()).isAir() &&
//                    world.getBlockState(newPos.up()).isAir() &&
//                    world.getBlockState(newPos.down()).isAir() &&
//                    world.getBlockState(newPos.north().down()).isAir() &&
//                    world.getBlockState(newPos.north().up()).isAir() &&
//                    world.getBlockState(newPos.east().down()).isAir() &&
//                    world.getBlockState(newPos.east().up()).isAir() &&
//                    world.getBlockState(newPos.west().down()).isAir() &&
//                    world.getBlockState(newPos.west().up()).isAir() &&
//                    world.getBlockState(newPos.south().down()).isAir() &&
//                    world.getBlockState(newPos.south().up()).isAir();
//            case SOUTH -> world.getBlockState(newPos.east()).isAir() &&
//                    world.getBlockState(newPos.south()).isAir() &&
//                    world.getBlockState(newPos.west()).isAir() &&
//                    world.getBlockState(newPos.up()).isAir() &&
//                    world.getBlockState(newPos.down()).isAir() &&
//                    world.getBlockState(newPos.north().down()).isAir() &&
//                    world.getBlockState(newPos.north().up()).isAir() &&
//                    world.getBlockState(newPos.east().down()).isAir() &&
//                    world.getBlockState(newPos.east().up()).isAir() &&
//                    world.getBlockState(newPos.west().down()).isAir() &&
//                    world.getBlockState(newPos.west().up()).isAir() &&
//                    world.getBlockState(newPos.south().down()).isAir() &&
//                    world.getBlockState(newPos.south().up()).isAir();
//            case WEST -> world.getBlockState(newPos.north()).isAir() &&
//                    world.getBlockState(newPos.south()).isAir() &&
//                    world.getBlockState(newPos.west()).isAir() &&
//                    world.getBlockState(newPos.up()).isAir() &&
//                    world.getBlockState(newPos.down()).isAir() &&
//                    world.getBlockState(newPos.north().down()).isAir() &&
//                    world.getBlockState(newPos.north().up()).isAir() &&
//                    world.getBlockState(newPos.east().down()).isAir() &&
//                    world.getBlockState(newPos.east().up()).isAir() &&
//                    world.getBlockState(newPos.west().down()).isAir() &&
//                    world.getBlockState(newPos.west().up()).isAir() &&
//                    world.getBlockState(newPos.south().down()).isAir() &&
//                    world.getBlockState(newPos.south().up()).isAir();
//            case UP -> world.getBlockState(newPos.north()).isAir() &&
//                    world.getBlockState(newPos.east()).isAir() &&
//                    world.getBlockState(newPos.south()).isAir() &&
//                    world.getBlockState(newPos.west()).isAir() &&
//                    world.getBlockState(newPos.up()).isAir() &&
//                    world.getBlockState(newPos.north().down()).isAir() &&
//                    world.getBlockState(newPos.north().up()).isAir() &&
//                    world.getBlockState(newPos.east().down()).isAir() &&
//                    world.getBlockState(newPos.east().up()).isAir() &&
//                    world.getBlockState(newPos.west().down()).isAir() &&
//                    world.getBlockState(newPos.west().up()).isAir() &&
//                    world.getBlockState(newPos.south().down()).isAir() &&
//                    world.getBlockState(newPos.south().up()).isAir();
//            case DOWN -> world.getBlockState(newPos.north()).isAir() &&
//                    world.getBlockState(newPos.east()).isAir() &&
//                    world.getBlockState(newPos.south()).isAir() &&
//                    world.getBlockState(newPos.west()).isAir() &&
//                    world.getBlockState(newPos.down()).isAir() &&
//                    world.getBlockState(newPos.north().down()).isAir() &&
//                    world.getBlockState(newPos.north().up()).isAir() &&
//                    world.getBlockState(newPos.east().down()).isAir() &&
//                    world.getBlockState(newPos.east().up()).isAir() &&
//                    world.getBlockState(newPos.west().down()).isAir() &&
//                    world.getBlockState(newPos.west().up()).isAir() &&
//                    world.getBlockState(newPos.south().down()).isAir() &&
//                    world.getBlockState(newPos.south().up()).isAir();
//        };
//    }
    private boolean isSurroundedByAir(World world, BlockPos newPos) {
        BlockPos[] positions = {
                newPos.north(), newPos.east(), newPos.south(), newPos.west(), newPos.up(), newPos.down(),
                newPos.north().down(), newPos.north().up(), newPos.east().down(), newPos.east().up(),
                newPos.west().down(), newPos.west().up(), newPos.south().down(), newPos.south().up()
        };

        for (BlockPos pos : positions) {
            if (!world.getBlockState(pos).isAir()) {
                return false;
            }
        }
        return true;
    }
}