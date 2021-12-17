public class TowerPoisonFactory implements ITowerFactory {
    //TODO
    @Override
    public Tower createTower(Vector2D position) {
        Tower tower = new TowerPoison(position);
        return tower;
    }
}
