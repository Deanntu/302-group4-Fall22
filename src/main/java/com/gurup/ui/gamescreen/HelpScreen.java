package com.gurup.ui.gamescreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.gurup.ui.ImageLoader.*;

public class HelpScreen extends JFrame implements ActionListener {

    private final Container helpScreenContainer = getContentPane();
    private final JButton backButton = new JButton("BACK");
    private boolean showBack = false;

    public HelpScreen() {
        helpScreenContainer.setLayout(null);
        setSizeandAdd();
    }

    private void setSizeandAdd() {

        backButton.setBounds(10, 10, 100, 30);
        helpScreenContainer.add(backButton);
        backButton.addActionListener(this);

        // text field
        JLabel helpLabel = new JLabel(
                "<html>\n" +
                        "\t<head>\n" +
                        "\t\t<title>Codebeautify.org Text to HTML Converter</title>\n" +
                        "\t</head>\n" +
                        "\t<body>\n" +
                        "\t\t<p>\t\t\t\t\tOYUN\n" +
                        "\t\t\t<br/>\n" +
                        "About Oyun\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "OYUN is an easy-to-play game that combines fun and challenge. The game space takes place on the KOÇ university campus where a student is trying to find a sequence of keys in the campus buildings. The game starts when the player enters one of the buildings and starts looking for a key in different rooms. During that journey, aliens may show up and try to catch the player, who should try to escape or distract them. The player is aiming at finding the key before the timeout. To accomplish that, some hints show up here and there. Once the key is found, the building will be marked as complete and the player can choose the next open building, which is basically the next level. Some promotions can be offered, like adding more time. The game is over If the player fails to find the key within the time limit. If he manages to find all the keys, then he wins the game.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "How To Play?\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "The game is played in 2 different stages. In the first stage, you have to place the objects for the game, in short, you have to create the world. Then you start the game. You are trying to find the hidden keys in the game you have created. Below is an understanding of how both modes work.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Building Mode\n" +
                        "\t\t\t<br/>\n" +
                        "In this part of the game, you will have to place a different number of objects in random locations within the building for each stage, that is, for each building. To do this, after clicking Play, you will have to click on the objects on the right in the window that opens and place them in the windows on the left, that is, inside the buildings. By clicking and releasing, you can place that object inside the building and move on to the other object. After all objects and objects are finished your game will be ready to start.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Running Mode\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "In this part of the game, you have to find the keys stored in the rooms you have created. To do this, you need to move your player using the arrow keys. You can move in the direction you want with the arrow keys.\n" +
                        "\t\t\t<br/>\n" +
                        "When you come near the objects, you will need to click on the objects with the mouse to check if there is a key. In this way, you have to visit all the objects in the building until you find the key. When you find the key, the door of the building will open and you will pass to the next building, the next level.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "In-game Objects\n" +
                        "\t\t\t<br/>\n" +
                        "You will also have enemies as you try to find the keys. These are aliens. There are 3 different types of aliens. Blind alien, shooter alien and time wasting alien.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Bliend alien: his type of alien also tries to kill the player. However, in order to kill him / her, the alien must be right next to the player. The thing is that the alien is blind. So it cannot see the player. He randomly walks around. However, this alien is sensitive to the voices. When the player has the plastic bottle power-up, if she/he throws the bottle, he/she can fool the alien. For example, if the player throws the bottle to the opposite direction where he/she will go, then the alien will go in the bottle's direction. The protection vest does not protect the player from the blind alien because it comes closer and chokes the player.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Shooter alien: This type of alien appears in a random location in the building and shoots a bullet every second. If the player is close to the shooter alien less than 40 inches, then he/she will lose a life. The player has three lives at the beginning of the game. He/she can collect some extra lives during the game (see Section 2.2., extra life power-up). Also, if the player wears a protection vest, then he/she can get close to the shooter alien without losing a life.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Time-wasting alien: This type of alien does not kill the player but it changes the location of the key randomly every 5 seconds. This type of alien has some magical abilities. It does not walk around but just resides wherever it appears and the player cannot see how it changes the location of the key.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "You will also have a friend while you try to find the keys. These are powerups. Power-ups help the player escape from the aliens and find the keys easily. Each power-up appears randomly every 12 seconds in the random locations. They disappear if the player does not collect them in 6 seconds. To collect the power-ups, the player needs to right click with the mouse to the power-ups. However, unlike clicking the regular objects, the player is not required to be next to a power-up to collect it. Except the extra time and extra life power-ups, once collected, they can be stored in the player's bag for later use. Extra time power-up automatically adds some extra time to the player's timer.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Extra time power-up: The game starts with a building mode where the player designs inside of the buildings. In each building, there are minimum criteria to fulfill. The time limit in each building is 5 seconds for each object present in the building. For instance, if there are 5 objects in a building where the key can be hidden under, then the time limit for that building is 5x5=25 seconds. When the user collects an extra time power-up extra 5 seconds are added to the player's timer.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Hint: This power-up gives a hint about the location of the key to the player. Once collected, it goes to the player's bag. The player can use it as soon as he/she has it or can save it for the next levels. To use this power up, the player hits the H button on the keyboard. Then, a rectangle of 4x4 is highlighted/bordered where the key is hidden. In other words, the hint shows a small region containing the key. The highlight/border appears for 10 seconds and then disappears.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Protection vest: This power-up protects the player from being shot by the shooter alien. To use this power up, the player clicks the P button on the keyboard. Like hint, the player can use this power-up as soon as he/she has it or can save it for later use. When the player wears it, its protection lasts for 20 seconds.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Plastic bottle: This power up is used to fool the blind alien. To use it, the player clicks the B button and then one of the following buttons A, D, W, or X to decide on the bottle's direction. A: west, D: east, W: north, X: south.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "Extra life: This power-up adds one extra life to the player's lives. Like extra time power-up, the addition of the extra life is automatic.\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t\t<br/>\n" +
                        "\t\t</p>\n" +
                        "\t</body>\n" +
                        "</html>");
        helpScreenContainer.add(helpLabel);
        helpLabel.setBounds(50, 50, 800, 800);

        // png file
        //JLabel pngFile1 = new JLabel(new ImageIcon(help_screen1));
        //pngFile1.setVerticalAlignment(JLabel.CENTER);
        //pngFile1.setBounds(100, 100, 1000, 1000);
        //pngFile1.resize(helpScreenContainer.getWidth(),helpScreenContainer.getHeight());
        //helpScreenContainer.add(pngFile1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            showBack = true;
        }

    }

    public boolean showBackPressed() {
        return showBack;
    }
}