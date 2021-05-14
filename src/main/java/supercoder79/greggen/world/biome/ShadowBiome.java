package supercoder79.greggen.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import supercoder79.greggen.util.BiomePicker;

public abstract class ShadowBiome extends BiomeGenBase {
    public ShadowBiome(int id) {
        super(id, false);
        GregGenBiomes.SHADOW_BIOMES.put(id, this);
    }

    public void shadowBiome(int realId, double weight) {
        GregGenBiomes.BIOME_PICKERS.computeIfAbsent(realId, (id) -> new BiomePicker()).add(this.biomeID, weight);
    }
}
