package net.glebtik.hiaj;

import net.fabricmc.api.ModInitializer;
import net.glebtik.hiaj.item.ModItems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HeadInAJar implements ModInitializer { 
	public static final Logger LOGGER = LogManager.getLogger("hiaj");
	public static final String MODID  = "hiaj"; 

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		LOGGER.debug("Head In A Jar initialized.");
	}
}
