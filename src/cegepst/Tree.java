package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.entity.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Tree extends StaticEntity {

    private static final String SPRITE_PATH = "images/tree.png";
    private Image image;
    private Blockade treeRoot;

    public Tree(int x, int y) {
        teleport(x, y);
        load();
        treeRoot = new Blockade();
        treeRoot.setDimensions(30, 16);
        blockadeFromTop();
    }

    public void blockadeFromTop() {
        treeRoot.teleport(x + 16, y + 64);
    }

    public void blockadeFromBottom() {
        treeRoot.teleport(x + 16, y + 48);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x, y);
        treeRoot.draw(buffer);
    }

    private void load() {
        try {
            image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(SPRITE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
