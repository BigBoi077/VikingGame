import cegepst.engine.Buffer;
import cegepst.engine.Game;

public class VikingGame extends Game {

    private GamePad gamePad;
    private Player player;

    public VikingGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(200, 200);
    }

    @Override
    public void initialise() {

    }

    @Override
    public void update() {
        player.update();
        if (gamePad.isQuitPressed()) {
            super.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        player.draw(buffer);
    }

    @Override
    public void conclude() {

    }
}
