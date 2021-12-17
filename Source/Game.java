import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game {
    private static final Game _inst = new Game();
    public static Game getInstance() {
        return _inst;
    }
    //TODO
    private final ArrayList<Monster> currentMonsters;
    private final ArrayList<Tower> currentTowers;
    private final ITowerFactory regularTowerFactory;
    private final ITowerFactory iceTowerFactory ;
    private final ITowerFactory poisonTowerFactory ;
    private int gold;
    private int killCount;
    private int lives;
    private int wave;
    private int stepCount;
    private static Timer timer;

    public ArrayList<Monster> getCurrentMonsters() {
        return currentMonsters;
    }

    public void addToCurrentMonsters(Monster monster) {
        currentMonsters.add(monster);
    }

    public ArrayList<Tower> getCurrentTowers() {
        return currentTowers;
    }

    public void addToCurrentTowers(Tower tower) {
        currentTowers.add(tower);
    }


    public Game() {
        this.gold = 25;
        this.killCount = 0;
        this.wave = 0;
        this.lives = 3;
        this.stepCount = 0;
        regularTowerFactory = new TowerRegularFactory();
        iceTowerFactory = new TowerIceFactory();
        poisonTowerFactory = new TowerPoisonFactory();
        currentMonsters = new ArrayList<Monster>();
        currentTowers = new ArrayList<Tower>();


    }
    private void cornerStrategy(int chance){
        Random random = new Random();
        for(Monster m: currentMonsters) {
            /*This if checks corners passes
            (maybe a small bug when a monster passes through this coordinates while inflicted by iceTower
            can cause monster change multiple states at each corner but really small percentage) */
            if((m.getPosition().getIntX()>=100 && m.getPosition().getIntX()<101 && m.getPosition().getIntY()<=80) ||
                (m.getPosition().getIntY()>=100 && m.getPosition().getIntY()<101 && m.getPosition().getIntX()>=320) ||
                (m.getPosition().getIntX()<=300 && m.getPosition().getIntX()>299 && m.getPosition().getIntY()>=320)) {
                    int percentage = random.nextInt(100);
                    if (percentage < chance) {
                        int coinToss = random.nextInt(2);
                        if (coinToss == 0) m.setStrategy(new MonsterCircularStrategy());
                        else m.setStrategy(new MonsterZigZagStrategy());
                }
            }
        }
    }

    /**
     * This function gets a coordinate and a char from mouse and keyboard inputs in runtime
     * and creates a tower according to it.
     * @param coord Vector2D coordinate for tower location
     * @param c char parameter for tower type to create
     */
    public void addTower(Vector2D coord, char c){
        boolean towerExists = false;
        Vector2D position = closestCenter(coord);
        for(Tower t: currentTowers){
            if(t.position.equals(position)){
                towerExists = true;
                break;
            }
        }
        if(!towerExists) {

            Tower tower = null;
            if (c == 'r' && gold >= 20)
                tower = regularTowerFactory.createTower(position);

            else if (c == 'i' && gold >=15) tower = iceTowerFactory.createTower(position);
            else if (c == 'p' && gold >=25) tower = poisonTowerFactory.createTower(position);
            if(tower != null) {


                addToCurrentTowers(tower);
                tower.setMonsterList(currentMonsters);
                this.gold -= tower.getCost();
                Display.getInstance().getInfoPanel().addToCurrentGoldLabel(-tower.getCost());
            }
        }
    }

    /**
     * This function helps addTower function to generate a proper coordinate for tower construction or creation.
     * Returned coordinate is a center coordinate of a Tower Zone which is closest to former coordinate.
     * @param coord mouse input coordinate from addTower function
     * @return  Vector2D centered at one of the Tower Zones
     */
    private Vector2D closestCenter(Vector2D coord){
        double minDistance = 10000;
        Vector2D vector,minVector = null;
        int divideLength = Commons.TowerZoneDivideLength;
        int startX = Commons.TowerZoneX+divideLength/2;
        int startY = Commons.TowerZoneY+divideLength/2;
        for(int i = 0 ; i < 4; i++){
            for(int j = 0; j < 4 ;j++ ){
                vector = new Vector2D(startX+i*divideLength,startY+j*divideLength);
                double distance = coord.distance(vector);
                if(distance < minDistance){
                    minDistance = distance;
                    minVector = vector;
                }
            }

        }
        return minVector;
    }

    /**
     * Paint function to paint Monsters and Towers
     * @param g
     */
    public void paint(Graphics g) {
        //TODO
        Tower tower;
        for(Monster m: currentMonsters){
            m.paint(g);
            if(m.getState() != null) m.getState().paint(g);
        }
        for(Tower t:currentTowers){

            tower = new TowerDecoratorGrade1(new TowerDecoratorGrade2(new TowerDecoratorGrade3(t)));
            tower.paint(g);

        }

    }

    /**
     *This function checks conditions and according to them adds or removes Monsters, updates state of them and changes InfoPanel values. Possiblity calculations are also done in here.
     * Timer stops if player have no remaining lives.
     */
    public void step() {
        if(lives>0) {
            if (currentMonsters.size() == 0) {
                wave++;
                Random random = new Random();
                for (int i = 0; i < wave; i++) {
                    int randomX = random.nextInt(61);
                    int randomY = random.nextInt(61);
                    int coinToss = random.nextInt(2);
                    randomX += 20;
                    randomY += 320;
                    Monster m;
                    if (coinToss == 0)
                        m = new Monster(new MonsterCircularStrategy(), new Vector2D(randomX, randomY), wave);
                    else m = new Monster(new MonsterZigZagStrategy(), new Vector2D(randomX, randomY), wave);
                    currentMonsters.add(m);
                }
                for (Monster m : currentMonsters) {
                    m.step();
                    if (m.getState() != null) m.getState().update();
                }
                for (Tower t : currentTowers) {
                    t.step();
                }
                stepCount = 0;
                Display.getInstance().getInfoPanel().addToCurrentWaveLabel(1);
            } else if (stepCount > 800) {
                boolean monstersSurvived = false;
                double halfDiagonal = ((Commons.StartHeight) / 2) * (1.42);
                Vector2D centerOfStartZone = new Vector2D(50, 350);
                for (Monster m : currentMonsters) {
                    if (m.getPosition().distance(centerOfStartZone) <= halfDiagonal) {
                        --lives;
                        stepCount = 0;
                        Display.getInstance().getInfoPanel().addToCurrentLivesLabel(-1);
                        monstersSurvived = true;
                        currentMonsters.clear();
                        break;
                    }

                }
                if (!monstersSurvived) {
                    for (Monster m : currentMonsters) {
                        m.step();
                        if (m.getState() != null) m.getState().update();
                    }
                    for (Tower t : currentTowers) {
                        t.step();
                    }
                    stepCount++;
                }
            } else {
                for (Monster m : currentMonsters) {
                    m.step();
                    if (m.getState() != null) m.getState().update();
                }
                for (Tower t : currentTowers) {
                    t.step();
                }
                stepCount++;
            }
            //Check if any monster is dead after towerStep
            Iterator<Monster> it = currentMonsters.iterator();
            while (it.hasNext()) {
                Monster m = it.next();
                if (m.isDead()) {
                    it.remove();
                    killCount++;
                    gold += m.getReward();
                    Display.getInstance().getInfoPanel().addToCurrentKillsLabel(1);
                    Display.getInstance().getInfoPanel().addToCurrentGoldLabel(m.getReward());

                }
            }
            // CornerStrategyChange implementation

            cornerStrategy(10); // This takes percentage for the chance of strategy change


            Display.getInstance().getGamePanel().repaint();
        }
        else timer.stop();
//        else{
//            JFrame endFrame = new JFrame("Game Over!");
//            JLabel endLabel = new JLabel("To play again please reopen the game");
//            endFrame.setBackground(Color.WHITE);
//            JButton exitButton = new JButton("Exit");
//            endFrame.add(exitButton);
//            endFrame.add(endLabel);
//            exitButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    System.exit(1);
//                }
//            });
//            endFrame.pack();
//            endFrame.setVisible(true);
//        }
    }

    //You can make changes
    public static void startGame() {
        Display.getInstance().setVisible(true);
        //Optional additions


         timer = new Timer(5, actionEvent -> {
            Game.getInstance().step();
            //Optional additions
            
        });
        timer.start();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }
}
