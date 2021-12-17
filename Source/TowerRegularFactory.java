public class TowerRegularFactory implements ITowerFactory {
    /**
     * This class is dedicated to return a regular tower with given position
     * @param position Vector2D position vector
     * @return returns a regular tower object
     */
    //TODO
    @Override
    public Tower createTower(Vector2D position) {
        Tower tower = new TowerRegular(position);
        return tower;
    }
}
