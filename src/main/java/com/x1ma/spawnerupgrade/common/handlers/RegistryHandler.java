package com.x1ma.spawnerupgrade.common.handlers;

import com.x1ma.spawnerupgrade.client.handlers.RecipeHandler;
import com.x1ma.spawnerupgrade.common.init.InitBlocks;

public class RegistryHandler {
	public static void Client() {
		InitBlocks.init();
		InitBlocks.register(false);
		RecipeHandler.registerCrafting();
	}

	public static void Server() {
		InitBlocks.init();
		InitBlocks.register(true);
		RecipeHandler.registerCrafting();
	}

	public static void Common(){

	}

}
