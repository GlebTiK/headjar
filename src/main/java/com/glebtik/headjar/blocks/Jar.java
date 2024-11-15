/*
 * AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
 */ 
package com.glebtik.headjar.blocks;

import net.minecraft.block.Block;
//import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
//import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class Jar extends Block {
    private static final AxisAlignedBB hitbox = new AxisAlignedBB(0, 0, 0, 1, 3.0d / 16d, 1);
    private static final AxisAlignedBB walking_hitbox = new AxisAlignedBB(0, 0, 0, 1, 1.0d / 16d, 1);
    public Jar() {
        super(Material.IRON);
        setHardness(5f);
        setSoundType(SoundType.METAL);
        setUnlocalizedName("jar");
        setRegistryName("jar");
    }
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return hitbox;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return walking_hitbox;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        System.out.println("JOE WAS HERE ---");
        System.out.println(pos);
        return false;
    }
/*    private static final AxisAlignedBB hitbox = new AxisAlignedBB(0, 0, 0, 1, 3.0d / 16d, 1);
    private static final AxisAlignedBB walking_hitbox = new AxisAlignedBB(0, 0, 0, 1, 1.0d / 16d, 1);

    public JarModificator() {
        super(Material.IRON);
        setHardness(5f);
        setSoundType(SoundType.METAL);
        setUnlocalizedName("jar_modificator");
        setRegistryName("jar_modificator");
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return hitbox;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return walking_hitbox;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
**/
}