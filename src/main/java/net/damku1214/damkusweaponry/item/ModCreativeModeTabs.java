package net.damku1214.damkusweaponry.item;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            DamkusWeaponry.MOD_ID);

    public static RegistryObject<CreativeModeTab> DAMKUS_WEAPONRY = CREATIVE_MODE_TABS.register("damkus_weaponry", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DAMKU.get()))
                    .title(Component.translatable("itemGroup.damkusweaponry")).displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.JADE_VINE_SICKLE.get());
                        pOutput.accept(ModItems.JADE.get());
                        pOutput.accept(ModItems.JADE_HANDLE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }


}
