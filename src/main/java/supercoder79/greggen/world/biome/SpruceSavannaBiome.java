package supercoder79.greggen.world.biome;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga1;

import java.util.Random;

// Savanna with pine trees (it's a real thing)
public final class SpruceSavannaBiome extends ShadowBiome {
    private static final WorldGenSavannaTree SAVANNA_TREE = new WorldGenSavannaTree(false);

    public SpruceSavannaBiome(int id) {
        super(id);
        this.shadowBiome(BiomeGenBase.savanna.biomeID, 0.3);
        this.theBiomeDecorator.treesPerChunk = 3;
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 1, 2, 6));
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 20;
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random) {
        if (random.nextInt(3) != 0) {
            return new WorldGenShrub(1, 1);
        }

        if (random.nextInt(4) == 0) {
            return new WorldGenTaiga1();
        }

        if (random.nextInt(3) > 0) {
            return SAVANNA_TREE;
        }

        return this.worldGeneratorTrees;
    }

    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_) {
        genTallFlowers.func_150548_a(2);

        for (int k = 0; k < 7; ++k) {
            int l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            int i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            int j1 = p_76728_2_.nextInt(p_76728_1_.getHeightValue(l, i1) + 32);
            genTallFlowers.generate(p_76728_1_, p_76728_2_, l, j1, i1);
        }

        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }
}
