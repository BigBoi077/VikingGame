package cegepst;

import cegepst.engine.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "images/demo.png";
    private Image background;
    private ArrayList<Blockade> worldBorders;

    public World() {
        loadBackground();
        worldBorders = new ArrayList<>();
        Blockade topdBorder = new Blockade();
        topdBorder.setDimensions(400, 48);
        topdBorder.teleport(0, 0);

        Blockade topdBorder2 = new Blockade();
        topdBorder2.setDimensions(400, 48);
        topdBorder2.teleport(464, 0);

        worldBorders.add(topdBorder);
        worldBorders.add(topdBorder2);
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(background, 0, -64);
        for (Blockade blockade : worldBorders) {
            blockade.draw(buffer);
        }
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
