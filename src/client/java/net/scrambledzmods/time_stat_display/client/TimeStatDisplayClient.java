package net.scrambledzmods.time_stat_display.client;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.clock.ClockManager;
import net.minecraft.world.timeline.Timeline;
import net.minecraft.world.timeline.Timelines;
import net.scrambledzmods.time_stat_display.TimeStatDisplay;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeStatDisplayClient implements ClientModInitializer {
	static Minecraft mc = Minecraft.getInstance();
	static Timeline clock = null;
	static ClockManager cm = null;
	// Initialize to 6:00 AM
	static long ticks = 0;
	static int intervals = 12;
	static String timeStr = "";

    @Override
	public void onInitializeClient() {
		TSDConfig.HANDLER.load();
		HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(TimeStatDisplay.MOD_ID, "before_chat"), TimeStatDisplayClient::extract);
	}

	private static void extract(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
		//int dayTime =
		cm = mc.level.clockManager();
		clock = mc.level.registryAccess().getOrThrow(Timelines.OVERWORLD_DAY).value();
		ticks = clock.getCurrentTicks(cm);

		intervals = Math.toIntExact((long)(72+Math.floor(ticks/83.333333333333333333))); // basically 5 minutes

		if (!TSDConfig.clockType) {
			timeStr = LocalTime.MIN.plusMinutes(intervals * 5)
					.format(DateTimeFormatter.ofPattern("h:mm a"));
		} else {
			timeStr = LocalTime.MIN.plusMinutes(intervals * 5)
					.format(DateTimeFormatter.ofPattern("HH:mm"));
		}
		graphics.text(mc.font, timeStr, 15, mc.getWindow().getGuiScaledHeight()-15, TSDConfig.color.getRGB(), true);
	}
}