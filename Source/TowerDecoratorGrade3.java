import java.awt.*;
import java.util.ArrayList;
/**
 * This class decorates Tower's according to their kill count.
 * @see #paint(Graphics)
 */
public class TowerDecoratorGrade3 extends TowerDecorator {
    //TODO

    public TowerDecoratorGrade3(Tower tower) {
        super(tower);
        this.tower = tower;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (killCount >= 50) {
            g.setColor(Color.BLACK);
            g.drawString("|", super.position.getIntX() + 3, super.position.getIntY());
        }
    }

    @Override
    public void step() {
        super.step();
        killCount = getKillCount();
    }
}
