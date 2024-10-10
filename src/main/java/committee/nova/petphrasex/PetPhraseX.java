package committee.nova.petphrasex;

import committee.nova.petphrasex.util.StringUtil;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.ArrayList;
import java.util.List;

@Mod(petphrasex.MODID)
public class petphrasex {
    public static final String MODID = "petphrasexclient";
    public static final ForgeConfigSpec CLIENT_CONFIG;
    public static final ForgeConfigSpec.ConfigValue<String> petphrasex;
    public static final ForgeConfigSpec.ConfigValue<List<String>> filteredPrefix;
    public static final List<String> defaultList = new ArrayList<>();

    static {
        defaultList.add("/");
        defaultList.add("xaero-waypoint");
        final var builder = new ForgeConfigSpec.Builder();
        builder.comment("petphrasex Configuration");
        petphrasex = builder.comment("The pet phrase apply to your messages")
                .define("petphrasex", " nya");
        filteredPrefix = builder.comment("If the words in the list contain the message to be sent, petphrasex won't activate on the message.")
                .define("filteredPrefix", defaultList);
        CLIENT_CONFIG = builder.build();
    }

    public petphrasex() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientChat(final ClientChatEvent event) {
        final var petphrasex = petphrasex.petphrasex.get();
        if (petphrasex.isEmpty()) return;
        final var msg = event.getMessage();
        event.setMessage(StringUtil.fillpetphrasexIn(msg, petphrasex));
    }
}
