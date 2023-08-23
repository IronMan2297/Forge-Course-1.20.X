package net.ironman.mccourse.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.ironman.mccourse.MCCourseMod;
import net.ironman.mccourse.entity.custom.RhinoEntity;
import net.ironman.mccourse.entity.layers.ModModelLayers;
import net.ironman.mccourse.entity.variant.RhinoVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class RhinoRenderer extends MobRenderer<RhinoEntity, RhinoModel<RhinoEntity>> {
    private static final Map<RhinoVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RhinoVariant.class), map -> {
                map.put(RhinoVariant.DEFAULT,
                        new ResourceLocation(MCCourseMod.MOD_ID, "textures/entity/rhino.png"));
                map.put(RhinoVariant.WHITE,
                        new ResourceLocation(MCCourseMod.MOD_ID, "textures/entity/white_rhino.png"));
            });
    private static final ResourceLocation RHINO_LOCATION = new ResourceLocation(MCCourseMod.MOD_ID, "textures/entity/rhino.png");

    public RhinoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RhinoModel<>(pContext.bakeLayer(ModModelLayers.RHINO_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(RhinoEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    @Override
    public void render(RhinoEntity pEntity, float pEntityYaw, float pPartialTicks,
                       PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.67f, 0.67f, 0.67f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
