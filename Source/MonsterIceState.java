import java.awt.*;

/**
 * This class is dedicated for Monsters that are inflected with IceTower shot.
 */
public class MonsterIceState extends MonsterState{
    //TODO

    /**
     * Constructor for MonsterIceState.
     * @param monster Monster object
     */
    public MonsterIceState(Monster monster){
        this.monster = monster;
        timer = 5;
    }

    /**
     * This function updates the state of the given Monster if Monster's state is null.
     */
    @Override
    public void update() {
        if(timer != 0){
            monster.setSpeed(0.2*(6-timer));
            timer--;
        }
        else monster.changeState(null);

    }

    /**
     * This function helps to paint state of the Monster.
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        Vector2D position = monster.getPosition();
        g.setColor(Color.BLUE);
        g.drawRect(position.getIntX()-Commons.MonsterSize/2, position.getIntY()- Commons.MonsterSize/2, Commons.MonsterSize,Commons.MonsterSize);
    }
}
