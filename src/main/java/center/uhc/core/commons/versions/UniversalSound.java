package center.uhc.core.commons.versions;

import lombok.Getter;

public enum UniversalSound {

    //Other
    CHALLENGE_COMPLETE(null, "ui.toast.challenge_complete"),
    CLICK("random.click", "ui.button.click"),

    //Ambience
    AMBIENCE_CAVE("ambient.cave.cave", "ambient.cave"),

    //Entity
    CAT_MEOW("mob.cat.meow", "entity.cat.ambient"),

    VILLAGER_YES("mob.villager.yes", "entity.villager.yes"),
    VILLAGER_NO("mob.villager.no", "entity.villager.no"),

    ENDERDRAGON_GROWL("mob.enderdragon.growl", "entity.ender_dragon.growl"),
    ENDERDRAGON_HIT("mob.enderdragon.hit", "entity.ender_dragon.hurt"),

    BLAZE_DEATH("mob.blaze.death", "entity.blaze.death"),

    GHAST_DEATH("mob.ghast.death", "entity.ghast.death"),

    IRONGOLEM_DEATH("mob.irongolem.death", "entity.iron_golem.death"),

    WITHER_DEATH("mob.wither.death", "entity.wither.death"),

    HORSE_DEATH("mob.horse.death", "entity.horse.death"),

    ITEM_BREAK("random.break", "entity.item.break"),
    LEVEL_UP("random.levelup", "entity.player.levelup"),
    ORB_PICKUP("random.orb", "entity.experience_orb.pickup"),
    SPLASH("game.player.swim.splash", "entity.player.splash"),

    //Note
    NOTE_STICKS("note.hat", "block.note_block.hat"),
    NOTE_BASS("note.bass", "block.note_block.bass"),
    NOTE_PLING("note.pling", "block.note_block.pling"),

    //Block
    PORTAL_TRIGGER("portal.trigger", "block.portal.trigger");

    // Both use playsound args because yes
    @Getter private String sound_1_8;
    @Getter private String sound_1_16;

    private UniversalSound(String sound_1_8, String sound_1_16) {
        this.sound_1_8 = sound_1_8;
        this.sound_1_16 = sound_1_16;
    }

}
