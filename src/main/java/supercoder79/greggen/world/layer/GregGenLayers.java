package supercoder79.greggen.world.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public final class GregGenLayers {
    public static GenLayer[] build(long seed, WorldType worldType)
    {

        GenLayerIsland genlayerisland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        genlayeraddisland = new GenLayerAddIsland(3L, genlayeraddsnow);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, GenLayerEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
        genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer2 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        byte biomeSize = 4;

        if (worldType == WorldType.LARGE_BIOMES)
        {
            biomeSize = 6;
        }

        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer2, 0);
        GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, genlayer);

        // TODO: figure out if there's a way to use forge's hook
        GenLayer biomeLayer = new GenLayerBiome(200L, genlayer2, worldType);

        GenLayer shadowLayer = new ShadowBiomeLayer(300L, biomeLayer);

        shadowLayer = GenLayerZoom.magnify(1000L, shadowLayer, 2);

        // Create new reference to avoid overwriting the old one
        GenLayer biomeLayer2 = new NoOpLayer(1000, biomeLayer);

        biomeLayer2 = GenLayerZoom.magnify(1000L, biomeLayer2, 2);

        biomeLayer2 = new GenLayerBiomeEdge(1000L, biomeLayer2);

        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);

        // TODO: replace after hills
        GenLayerHills genlayerhills = new GenLayerHills(1000L, biomeLayer2, genlayer1);
        genlayer = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, biomeSize);
        GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        biomeLayer2 = new GenLayerRareBiome(1001L, genlayerhills);

        for (int j = 0; j < biomeSize; ++j)
        {
            biomeLayer2 = new GenLayerZoom((1000 + j), biomeLayer2);
            shadowLayer = new GenLayerZoom((1000 + j), shadowLayer);

            if (j == 0)
            {
                biomeLayer2 = new GenLayerAddIsland(3L, biomeLayer2);
            }

            if (j == 1)
            {
                biomeLayer2 = new GenLayerShore(1000L, biomeLayer2);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, biomeLayer2);
        GenLayer smoothShadowLayer = new GenLayerSmooth(1000L, shadowLayer);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        smoothShadowLayer = new FixFinalizeLayer(100L, smoothShadowLayer, genlayerrivermix);

        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        NonMaskingVoronoiZoomLayer voronoiShadowLayer = new NonMaskingVoronoiZoomLayer(10L, smoothShadowLayer);

        genlayerrivermix.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);
        smoothShadowLayer.initWorldGenSeed(seed);
        voronoiShadowLayer.initWorldGenSeed(seed);

        return new GenLayer[] {genlayerrivermix, genlayervoronoizoom, smoothShadowLayer, voronoiShadowLayer};
    }
}
