import java.awt.*;
import java.util.ArrayList;

/**
 * Tower Regular for Regular Tower
 */
public class TowerRegular extends Tower{

    //TODO

    public TowerRegular(Vector2D position){
        this.range = 150;
        this.rOF = 20;
        this.damage = 20;
        this.cost = 20;
        this.timer = 0;
        this.killCount = 0;
        this.position = position;

    }
    /**
     * This function holds a timer and attacks closest  monster in range according to its Rate of Fire.
     *
     * See also {@link #getClosestMonster(ArrayList)}
     */
    @Override
    public void step() {
        if(timer == 0){
            Monster monster = getClosestMonster(monsterList);
            if(monster != null) {
                monster.decreaseHealth(damage);
                if(monster.getHealth() == 0) killCount++;
                timer = rOF;
            }
        }
        else timer--;
    }

    @Override
    public void paint(Graphics g) {
        //TODO

        g.setColor(Color.MAGENTA);
        g.fillOval(position.getIntX()-Commons.TowerSize/2,position.getIntY()-Commons.TowerSize/2,Commons.TowerSize, Commons.TowerSize);
        g.setColor(Color.pink);
        g.drawOval(position.getIntX()-range,position.getIntY()-range,range*2,range*2);
    }
}
