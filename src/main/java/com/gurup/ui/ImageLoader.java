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
	private static final String PLASTIC_BOTTLE = "plastic_bottle.png";
	private static final String HEALTH = "health.png";
	private static final String TIME = "time.png";
	private static final String VEST = "vest.png";
	private static final String THROWN_PLASTIC_BOTTLE = "thrown_plastic_bottle.png";
	public static final BufferedImage table_image, bin_image, player_image, shooter_alien_image, blind_alien_image,
			plastic_bottle_image, health_image, time_image, vest_image, thrown_plastic_bottle_image;

	static {
		try {
			table_image = ImageIO.read(new File(PARENT_PATH, TABLE));
			bin_image = ImageIO.read(new File(PARENT_PATH, BIN));
			player_image = ImageIO.read(new File(PARENT_PATH, PLAYER));
			shooter_alien_image = ImageIO.read(new File(PARENT_PATH, SHOOTER_ALIEN));
			blind_alien_image = ImageIO.read(new File(PARENT_PATH, BLIND_ALIEN));
			plastic_bottle_image = ImageIO.read(new File(PARENT_PATH, PLASTIC_BOTTLE));
			health_image = ImageIO.read(new File(PARENT_PATH, HEALTH));
			time_image = ImageIO.read(new File(PARENT_PATH, TIME));
			vest_image = ImageIO.read(new File(PARENT_PATH, VEST));
			thrown_plastic_bottle_image = ImageIO.read(new File(PARENT_PATH, THROWN_PLASTIC_BOTTLE));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private ImageLoader() throws IOException {
	}

}
