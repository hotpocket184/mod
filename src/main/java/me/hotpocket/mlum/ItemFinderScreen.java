package me.hotpocket.mlum;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.SimpleOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ItemFinderScreen extends SimpleOptionsScreen {
    private static Mlum p_95572_ = Mlum.instance;
    public ItemFinderScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.of("Item Finder"), new SimpleOption[]{p_95572_.getLocateFile(), p_95572_.getLocateRazor(), p_95572_.getLocateTape(), p_95572_.getLocateWire(), p_95572_.getLocateMetal(), p_95572_.getLocateGlass(), p_95572_.getLocateNails(), p_95572_.getLocateSoap(), p_95572_.getLocateBat(), p_95572_.getLocateSock(), p_95572_.getLocateTimber(), p_95572_.getLocateNewspaper(), p_95572_.getLocateCrowbar(), p_95572_.getLocateTrowel(), p_95572_.getLocateCloak(), p_95572_.getLocateShank()});

    }
}
