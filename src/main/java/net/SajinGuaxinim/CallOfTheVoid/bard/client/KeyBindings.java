package net.SajinGuaxinim.CallOfTheVoid.bard.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String CATEGORY = "key.categories.callofthevoid.bard";

    public static final KeyMapping TOGGLE_MODE = new KeyMapping(
            "key.callofthevoid.toggle_mode",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R, // Tecla R para trocar modo
            CATEGORY
    );

    public static final KeyMapping CYCLE_SONG = new KeyMapping(
            "key.callofthevoid.cycle_song",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V, // Tecla V para trocar música
            CATEGORY
    );

    public static final KeyMapping OPEN_MINIGAME = new KeyMapping(
            "key.callofthevoid.open_minigame",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G, // Tecla G para abrir minigame
            CATEGORY
    );
}