package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.RenderingEngine;
import cegepst.engine.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class VikingGame extends Game {

    private World world;
    private Tree tree;
    private GamePad gamePad;
    private Player player;
    private int soundCoolDown;

    public VikingGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(200, 200);
        world = new World();
        tree = new Tree(300, 350);
    }

    @Override
    public void initialise() {
        // RenderingEngine.getInstance().getScreen().hideCursor();
        // RenderingEngine.getInstance().getScreen().fullScreen();
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream("musics/map.wav")
            );
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        player.update();
        if (gamePad.isQuitPressed()) {
            super.stop();
        }
        soundCoolDown--;
        if (soundCoolDown < 0) {
            soundCoolDown = 0;
        }
        if (gamePad.isFirePressed() && soundCoolDown == 0) {
            soundCoolDown = 40;
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        this.getClass().getClassLoader().getResourceAsStream("sounds/best.wav")
                );
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (player.getY() < tree.getY() + 52) {
            tree.blockadeFromTop();
        } else {
            tree.blockadeFromBottom();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        world.draw(buffer);
        if (player.getY() < tree.getY() + 52) { // 80 - 28 (tolérance)
            player.draw(buffer);
            tree.draw(buffer);
        } else {
            tree.draw(buffer);
            player.draw(buffer);
        }
    }

    @Override
    public void conclude() {

    }
}
