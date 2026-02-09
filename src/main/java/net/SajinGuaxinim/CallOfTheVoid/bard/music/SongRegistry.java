package net.SajinGuaxinim.CallOfTheVoid.bard.music;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.minecraft.resources.ResourceLocation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SongRegistry {
    private static final Map<ResourceLocation, BardSong> SONGS = new HashMap<>();

    // Músicas pré-carregadas (dos discos do Minecraft)
    public static final ResourceLocation VANILLA_13 = register("minecraft", "13");
    public static final ResourceLocation VANILLA_CAT = register("minecraft", "cat");
    public static final ResourceLocation VANILLA_BLOCKS = register("minecraft", "blocks");
    public static final ResourceLocation VANILLA_CHIRP = register("minecraft", "chirp");
    public static final ResourceLocation VANILLA_FAR = register("minecraft", "far");
    public static final ResourceLocation VANILLA_MALL = register("minecraft", "mall");
    public static final ResourceLocation VANILLA_MELLOHI = register("minecraft", "mellohi");
    public static final ResourceLocation VANILLA_STAL = register("minecraft", "stal");
    public static final ResourceLocation VANILLA_STRAD = register("minecraft", "strad");
    public static final ResourceLocation VANILLA_WARD = register("minecraft", "ward");
    public static final ResourceLocation VANILLA_11 = register("minecraft", "11");
    public static final ResourceLocation VANILLA_WAIT = register("minecraft", "wait");

    public static ResourceLocation register(String namespace, String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(namespace, path);
        // As músicas vanilla serão referências aos discos originais
        return id;
    }

    public static ResourceLocation registerCustomSong(String name, BardSong song) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, name);
        SONGS.put(id, song);
        CallOfTheVoid.LOGGER.info("Registered custom song: {}", name);
        return id;
    }

    public static BardSong getSong(ResourceLocation id) {
        return SONGS.get(id);
    }

    public static Set<ResourceLocation> getAllSongIds() {
        return SONGS.keySet();
    }

    public static void loadFromFile(File file) {
        try {
            BardSong song = NBSParser.parseNBS(file);
            String fileName = file.getName().replace(".nbs", "");
            registerCustomSong(fileName, song);
        } catch (IOException e) {
            CallOfTheVoid.LOGGER.error("Failed to load song from file: {}", file.getName(), e);
        }
    }

    public static void loadAllFromDirectory(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
            return;
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".nbs"));
        if (files != null) {
            for (File file : files) {
                loadFromFile(file);
            }
        }
    }
}