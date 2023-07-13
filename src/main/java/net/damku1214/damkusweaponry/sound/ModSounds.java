package net.damku1214.damkusweaponry.sound;

import net.damku1214.damkusweaponry.DamkusWeaponry;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundEventRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DamkusWeaponry.MOD_ID);

    public static final RegistryObject<SoundEvent> JADE_VINE_SICKLE_USE_SHORT =
            registerSoundEvent("jade_vine_sickle_use_short");

    public static final RegistryObject<SoundEvent> JADE_VINE_SICKLE_USE_LONG =
            registerSoundEvent("jade_vine_sickle_use_long");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(DamkusWeaponry.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
