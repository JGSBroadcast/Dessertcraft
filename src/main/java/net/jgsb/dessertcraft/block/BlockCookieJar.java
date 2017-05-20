package net.jgsb.dessertcraft.block;

import net.jgsb.dessertcraft.tileentity.TileEntityCookieJar;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Jacob on 3/7/2017.
 */
public class BlockCookieJar extends Block implements ITileEntityProvider {

    private static AxisAlignedBB BOUNDING_BOX;
    private static AxisAlignedBB COLLISION_BOX;

    public BlockCookieJar() {
        super(Material.GLASS);
        setCreativeTab(CreativeTabs.DECORATIONS);
        BOUNDING_BOX = new AxisAlignedBB(0.0625F * 3.0F, 0.0F, 0.0625F * 3.0F, 0.0625F * 13.0F, 0.0625F * 13.0F, 0.0625F * 13.0F);
        COLLISION_BOX = new AxisAlignedBB(0.0625F * 3.0F, 0.0F, 0.0625F * 3.0F, 0.0625F * 13.0F, 0.0625F * 13.0F, 0.0625F * 13.0F);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return COLLISION_BOX;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            ItemStack heldItem = playerIn.getHeldItem(hand);
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof TileEntityCookieJar) {
                TileEntityCookieJar cookieJar = (TileEntityCookieJar) tileEntity;
                if(heldItem.getItem() != Items.AIR) {
                    if(heldItem.getItem() == Items.COOKIE) {
                        if(cookieJar.addCookie()) {
                            heldItem.setCount(heldItem.getCount() - 1);
                            return true;
                        }
                    }
                }

                if(playerIn.isSneaking()) {
                    cookieJar.removeCookie();
                }
            }
        }

        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityCookieJar te = (TileEntityCookieJar) worldIn.getTileEntity(pos);
        if(te.getCookieCount() > 0) {
            worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.COOKIE, te.getCookieCount())));
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCookieJar();
    }
}
