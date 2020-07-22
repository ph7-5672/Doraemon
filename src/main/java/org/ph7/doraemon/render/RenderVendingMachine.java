package org.ph7.doraemon.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.entity.EntityVendingMachine;
import org.ph7.doraemon.model.ModelVendingMachine;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderVendingMachine extends RenderBase<EntityVendingMachine>
{
    private static final ResourceLocation TEXTURE_MACHINE = new ResourceLocation(Reference.MOD_ID, "textures/entity/vending_machine.png");
    private final ModelVendingMachine modelVendingMachine = new ModelVendingMachine();

    public RenderVendingMachine(RenderManager renderManager)
    {
        super(renderManager);
        this.shadowSize = 0.5F;
    }

    @Override
    protected ModelBase getModel()
    {
        return modelVendingMachine;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityVendingMachine entity)
    {
        return TEXTURE_MACHINE;
    }
}
