package net.damku1214.damkusweaponry.util;

import net.damku1214.damkusweaponry.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        makeBow(ModItems.TEHONITE_BOW.get());
        makeBow(ModItems.GALAXY_NOVA.get());
    }

    private static void makeBow(Item item) {
        ItemProperties.register(item, new ResourceLocation("pull"), (itemStack, level, entity, p_174638_) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != itemStack ? 0.0F : (float)(itemStack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(item, new ResourceLocation("pulling"), (itemStack, level, entity, p_174633_) -> {
            return entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }
}
