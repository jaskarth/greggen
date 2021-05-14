package supercoder79.greggen.world.layer;

import net.minecraft.world.gen.layer.GenLayer;

import java.util.Arrays;

public final class NoOpLayer extends GenLayer {
    public NoOpLayer(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int x, int z, int width, int height) {
        int[] parentIds = this.parent.getInts(x, z, width, height);
        int[] ret = Arrays.copyOf(parentIds, parentIds.length);

        return ret;
    }
}
