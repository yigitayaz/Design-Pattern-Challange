import java.awt.*;

/**
 * This class is dedicated to change Monster's state if the Monster is inflicted with poison.
 */
public class MonsterPoisonState extends MonsterState{
    //TODO
    public MonsterPoisonState(Monster monster){
        this.monster = monster;
        timer = 3;
    }
    /**
     * This function updates the state of the given Monster if Monster's state is null.
     */
    @Override
    public void update() {
        if(timer!=0){
            monster.decreaseHealth(5);
            timer--;
        }
        else monster.changeState(null);
    }

    /**
     * Helps to paint poison state of Monster.
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        Vector2D position = monster.getPosition();
        g.setColor(Color.GREEN);
        g.drawRect(position.getIntX()-Commons.MonsterSize/2, position.getIntY()- Commons.MonsterSize/2, Commons.MonsterSize,Commons.MonsterSize);
    }
}
