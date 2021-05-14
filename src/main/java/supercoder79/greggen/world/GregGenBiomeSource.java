package supercoder79.greggen.world;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import supercoder79.greggen.world.biome.GregGenBiomes;
import supercoder79.greggen.world.layer.GregGenLayers;

public class GregGenBiomeSource extends WorldChunkManager {
    private final GenLayer shadowLayer;
    private final GenLayer shadowVoronoiLayer;

    public GregGenBiomeSource(World world) {
        super(world);

        GenLayer[] layers = GregGenLayers.build(world.getSeed(), world.getWorldInfo().getTerrainType());

        // Scaled up (pos << 2) layer
        ObfuscationReflectionHelper.setPrivateValue(WorldChunkManager.class, this, layers[0], "genBiomes", "field_76944_d");
        // World coords layer
        ObfuscationReflectionHelper.setPrivateValue(WorldChunkManager.class, this, layers[1], "biomeIndexLayer", "field_76945_e");
        // Shadow biome layer (scaled up)
        this.shadowLayer = layers[2];
        // Shadow biome layer in world coords
        this.shadowVoronoiLayer = layers[3];
    }

    public BiomeGenBase[] getShadowBiome(BiomeGenBase[] biomes, int x, int z, int width, int height) {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height) {
            biomes = new BiomeGenBase[width * height];
        }

        int[] aint = this.shadowVoronoiLayer.getInts(x, z, width, height);

        for (int i = 0; i < width * height; ++i) {
            int biomeId = aint[i];
            if (biomeId > 255) {
                biomes[i] = GregGenBiomes.SHADOW_BIOMES.get(biomeId);
            } else {
                biomes[i] = BiomeGenBase.getBiome(biomeId);
            }
        }

        return biomes;
    }
}
