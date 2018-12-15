package com.x1ma.spawnerupgrade.common.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import com.x1ma.spawnerupgrade.common.blocks.SpawnUpgrader;

public class InitBlocks {
	public static Block spawn_upgraderi;
	public static Block spawn_upgraderii;
	public static Block spawn_upgraderiii;

	public static void init() {
		SpawnUpgrader upgraderi = new SpawnUpgrader("spawn_upgraderi", 2.0F, 2.0F, SoundType.STONE, 1);
		SpawnUpgrader upgraderii = new SpawnUpgrader("spawn_upgraderii", 2.0F, 2.0F, SoundType.STONE, 1);
		SpawnUpgrader upgraderiii = new SpawnUpgrader("spawn_upgraderiii", 2.0F, 2.0F, SoundType.STONE, 1);
		{
			// I
			upgraderi.SpawnCount = 5;
			upgraderi.MaxSpawnDelay = 700;
			upgraderi.MinSpawnDelay = 175;
			upgraderi.MaxNearbyEntities = 6;
			upgraderi.RequiredPlayerRange = 64;
			// II
			upgraderii.SpawnCount = 5;
			upgraderii.MaxSpawnDelay = 650;
			upgraderii.MinSpawnDelay = 150;
			upgraderii.MaxNearbyEntities = 6;
			upgraderii.RequiredPlayerRange = 128;
			// III
			upgraderiii.SpawnCount = 6;
			upgraderiii.MaxSpawnDelay = 475;
			upgraderiii.MinSpawnDelay = 125;
			upgraderiii.MaxNearbyEntities = 7;
			upgraderiii.RequiredPlayerRange = 256;
		}
		{
			spawn_upgraderi = upgraderi;
			spawn_upgraderii = upgraderii;
			spawn_upgraderiii = upgraderiii;
		}
	}

	public static void register(boolean isServer) {
		registerBlock(spawn_upgraderi, isServer);
		registerBlock(spawn_upgraderii, isServer);
		registerBlock(spawn_upgraderiii, isServer);
	}

	public static void registerBlock(Block block, boolean isServer) {
		ForgeRegistries.BLOCKS.register(block);
		block.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(item);
		if (!isServer) {
			ModelLoader.setCustomModelResourceLocation(item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}
	}

}
