import java.util.ArrayList;

/**
 * abstract Tower class
 * @see TowerRegular
 * @see TowerIce
 * @see TowerPoison
 * @see TowerDecorator
 */
public abstract class Tower extends Entity{
    //TODO
    public ArrayList<Monster> monsterList;
    protected int  range;
    protected int rOF;
    protected int damage;



    protected int cost;
    protected int timer;

    /**
     * Returns kill count of that tower
     * @return kill count integer
     */
    public int getKillCount() {
        return killCount;
    }


    protected int killCount;
    protected Vector2D position;
    public abstract void step();

    /**
     * This function returns closest Monster to the corresponding tower that asks for it.
     * @param monsterList the current Monster List that is stored in game class.
     * @return  closest Monster to the tower
     */
    public Monster getClosestMonster(ArrayList<Monster> monsterList){
        double distance;
        double minDistance = 50000; // Random big number
        Monster closest = null;
        for(Monster m: this.monsterList){
            distance = this.position.distance(m.getPosition());
            if(range >= distance && distance < minDistance){
                minDistance = distance;
                closest = m;
            }
        }
        return closest; // This may be null
    }

    /**
     * This function passes  to Tower reference object of currently alive Monster ArrayList
     * @param monsterList currentMonster ArrayList
     */
    public void setMonsterList(ArrayList<Monster> monsterList){
        this.monsterList = monsterList;
    }
    public int getCost() {
        return cost;
    }
    
}
