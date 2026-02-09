package net.SajinGuaxinim.CallOfTheVoid.bard.client.gui;

import net.SajinGuaxinim.CallOfTheVoid.CallOfTheVoid;
import net.SajinGuaxinim.CallOfTheVoid.bard.minigame.RhythmMinigame;
import net.SajinGuaxinim.CallOfTheVoid.bard.minigame.StreakTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class RhythmGameScreen extends Screen {
    private static final ResourceLocation NOTE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(CallOfTheVoid.MOD_ID, "textures/gui/rhythm_game.png");

    private static final int NOTE_SIZE = 32;
    private static final int LANE_WIDTH = 64;
    private static final int HIT_LINE_Y = 200;

    private final RhythmMinigame minigame;
    private List<RhythmMinigame.RhythmNote> sequence;
    private int currentNoteIndex;

    public RhythmGameScreen(RhythmMinigame minigame) {
        super(Component.literal("Rhythm Minigame"));
        this.minigame = minigame;
        this.sequence = minigame.getSequence();
        this.currentNoteIndex = 0;
    }

    @Override
    protected void init() {
        super.init();
        if (!minigame.isActive()) {
            minigame.start();
            sequence = minigame.getSequence();
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics, mouseX, mouseY, partialTick);

        // Fundo semi-transparente
        graphics.fill(0, 0, width, height, 0x80000000);

        // Título
        graphics.drawCenteredString(
                font,
                "♪ Minigame Rítmico ♪",
                width / 2,
                20,
                0xFFFFFF
        );

        // Streak
        int streak = StreakTracker.getStreak(minecraft.player);
        graphics.drawCenteredString(
                font,
                "Streak: " + streak + "x",
                width / 2,
                40,
                streak >= 3 ? 0xFFD700 : 0xFFFFFF
        );

        // Linha de acerto
        graphics.fill(
                width / 2 - 150,
                HIT_LINE_Y - 2,
                width / 2 + 150,
                HIT_LINE_Y + 2,
                0xFFFFFFFF
        );

        // Renderiza sequência de notas
        renderNoteSequence(graphics);

        // Instruções
        graphics.drawCenteredString(
                font,
                "Pressione as teclas quando as notas atingirem a linha!",
                width / 2,
                height - 40,
                0xAAAAAA
        );

        graphics.drawCenteredString(
                font,
                "Q = Vermelho | W = Verde | E = Azul | R = Amarelo",
                width / 2,
                height - 20,
                0xAAAAAA
        );

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void renderNoteSequence(GuiGraphics graphics) {
        int centerX = width / 2;

        for (int i = 0; i < sequence.size(); i++) {
            RhythmMinigame.RhythmNote note = sequence.get(i);

            // Calcula posição Y (desce com o tempo)
            int offsetY = HIT_LINE_Y - ((i - currentNoteIndex) * 100);

            // Só renderiza notas visíveis
            if (offsetY < -50 || offsetY > height + 50) continue;

            // Determina cor da nota
            int color = note.getColor();

            // Renderiza nota como círculo colorido com símbolo de nota
            int noteX = centerX - NOTE_SIZE / 2;
            int noteY = offsetY - NOTE_SIZE / 2;

            // Círculo de fundo
            graphics.fill(
                    noteX, noteY,
                    noteX + NOTE_SIZE, noteY + NOTE_SIZE,
                    color | 0xFF000000
            );

            // Borda
            graphics.fill(
                    noteX - 2, noteY - 2,
                    noteX + NOTE_SIZE + 2, noteY - 1,
                    0xFFFFFFFF
            );
            graphics.fill(
                    noteX - 2, noteY + NOTE_SIZE + 1,
                    noteX + NOTE_SIZE + 2, noteY + NOTE_SIZE + 2,
                    0xFFFFFFFF
            );
            graphics.fill(
                    noteX - 2, noteY,
                    noteX - 1, noteY + NOTE_SIZE,
                    0xFFFFFFFF
            );
            graphics.fill(
                    noteX + NOTE_SIZE + 1, noteY,
                    noteX + NOTE_SIZE + 2, noteY + NOTE_SIZE,
                    0xFFFFFFFF
            );

            // Símbolo de nota musical (♪)
            graphics.drawCenteredString(
                    font,
                    "♪",
                    centerX,
                    offsetY - font.lineHeight / 2,
                    0xFFFFFF
            );

            // Destaque na nota atual
            if (i == currentNoteIndex) {
                graphics.fill(
                        noteX - 4, noteY - 4,
                        noteX + NOTE_SIZE + 4, noteY + NOTE_SIZE + 4,
                        0x80FFFFFF
                );
            }
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        RhythmMinigame.RhythmNote inputNote = null;

        // Q = Vermelho (81)
        if (keyCode == 81) inputNote = RhythmMinigame.RhythmNote.RED;
            // W = Verde (87)
        else if (keyCode == 87) inputNote = RhythmMinigame.RhythmNote.GREEN;
            // E = Azul (69)
        else if (keyCode == 69) inputNote = RhythmMinigame.RhythmNote.BLUE;
            // R = Amarelo (82)
        else if (keyCode == 82) inputNote = RhythmMinigame.RhythmNote.YELLOW;

        if (inputNote != null) {
            RhythmMinigame.MinigameResult result = minigame.processInput(inputNote);

            if (result.isMiss()) {
                // Errou
                minecraft.player.displayClientMessage(
                        Component.literal("✗ Miss!").withColor(0xFF0000),
                        true
                );
                StreakTracker.resetStreak(minecraft.player);
                onClose();
            } else if (result.isCompleted()) {
                // Completou!
                minecraft.player.displayClientMessage(
                        Component.literal("★ Perfeito! ★").withColor(0xFFD700),
                        true
                );
                StreakTracker.addSuccess(minecraft.player);
                onClose();
            } else {
                // Acertou a nota
                currentNoteIndex++;
                String feedback = result.getTiming().getDisplayText();
                int color = result.getTiming() == RhythmMinigame.TimingQuality.PERFECT ? 0x00FF00 : 0xFFFF00;

                minecraft.player.displayClientMessage(
                        Component.literal(feedback).withColor(color),
                        true
                );
            }

            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}