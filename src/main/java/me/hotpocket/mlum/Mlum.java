package me.hotpocket.mlum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.ColorHelper;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.List;

public class Mlum implements ClientModInitializer {

    public boolean isContraband;
    public ItemStack currentItem;
    public static Mlum instance;
    public static HashMap<String, Integer> chocoMap = new HashMap<>();
    private static KeyBinding keyBinding;
    private final SimpleOption<Boolean> locateFile = SimpleOption.ofBoolean("File", false);
    private final SimpleOption<Boolean> locateRazor = SimpleOption.ofBoolean("Razor", false);
    private final SimpleOption<Boolean> locateTape = SimpleOption.ofBoolean("Duct Tape", false);
    private final SimpleOption<Boolean> locateWire = SimpleOption.ofBoolean("Wire", false);
    private final SimpleOption<Boolean> locateMetal = SimpleOption.ofBoolean("Sheet of Metal", false);
    private final SimpleOption<Boolean> locateGlass = SimpleOption.ofBoolean("Shard of Glass", false);
    private final SimpleOption<Boolean> locateNails = SimpleOption.ofBoolean("Nails", false);
    private final SimpleOption<Boolean> locateSoap = SimpleOption.ofBoolean("Soap", false);
    private final SimpleOption<Boolean> locateBat = SimpleOption.ofBoolean("Baseball Bat", false);
    private final SimpleOption<Boolean> locateSock = SimpleOption.ofBoolean("Sock", false);
    private final SimpleOption<Boolean> locateTimber = SimpleOption.ofBoolean("Timber", false);
    private final SimpleOption<Boolean> locateNewspaper = SimpleOption.ofBoolean("Newspaper", false);
    private final SimpleOption<Boolean> locateCrowbar = SimpleOption.ofBoolean("Crowbar", false);
    private final SimpleOption<Boolean> locateTrowel = SimpleOption.ofBoolean("Trowel", false);
    private final SimpleOption<Boolean> locateCloak = SimpleOption.ofBoolean("Cloak", false);
    private final SimpleOption<Boolean> locateShank = SimpleOption.ofBoolean("Shank", false);

    public SimpleOption<Boolean> getLocateFile() { return this.locateFile; }
    public SimpleOption<Boolean> getLocateRazor() { return this.locateRazor; }
    public SimpleOption<Boolean> getLocateTape() { return this.locateTape; }
    public SimpleOption<Boolean> getLocateWire() { return this.locateWire; }
    public SimpleOption<Boolean> getLocateMetal() { return this.locateMetal; }
    public SimpleOption<Boolean> getLocateGlass() { return this.locateGlass; }
    public SimpleOption<Boolean> getLocateNails() { return this.locateNails; }
    public SimpleOption<Boolean> getLocateSoap() { return this.locateSoap; }
    public SimpleOption<Boolean> getLocateBat() { return this.locateBat; }
    public SimpleOption<Boolean> getLocateSock() { return this.locateSock; }
    public SimpleOption<Boolean> getLocateTimber() { return this.locateTimber; }
    public SimpleOption<Boolean> getLocateNewspaper() { return this.locateNewspaper; }
    public SimpleOption<Boolean> getLocateCrowbar() { return this.locateCrowbar; }
    public SimpleOption<Boolean> getLocateTrowel() { return this.locateTrowel; }
    public SimpleOption<Boolean> getLocateCloak() { return this.locateCloak; }
    public SimpleOption<Boolean> getLocateShank() { return this.locateShank; }

    private void evenRotation(MinecraftClient client) {
        int rot = 0;
        switch (client.player.getHorizontalFacing()) {
            case NORTH:
                rot = -180;
                break;
            case SOUTH:
                rot = 0;
                break;
            case EAST:
                rot = -90;
                break;
            case WEST:
                rot = 90;
                break;
        }
        client.player.setYaw(rot);
    }

    @Override
    public void onInitializeClient() {
        instance = this;
        chocoMap.put("Timber", 4);
        chocoMap.put("File", 1);
        chocoMap.put("Bar of Soap", 1);
        chocoMap.put("Sheet of Metal", 4);
        chocoMap.put("Sock", 1);
        chocoMap.put("Comb", 0);
        chocoMap.put("Razor Blade", 1);
        chocoMap.put("Wet Newspaper", 2);
        chocoMap.put("Roll of Duct Tape", 3);
        chocoMap.put("Wire", 1);
        chocoMap.put("Shard of Glass", 7);
        chocoMap.put("Nails", 5);
        chocoMap.put("Baseball Bat", 5);
        chocoMap.put("Bar of Metal", 5);
        chocoMap.put("Tool Handle", 5);
        chocoMap.put("Crowbar", 5);
        chocoMap.put("Flimsy Pickaxe", 21);
        chocoMap.put("Flimsy Shovel", 26);
        chocoMap.put("Flimsy Cutters", 26);
        chocoMap.put("Paperclip", 0);
        chocoMap.put("Bamboo", 0);
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Rotate", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "mlum" // The translation key of the keybinding's category.
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                evenRotation(client);
            }
        });
        ItemTooltipCallback.EVENT.register((ItemStack stack, TooltipContext context, List<Text> lines) -> {
            if (lines.size() == 0) return;
            Mlum.instance.isContraband = isContraband(lines);
            Mlum.instance.currentItem = stack;
            if (isCraftable(lines)) {
                int price = 0;
                float y = 25F;
                boolean didthegreen = false;
                MatrixStack matrixStack = new MatrixStack();
                MinecraftClient client = MinecraftClient.getInstance();
                for (int i = 2; i < lines.size(); i++) {
                    String requirement = lines.get(i).getString().replaceFirst(" - \\dx ", "");
                    int amount = Integer.parseInt(String.valueOf(lines.get(i).getString().charAt(3)));
                    if (chocoMap.containsKey(requirement)) {
                        price += (amount * chocoMap.get(requirement));
                        for (ItemStack item : client.player.getInventory().main) {
                            if ((item.getName().getString().equalsIgnoreCase(requirement)) && (item.getCount() >= amount)) {
                                lines.set(i, Text.of(" ✔ " + requirement + " x" + amount).getWithStyle(Style.EMPTY.withColor(Formatting.GREEN)).get(0));
                                client.textRenderer.draw(matrixStack, amount + "x " + requirement + " - " + (amount * chocoMap.get(requirement)), 5F, y, ColorHelper.Argb.getArgb(255, 75, 255, 75));
                                price -= (amount * chocoMap.get(requirement));
                                didthegreen = true;
                            }
                        }
                        if (!didthegreen) {
                            lines.set(i, Text.of(" ❌ " + requirement + " x" + amount).getWithStyle(Style.EMPTY.withColor(Formatting.RED)).get(0));
                            client.textRenderer.draw(matrixStack, amount + "x " + requirement + " - " + (amount * chocoMap.get(requirement)), 5F, y, ColorHelper.Argb.getArgb(255, 255, 75, 75));
                        }
                        y += 10;
                        didthegreen = false;
                    }
                }
                client.getItemRenderer().renderInGui(matrixStack, new ItemStack(Items.NETHER_BRICK), 5, 5);
                client.textRenderer.draw(matrixStack, "x" + price, 25F, 10F, ColorHelper.Argb.getArgb(255, 255, 255, 255));
            }
        });
    }

    private boolean isContraband(List<Text> lines) {
        for (Text text : lines)
            if (text.getString().contains("CONTRABAND"))
                return true;
        return false;
    }

    private boolean isCraftable(List<Text> lines) {
        for (Text text : lines)
            if (text.getString().contains("Requirements:"))
                return true;
        return false;
    }
}
