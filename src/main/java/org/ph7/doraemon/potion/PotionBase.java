package org.ph7.doraemon.potion;

import net.minecraft.potion.Potion;

public class PotionBase extends Potion
{
    protected PotionBase(boolean isBadEffectIn, int liquidColorIn)
    {
        super(isBadEffectIn, liquidColorIn);
        this.setIconIndex(0, 0);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return duration > 0;
    }
}
