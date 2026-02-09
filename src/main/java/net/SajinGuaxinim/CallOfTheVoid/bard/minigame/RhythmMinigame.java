package net.SajinGuaxinim.CallOfTheVoid.bard.minigame;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RhythmMinigame {
    private static final int SEQUENCE_LENGTH = 5;
    private static final long NOTE_INTERVAL = 500; // ms entre notas

    private final Player player;
    private final List<RhythmNote> sequence;
    private int currentIndex;
    private long lastNoteTime;
    private boolean isActive;

    public RhythmMinigame(Player player) {
        this.player = player;
        this.sequence = new ArrayList<>();
        this.currentIndex = 0;
        this.isActive = false;
    }

    public void start() {
        generateSequence();
        isActive = true;
        currentIndex = 0;
        lastNoteTime = System.currentTimeMillis();
    }

    private void generateSequence() {
        sequence.clear();
        Random random = new Random();

        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            RhythmNote note = RhythmNote.values()[random.nextInt(RhythmNote.values().length)];
            sequence.add(note);
        }
    }

    public MinigameResult processInput(RhythmNote inputNote) {
        if (!isActive) {
            return MinigameResult.NOT_ACTIVE;
        }

        long currentTime = System.currentTimeMillis();
        long timeDiff = currentTime - lastNoteTime;

        RhythmNote expectedNote = sequence.get(currentIndex);

        // Verifica se acertou a nota
        if (inputNote != expectedNote) {
            player.sendSystemMessage(Component.literal("Você errou!").withStyle(ChatFormatting.RED));
            reset();
            return MinigameResult.MISS;
        }

        // Verifica timing (janela de 200ms para perfeito, 400ms para bom)
        TimingQuality timing;
        if (timeDiff < 200) {
            timing = TimingQuality.PERFECT;
        } else if (timeDiff < 400) {
            timing = TimingQuality.GOOD;
        } else {
            timing = TimingQuality.LATE;
        }

        currentIndex++;
        lastNoteTime = currentTime;

        // Completou a sequência
        if (currentIndex >= sequence.size()) {
            isActive = false;
            return new MinigameResult(true, timing, currentIndex);
        }

        // Feedback de timing
        player.sendSystemMessage(Component.literal(timing.getDisplayText()).withStyle(ChatFormatting.GREEN));

        return new MinigameResult(false, timing, currentIndex);
    }

    public void reset() {
        currentIndex = 0;
        isActive = false;
        sequence.clear();
    }

    public List<RhythmNote> getSequence() {
        return new ArrayList<>(sequence);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public boolean isActive() {
        return isActive;
    }

    // Enum de Notas
    public enum RhythmNote {
        RED(0xFF0000, "Nota Vermelha"),
        GREEN(0x00FF00, "Nota Verde"),
        BLUE(0x0000FF, "Nota Azul"),
        YELLOW(0xFFFF00, "Nota Amarela");

        private final int color;
        private final String name;

        RhythmNote(int color, String name) {
            this.color = color;
            this.name = name;
        }

        public int getColor() { return color; }
        public String getName() { return name; }
    }

    // Qualidade do Timing
    public enum TimingQuality {
        PERFECT(2.0f, "Perfeito!"),
        GOOD(1.5f, "Bom!"),
        LATE(1.0f, "Atrasado");

        private final float multiplier;
        private final String displayText;

        TimingQuality(float multiplier, String displayText) {
            this.multiplier = multiplier;
            this.displayText = displayText;
        }

        public float getMultiplier() { return multiplier; }
        public String getDisplayText() { return displayText; }
    }

    // Resultado do Minigame
    public static class MinigameResult {
        public static final MinigameResult NOT_ACTIVE = new MinigameResult(false, null, 0);
        public static final MinigameResult MISS = new MinigameResult(false, null, -1);

        private final boolean completed;
        private final TimingQuality timing;
        private final int notesHit;

        public MinigameResult(boolean completed, TimingQuality timing, int notesHit) {
            this.completed = completed;
            this.timing = timing;
            this.notesHit = notesHit;
        }

        public boolean isCompleted() { return completed; }
        public TimingQuality getTiming() { return timing; }
        public int getNotesHit() { return notesHit; }
        public boolean isMiss() { return notesHit == -1; }
    }
}