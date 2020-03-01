package net.lrsoft.mets.manager;

import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.lrsoft.mets.MoreElectricTools;
import net.lrsoft.mets.armor.DivingMask;
import net.lrsoft.mets.item.AdvancedIridiumSword;
import net.lrsoft.mets.item.ElectricFishingRod;
import net.lrsoft.mets.item.ElectricNutritionSupply;
import net.lrsoft.mets.item.ElectricShield;
import net.lrsoft.mets.item.NanoBow;
import net.lrsoft.mets.item.PlasmaAirCannon;
import net.lrsoft.mets.item.battery.AdvancedLithiumBattery;
import net.lrsoft.mets.item.battery.LithiumBattery;
import net.lrsoft.mets.item.crafting.ItemCraftingManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod.EventBusSubscriber(modid = MoreElectricTools.MODID)
public class ItemManager {
	
	public static AdvancedIridiumSword advancedIridiumSword;
	
	public static AdvancedLithiumBattery advancedLithiumBattery;
	public static LithiumBattery lithiumBattery;
	
	public static ElectricNutritionSupply electricNutritionSupply;
	public static ElectricFishingRod electricFishingRod;
	public static ElectricShield electricShield;
	
	public static NanoBow nanoBow;
	public static PlasmaAirCannon plasmaAirCannon;
	
	public static DivingMask divingMask;
	
	static 
	{
		advancedIridiumSword = new AdvancedIridiumSword();
		
		advancedLithiumBattery = new AdvancedLithiumBattery();
		lithiumBattery = new LithiumBattery();
		
		electricNutritionSupply = new ElectricNutritionSupply();
		electricFishingRod = new ElectricFishingRod();
		electricShield = new ElectricShield();
		nanoBow = new NanoBow();
		plasmaAirCannon = new PlasmaAirCannon();	
		
		divingMask = new DivingMask();		
	}
	
	@SubscribeEvent
	public static void onItemInit(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(advancedIridiumSword);
		event.getRegistry().register(advancedLithiumBattery);
		event.getRegistry().register(lithiumBattery);
		event.getRegistry().register(electricNutritionSupply);
		event.getRegistry().register(electricFishingRod);
		event.getRegistry().register(electricShield);
		event.getRegistry().register(nanoBow);
		event.getRegistry().register(plasmaAirCannon);
		
		event.getRegistry().register(divingMask);
		
		ItemCraftingManager.onCraftingItemInit(event);
		ItemCraftingManager.onCraftingItemRecipeInit();
		onRecipeInit();
	}
	
	private static void onRecipeInit() 
	{
		Recipes.advRecipes.addRecipe(new ItemStack(advancedIridiumSword), 
				new Object[] {
						"SIS",
						"SIS",
						"CDC",
						'S', IC2Items.getItem("casing", "steel"),
						'I', IC2Items.getItem("crafting", "iridium"),
						'C', IC2Items.getItem("crafting", "carbon_plate"),
						'D', IC2Items.getItem("lapotron_crystal")
				});
		
		Recipes.advRecipes.addRecipe(new ItemStack(advancedLithiumBattery), 
				new Object[] {
						"CSC",
						"SLS",
						"SPS",
						'C', IC2Items.getItem("cable", "type:copper,insulation:1"),
						'S', IC2Items.getItem("casing", "steel"),
						'L', IC2Items.getItem("dust", "lithium"),
						'P', IC2Items.getItem("dust", "lead")
				});
		
		Recipes.advRecipes.addRecipe(new ItemStack(lithiumBattery), 
				new Object[] {
						" C ",
						"SLS",
						"SLS",
						'C', IC2Items.getItem("cable", "type:copper,insulation:1"),
						'S', IC2Items.getItem("casing", "copper"),
						'L', IC2Items.getItem("dust", "lithium")
				});
		
		Recipes.advRecipes.addRecipe(new ItemStack(electricNutritionSupply), 
				new Object[] {
						"SPS",
						"HCH",
						"SBS",
						'P', IC2Items.getItem("treetap"),
						'S', IC2Items.getItem("casing", "gold"),
						'C', IC2Items.getItem("crafting", "circuit"),
						'H', IC2Items.getItem("heat_exchanger"),
						'B', IC2Items.getItem("re_battery")
				});
		
		Recipes.advRecipes.addRecipe(new ItemStack(electricFishingRod), 
				new Object[] {
						" SL",
						"SDL",
						" SL",
						'S', IC2Items.getItem("casing", "iron"),
						'L', Items.STRING,
						'D', IC2Items.getItem("crafting", "small_power_unit")
				});
		
		Recipes.advRecipes.addRecipe(new ItemStack(nanoBow), 
				new Object[] {
						"CMS",
						"CBA",
						"CMS",
						'C', IC2Items.getItem("crafting", "carbon_plate"),
						'B', IC2Items.getItem("energy_crystal"),
						'M', IC2Items.getItem("crafting", "electric_motor"),
						'S', Items.LEAD,
						'A', IC2Items.getItem("crafting", "advanced_circuit")
				});		
		
		Recipes.advRecipes.addRecipe(new ItemStack(plasmaAirCannon), 
				new Object[] {
						"CCB",
						"LPA",
						"LLB",
						'C', IC2Items.getItem("crafting", "coil"),
						'B', lithiumBattery,
						'L', IC2Items.getItem("crafting", "alloy"),
						'P', IC2Items.getItem("fluid_cell"),
						'A', IC2Items.getItem("crafting", "advanced_circuit")
				});
		
		
		Recipes.advRecipes.addRecipe(new ItemStack(electricShield), 
				new Object[] {
						"SAS",
						"SBS",
						"SDS",
						'S', IC2Items.getItem("plate", "steel"),
						'B', lithiumBattery,
						'D', IC2Items.getItem("crafting", "power_unit"),
						'A', IC2Items.getItem("crafting", "advanced_circuit")
				});
		
		Recipes.advRecipes.addRecipe(new ItemStack(divingMask), 
				new Object[] {
						"EBE",
						"FGF",
						"SCS",
						'E', IC2Items.getItem("component_heat_exchanger"),
						'B', lithiumBattery,
						'G', IC2Items.getItem("glass", "reinforced"),
						'F', IC2Items.getItem("fluid_cell"),
						'C', IC2Items.getItem("crafting", "advanced_circuit"),
						'S', IC2Items.getItem("crafting", "rubber")
				});
		
		
		}	
}
