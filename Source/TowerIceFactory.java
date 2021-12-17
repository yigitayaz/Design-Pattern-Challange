public class TowerIceFactory implements ITowerFactory {
    //TODO
    /**
     * This class is dedicated to return a ice tower with given position
     * @param position Vector2D position vector
     * @return returns a ice tower object
     */
    @Override
    public Tower createTower(Vector2D position) {
        Tower tower = new TowerIce(position);
        return tower;
    }
}
