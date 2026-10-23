package dev.hopperspeed;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(HopperSpeedMod.MOD_ID)
public final class HopperSpeedMod {
    public static final String MOD_ID = "hopperspeed";

    public HopperSpeedMod() {
        HopperSpeedSettings.load();
        RegisterCommandsEvent.BUS.addListener(HopperSpeedMod::registerCommands);
    }

    private static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("hopperspeed")
            .requires(source -> source.hasPermission(2))
            .executes(ctx -> {
                int interval = HopperSpeedSettings.getInterval();
                ctx.getSource().sendSuccess(
                    () -> Component.literal("[HopperSpeed] Intervalo atual: " + interval
                        + " tick(s) (" + (8.0 / interval) + "x em relacao ao vanilla)."),
                    false
                );
                return interval;
            })
            .then(Commands.argument("ticks", IntegerArgumentType.integer(1, 8))
                .executes(ctx -> changeInterval(ctx.getSource(),
                    IntegerArgumentType.getInteger(ctx, "ticks"))))
            .then(Commands.literal("reset")
                .executes(ctx -> changeInterval(ctx.getSource(), 8))));
    }

    private static int changeInterval(net.minecraft.commands.CommandSourceStack source, int ticks) {
        if (!HopperSpeedSettings.setInterval(ticks)) {
            source.sendFailure(Component.literal(
                "[HopperSpeed] Erro ao salvar config/hopperspeed.properties. Veja latest.log."));
            return 0;
        }
        source.sendSuccess(() -> Component.literal(
            "[HopperSpeed] Todos os funis carregados agora usam " + ticks
                + " tick(s) por transferencia. Configuracao salva."), true);
        return ticks;
    }
}
