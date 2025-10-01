package net.flokes.enderite.world.gen.feature;

import com.mojang.serialization.Codec;
import net.flokes.enderite.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class EnderiteCrystalFeature extends Feature<DefaultFeatureConfig> {
    public EnderiteCrystalFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        int[] blocks_per_y_level_inner = {2, 4, 4, 3, 3, 2, 1, 1};
        int x_offset_base = 5;

        // Go underneath end island
        while(!structureWorldAccess.isAir(blockPos.down())) {
            blockPos = blockPos.down();
        }

        // Random y offset
        blockPos = blockPos.add(0, random.nextBetween(-4, 3), 0);

        // Generate inner Obsidian layer
        for (int y_offset = 7; y_offset >= 0; y_offset--) {
            for (int x_offset = 0; x_offset < blocks_per_y_level_inner[y_offset]; x_offset++) {
                this.setBlockState(structureWorldAccess, blockPos.add(x_offset_base + x_offset, y_offset, 0), Blocks.OBSIDIAN.getDefaultState());
                this.setBlockState(structureWorldAccess, blockPos.add(-x_offset_base - x_offset, -y_offset, 0), Blocks.OBSIDIAN.getDefaultState());
            }
            x_offset_base--;
        }

        // Generate outer Obsidian layers
        x_offset_base = 3;
        for (int y_offset = 4; y_offset >= 0; y_offset--) {
            for (int x_offset = 0; x_offset <= (y_offset < 2 ? 1 : 0); x_offset++) {
                this.setBlockState(structureWorldAccess, blockPos.add(x_offset_base - x_offset, y_offset, 1), Blocks.OBSIDIAN.getDefaultState());
                this.setBlockState(structureWorldAccess, blockPos.add(-x_offset_base + x_offset, -y_offset, 1), Blocks.OBSIDIAN.getDefaultState());
                this.setBlockState(structureWorldAccess, blockPos.add(x_offset_base - x_offset, y_offset, -1), Blocks.OBSIDIAN.getDefaultState());
                this.setBlockState(structureWorldAccess, blockPos.add(-x_offset_base + x_offset, -y_offset, -1), Blocks.OBSIDIAN.getDefaultState());
            }
            if (y_offset % 2 == 0) {
                x_offset_base--;
            }
        }

        // Generate ore block at center
        this.setBlockState(structureWorldAccess, blockPos, ModBlocks.ENDERITE_ORE.getDefaultState());
        return true;
    }
}
