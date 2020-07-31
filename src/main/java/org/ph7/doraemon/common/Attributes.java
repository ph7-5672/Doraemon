package org.ph7.doraemon.common;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

public class Attributes
{
    public static final IAttribute ENTITY_WIDTH_SCALE = new RangedAttribute(null, "doraemon.entityWidthScale", 1.0D, 1.401298464324817E-45D, 3.4028234663852886E38D)
                                                    .setDescription("Entity Width Scale")
                                                    .setShouldWatch(true);
    public static final IAttribute ENTITY_HEIGHT_SCALE = new RangedAttribute(null, "doraemon.entityHeightScale", 1.0D, 1.401298464324817E-45D, 3.4028234663852886E38D)
                                                    .setDescription("Entity Height Scale")
                                                    .setShouldWatch(true);
    public static final IAttribute ENTITY_WIDTH = new RangedAttribute(null, "doraemon.entityWidth", 1.0D, 1.401298464324817E-45D, 3.4028234663852886E38D)
                                                    .setDescription("Entity Width")
                                                    .setShouldWatch(true);
    public static final IAttribute ENTITY_HEIGHT = new RangedAttribute(null, "doraemon.entityHeight", 1.0D, 1.401298464324817E-45D, 3.4028234663852886E38D)
                                                    .setDescription("Entity Height")
                                                    .setShouldWatch(true);
}
