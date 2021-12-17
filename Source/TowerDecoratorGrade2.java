import java.awt.*;
import java.util.ArrayList;
/**
 * This class decorates Tower's according to their kill count.
 * @see #paint(Graphics)
 */
public class TowerDecoratorGrade2 extends TowerDecorator {
    //TODO
    public TowerDecoratorGrade2(Tower tower) {
        super(tower);
        this.tower = tower;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (killCount >= 25) {


            g.setColor(Color.BLACK);
            g.drawString("|", tower.position.getIntX(), tower.position.getIntY());
        }
    }

    @Override
    public void step() {
        super.step();
    }

}