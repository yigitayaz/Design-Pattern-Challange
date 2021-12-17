import java.awt.*;
import java.util.ArrayList;

public class TowerPoison extends Tower{
    //TODO
    public TowerPoison(Vector2D position){
        this.range = 75;
        this.rOF = 10;
        this.damage = 5;
        this.cost = 25;
        this.timer = 0;
        this.killCount = 0;
        this.position = position;
    }
    /**
     * This function holds a timer and attacks closest  monster in range according to its Rate of Fire.
     * It also changes state of Monster if Monster receives a damage from poisonTower.
     * See also {@link #getClosestMonster(ArrayList)}
     */
    @Override
    public void step() {
        //TODO
        if(timer == 0){
            Monster monster = getClosestMonster(monsterList);
            if(monster != null) {
                if(monster.getState() == null) {
                    monster.changeState(new MonsterPoisonState(monster));
                }
                monster.decreaseHealth(damage);
                if(monster.getHealth() <= 15) killCount++;
                timer = rOF;
            }
        }
        else timer--;
    }

    @Override
    public void paint(Graphics g) {
        //TODO
        g.setColor(Color.green);
        g.fillOval(position.getIntX()-Commons.TowerSize/2,position.getIntY()-Commons.TowerSize/2,Commons.TowerSize, Commons.TowerSize);
        g.setColor(Color.pink);
        g.drawOval(position.getIntX()-range,position.getIntY()-range,range*2,range*2);
    }
}
