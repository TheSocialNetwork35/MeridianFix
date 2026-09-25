// MeridianFix modification, 2026-09-25: 26.3 field name and live empty-map view semantics.
package org.embeddedt.modernfix.common.mixin.perf.ticking_chunk_alloc;

import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.Map;

@Mixin(value = ChunkAccess.class, priority = 800)
public class ChunkAccessMixin {
    @Shadow @Final private Map<?, ?> structureReferences;
    private Map<?, ?> mfix$structureRefsView;

    /**
     * @author embeddedt
     * @reason Cache the returned map view while preserving vanilla live-view behavior
     */
    @Overwrite
    public Map<?, ?> getAllReferences() {
        Map<?, ?> view = this.mfix$structureRefsView;
        if(view == null) {
            this.mfix$structureRefsView = view = Collections.unmodifiableMap(this.structureReferences);
        }
        return view;
    }
}
