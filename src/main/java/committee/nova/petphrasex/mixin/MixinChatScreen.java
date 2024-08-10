package committee.nova.petphrasex.mixin;

import committee.nova.petphrasex.client.PetphraseClient;
import committee.nova.petphrasex.util.StringUtil;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChatScreen.class)
public abstract class MixinChatScreen {
    @ModifyVariable(method = "sendMessage", argsOnly = true, at = @At("STORE"))
    private String modifyMsg(String chatMessage) {
        return StringUtil.fillPetPhraseIn(chatMessage, PetphraseClient.cfg.petPhrase, PetphraseClient.cfg.filteredPrefix);
    }
}
