package net.scrambledzmods.time_stat_display.client;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.network.chat.Component;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .title(Component.literal("Time Stats Display"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Settings"))
                        .group(OptionGroup.createBuilder()
                                .name(Component.literal("Formatting"))
                                .description(OptionDescription.of(Component.literal("I don't think you need an explanation, unless your IQ is under room temperature.")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Component.literal("24 hour time"))
                                        .description(OptionDescription.of(Component.literal("Switch between the 24 hour clock and the 12 hour clock")))
                                        .binding(true, () -> TSDConfig.clockType, newVal -> TSDConfig.clockType = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .save(TSDConfig.save)
                .build().generateScreen(parentScreen);
    }
}