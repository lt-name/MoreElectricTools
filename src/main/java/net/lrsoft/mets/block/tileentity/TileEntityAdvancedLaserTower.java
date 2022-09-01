package net.lrsoft.mets.block.tileentity;

import net.lrsoft.mets.block.tileentity.OilRig.TileEntityGUIMachine;
import net.lrsoft.mets.entity.EntityGunBullet;
import net.lrsoft.mets.manager.ConfigManager;
import net.lrsoft.mets.manager.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

import java.util.LinkedList;
import java.util.List;

public class TileEntityAdvancedLaserTower extends TileEntityGUIMachine {
	private static double attackConsume = ConfigManager.AdvancedLaserTowerCost;
	private Vec3d shootOffset = new Vec3d(0.5d, 1.0d, 0.5d);
	private static int maxEnergy = 500000, tier = 3;
	private static int maxLockTarget = 5;
	
	private int scanRange = 30;
	
	private int updateInterval = 25, currentInterval = 0;

	private LinkedList<EntityGunBullet> bulletQueue = new LinkedList<>();

	public TileEntityAdvancedLaserTower() {
		super(maxEnergy, tier);
	}
	
	@Override
	protected void updateEntityServer() {
		super.updateEntityServer();
		updateTileEntity();
	}

	private void updateTileEntity() {
		boolean shouldActive = false;
		if (this.energy.canUseEnergy(attackConsume)) {
			shouldActive = true;
			
			currentInterval++;
			if(currentInterval >= updateInterval) {
				//限制每个防御塔可生成的子弹实体数量
				if (this.bulletQueue.size() > maxLockTarget * 10) {
					for (int i = 0; i < maxLockTarget; i++) {
						EntityGunBullet bullet = this.bulletQueue.poll();
						if (bullet == null) {
							break;
						}
						if (!bullet.isDead) {
							bullet.setDead();
						}
					}
				}
				Vec3d currentPos = new Vec3d(this.pos.getX(), pos.getY(), pos.getZ());
				currentPos = currentPos.add(shootOffset);

				AxisAlignedBB bb = new AxisAlignedBB(
						currentPos.x - scanRange, currentPos.y - scanRange, currentPos.z - scanRange, 
						currentPos.x + scanRange, currentPos.y + scanRange, currentPos.z + scanRange);

				List<Entity> list = this.world.getEntitiesInAABBexcluding(null, bb, TileEntiyLaserTower.ALIVE_MOB_SELECTOR);
				if (!list.isEmpty() && this.energy.useEnergy(attackConsume)) {
					int currentSize = list.size();
					int maxSize = Math.min(currentSize, maxLockTarget);
					for(int index = 0; index < maxSize; index++) {
						Entity mob = list.get(index);

						double yOffset = mob.getEyeHeight();
						Vec3d mobPos = new Vec3d(mob.posX, mob.posY + yOffset, mob.posZ);
						mobPos = mobPos.subtract(currentPos);
						
						double vecLength = mobPos.lengthVector();
						double tVecLength = 1.0D - vecLength;
						tVecLength = tVecLength * tVecLength;
						
						Vec3d motion = new Vec3d(mobPos.x / vecLength * tVecLength, mobPos.y / vecLength * tVecLength, mobPos.z / vecLength * tVecLength);
						
						EntityGunBullet bullet = new EntityGunBullet(world, currentPos, 40f, 420, true);
						bullet.shoot(motion.x, motion.y, motion.z, 3f, 0.0f);
						
						world.spawnEntity(bullet);

						this.bulletQueue.add(bullet);
					
					}
					world.playSound((EntityPlayer)null, currentPos.x , currentPos.y, currentPos.z, 
							SoundManager.laser_bullet_shoot, SoundCategory.AMBIENT, 0.6f, 0.8F);
				}
				
				currentInterval = 0;
			}
		}else {
			currentInterval = 0;
		}
		setActive(shouldActive);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("shootInterval", currentInterval);
		return tag;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		currentInterval = tag.getInteger("shootInterval");
	}

}
