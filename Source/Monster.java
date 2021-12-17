
import java.awt.*;

/**
 * Monster object for creation of Monsters
 */
public class Monster extends Entity {
    //TODO
    private int oldhealth;
    private int health;
    private double speed;
    private int reward;

    private Vector2D position;
    private Vector2D direction;

    private IMonsterStrategy strategy;

    private MonsterState state;

    public Monster(IMonsterStrategy strategy, Vector2D position, int waveCount){
        this.strategy = strategy;
        this.position = position;
        this.direction = new Vector2D(0,-1);
        this.health = 100+waveCount*20;
        this.oldhealth = health;
        this.reward = 10;
        this.speed = 1; // One pixel at every step
    }

    /**
     * Sets strategy of Monster with given strategy parameter. Monster is not aware of the type of strategy it has.
     * @param strategy random strategy assigned for Monster object
     */
    public void setStrategy(IMonsterStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Returns health of Monster
     * @return int health of Monster
     */
    public int getHealth() {
        return health;
    }

    /**
     * helps to decrease Monster's health if Monster has received damaged from a tower.
     * @param amount damage amount that has been received.
     */
    public void decreaseHealth(int amount) {
        this.health -= amount;
    }

    /**
     * Returns speed of Monster
     * @return
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets speed of Monster
     * @param speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Returns reward of killing Monster
     * @return
     */
    public int getReward() {
        return reward;
    }

    /**
     * returns Vector2D position of Monster
     * @return
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * This function helps to update direction of Monster with speed and direction of it.
     * See also {@link IMonsterStrategy#updateDirection(Vector2D, Vector2D)}
     * @param position current position of Monster
     * @param direction current direction of Monster
     */
    public void setPosition(Vector2D position, Vector2D direction) {
        this.direction = strategy.updateDirection(position,direction);
        this.position = position.add(this.direction.multiply(speed));
    }

    /**
     * Changes state of Monster
     * @param state state object that is appropriate
     */
    public void changeState(MonsterState state){
        this.state = state;
    }

    /**
     * Returns current state of Monster
     * @return current state object
     */
    public MonsterState getState(){ return this.state;}

    /**
     * Checks if Monster's health is below zero.
     * @return returns a boolean for whether Monster is dead or not
     */
    public boolean isDead(){
        if(health <= 0) return true;

        return false;
    }

    /**
     * This function is only using {@link #setPosition(Vector2D, Vector2D)} function since monster has no skill but moving
     */
    @Override
    public void step() {
        this.setPosition(position,direction);
    }

    /**
     * This function helps to paint the Monster at given positions.
      * @param g
     */
    @Override
    public void paint(Graphics g) {
        //TODO

        g.setColor(Color.ORANGE);
        g.fillRect(position.getIntX()-Commons.MonsterSize/2, position.getIntY()- Commons.MonsterSize/2, Commons.MonsterSize,Commons.MonsterSize);
        if(oldhealth == health)
        g.setColor(Color.WHITE);
        else{
            g.setColor(Color.red);
            oldhealth -=5;
        }
        g.drawString(((Integer) health).toString(), position.getIntX()-11, position.getIntY()+5);
    }
}
