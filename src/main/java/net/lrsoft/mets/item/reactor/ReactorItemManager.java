package net.lrsoft.mets.item.reactor;

import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import net.lrsoft.mets.item.crafting.ItemCraftingManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;

public class ReactorItemManager {
	public static Item advOCHeatVent;
	
	static 
	{
		advOCHeatVent = new ReactorHeatVent("advanced_oc_heat_vent", 72, 56, 4000);
	}
	
	public static void onItemInit(Register<Item> event)
	{
		event.getRegistry().register(advOCHeatVent);
	}
	
	public static void onItemRecipeInit()
	{
		Recipes.advRecipes.addRecipe(new ItemStack(advOCHeatVent), 
				new Object[] {
						"GNG",
						"EIE",
						"GNG",
						'G', IC2Items.getItem("crafting", "electric_motor"),
						'N', ItemCraftingManager.niobium_titanium_plate,
						'E', IC2Items.getItem("overclocked_heat_vent"),
						'I', IC2Items.getItem("crafting", "iridium")
				});
	}
	
	public static void onItemModelInit()
	{
		ModelLoader.setCustomModelResourceLocation(advOCHeatVent, 0,
				new ModelResourceLocation(advOCHeatVent.getRegistryName(), "inventory"));
	}
}
