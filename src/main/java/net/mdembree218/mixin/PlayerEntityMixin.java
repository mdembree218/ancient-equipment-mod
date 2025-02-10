// src/main/java/net/mdembree218/mixin/PlayerEntityMixin.java
package net.mdembree218.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

//    @Inject(method = "getAttackReach", at = @At("HEAD"), cancellable = true)
//    private void getAttackReach(CallbackInfoReturnable<Float> cir) {
//        PlayerEntity player = (PlayerEntity) (Object) this;
//        ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);
//        if (itemStack.getItem() instanceof CustomSwordItem customSword) {
//            cir.setReturnValue(customSword.getAttackRange());
//        }
//    }
}