package com.x1ma.spawnerupgrade.client.handlers;

import com.x1ma.spawnerupgrade.common.init.InitBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {
	public static void registerCrafting() {
		GameRegistry.addShapedRecipe(
				new ResourceLocation("tm:spawn_upgraderi"),
				new ResourceLocation(""),
				new ItemStack(InitBlocks.spawn_upgraderi),
				new Object[] {
						"ODO",
						"DED",
						"ODO",
						'D',
						Items.DIAMOND,
						'O',
						Blocks.OBSIDIAN,
						'E',
						Items.ENDER_EYE
			 }
		);
		GameRegistry.addShapedRecipe(
				new ResourceLocation("tm:spawn_upgraderii"),
				new ResourceLocation(""),
				new ItemStack(InitBlocks.spawn_upgraderii),
				new Object[] {
						"DDD",
						"DED",
						"DDD",
						'D',
						Items.DIAMOND,
						'E',
						InitBlocks.spawn_upgraderi
			 }
		);
		GameRegistry.addShapedRecipe(
				new ResourceLocation("tm:spawn_upgraderiii"),
				new ResourceLocation(""),
				new ItemStack(InitBlocks.spawn_upgraderiii),
				new Object[] {
						"DDD",
						"DED",
						"DDD",
						'D',
						Items.DIAMOND,
						'E',
						InitBlocks.spawn_upgraderii
			 }
		);
	}
	
	public static void registerSmelting() {
		//GameRegistry.addSmelting(InitBlocks.spawn_upgrader, new ItemStack(InitItems.tin_ingot), 1);
	}
}
