package com.x1ma.spawnerupgrade.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.util.EnumParticleTypes;

import net.minecraft.world.World;

import net.minecraft.world.Explosion;

import net.minecraft.item.ItemStack;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;

import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class SpawnUpgrader extends BlockBasic
{

	public short SpawnCount = 0;
	public short MaxSpawnDelay = 0;
	public short MinSpawnDelay = 0;
	public short MaxNearbyEntities = 0;
	public short RequiredPlayerRange = 0;

	public BlockPos pos = new BlockPos(0, 0, 0);

	public SpawnUpgrader(String name, float resistance, float hardness, SoundType soundType, int harvestLevel) {
		super(name, resistance, hardness, soundType);
		this.setHarvestLevel("pickaxe", harvestLevel);
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

  public boolean isMobSpawnerAt(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (!(block instanceof BlockAir)) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TileEntityMobSpawner) return true;
		}
		return false;
  }

  public boolean updateSpawnerState(World worldIn, BlockPos pos) {
  	if (this.isMobSpawnerAt(worldIn, pos.down())) {
			TileEntity te = worldIn.getTileEntity(pos.down());
			this.setSpawnerState(worldIn, te, "SpawnCount", this.SpawnCount);
			this.setSpawnerState(worldIn, te, "MaxSpawnDelay", this.MaxSpawnDelay);
			this.setSpawnerState(worldIn, te, "MinSpawnDelay", this.MinSpawnDelay);
			this.setSpawnerState(worldIn, te, "MaxNearbyEntities", this.MaxNearbyEntities);
			this.setSpawnerState(worldIn, te, "RequiredPlayerRange", this.RequiredPlayerRange);
			
			return true;
		}
  	return false;
  }

	public void setSpawnerState(World worldIn, TileEntity te, String property, short value) {
		TileEntityMobSpawner tems = (TileEntityMobSpawner) te;
    NBTTagCompound sNBT = new NBTTagCompound();
    tems.writeToNBT(sNBT);
    sNBT.setShort(property, value);
    tems.readFromNBT(sNBT);
		//if (last != value) Minecraft.getMinecraft().player.sendChatMessage(value);
	}

	public void resetSpawnerState(World worldIn, BlockPos pos) {
		if (this.isMobSpawnerAt(worldIn, pos.down())) {
			TileEntity te = worldIn.getTileEntity(pos.down());
			this.setSpawnerState(worldIn, te, "SpawnCount", (short) 4);
			this.setSpawnerState(worldIn, te, "MaxSpawnDelay", (short) 200);
			this.setSpawnerState(worldIn, te, "MinSpawnDelay", (short) 800);
			this.setSpawnerState(worldIn, te, "MaxNearbyEntities", (short) 6);
			this.setSpawnerState(worldIn, te, "RequiredPlayerRange", (short) 16);
		}
	}

  /*@Override
	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.DESTROY;
	}*/

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.resetSpawnerState(worldIn, this.pos);
		this.pos = pos;
		this.updateSpawnerState(worldIn, pos);
	}

	@Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		this.pos = pos;
		this.updateSpawnerState(worldIn, pos);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
  }

  @Override
  public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		this.resetSpawnerState(worldIn, pos);
  }

  @Override
  public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
  	this.resetSpawnerState(worldIn, pos);
  }

  @Override
  public void onBlockExploded(World worldIn, BlockPos pos, Explosion explosion) {
  	this.resetSpawnerState(worldIn, pos);
  }

  @Override
  public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
  	this.resetSpawnerState(worldIn, pos);
  }

  @Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}

  @Override
  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.TRANSLUCENT;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
  	if (!this.isMobSpawnerAt(worldIn, pos.down())) return;
    double x = (double) pos.getX() + 0.5;
    double y = (double) pos.getY() + (1.0 / 16.0) * 8.0;
    double z = (double) pos.getZ() + 0.5;
    for (int x1 = -1; x1 <= 1; x1 += 2) {
      for (int z1 = -1; z1 <= 1; z1 += 2) {
        double offset1 = x1 * (7.0 / 16.0);
        double offset2 = z1 * (3.0 / 16.0);
        worldIn.spawnParticle(EnumParticleTypes.SPELL_WITCH, x + offset1, y, z + offset2, 0.0, 0.0, 0.0, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.SPELL_WITCH, x + offset2, y, z + offset1, 0.0, 0.0, 0.0, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.SPELL_WITCH, x + offset1, y - 0.5, z + offset2, 0.0, 0.0, 0.0, new int[0]);
        worldIn.spawnParticle(EnumParticleTypes.SPELL_WITCH, x + offset2, y - 0.5, z + offset1, 0.0, 0.0, 0.0, new int[0]);
      }
    }
  }

}
