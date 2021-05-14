package supercoder79.greggen.world.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import supercoder79.greggen.world.biome.GregGenBiomes;

public final class ShadowBiomeLayer extends GenLayer {
    public ShadowBiomeLayer(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int x, int z, int width, int height) {
        int[] parentIds = this.parent.getInts(x, z, width, height);
        int[] returnIds = IntCache.getIntCache(width * height);

        for (int x1 = 0; x1 < height; ++x1) {
            for (int z1 = 0; z1 < width; ++z1) {
                this.initChunkSeed(x1 + x, z1 + z);
                int parentId = parentIds[z1 + x1 * width];

                if (GregGenBiomes.BIOME_PICKERS.containsKey(parentId)) {
                    // Choose new biome id
                    int newBiomeId = GregGenBiomes.BIOME_PICKERS.get(parentId).choose(this::nextInt);

                    // -1 means use existing biome
                    if (newBiomeId > 0) {

                        returnIds[z1 + x1 * width] = newBiomeId;
                        continue;
                    }
                }


                returnIds[z1 + x1 * width] = parentId;
            }
        }

        return returnIds;
    }
}
