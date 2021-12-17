import java.awt.*;
import java.util.ArrayList;

/**
 * Tower Decorator abstract class for wrapping Tower class
 */
public abstract class TowerDecorator extends Tower {
    //TODO

    Tower tower;

    /**
     * TowerDecorator constructor that gets a tower object which will be wrapped.
     * @param tower
     */
    public TowerDecorator(Tower tower){
        this.tower =tower;
        this.position = tower.position;
        this.killCount = tower.killCount;


    }
    @Override
    public void paint(Graphics g){
        tower.paint(g);
    }

    @Override
    public void step(){
        tower.step();
    }



    /**
     * Gets the kill count of Tower that is wrapped.
     * @return
     */
    @Override
    public int getKillCount() {
        return super.getKillCount();
    }

}
