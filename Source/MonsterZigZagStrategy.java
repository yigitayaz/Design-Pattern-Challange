/** This class is dedicated to zigzag moving algorithm for Monster
 * @see #updateDirection
 */
public class MonsterZigZagStrategy implements IMonsterStrategy {
    //TODO
    /**
     * This function gets position and direction of Monster and updates them accordingly. It is also prone to strategy changes
     * @param position position of Monster
     * @param direction old direction of Monster
     * @return new direction for Monster
     */
    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {
        double posX =position.getX();
        double posY = position.getY();
        Vector2D upleft = new Vector2D(-1,-1);
        Vector2D upright = new Vector2D(1,-1);
        Vector2D downleft = new Vector2D(-1,1);
        Vector2D downRight = new Vector2D(1,1);
        upleft.normalize();
        upright.normalize();
        downleft.normalize();
        downRight.normalize();
        if(direction.equals(new Vector2D(0,-1))){

            return upright;
        }
        else if(direction.equals(new Vector2D(1,0))) return downRight;
        else if(direction.equals(new Vector2D(0,1))) return downleft;
        else if(direction.equals(new Vector2D(-1,0))) return upleft  ;
        else if(posX>80 && posX <=81 && posY >80){
            return upleft;
        }
        else if(posX<=20 ){
            return upright;
        }
        else if(posY <=20){
            return downRight;
        }
        else if(posY>80 && posY <=81 && posX>80 && posX <= 320  ){
            return upright;
        }
        else if(posX >=380 ){
            return downleft;
        }
        else if(posX<320 && posX>=319  && posY>80 && posY <320  ){
            return downRight;
        }
        else if(posY>380){
            return upleft;
        }
        else if(posY<320 && posY >=319 && posX< 320 && posX >80 ) {
            return downleft;
        }
        else
            return direction;
    }
}
