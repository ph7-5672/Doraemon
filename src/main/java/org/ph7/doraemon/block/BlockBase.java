package org.ph7.doraemon.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.ph7.doraemon.common.Reference;

public class BlockBase extends Block
{
    public BlockBase(String name, Material blockMaterialIn)
    {
        super(blockMaterialIn);
        this.setRegistryName(Reference.MOD_ID, name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reference.CREATIVE_TAB);
    }
}
