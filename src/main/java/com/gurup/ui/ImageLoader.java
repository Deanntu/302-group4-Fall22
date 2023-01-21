package com.gurup.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
    private static final String PARENT_PATH = "src/assets/";
    private static final String TABLE = "table.png";
    private static final String BIN = "bin.png";
    private static final String PLAYER = "player.png";
    private static final String SHOOTER_ALIEN = "shooter_alien.png";
    private static final String BLIND_ALIEN = "blind_alien.png";
    private static final String TIME_WASTING_ALIEN = "time_wasting_alien.png";
    private static final String PLASTIC_BOTTLE = "plastic_bottle.png";
    private static final String HEALTH = "health.png";
    private static final String TIME = "time.png";
    private static final String VEST = "vest.png";
    private static final String THROWN_PLASTIC_BOTTLE = "thrown_plastic_bottle.png";
    private static final String BOOK = "book.png";
    private static final String PEN = "pen.png";
    private static final String PRINTER = "printer.png";
    private static final String CLOSED_DOOR = "closed_door.png";
    private static final String OPEN_DOOR = "open_door.png";
    private static final String HELP = "help.png";

    private static final String KEY = "key.png";
    private static final String HINT = "hint.png";



    public static final BufferedImage table_image, bin_image, player_image, shooter_alien_image, blind_alien_image,
            time_wasting_alien_image, plastic_bottle_image, health_image, time_image, vest_image,
            thrown_plastic_bottle_image, book_image, pen_image, printer_image, closed_door_image, open_door_image, help,
            key_image, hint_image;

    static {
        try {
            table_image = ImageIO.read(new File(PARENT_PATH, TABLE));
            bin_image = ImageIO.read(new File(PARENT_PATH, BIN));
            player_image = ImageIO.read(new File(PARENT_PATH, PLAYER));
            shooter_alien_image = ImageIO.read(new File(PARENT_PATH, SHOOTER_ALIEN));
            blind_alien_image = ImageIO.read(new File(PARENT_PATH, BLIND_ALIEN));
            time_wasting_alien_image = ImageIO.read(new File(PARENT_PATH, TIME_WASTING_ALIEN));
            plastic_bottle_image = ImageIO.read(new File(PARENT_PATH, PLASTIC_BOTTLE));
            health_image = ImageIO.read(new File(PARENT_PATH, HEALTH));
            time_image = ImageIO.read(new File(PARENT_PATH, TIME));
            vest_image = ImageIO.read(new File(PARENT_PATH, VEST));
            thrown_plastic_bottle_image = ImageIO.read(new File(PARENT_PATH, THROWN_PLASTIC_BOTTLE));
            book_image = ImageIO.read(new File(PARENT_PATH, BOOK));
            pen_image = ImageIO.read(new File(PARENT_PATH, PEN));
            printer_image = ImageIO.read(new File(PARENT_PATH, PRINTER));
            closed_door_image = ImageIO.read(new File(PARENT_PATH, CLOSED_DOOR));
            open_door_image = ImageIO.read(new File(PARENT_PATH, OPEN_DOOR));
            help = ImageIO.read(new File(PARENT_PATH, HELP));

            key_image = ImageIO.read(new File(PARENT_PATH, KEY));
            hint_image = ImageIO.read(new File(PARENT_PATH, HINT));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ImageLoader() throws IOException {
    }


}
