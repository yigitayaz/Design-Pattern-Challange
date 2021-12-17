import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private JLabel currentGoldLabel;
    private JLabel currentLivesLabel;
    private JLabel currentKillsLabel;
    private JLabel currentWaveLabel;
    public InfoPanel() {


        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(300, 400));
        this.setMinimumSize(new Dimension(300, 400));
        this.setSize(300, 400);
        this.setLayout(new GridLayout(4, 2));

        currentGoldLabel = new JLabel("25"); //TODO Update Gold
        currentLivesLabel = new JLabel("3"); //TODO Update Lives when necessary
        currentKillsLabel = new JLabel("0"); //TODO Update Kills
        currentWaveLabel = new JLabel("0"); //TODO Update Wave

        JLabel temp = new JLabel("Gold:");
        temp.setHorizontalAlignment(SwingConstants.RIGHT);
        temp.setFont(new Font("Serif", Font.BOLD, 20));
        temp.setForeground(Color.ORANGE);
        this.add(temp);this.add(currentGoldLabel);
        temp = new JLabel("Lives:");
        temp.setHorizontalAlignment(SwingConstants.RIGHT);
        temp.setFont(new Font("Serif", Font.BOLD, 20));
        temp.setForeground(Color.GREEN);
        this.add(temp);this.add(currentLivesLabel);
        temp = new JLabel("Kills:");
        temp.setHorizontalAlignment(SwingConstants.RIGHT);
        temp.setFont(new Font("Serif", Font.BOLD, 20));
        temp.setForeground(Color.RED);
        this.add(temp);this.add(currentKillsLabel);
        temp = new JLabel("Wave:");
        temp.setHorizontalAlignment(SwingConstants.RIGHT);
        temp.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(temp);this.add(currentWaveLabel);


        currentGoldLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentLivesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentKillsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentWaveLabel.setHorizontalAlignment(SwingConstants.LEFT);

        currentGoldLabel.setFont(new Font("Serif", Font.BOLD, 20));
        currentLivesLabel.setFont(new Font("Serif", Font.BOLD, 20));
        currentKillsLabel.setFont(new Font("Serif", Font.BOLD, 20));
        currentWaveLabel.setFont(new Font("Serif", Font.BOLD, 20));

    }

    //TODO

    /**
     * This function adds the amount that is given to current gold amount which is displayed. Give a negative amount to substract.
     * @param amount
     */
    public void addToCurrentGoldLabel(int amount){
        String text = currentGoldLabel.getText();
        int textAmount = Integer.parseInt(text);
         textAmount += amount;
         text = Integer.toString(textAmount);
         currentGoldLabel.setText(text);
    }
    /**
     * This function adds the amount that is given to current lives amount which is displayed. Give a negative amount to substract.
     * @param amount
     */
    public void addToCurrentLivesLabel(int amount){
        String text = currentLivesLabel.getText();
        int textAmount = Integer.parseInt(text);
        textAmount += amount;
        text = Integer.toString(textAmount);
        currentLivesLabel.setText(text);
    }
    /**
     * This function adds the amount that is given to current kill count which is displayed.
     * @param amount
     */
    public void addToCurrentKillsLabel(int amount){
        String text = currentKillsLabel.getText();
        int textAmount = Integer.parseInt(text);
        textAmount += amount;
        text = Integer.toString(textAmount);
        currentKillsLabel.setText(text);
    }
    /**
     * This function adds the amount that is given to current wave count which is displayed.
     * @param amount
     */
    public void addToCurrentWaveLabel(int amount){
        String text = currentWaveLabel.getText();
        int textAmount = Integer.parseInt(text);
        textAmount += amount;
        text = Integer.toString(textAmount);
        currentWaveLabel.setText(text);
    }
}
