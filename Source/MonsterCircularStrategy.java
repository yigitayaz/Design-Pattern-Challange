/**
 * This class is dedicated to circular moving algorithm for Monster
 * @see #updateDirection
 */
public class MonsterCircularStrategy implements IMonsterStrategy {
    //TODO

    /**
     * This function gets position and direction of Monster and updates them accordingly. It is also prone to strategy changes
     * @param position position of Monster
     * @param direction old direction of Monster
     * @return new direction for Monster
     */
    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {
        Vector2D result = direction;
        Vector2D up = new Vector2D(0,-1);
        Vector2D down = new Vector2D(0,1);
        Vector2D right = new Vector2D(1,0);
        Vector2D left = new Vector2D(-1,0);

        if(direction.equals(up) && position.getIntY() <= 50) result = right;
        else if(direction.equals(right) && position.getIntX() >= 350) result = down;
        else if(direction.equals(down) && position.getIntY() >= 350) result = left;
        else if(!direction.equals(up) && !direction.equals(down) && !direction.equals(left)){
            if(position.getIntX()>100 && position.getIntY() <=80){
                result = right;
            }
            else if(position.getIntX()>=320 && position.getIntY()>100){
                result = down;
            }
            else if(position.getIntX()<320 && position.getIntY()>=320 ){
                result = left;
            }
        }
        return result;


    }
}
