import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A simple Swing dungeon game.
 *
 * @author Joseph Townshend
 * @version Mar 29, 2020
 */

public class Game implements ActionListener {

  private String location = "passage";
  private boolean item = false;

  private final JFrame frame;
  private final JPanel panel;

  private final JLabel message;
  private final JButton northButton;
  private final JButton southButton;
  private final JButton eastButton;
  private final JButton westButton;


  public Game() {

    // UI setup
    frame = new JFrame();
    panel = new JPanel();

    frame.getContentPane().add("Center", panel);
    frame.setSize(650, 300);

    panel.setBackground(Color.BLACK);
//    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); // padding around the button panel
    panel.setLayout(new BorderLayout(2, 1)); // this is what allows you to have N,S,E,W compass points.

    frame.add(panel, BorderLayout.CENTER); // add panel to frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // behaviour for closing frame
    frame.setTitle("Cave Crawler"); // set title of frame
//    frame.pack(); // set frame to same as elements within
    frame.setVisible(true); // set window to be visible and in focus

    // North button
    northButton = new JButton("North");
    northButton.setBackground(Color.DARK_GRAY);
    northButton.setForeground(Color.RED);
    northButton.setFont(new Font("SimSun", Font.PLAIN, 20));
    northButton.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLoweredBevelBorder(),
      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    northButton.addActionListener(this);

    // South button
    southButton = new JButton("South");
    southButton.setBackground(Color.DARK_GRAY);
    southButton.setForeground(Color.RED);
    southButton.setFont(new Font("SimSun", Font.PLAIN, 20));
    southButton.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLoweredBevelBorder(),
      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    southButton.addActionListener(this);

    // East button
    eastButton = new JButton("East");
    eastButton.setBackground(Color.DARK_GRAY);
    eastButton.setForeground(Color.RED);
    eastButton.setFont(new Font("SimSun", Font.PLAIN, 20));
    eastButton.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLoweredBevelBorder(),
      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    eastButton.addActionListener(this);

    // West button
    westButton = new JButton("West");
    westButton.setBackground(Color.DARK_GRAY);
    westButton.setForeground(Color.RED);
    westButton.setFont(new Font("SimSun", Font.PLAIN, 20));
    westButton.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLoweredBevelBorder(),
      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    westButton.addActionListener(this);

    // In order to make the label centred swing constants must be set here.
    message = new JLabel("You find yourself in a long passage", SwingConstants.CENTER);
    message.setForeground(Color.RED);
    message.setFont(new Font("SimSun", Font.PLAIN, 20));

    panel.add(message);
    panel.add(northButton, BorderLayout.NORTH);
    panel.add(southButton, BorderLayout.SOUTH);
    panel.add(eastButton, BorderLayout.EAST);
    panel.add(westButton, BorderLayout.WEST);

  }


  public static void main(String[] args) {
    new Game();
  }


  /**
   * Increments the counter on each button click and displays the current room in
   * which the player occupies.
   *
   * @param event information about the button click
   */
  @Override
  public void actionPerformed(ActionEvent event) {

      // passage
    if (location == "passage" && event.getSource() == northButton) {
      location = "cave";
      message.setText("You walk into a dark cave");
    } else if (location == "passage" && event.getSource() == southButton && item == true) {
      location = "passage";
      message.setText("You walk into sunlight");
    } else if (location == "passage" && event.getSource() == southButton) {
      location = "passage";
      message.setText("You're quest is not yet complete...");
    } else if (location == "passage" && event.getSource() == westButton) {
      message.setText("Nothing but cold dark walls...");
    } else if (location == "passage" && event.getSource() == eastButton) {
      location = "chamber1";
      message.setText("You walk into a small chamber");

      // cave
    } else if (location == "cave" && event.getSource() == northButton) {
      message.setText("You can go no further...");
    } else if (location == "cave" && event.getSource() == southButton) {
      location = "passage";
      message.setText("You find yourself in a long passage");
    } else if (location == "cave" && event.getSource() == westButton) {
      location = "chamber2";
      message.setText("You walk into a dark chamber");

      // chamber1
    } else if (location == "chamber1" && event.getSource() == westButton) {
      message.setText("You find yourself in a long passage");
      location = "passage";
    } else if (location == "chamber1" && event.getSource() == northButton) {
      message.setText("You stumble into a chest! It's empty...");
    } else if (location == "chamber1" && event.getSource() == southButton) {
      message.setText("Nothing but cold dark walls...");
    } else if (location == "chamber1" && event.getSource() == eastButton) {
      message.setText("Nothing but cold dark walls...");

      // chamber2
    } else if (location == "chamber2" && event.getSource() == eastButton) {
      message.setText("You walk into a dark cave");
      location = "cave";
    } else if (location == "chamber2" && event.getSource() == northButton) {
      message.setText("Nothing but cold dark walls...");
    } else if (location == "chamber2" && event.getSource() == southButton && item == false) {
      message.setText("You stumble into a chest! You find a steel ring...");
      item = true;
    } else if (location == "chamber2" && event.getSource() == southButton && item == true) {
      message.setText("You stumble into a chest, it's empty...");
    }else if (location == "chamber2" && event.getSource() == westButton) {
      message.setText("Nothing but cold dark walls...");
    } else {
      return;
    }

  }
}