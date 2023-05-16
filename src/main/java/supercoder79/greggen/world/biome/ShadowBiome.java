package supercoder79.greggen.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import supercoder79.greggen.util.BiomePicker;

public abstract class ShadowBiome extends BiomeGenBase {
    private int realId = -1;

    public ShadowBiome(int id) {
        super(id, false);
        GregGenBiomes.SHADOW_BIOMES.put(id, this);
    }

    public void shadowBiome(int realId, double weight) {
        this.realId = realId;
        GregGenBiomes.BIOME_PICKERS.computeIfAbsent(realId, (id) -> new BiomePicker()).add(this.biomeID, weight);
    }

    public int getRealId() {
        if (realId == -1) {
            throw new IllegalStateException("Shadow biome " + this.biomeName + " has no real biome ID!");
        }

        return realId;
    }
}
