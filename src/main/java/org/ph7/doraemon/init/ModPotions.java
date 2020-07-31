package org.ph7.doraemon.init;

import com.google.common.collect.Lists;
import net.minecraft.potion.Potion;
import org.ph7.doraemon.common.Reference;
import org.ph7.doraemon.potion.PotionAirGun;
import org.ph7.doraemon.potion.PotionMemory;

import java.util.List;

public class ModPotions
{

    public static List<Potion> POTIONS = Lists.newArrayList();

    public static PotionAirGun AIR_GUN = new PotionAirGun();
    public static PotionMemory MEMORY = new PotionMemory();

    static
    {
        //register(AIR_GUN, "air_gun");
        //register(MEMORY, "memory");
    }

    public static void register(Potion potion, String name)
    {
        potion.setRegistryName(Reference.MOD_ID, name);
        POTIONS.add(potion);
    }


}
