package net.flokes.enderite.world.gen.feature;

import com.mojang.serialization.Codec;
import net.flokes.enderite.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FloatingEnderiteCrystalsFeature extends Feature<DefaultFeatureConfig> {
    public FloatingEnderiteCrystalsFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();

        // Generate central Enderite crystal
        for (int y_offset = -8; y_offset <= 8; y_offset++) {
            int bound_for_xz = (8 - MathHelper.abs(y_offset)) / 2;
            for (int x_offset = bound_for_xz * (-1); x_offset <= bound_for_xz; x_offset++) {
                for (int z_offset = 0; (z_offset <= bound_for_xz - MathHelper.abs(x_offset)); z_offset++) {
                    this.setBlockState(structureWorldAccess, blockPos.add(x_offset, y_offset, z_offset), Blocks.OBSIDIAN.getDefaultState());
                    if (z_offset > 0) {
                        this.setBlockState(structureWorldAccess, blockPos.add(x_offset, y_offset, -z_offset), Blocks.OBSIDIAN.getDefaultState());
                    }
                }
            }
        }

        Direction[] directions = {Direction.UP, Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        for (Direction direction : directions) {
            if (random.nextInt(1) > 0) {
                this.setBlockState(structureWorldAccess, blockPos.offset(direction), ModBlocks.ENDERITE_ORE.getDefaultState());
            }
        }
        this.setBlockState(structureWorldAccess, blockPos, ModBlocks.ENDERITE_ORE.getDefaultState());


        generateOuterCrystal(structureWorldAccess, random, blockPos, Direction.NORTH);
        generateOuterCrystal(structureWorldAccess, random, blockPos, Direction.EAST);
        generateOuterCrystal(structureWorldAccess, random, blockPos, Direction.SOUTH);
        generateOuterCrystal(structureWorldAccess, random, blockPos, Direction.WEST);

        return true;
    }

    private void generateOuterCrystal(ServerWorldAccess world, Random random, BlockPos center, Direction direction) {
        BlockPos outerCenter = center.offset(direction, 10);

        // generate crystal
        for (int y_offset = 0; y_offset >= -4; y_offset--) {
            int bound_for_xz = (4 + y_offset) / 2;
            for (int x_offset = bound_for_xz * (-1); x_offset <= bound_for_xz; x_offset++) {
                for (int z_offset = 0; z_offset <= bound_for_xz - MathHelper.abs(x_offset); z_offset++) {
                    if (MathHelper.abs(x_offset) < 2 && z_offset < 2) {
                        this.setBlockState(world, outerCenter.add(x_offset, y_offset, z_offset), Blocks.OBSIDIAN.getDefaultState());
                        if (z_offset > 0) {
                            this.setBlockState(world, outerCenter.add(x_offset, y_offset, -z_offset), Blocks.OBSIDIAN.getDefaultState());
                        }
                    }
                }
            }
        }
        this.setBlockState(world, outerCenter.down(), ModBlocks.ENDERITE_ORE.getDefaultState());

        // generate bridge
        for (BlockPos blockPos : BlockPos.iterateRandomly(random, 9, outerCenter.offset(direction, -4), 2)) {
            this.setBlockState(world, blockPos, Blocks.MAGENTA_STAINED_GLASS_PANE.getDefaultState());
        }
        this.setBlockState(world, center.offset(direction, 4), Blocks.OBSIDIAN.getDefaultState());
        Direction facingDirection = direction;
        for (int distance = 5; distance <= 8; distance++) {
            this.setBlockState(world, center.offset(direction, distance), Blocks.END_ROD.getDefaultState().with(FacingBlock.FACING, facingDirection));
            facingDirection = facingDirection.getOpposite();
        }

        // place endcrystal
        EndCrystalEntity endCrystalEntity = EntityType.END_CRYSTAL.create(world.toServerWorld(), SpawnReason.STRUCTURE);
        if (endCrystalEntity != null) {
            endCrystalEntity.setShowBottom(false);
            endCrystalEntity.refreshPositionAndAngles(outerCenter.getX() + 0.5, outerCenter.getY() + 1, outerCenter.getZ() + 0.5, random.nextFloat() * 360.0F, 0.0F);
            world.spawnEntity(endCrystalEntity);
        }
    }
}
