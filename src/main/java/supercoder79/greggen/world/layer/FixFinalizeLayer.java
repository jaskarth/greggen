package supercoder79.greggen.world.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import supercoder79.greggen.world.biome.GregGenBiomes;

public final class FixFinalizeLayer extends GenLayer {
    private final GenLayer shadow;
    private final GenLayer real;

    public FixFinalizeLayer(long seed, GenLayer shadow, GenLayer real) {
        super(seed);
        this.shadow = shadow;
        this.real = real;
    }

    public void initWorldGenSeed(long seed) {
        this.shadow.initWorldGenSeed(seed);
        this.real.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int height) {
        int[] shadowIds = this.shadow.getInts(x, z, width, height);
        int[] realIds = this.real.getInts(x, z, width, height);
        int[] returnIds = IntCache.getIntCache(width * height);

        for (int i = 0; i < width * width; ++i) {
            if (shadowIds[i] != realIds[i]) {
                if (shadowIds[i] < 256) { // If there's a discrepancy and the shadow biome is not an actual shadowed biome, assume we're wrong and replace with real
                    returnIds[i] = realIds[i];
                } else {
                    returnIds[i] = shadowIds[i];
                }
            } else {
                returnIds[i] = shadowIds[i];
            }
        }
        return returnIds;
    }
}
