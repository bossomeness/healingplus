package me.bossomeness.healingplus;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
//import org.bukkit.Location;
import org.bukkit.Material;
//import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.material.Chest;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;

	@Override
	public void onEnable() {
		PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info(pdffile.getName() + " Version " + pdffile.getVersion()
				+ " Has Been Enabled!");
		// getConfig().options().copyDefaults(true);
		// saveConfig();
		PluginManager pm = this.getServer().getPluginManager();
		pm.addPermission(new Permisssions().healself);
		pm.addPermission(new Permisssions().healother);
		pm.addPermission(new Permisssions().halfhealself);
		pm.addPermission(new Permisssions().halfhealother);
		pm.addPermission(new Permisssions().killself);
		pm.addPermission(new Permisssions().killother);
		pm.addPermission(new Permisssions().healthself);
		pm.addPermission(new Permisssions().healthother);
		pm.addPermission(new Permisssions().activatehelmetself);
		pm.addPermission(new Permisssions().activatehelmetother);
		pm.addPermission(new Permisssions().help);
		//pm.addPermission(new Permisssions().healthpackself);
		//pm.addPermission(new Permisssions().healthpackother);
		ItemStack HelmetItem = new ItemStack(Material.IRON_HELMET);
		ItemMeta HelmetMeta = HelmetItem.getItemMeta();
		HelmetMeta.setDisplayName(ChatColor.DARK_PURPLE + "Regeneration Helmet");
		HelmetMeta.addEnchant(Enchantment.ARROW_INFINITE, 20000, true);
		HelmetItem.setItemMeta(HelmetMeta);
		ShapedRecipe regenhelm = new ShapedRecipe(new ItemStack(
				Material.IRON_HELMET, 1)).shape("*@*", "/$/", "$#$")
				.setIngredient('*', Material.GHAST_TEAR)
				.setIngredient('@', Material.DIAMOND)
				.setIngredient('/', Material.BLAZE_ROD)
				.setIngredient('$', Material.IRON_INGOT)
				.setIngredient('#', Material.NETHER_STAR);
		getServer().addRecipe(regenhelm);
	}

	@Override
	public void onDisable() {
		PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info(pdffile.getName() + " Has Been Disabled!");
		getServer().getPluginManager().removePermission(
				new Permisssions().healself);
		getServer().getPluginManager().removePermission(
				new Permisssions().healother);
		getServer().getPluginManager().removePermission(
				new Permisssions().halfhealself);
		getServer().getPluginManager().removePermission(
				new Permisssions().halfhealother);
		getServer().getPluginManager().removePermission(
				new Permisssions().killself);
		getServer().getPluginManager().removePermission(
				new Permisssions().killother);
		getServer().getPluginManager().removePermission(
				new Permisssions().healthself);
		getServer().getPluginManager().removePermission(
				new Permisssions().activatehelmetself);
		getServer().getPluginManager().removePermission(
				new Permisssions().activatehelmetother);
		getServer().getPluginManager()
				.removePermission(new Permisssions().help);
		//getServer().getPluginManager().removePermission(
				//new Permisssions().healthpackself);
		//getServer().getPluginManager().removePermission(
				//new Permisssions().healthpackother);
		getServer().clearRecipes();

	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			// +----------------------------------+
			// | THIS IS THE HEAL COMMAND SECTION |
			// +----------------------------------+
			if (commandLabel.equalsIgnoreCase("heal")
					|| commandLabel.equalsIgnoreCase("h")) {
				if (args.length == 0) {
					// heal = 0 args /heal bossomeness = 1 args
					if (sender.hasPermission(new Permisssions().healself)) {
						player.setHealth(20);
						player.setFoodLevel(20);
						player.setRemainingAir(20);
						player.setFireTicks(0);
						player.sendMessage(ChatColor.AQUA + "Healed!");
					} else {
						sender.sendMessage(ChatColor.RED
								+ "You do not have permission to perform this command!");
					}
				} else if (args.length == 1) {
					if (player.getServer().getPlayer(args[0]) != null) {
						if (sender.hasPermission(new Permisssions().healother)) {
							Player targetPlayer = player.getServer().getPlayer(
									args[0]);
							targetPlayer.setHealth(20);
							targetPlayer.setFoodLevel(20);
							targetPlayer.setRemainingAir(20);
							targetPlayer.setFireTicks(0);
							targetPlayer.sendMessage(ChatColor.AQUA
									+ "You have just been healed!");
						} else {
							sender.sendMessage(ChatColor.RED
									+ "You do not have permission to perform this command!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Player Not Found!");
					}
					// +--------------------------------------+
					// | THIS IS THE HALFHEAL COMMAND SECTION |
					// +--------------------------------------+
				} else if (commandLabel.equalsIgnoreCase("halfheal")) {
					if (player.getServer().getPlayer(args[0]) != null) {
						if (sender
								.hasPermission(new Permisssions().halfhealself)) {
							player.sendMessage(ChatColor.GREEN
									+ "Health Reduced to Half!");
							player.setHealth(5);
							player.setFoodLevel(5);
							player.setRemainingAir(5);
							player.setFireTicks(0);
						} else {
							sender.sendMessage(ChatColor.RED
									+ "You do not have permission to perform this command!");
						}
					} else if (args.length == 1) {
						if (player.getServer().getPlayer(args[0]) != null) {
							if (player.getServer().getPlayer(args[0]) != null) {
								if (sender
										.hasPermission(new Permisssions().halfhealother)) {
									Player targetPlayer = player.getServer()
											.getPlayer(args[0]);
									targetPlayer.setHealth(5);
									targetPlayer.setFoodLevel(5);
									targetPlayer.setRemainingAir(5);
									targetPlayer.setFireTicks(0);
									targetPlayer.sendMessage(ChatColor.GREEN
											+ "Health Reduced to Half!");
								} else {
									sender.sendMessage(ChatColor.RED
											+ "You do not have permission to perform this command!");
								}
							}
						} else {
							player.sendMessage(ChatColor.RED
									+ "Player Not Found!");
						}
						// +----------------------------------+
						// | THIS IS THE KILL COMMAND SECTION |
						// +----------------------------------+
					} else if (commandLabel.equalsIgnoreCase("kill")) {
						if (player.getServer().getPlayer(args[0]) != null) {
							if (sender
									.hasPermission(new Permisssions().killself)) {
								player.sendMessage(ChatColor.DARK_RED
										+ "You have killed yourself!");
								player.setHealth(0);
							} else {
								sender.sendMessage(ChatColor.RED
										+ "You do not have permission to perform this command!");
							}
						} else if (args.length == 1) {
							if (player.getServer().getPlayer(args[0]) != null) {
								if (player.getServer().getPlayer(args[0]) != null) {
									if (sender
											.hasPermission(new Permisssions().killother)) {
										Player targetPlayer = player
												.getServer().getPlayer(args[0]);
										targetPlayer.setHealth(0);
										targetPlayer
												.sendMessage(ChatColor.DARK_RED
														+ "You have been killed!");
									} else {
										sender.sendMessage(ChatColor.RED
												+ "You do not have permission to perform this command!");
									}
								}
							} else {
								player.sendMessage(ChatColor.RED
										+ "Player Not Found!");
							}
						}
						// +------------------------------------+
						// | THIS IS THE HEALTH COMMAND SECTION |
						// +------------------------------------+
						else if (commandLabel.equalsIgnoreCase("health")) {
							if (player.getServer().getPlayer(args[0]) != null) {
								if (sender
										.hasPermission(new Permisssions().healthself)) {
									player.sendMessage(ChatColor.DARK_RED
											+ "Your health has been set");
									player.setHealth(Integer.parseInt(args[1]));
								} else {
									sender.sendMessage(ChatColor.RED
											+ "You do not have permission to perform this command!");
								}
							} else if (args.length == 2) {
								if (player.getServer().getPlayer(args[0]) != null) {
									if (player.getServer().getPlayer(args[0]) != null) {
										if (sender
												.hasPermission(new Permisssions().healthother)) {
											Player targetPlayer = player
													.getServer().getPlayer(
															args[0]);
											targetPlayer.setHealth(Integer
													.parseInt(args[1]));
											targetPlayer
													.sendMessage(ChatColor.DARK_RED
															+ "Your health has been set.");
										} else {
											sender.sendMessage(ChatColor.RED
													+ "You do not have permission to perform this command!");
										}
									}
								} else {
									player.sendMessage(ChatColor.RED
											+ "Player Not Found!");

								}
							}
							// +---------------------------------------------+
							// | THIS IS THE ACTIVATE HELMET COMMAND SECTION |
							// +---------------------------------------------+
							else if (commandLabel
									.equalsIgnoreCase("activatehelmet")) {
								if (player.getServer().getPlayer(args[0]) != null) {
									if (sender
											.hasPermission(new Permisssions().activatehelmetself)) {
										ItemStack HelmetItem = new ItemStack(
												Material.IRON_HELMET);
										ItemMeta HelmetMeta = HelmetItem
												.getItemMeta();
										HelmetMeta
												.setDisplayName(ChatColor.DARK_PURPLE
														+ "Regeneration Helmet");
										HelmetMeta.addEnchant(
												Enchantment.ARROW_INFINITE,
												20000, true);
										HelmetItem.setItemMeta(HelmetMeta);
										if (player.getInventory().contains(
												ItemStack(HelmetItem))) {
											player.addPotionEffect(new PotionEffect(
													PotionEffectType.REGENERATION,
													Integer.MAX_VALUE, 1));
											player.sendMessage(ChatColor.AQUA
													+ "You have successfully activated your Regeneration Helmet");
										}
									} else {
										sender.sendMessage(ChatColor.RED
												+ "You do not have permission to perform this command!");
									}
								} else if (args.length == 1) {
									if (player.getServer().getPlayer(args[0]) != null) {
										if (player.getServer().getPlayer(
												args[0]) != null) {
											if (sender
													.hasPermission(new Permisssions().activatehelmetother)) {
												ItemStack RegenHelmet = new ItemStack(
														Material.IRON_HELMET);
												Player targetPlayer = player
														.getServer().getPlayer(
																args[0]);
												if (targetPlayer
														.getInventory()
														.contains(
																ItemStack(RegenHelmet))) {
													targetPlayer
															.addPotionEffect(new PotionEffect(
																	PotionEffectType.REGENERATION,
																	Integer.MAX_VALUE,
																	1));

													targetPlayer
															.sendMessage(ChatColor.AQUA
																	+ "Your Regeneration Helmet has been activated by another player.");
												} else {
													sender.sendMessage(ChatColor.RED
															+ "You do not have permission to perform this command!");
												}
											}
										} else {
											player.sendMessage(ChatColor.RED
													+ "Player Not Found!");
										}
										// +----------------------------------+
										// | THIS IS THE HELP COMMAND SECTION |
										// +----------------------------------+
									} else if (commandLabel
											.equalsIgnoreCase("healingplus")) {
										if (player.getServer().getPlayer(
												args[0]) != null) {
											if (sender
													.hasPermission(new Permisssions().help)) {
												player.sendMessage(ChatColor.GOLD
														+ "===========[HealingPlus Help]=============================================");
												player.sendMessage(ChatColor.YELLOW
														+ "/healingplus"
														+ ChatColor.AQUA
														+ " - Displays this page");
												player.sendMessage(ChatColor.YELLOW
														+ "/heal [player]"
														+ ChatColor.AQUA
														+ " - Fully heal yourself or another player");
												player.sendMessage(ChatColor.YELLOW
														+ "/halfheal [player]"
														+ ChatColor.AQUA
														+ " - Halfway heal yourself or another player");
												player.sendMessage(ChatColor.YELLOW
														+ "/kill [player]"
														+ ChatColor.AQUA
														+ " - Sets your or another player's health to zero");
												player.sendMessage(ChatColor.YELLOW
														+ "/activatehelmet [player]"
														+ ChatColor.AQUA
														+ " - Activate the effects on a Regeneration Helmet");
											} else {
												sender.sendMessage(ChatColor.RED
														+ "You do not have permission to perform this command!");
											}
											// +-----------------------------------------+
											// | THIS IS THE HEALTH PACK COMMAND SECTION |
											// +-----------------------------------------+
										} //else if (commandLabel
										//		.equalsIgnoreCase("healthpack")) {
										//	if (player.getServer().getPlayer(
										//			args[0]) != null) {
										//		if (sender
										//				.hasPermission(new Permisssions().healthpackself)) {
										//			player.sendMessage(ChatColor.RED
										//					+ "You gave yourself a health pack!");
										//			Location pl = player.getLocation();
										//			World world = player.getWorld();
										//			Location chestlocation = new Location(world, pl.getX(), pl.getY() + 2, pl.getZ());
										//			chestlocation.getBlock().setType(Material.CHEST);
										//			Chest chest = (Chest) chestlocation.getBlock().getState();
										//			ItemStack[] inventoryBefore = player.getInventory().getContents();
										//			ItemStack[] chestContents = new ItemStack[inventoryBefore.length];
										//			for (int i = 0; i < player.getInventory().getSize();i++){
										//				chestContents[i] = inventoryBefore[i];
										//			}
										//			
										//			for (int i = 0; i < ((HumanEntity) chest).getInventory().getSize(); i++) {
										//				if(chestContents[i] !=null){
										//					((HumanEntity) chest).getInventory().addItem(chestContents[i]);
										//				}
										//			}
										//			player.getInventory().clear();
										//		} else {
										//			sender.sendMessage(ChatColor.RED
										//					+ "You do not have permission to perform this command!");
										//		}
										//	} else if (args.length == 1) {
										//		if (player.getServer()
										//				.getPlayer(args[0]) != null) {
										//			if (player.getServer()
										//					.getPlayer(args[0]) != null) {
										//				if (sender
										//						.hasPermission(new Permisssions().healthpackother)) {
										//					Player targetPlayer = player
										//							.getServer()
										//							.getPlayer(
										//									args[0]);
										//					targetPlayer
										//							.sendMessage(ChatColor.DARK_RED
										//									+ "You have been killed!");
										//				} else {
										//					sender.sendMessage(ChatColor.RED
										//							+ "You do not have permission to perform this command!");
										//				}
										//			}
										//		} else {
										//			player.sendMessage(ChatColor.RED
										//					+ "Player Not Found!");
										//		}
								//			}
							//			}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private Material ItemStack(ItemStack RegenHelmet) {
		return null;
	}
}