package dev.hopperspeed;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Configuracao global da instalacao/servidor; nao precisa varrer chunks. */
public final class HopperSpeedSettings {
    private static volatile int interval = 8;
    private static final String KEY = "intervalo_ticks";

    private HopperSpeedSettings() { }

    public static int getInterval() {
        return interval;
    }

    private static Path configFile() {
        return FMLPaths.CONFIGDIR.get().resolve("hopperspeed.properties");
    }

    public static void load() {
        Path file = configFile();
        if (!Files.isRegularFile(file)) {
            interval = 8;
            return;
        }
        Properties config = new Properties();
        try (InputStream in = Files.newInputStream(file)) {
            config.load(in);
            int value = Integer.parseInt(config.getProperty(KEY, "8").trim());
            interval = Math.max(1, Math.min(8, value));
        } catch (IOException | NumberFormatException ex) {
            interval = 8;
            System.err.println("[HopperSpeed] Configuracao invalida; usando 8 ticks: " + ex);
        }
    }

    public static boolean setInterval(int newInterval) {
        if (newInterval < 1 || newInterval > 8) {
            return false;
        }
        Properties properties = new Properties();
        properties.setProperty(KEY, Integer.toString(newInterval));
        Path file = configFile();
        try {
            Files.createDirectories(file.getParent());
            try (OutputStream out = Files.newOutputStream(file)) {
                properties.store(out, "HopperSpeed - Minecraft Forge 1.21.8");
            }
            interval = newInterval;
            return true;
        } catch (IOException ex) {
            System.err.println("[HopperSpeed] Falha ao salvar configuracao: " + ex);
            return false;
        }
    }
}
