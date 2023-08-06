package me.hotpocket.mlum;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.item.ItemStack;

public class Mlum implements ClientModInitializer {

    public boolean isContraband;
    public static Mlum instance;

    @Override
    public void onInitializeClient() {
        instance = this;
    }
}
