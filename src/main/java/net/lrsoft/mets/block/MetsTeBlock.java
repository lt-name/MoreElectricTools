package net.lrsoft.mets.block;

import java.util.Set;

import ic2.core.block.*;
import ic2.core.ref.TeBlock;
import ic2.core.ref.TeBlock.*;
import ic2.core.ref.TeBlock.DefaultDrop;
import ic2.core.ref.TeBlock.HarvestTool;
import ic2.core.ref.IC2Material;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import ic2.core.util.Util;
import net.lrsoft.mets.MoreElectricTools;
import net.lrsoft.mets.block.tileentity.*;

public enum MetsTeBlock implements ITeBlock {
	
	lesu((Class)TileEntityLESU.class, 0, false, Util.allFacings, true, HarvestTool.Wrench, DefaultDrop.Self, 2.0F, 10.0F, EnumRarity.COMMON, IC2Material.MACHINE, false);

	public static final ResourceLocation loc = new ResourceLocation(MoreElectricTools.MODID, "te");
	private final Class<? extends TileEntityBlock> teClass;
	private final int itemMeta;
	private final boolean hasActive;
	private final Set<EnumFacing> supportedFacings;
	private final boolean allowWrenchRotating;
	private final HarvestTool harvestTool;
	private final DefaultDrop defaultDrop;
	private final float hardness;

	MetsTeBlock(Class<? extends TileEntityBlock> teClass, int itemMeta, boolean hasActive,
			Set<EnumFacing> supportedFacings, boolean allowWrenchRotating, HarvestTool harvestTool,
			DefaultDrop defaultDrop, float hardness, float explosionResistance, EnumRarity rarity, Material material,
			boolean transparent) {
		this.teClass = teClass;
		this.itemMeta = itemMeta;
		this.hasActive = hasActive;
		this.supportedFacings = supportedFacings;
		this.allowWrenchRotating = allowWrenchRotating;
		this.harvestTool = harvestTool;
		this.defaultDrop = defaultDrop;
		this.hardness = hardness;
		this.explosionResistance = explosionResistance;
		this.rarity = rarity;
		this.material = material;
		this.transparent = transparent;
	}

	private final float explosionResistance;private final EnumRarity rarity;private final Material material;private final boolean transparent;private TileEntityBlock dummyTe;private ITePlaceHandler placeHandler;

	static {
		for (MetsTeBlock block : values()) {
			TileEntity.register(loc.getResourceDomain() + ':' + block.getName(), block.getTeClass());
		}
	}

	public static void buildDummies() {
 		for (MetsTeBlock block : values()) {
 			if (block.teClass != null) {
 				try {
 					block.dummyTe = block.teClass.newInstance();
 				} catch (Exception e) {
 					e.printStackTrace();
				}
			}
		}
 	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return this.itemMeta;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name();
	}

	@Override
	public boolean allowWrenchRotating() {
		// TODO Auto-generated method stub
		return this.allowWrenchRotating;
	}

	@Override
	public DefaultDrop getDefaultDrop() {
		// TODO Auto-generated method stub
		return this.defaultDrop;
	}

	@Override
	public TileEntityBlock getDummyTe() {
		// TODO Auto-generated method stub
		return this.dummyTe;
	}

	@Override
	public float getExplosionResistance() {
		// TODO Auto-generated method stub
		return this.explosionResistance;
	}

	@Override
	public float getHardness() {
		// TODO Auto-generated method stub
		return this.hardness;
	}

	@Override
	public HarvestTool getHarvestTool() {
		// TODO Auto-generated method stub
		return this.harvestTool;
	}

	@Override
	public ResourceLocation getIdentifier() {
		// TODO Auto-generated method stub
		return loc;
	}

	@Override
	public EnumRarity getRarity() {
		// TODO Auto-generated method stub
		return this.rarity;
	}

	@Override
	public Set<EnumFacing> getSupportedFacings() {
		// TODO Auto-generated method stub
		return this.supportedFacings;
	}

	@Override
	public Class<? extends TileEntityBlock> getTeClass() {
		// TODO Auto-generated method stub
		return this.teClass;
	}

	@Override
	public boolean hasActive() {
		// TODO Auto-generated method stub
		return this.hasActive;
	}

	@Override
	public boolean hasItem() {
		// TODO Auto-generated method stub
		return (this.teClass != null && this.itemMeta != -1);
	}

}