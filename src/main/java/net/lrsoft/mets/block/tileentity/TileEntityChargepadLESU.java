package net.lrsoft.mets.block.tileentity;

import ic2.core.block.wiring.TileEntityChargepadBlock;
import ic2.core.profile.NotClassic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TileEntityChargepadLESU extends TileEntityChargepadBlock implements IMets{
	public final static int maxStorageEnergy = 1000000; 
	public TileEntityChargepadLESU() 
	{
		super(3, 512, maxStorageEnergy);
	}
	
	@Override
	protected void getItems(EntityPlayer player) {
		if (player != null) {
			for (ItemStack current : player.inventory.armorInventory) {
				if (current == null)
					continue;
				chargeItem(current, 512);
			}

			for (ItemStack current : player.inventory.mainInventory) {
				if (current == null)
					continue;
				chargeItem(current, 512);
			}
		}
	}
}
