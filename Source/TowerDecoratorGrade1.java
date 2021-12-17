import java.awt.*;
import java.util.ArrayList;

/**
 * This class decorates Tower's according to their kill count.
 * @see #paint(Graphics) 
 */
public class TowerDecoratorGrade1 extends TowerDecorator{
    //TODO

    public TowerDecoratorGrade1(Tower tower){
        super(tower);
        this.tower = tower;
        this.killCount = tower.killCount;
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (killCount >= 10) {
            g.setColor(Color.BLACK);
            g.drawString("|", super.position.getIntX()-3 , super.position.getIntY());

        }


    }


    @Override
    public void step() {
        super.step();
    }

}

