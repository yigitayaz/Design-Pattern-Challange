import java.awt.*;
import java.util.ArrayList;

/**
 * TowerIce class for creating Ice Tower objects
 */
public class TowerIce extends Tower{
    //TODO
    public TowerIce(Vector2D position){
        this.range = 100;
        this.rOF = 20;
        this.damage = 10;
        this.cost = 15;
        this.timer = 0;
        this.killCount = 0;
        this.position = position;
    }

    /**
     * This function holds a timer and attacks closest  monster in range according to its Rate of Fire.
     * It also changes state of Monster if Monster receives a damage from iceTower. 
     * See also {@link #getClosestMonster(ArrayList)}
     */
    @Override
    public void step() {
        //TODO
        if(timer == 0){
            Monster monster = getClosestMonster(monsterList);
            if(monster != null) {
                if(monster.getState() == null) monster.changeState(new MonsterIceState(monster));
                monster.decreaseHealth(damage);
                if(monster.getHealth() == 0) killCount++;
                timer = rOF;
            }
        }
        else timer--;
    }

    /**
     * This function helps to paint Tower.
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        g.setColor(Color.blue);
        g.fillOval(position.getIntX()-Commons.TowerSize/2,position.getIntY()-Commons.TowerSize/2,Commons.TowerSize, Commons.TowerSize);
        g.setColor(Color.pink);
        g.drawOval(position.getIntX()-range,position.getIntY()-range,range*2,range*2);
    }
}
