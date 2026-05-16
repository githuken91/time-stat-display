package net.scrambledzmods.time_stat_display.client;

import com.google.gson.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

import static net.scrambledzmods.time_stat_display.TimeStatDisplay.MOD_ID;

public class TSDConfig {
    public static ConfigClassHandler<TSDConfig> HANDLER = ConfigClassHandler.createBuilder(TSDConfig.class)
            .id(Identifier.fromNamespaceAndPath(MOD_ID, ""))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("timestatdisplay.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                    .setJson5(true)
                    .build())
            .build();

    @SerialEntry
    public static boolean clockType = false;

    public static Runnable save = () -> {
        TSDConfig.HANDLER.save();
    };
}