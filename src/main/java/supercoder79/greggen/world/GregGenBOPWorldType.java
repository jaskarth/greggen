package supercoder79.greggen.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;

public final class GregGenBOPWorldType extends WorldType {
    public GregGenBOPWorldType() {
        super("greggen_bop");
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
        return new GregGenChunkGenerator(world, world.getSeed(), true);
    }

    @Override
    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer) {
        try {
            Class<?> GenLayerBiomeBOP = Class.forName("biomesoplenty.common.world.layer.GenLayerBiomeBOP");
            GenLayer ret = (GenLayer) GenLayerBiomeBOP.getConstructor(long.class, GenLayer.class, WorldType.class).newInstance(200L, parentLayer, this);

            ret = GenLayerZoom.magnify(1000L, ret, 2);
            ret = new GenLayerBiomeEdge(1000L, ret);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return super.getBiomeLayer(worldSeed, parentLayer);
        }
    }

    @Override
    public WorldChunkManager getChunkManager(World world) {
        try {
            Class<?> WorldChunkManagerBOP = Class.forName("biomesoplenty.common.world.WorldChunkManagerBOP");
            return (WorldChunkManager) WorldChunkManagerBOP.getConstructor(World.class).newInstance(world);
        } catch (Exception e) {
            e.printStackTrace();
            return super.getChunkManager(world);
        }
    }
}
