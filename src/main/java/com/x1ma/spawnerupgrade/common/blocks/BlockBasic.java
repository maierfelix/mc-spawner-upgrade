package com.x1ma.spawnerupgrade.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block
{
	public BlockBasic(String name, float resistance, float hardness, SoundType soundType) 
	{
		super(Material.IRON);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setResistance(2.5f);
		this.setHardness(2.0f);
		this.setSoundType(soundType);
	}

}
