package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;

import main.DraynorAIO;
import main.global.Methods;
import money.misc.Constant;

import org.powerbot.game.api.methods.tab.Skills;

import spawner.misc.Enums.Eggs;
import spawner.misc.Enums.Fruit;
import spawner.misc.Enums.PouchTypes;
import spawner.misc.Enums.ScrollTypes;
import spawner.misc.Variables;
import fletching.misc.Enums;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUI() {
		initComponents();
	}

	private void btnSpawnActionPerformed(ActionEvent e) {

		DraynorAIO.globalMode = 0;
		spawner.misc.Variables.startingExperience = Skills
				.getExperience(Skills.SUMMONING);
		switch (comboSpawn.getSelectedIndex()) {
		case 0:
			main.global.Methods.s("Spirit Spider");
			Variables.mode = 1;
			Variables.xpEachCast = 0.2f;
			Variables.pointCost = 1;
			Variables.pouchId = PouchTypes.SPIDER.getId();
			Variables.scrollId = ScrollTypes.EGG_SPAWN.getId();
			Variables.lootList.add(Eggs.RED_SPIDER_EGG.getId());
			DraynorAIO.jobs.add(new spawner.nodes.Spawn());
			DraynorAIO.jobs.add(new spawner.nodes.Looting());
			DraynorAIO.jobs.add(new spawner.nodes.Banking());
			DraynorAIO.jobs.add(new spawner.nodes.SummonNew());
			DraynorAIO.jobs.add(new spawner.nodes.ObeliskWalk());
			DraynorAIO.jobs.add(new spawner.nodes.BankWalk());
			DraynorAIO.jobs.add(new spawner.nodes.Failsafe());
			break;
		case 1:
			Variables.mode = 2;
			Variables.pointCost = 5;
			Variables.xpEachCast = 3.1f;
			Variables.pouchId = PouchTypes.COBRA.getId();
			Variables.scrollId = ScrollTypes.OPH_INCUBATION.getId();
			DraynorAIO.jobs.add(new spawner.nodes.cobra.Spawn());
			DraynorAIO.jobs.add(new spawner.nodes.cobra.Banking());
			DraynorAIO.jobs.add(new spawner.nodes.cobra.ObeliskWalk());
			DraynorAIO.jobs.add(new spawner.nodes.cobra.BankWalk());
			DraynorAIO.jobs.add(new spawner.nodes.cobra.SummonNew());
			DraynorAIO.jobs.add(new spawner.nodes.cobra.Failsafe());
			break;
		case 2:
			main.global.Methods.s("Fruit bat");
			Variables.mode = 0;
			Variables.xpEachCast = 1.4f;
			Variables.pointCost = 7;
			Variables.pouchId = PouchTypes.BAT.getId();
			Variables.scrollId = ScrollTypes.FRUITFALL.getId();
			DraynorAIO.jobs.add(new spawner.nodes.Spawn());
			DraynorAIO.jobs.add(new spawner.nodes.Looting());
			DraynorAIO.jobs.add(new spawner.nodes.Banking());
			DraynorAIO.jobs.add(new spawner.nodes.SummonNew());
			DraynorAIO.jobs.add(new spawner.nodes.ObeliskWalk());
			DraynorAIO.jobs.add(new spawner.nodes.BankWalk());
			DraynorAIO.jobs.add(new spawner.nodes.Failsafe());
			for (int i : llistFruit.getSelectedIndices()) {
				switch (i) {
				case 0:
					Variables.lootList.add(Fruit.ORANGE.getId());
					break;
				case 1:
					Variables.lootList.add(Fruit.COCONUT.getId());
					break;
				case 2:
					Variables.lootList.add(Fruit.PAPAYA.getId());
					break;
				case 3:
					Variables.lootList.add(Fruit.LIME.getId());
					break;
				case 4:
					Variables.lootList.add(Fruit.BANANA.getId());
					break;
				case 5:
					Variables.lootList.add(Fruit.LEMON.getId());
					break;
				case 6:
					Variables.lootList.add(Fruit.WATERMELON.getId());
					break;
				}

			}
			break;
		}
		Variables.loot = main.global.Methods.listToArray(Variables.lootList);
		DraynorAIO.guiIsDone = true;
		this.setVisible(false);
	}

	// ----------------------------------------------------------
	private void btnFletchActionPerformed(ActionEvent e) {
		DraynorAIO.globalMode = 1;
		if(radString.isSelected()) {
			DraynorAIO.jobs.add(new fletching.nodes.Stringing());
		} else {
			DraynorAIO.jobs.add(new fletching.nodes.Cutting());
		}
		DraynorAIO.jobs.add(new fletching.nodes.Banking());
		fletching.misc.Variables.startLevel = Skills
				.getRealLevel(Skills.FLETCHING);
		fletching.misc.Variables.startXp = Skills
				.getExperience(Skills.FLETCHING);
		fletching.misc.Variables.action = radString.isSelected() ? 1 : 0;
		fletching.misc.Variables.bowType = radShortbow.isSelected() ? 0 : 1;
		switch (comboLogType.getSelectedIndex()) {
		case 0:
			fletching.misc.Variables.shortbowId = Enums.BowData.NORMAL.getUSId();
			fletching.misc.Variables.longbowId = Enums.BowData.NORMAL.getULId();
			fletching.misc.Variables.logType = Enums.LogTypes.NOMRAL.getId();
			break;
		case 1:
			fletching.misc.Variables.shortbowId = Enums.BowData.OAK.getUSId();
			fletching.misc.Variables.longbowId = Enums.BowData.OAK.getULId();
			fletching.misc.Variables.logType = Enums.LogTypes.OAK.getId();
			break;
		case 2:
			fletching.misc.Variables.shortbowId = Enums.BowData.WILLOW.getUSId();
			fletching.misc.Variables.longbowId = Enums.BowData.WILLOW.getULId();
			fletching.misc.Variables.logType = Enums.LogTypes.WILLOW.getId();
			break;
		case 3:
			fletching.misc.Variables.shortbowId = Enums.BowData.MAPLE.getUSId();
			fletching.misc.Variables.longbowId = Enums.BowData.MAPLE.getULId();
			fletching.misc.Variables.logType = Enums.LogTypes.MAPLE.getId();
			break;
		case 4:
			fletching.misc.Variables.shortbowId = Enums.BowData.YEW.getUSId();
			fletching.misc.Variables.longbowId = Enums.BowData.YEW.getULId();
			fletching.misc.Variables.logType = Enums.LogTypes.YEW.getId();
			break;
		case 5:
			fletching.misc.Variables.shortbowId = Enums.BowData.MAGIC.getUSId();
			fletching.misc.Variables.longbowId = Enums.BowData.MAGIC.getULId();
			fletching.misc.Variables.logType = Enums.LogTypes.MAGIC.getId();
			break;
		}
		DraynorAIO.guiIsDone = true;
		System.out.println(fletching.misc.Variables.logType);
		setVisible(false);
	}

	private void radShortbowActionPerformed(ActionEvent e) {
		radLongbow.setSelected(false);
	}

	private void radLongbowActionPerformed(ActionEvent e) {
		radShortbow.setSelected(false);
	}

	private void radCutActionPerformed(ActionEvent e) {
		radString.setSelected(false);
	}

	private void radStringActionPerformed(ActionEvent e) {
		radCut.setSelected(false);
	}

	// ---------------------------------------------------

	private void btnFishActionPerformed(ActionEvent e) {
		DraynorAIO.globalMode = 2;
		DraynorAIO.jobs.add(new fishing.nodes.Fishing());
		DraynorAIO.jobs.add(new fishing.nodes.FishWalk());
		if (chkPowerfish.isSelected()) {
			System.out.println("POWERFISHING");
			fishing.misc.Variables.powerFishing = true;
			DraynorAIO.jobs.add(new fishing.nodes.Dropping());
		} else {
			DraynorAIO.jobs.add(new fishing.nodes.Banking());
			DraynorAIO.jobs.add(new fishing.nodes.BankWalk());
		}
		if (chkMousekeyFish.isSelected()) {
			fishing.misc.Variables.mouseKey = true;
		}
		switch (comboFishing.getSelectedIndex()) {
		case 0:
			fishing.misc.Variables.mode = 0;
			fishing.misc.Variables.animation = 621;
			fishing.misc.Variables.fishList
					.add(fishing.misc.Enums.FISH.ANCHOVIE.getId());
			fishing.misc.Variables.fishList.add(fishing.misc.Enums.FISH.SHRIMP
					.getId());
			break;
		case 1:
			fishing.misc.Variables.mode = 1;
			fishing.misc.Variables.animation = 623;
			fishing.misc.Variables.fishList.add(fishing.misc.Enums.FISH.SARDINE
					.getId());
			fishing.misc.Variables.fishList.add(fishing.misc.Enums.FISH.HERRING
					.getId());
			break;
		}
		fishing.misc.Variables.startExp = Skills.getExperience(Skills.FISHING);
		DraynorAIO.guiIsDone = true;
		this.setVisible(false);
	}

	private void chkPowerfishActionPerformed(ActionEvent e) {
		chkMousekeyFish.setEnabled(chkPowerfish.isSelected() ? true : false);
	}

	private void btnMoneyActionPerformed(ActionEvent e) {
		DraynorAIO.globalMode = 3;
		DraynorAIO.jobs.add(new money.nodes.basket.Banking());
		DraynorAIO.jobs.add(new money.nodes.basket.Filling());
		if (radFillBasket.isSelected()) {
			money.misc.Variables.mode = 2;
			money.misc.Variables.basketPrice = Methods.getPrice(Constant.BASKET);

			switch (comboFill.getSelectedIndex()) {
			case 0:
				money.misc.Variables.fruitId = Constant.STRAWBERRY;
				money.misc.Variables.fullBasketId = Constant.STRAWBERRY_5;
				money.misc.Variables.fruitPrice = Methods
						.getPrice(Constant.STRAWBERRY);
				money.misc.Variables.fullBasketPrice = Methods
						.getPrice(Constant.STRAWBERRY_5);
				break;
			case 1:
				money.misc.Variables.fruitId = Constant.ORANGE;
				money.misc.Variables.fullBasketId = Constant.ORANGE_5;
				money.misc.Variables.fruitPrice = Methods
						.getPrice(Constant.ORANGE);
				money.misc.Variables.fullBasketPrice = Methods
						.getPrice(Constant.ORANGE_5);
				break;
			case 2:
				money.misc.Variables.fruitId = Constant.APPLE;
				money.misc.Variables.fullBasketId = Constant.APPLE_5;
				money.misc.Variables.fruitPrice = Methods
						.getPrice(Constant.APPLE);
				money.misc.Variables.fullBasketPrice = Methods
						.getPrice(Constant.APPLE_5);
				break;
			case 3:
				money.misc.Variables.fruitId = Constant.BANANA;
				money.misc.Variables.fullBasketId = Constant.BANANA_5;
				money.misc.Variables.fruitPrice = Methods
						.getPrice(Constant.BANANA);
				money.misc.Variables.fullBasketPrice = Methods
						.getPrice(Constant.BANANA_5);
				break;
			default:
				break;
			}
		}
		DraynorAIO.guiIsDone = true;
		this.setVisible(false);
	}

	private void radFillBasketActionPerformed(ActionEvent e) {
		comboFill.setEnabled(radFillBasket.isSelected() ? true : false);
		radSoftClay.setSelected(false);
		radVials.setSelected(false);
	}

	private void radSoftClayActionPerformed(ActionEvent e) {
		radFillBasket.setSelected(false);
		radVials.setSelected(false);
	}

	private void radVialsActionPerformed(ActionEvent e) {
		radSoftClay.setSelected(false);
		radFillBasket.setSelected(false);
	}

	private void chkPowerchopItemStateChanged(ItemEvent e) {
		// TODO add your code here
	}

	private void chkHatchetInInvItemStateChanged(ItemEvent e) {
		// TODO add your code here
	}

	private void btnChopActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void chkPotteryItemStateChanged(ItemEvent e) {
		// TODO add your code here
	}

	private void chkGemsItemStateChanged(ItemEvent e) {
		// TODO add your code here
	}

	private void chkLeatherItemStateChanged(ItemEvent e) {
		// TODO add your code here
	}

	private void btnCraftActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void btnThieveActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void btnBonfireActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - hiiiii jacobfromhf
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		scrollPane1 = new JScrollPane();
		llistFruit = new JList<>();
		btnSpawn = new JButton();
		comboSpawn = new JComboBox<>();
		label1 = new JLabel();
		label2 = new JLabel();
		panel2 = new JPanel();
		chkPowerchop = new JCheckBox();
		chkMousekeyChop = new JCheckBox();
		chkHatchetInInv = new JCheckBox();
		comboHatchet = new JComboBox<>();
		btnChop = new JButton();
		panel3 = new JPanel();
		btnFish = new JButton();
		comboFishing = new JComboBox<>();
		chkPowerfish = new JCheckBox();
		chkMousekeyFish = new JCheckBox();
		panel4 = new JPanel();
		btnThieve = new JButton();
		comboThieve = new JComboBox<>();
		txtFoodId = new JTextField();
		label4 = new JLabel();
		label5 = new JLabel();
		spinFoodAmount = new JSpinner();
		txtEatAt = new JTextField();
		label6 = new JLabel();
		label10 = new JLabel();
		panel5 = new JPanel();
		label3 = new JLabel();
		comboBonfire = new JComboBox<>();
		btnBonfire = new JButton();
		chkFireSpirit = new JCheckBox();
		panel7 = new JPanel();
		btnCraft = new JButton();
		chkPottery = new JCheckBox();
		comboPottery = new JComboBox<>();
		chkGems = new JCheckBox();
		comboGems = new JComboBox<>();
		chkLeather = new JCheckBox();
		comboLeather = new JComboBox<>();
		panel9 = new JPanel();
		btnFletch = new JButton();
		label7 = new JLabel();
		comboLogType = new JComboBox<>();
		label8 = new JLabel();
		label9 = new JLabel();
		radShortbow = new JRadioButton();
		radLongbow = new JRadioButton();
		radCut = new JRadioButton();
		radString = new JRadioButton();
		panel8 = new JPanel();
		btnMoney = new JButton();
		radSoftClay = new JRadioButton();
		radVials = new JRadioButton();
		radFillBasket = new JRadioButton();
		comboFill = new JComboBox<>();

		setAlwaysOnTop(true);
		setTitle("Draynor AIO");
		setResizable(false);
		Container contentPane = getContentPane();

		{

			{
				{

					llistFruit.setModel(new AbstractListModel<String>() {
						/**
						 * 
						 */
						private static final long serialVersionUID = -7730975111839713477L;
						String[] values = { "Oranges", "Coconuts", "Papaya",
								"Limes", "Bananas", "Lemons", "Watermelon" };

						@Override
						public int getSize() {
							return values.length;
						}

						@Override
						public String getElementAt(int i) {
							return values[i];
						}
					});
					llistFruit.setBorder(new MatteBorder(1, 1, 1, 1,
							Color.black));
					llistFruit.setToolTipText("Hold CTRL to select multiple");
					scrollPane1.setViewportView(llistFruit);
				}

				btnSpawn.setText("Start Spawning!");
				btnSpawn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btnSpawnActionPerformed(e);
					}
				});

				comboSpawn.setModel(new DefaultComboBoxModel<>(new String[] {
						"Spirit Spider", "Spirit Cockatrice", "Fruit Bat" }));

				label1.setText("Familiar to summon");

				label2.setText("Fruit to grab");

				GroupLayout panel1Layout = new GroupLayout(panel1);
				panel1.setLayout(panel1Layout);
				panel1Layout
						.setHorizontalGroup(panel1Layout
								.createParallelGroup()
								.addGroup(
										panel1Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel1Layout
																.createParallelGroup()
																.addComponent(
																		scrollPane1,
																		GroupLayout.PREFERRED_SIZE,
																		81,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		label2))
												.addGroup(
														panel1Layout
																.createParallelGroup()
																.addGroup(
																		panel1Layout
																				.createSequentialGroup()
																				.addGap(28,
																						28,
																						28)
																				.addGroup(
																						panel1Layout
																								.createParallelGroup()
																								.addComponent(
																										label1)
																								.addComponent(
																										comboSpawn,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE))
																				.addGap(0,
																						77,
																						Short.MAX_VALUE))
																.addGroup(
																		GroupLayout.Alignment.TRAILING,
																		panel1Layout
																				.createSequentialGroup()
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						98,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnSpawn)))
												.addContainerGap()));
				panel1Layout
						.setVerticalGroup(panel1Layout
								.createParallelGroup()
								.addGroup(
										panel1Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel1Layout
																.createParallelGroup(
																		GroupLayout.Alignment.BASELINE)
																.addComponent(
																		label2)
																.addComponent(
																		btnSpawn))
												.addGroup(
														panel1Layout
																.createParallelGroup()
																.addGroup(
																		panel1Layout
																				.createSequentialGroup()
																				.addGap(30,
																						30,
																						30)
																				.addComponent(
																						label1)
																				.addGap(3,
																						3,
																						3)
																				.addComponent(
																						comboSpawn,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(0,
																						0,
																						Short.MAX_VALUE))
																.addGroup(
																		GroupLayout.Alignment.TRAILING,
																		panel1Layout
																				.createSequentialGroup()
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						scrollPane1,
																						GroupLayout.PREFERRED_SIZE,
																						123,
																						GroupLayout.PREFERRED_SIZE)))
												.addContainerGap()));
			}
			tabbedPane1.addTab("Spawning", panel1);

			{

				chkPowerchop.setText("Powerchop");
				chkPowerchop.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						chkPowerchopItemStateChanged(e);
					}
				});

				chkMousekeyChop.setText("Mousekey Simulated");
				chkMousekeyChop.setEnabled(false);

				chkHatchetInInv.setText("Hatchet in Inv.");
				chkHatchetInInv
						.setToolTipText("Tick this if the hatchet you are using will be in your inventory");
				chkHatchetInInv.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						chkHatchetInInvItemStateChanged(e);
					}
				});

				comboHatchet.setEnabled(false);
				comboHatchet.setModel(new DefaultComboBoxModel<>(new String[] {
						"Bronze", "Iron", "Steel", "Mithril", "Adamant",
						"Rune", "Dragon" }));

				btnChop.setText("Start Chopping!");
				btnChop.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btnChopActionPerformed(e);
					}
				});

				GroupLayout panel2Layout = new GroupLayout(panel2);
				panel2.setLayout(panel2Layout);
				panel2Layout
						.setHorizontalGroup(panel2Layout
								.createParallelGroup()
								.addGroup(
										panel2Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel2Layout
																.createParallelGroup()
																.addGroup(
																		panel2Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel2Layout
																								.createParallelGroup()
																								.addComponent(
																										chkHatchetInInv)
																								.addGroup(
																										panel2Layout
																												.createSequentialGroup()
																												.addGap(21,
																														21,
																														21)
																												.addGroup(
																														panel2Layout
																																.createParallelGroup()
																																.addComponent(
																																		chkMousekeyChop)
																																.addComponent(
																																		comboHatchet,
																																		GroupLayout.PREFERRED_SIZE,
																																		86,
																																		GroupLayout.PREFERRED_SIZE))))
																				.addGap(0,
																						0,
																						Short.MAX_VALUE))
																.addGroup(
																		panel2Layout
																				.createSequentialGroup()
																				.addComponent(
																						chkPowerchop)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						104,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnChop)))
												.addContainerGap()));
				panel2Layout
						.setVerticalGroup(panel2Layout
								.createParallelGroup()
								.addGroup(
										panel2Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel2Layout
																.createParallelGroup(
																		GroupLayout.Alignment.BASELINE)
																.addComponent(
																		chkPowerchop)
																.addComponent(
																		btnChop))
												.addPreferredGap(
														LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(chkMousekeyChop)
												.addPreferredGap(
														LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(chkHatchetInInv)
												.addPreferredGap(
														LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(
														comboHatchet,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap(51,
														Short.MAX_VALUE)));
			}
			tabbedPane1.addTab("Chopping", panel2);

			{

				btnFish.setText("Start Fishing!");
				btnFish.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btnFishActionPerformed(e);
					}
				});

				comboFishing.setModel(new DefaultComboBoxModel<>(new String[] {
						"Netting", "Baiting" }));

				chkPowerfish.setText("Powerfish");
				chkPowerfish.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						chkPowerfishActionPerformed(e);
					}
				});

				chkMousekeyFish.setText("Mousekey Simulated");
				chkMousekeyFish.setEnabled(false);

				GroupLayout panel3Layout = new GroupLayout(panel3);
				panel3.setLayout(panel3Layout);
				panel3Layout
						.setHorizontalGroup(panel3Layout
								.createParallelGroup()
								.addGroup(
										panel3Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel3Layout
																.createParallelGroup()
																.addGroup(
																		panel3Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel3Layout
																								.createParallelGroup()
																								.addGroup(
																										panel3Layout
																												.createSequentialGroup()
																												.addGap(21,
																														21,
																														21)
																												.addComponent(
																														chkMousekeyFish))
																								.addComponent(
																										chkPowerfish))
																				.addGap(0,
																						0,
																						Short.MAX_VALUE))
																.addGroup(
																		panel3Layout
																				.createSequentialGroup()
																				.addComponent(
																						comboFishing,
																						GroupLayout.PREFERRED_SIZE,
																						71,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						120,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnFish)))
												.addContainerGap()));
				panel3Layout
						.setVerticalGroup(panel3Layout
								.createParallelGroup()
								.addGroup(
										panel3Layout
												.createSequentialGroup()
												.addGroup(
														panel3Layout
																.createParallelGroup()
																.addGroup(
																		panel3Layout
																				.createSequentialGroup()
																				.addGap(23,
																						23,
																						23)
																				.addComponent(
																						comboFishing,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(18,
																						18,
																						18)
																				.addComponent(
																						chkPowerfish)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.UNRELATED)
																				.addComponent(
																						chkMousekeyFish))
																.addGroup(
																		panel3Layout
																				.createSequentialGroup()
																				.addContainerGap()
																				.addComponent(
																						btnFish)))
												.addContainerGap(53,
														Short.MAX_VALUE)));
			}
			tabbedPane1.addTab("Fishing", panel3);

			{

				btnThieve.setText("Start Thieving!");
				btnThieve.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btnThieveActionPerformed(e);
					}
				});

				comboThieve.setModel(new DefaultComboBoxModel<>(new String[] {
						"Seed Stall", "Market Stall", "Master Farmer",
						"Master Gardener" }));
				comboThieve.setToolTipText("Choose thieving option");

				txtFoodId.setToolTipText("Enter the food id");

				label4.setText("Food Id");

				label5.setText("Food Amount");

				spinFoodAmount.setModel(new SpinnerNumberModel(0, null, 25, 1));

				txtEatAt.setToolTipText("Enter the number of lifepoints you want to eat at");

				label6.setText("Eat At");

				label10.setText("Number of LP to eat NOT %");
				label10.setFont(label10.getFont().deriveFont(
						label10.getFont().getStyle() | Font.BOLD));

				GroupLayout panel4Layout = new GroupLayout(panel4);
				panel4.setLayout(panel4Layout);
				panel4Layout
						.setHorizontalGroup(panel4Layout
								.createParallelGroup()
								.addGroup(
										panel4Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel4Layout
																.createParallelGroup()
																.addGroup(
																		panel4Layout
																				.createSequentialGroup()
																				.addComponent(
																						comboThieve,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						79,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnThieve))
																.addGroup(
																		panel4Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel4Layout
																								.createParallelGroup()
																								.addComponent(
																										spinFoodAmount,
																										GroupLayout.PREFERRED_SIZE,
																										47,
																										GroupLayout.PREFERRED_SIZE)
																								.addComponent(
																										label5)
																								.addGroup(
																										panel4Layout
																												.createSequentialGroup()
																												.addGroup(
																														panel4Layout
																																.createParallelGroup()
																																.addComponent(
																																		label4)
																																.addComponent(
																																		txtFoodId,
																																		GroupLayout.PREFERRED_SIZE,
																																		63,
																																		GroupLayout.PREFERRED_SIZE))
																												.addGap(18,
																														18,
																														18)
																												.addGroup(
																														panel4Layout
																																.createParallelGroup()
																																.addComponent(
																																		label6)
																																.addComponent(
																																		txtEatAt,
																																		GroupLayout.PREFERRED_SIZE,
																																		63,
																																		GroupLayout.PREFERRED_SIZE))))
																				.addGap(0,
																						144,
																						Short.MAX_VALUE))
																.addGroup(
																		GroupLayout.Alignment.TRAILING,
																		panel4Layout
																				.createSequentialGroup()
																				.addGap(0,
																						136,
																						Short.MAX_VALUE)
																				.addComponent(
																						label10)))
												.addContainerGap()));
				panel4Layout
						.setVerticalGroup(panel4Layout
								.createParallelGroup()
								.addGroup(
										panel4Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel4Layout
																.createParallelGroup(
																		GroupLayout.Alignment.TRAILING)
																.addComponent(
																		btnThieve)
																.addComponent(
																		comboThieve,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														panel4Layout
																.createParallelGroup()
																.addGroup(
																		panel4Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel4Layout
																								.createParallelGroup(
																										GroupLayout.Alignment.TRAILING)
																								.addGroup(
																										panel4Layout
																												.createSequentialGroup()
																												.addComponent(
																														label4)
																												.addPreferredGap(
																														LayoutStyle.ComponentPlacement.RELATED)
																												.addComponent(
																														txtFoodId,
																														GroupLayout.PREFERRED_SIZE,
																														GroupLayout.DEFAULT_SIZE,
																														GroupLayout.PREFERRED_SIZE))
																								.addGroup(
																										panel4Layout
																												.createSequentialGroup()
																												.addComponent(
																														label6)
																												.addGap(6,
																														6,
																														6)
																												.addComponent(
																														txtEatAt,
																														GroupLayout.PREFERRED_SIZE,
																														GroupLayout.DEFAULT_SIZE,
																														GroupLayout.PREFERRED_SIZE)))
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						label5)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						spinFoodAmount,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(0,
																						32,
																						Short.MAX_VALUE))
																.addGroup(
																		GroupLayout.Alignment.TRAILING,
																		panel4Layout
																				.createSequentialGroup()
																				.addGap(0,
																						104,
																						Short.MAX_VALUE)
																				.addComponent(
																						label10)))
												.addContainerGap()));
			}
			tabbedPane1.addTab("Thieving", panel4);

			{

				label3.setText("Bonfires only");
				label3.setFont(label3.getFont().deriveFont(
						label3.getFont().getStyle() | Font.BOLD));

				comboBonfire.setModel(new DefaultComboBoxModel<>(new String[] {
						"Normal Log", "Oak Log", "Willow Log", "Maple Log",
						"Teak Log", "Yew Log", "Magic Log" }));
				comboBonfire.setToolTipText("Type of log to burn");

				btnBonfire.setText("Start Burning!");
				btnBonfire.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btnBonfireActionPerformed(e);
					}
				});

				chkFireSpirit.setText("Fire Spirit");

				GroupLayout panel5Layout = new GroupLayout(panel5);
				panel5.setLayout(panel5Layout);
				panel5Layout
						.setHorizontalGroup(panel5Layout
								.createParallelGroup()
								.addGroup(
										panel5Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel5Layout
																.createParallelGroup()
																.addGroup(
																		GroupLayout.Alignment.TRAILING,
																		panel5Layout
																				.createSequentialGroup()
																				.addComponent(
																						comboBonfire,
																						GroupLayout.PREFERRED_SIZE,
																						91,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						98,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnBonfire))
																.addGroup(
																		panel5Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel5Layout
																								.createParallelGroup()
																								.addComponent(
																										chkFireSpirit,
																										GroupLayout.PREFERRED_SIZE,
																										91,
																										GroupLayout.PREFERRED_SIZE)
																								.addGroup(
																										panel5Layout
																												.createSequentialGroup()
																												.addGap(104,
																														104,
																														104)
																												.addComponent(
																														label3)))
																				.addGap(0,
																						111,
																						Short.MAX_VALUE)))
												.addContainerGap()));
				panel5Layout
						.setVerticalGroup(panel5Layout
								.createParallelGroup()
								.addGroup(
										panel5Layout
												.createSequentialGroup()
												.addGroup(
														panel5Layout
																.createParallelGroup()
																.addGroup(
																		panel5Layout
																				.createSequentialGroup()
																				.addContainerGap()
																				.addComponent(
																						btnBonfire))
																.addGroup(
																		panel5Layout
																				.createSequentialGroup()
																				.addGap(32,
																						32,
																						32)
																				.addComponent(
																						comboBonfire,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)))
												.addGap(18, 18, 18)
												.addComponent(chkFireSpirit)
												.addPreferredGap(
														LayoutStyle.ComponentPlacement.RELATED,
														45, Short.MAX_VALUE)
												.addComponent(label3)
												.addContainerGap()));
			}
			tabbedPane1.addTab("Firemaking", panel5);

			{

				btnCraft.setText("Start Crafting!");
				btnCraft.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btnCraftActionPerformed(e);
					}
				});

				chkPottery.setText("Make Pottery");
				chkPottery.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						chkPotteryItemStateChanged(e);
					}
				});

				comboPottery.setModel(new DefaultComboBoxModel<>(new String[] {
						"Pot", "Pie Dish", "Bowl", "Plant Pot" }));
				comboPottery.setEnabled(false);

				chkGems.setText("Cut Gems");
				chkGems.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						chkGemsItemStateChanged(e);
					}
				});

				comboGems.setEnabled(false);
				comboGems.setModel(new DefaultComboBoxModel<>(new String[] {
						"Opal", "Jade", "Sapphire", "Emerald", "Ruby",
						"Diamond" }));

				chkLeather.setText("Leather");
				chkLeather.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						chkLeatherItemStateChanged(e);
					}
				});

				comboLeather.setModel(new DefaultComboBoxModel<>(new String[] {
						"Gloves", "Boots", "Cowl", "Vambraces", "Body",
						"Chaps", "Hardleather Body" }));
				comboLeather.setEnabled(false);

				GroupLayout panel7Layout = new GroupLayout(panel7);
				panel7.setLayout(panel7Layout);
				panel7Layout
						.setHorizontalGroup(panel7Layout
								.createParallelGroup()
								.addGroup(
										panel7Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel7Layout
																.createParallelGroup()
																.addGroup(
																		panel7Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel7Layout
																								.createParallelGroup()
																								.addComponent(
																										chkGems)
																								.addGroup(
																										panel7Layout
																												.createSequentialGroup()
																												.addGap(21,
																														21,
																														21)
																												.addComponent(
																														comboGems,
																														GroupLayout.PREFERRED_SIZE,
																														GroupLayout.DEFAULT_SIZE,
																														GroupLayout.PREFERRED_SIZE)))
																				.addGap(50,
																						50,
																						50)
																				.addGroup(
																						panel7Layout
																								.createParallelGroup()
																								.addGroup(
																										panel7Layout
																												.createSequentialGroup()
																												.addGap(0,
																														51,
																														Short.MAX_VALUE)
																												.addComponent(
																														btnCraft))
																								.addGroup(
																										panel7Layout
																												.createSequentialGroup()
																												.addGroup(
																														panel7Layout
																																.createParallelGroup()
																																.addGroup(
																																		panel7Layout
																																				.createSequentialGroup()
																																				.addGap(21,
																																						21,
																																						21)
																																				.addComponent(
																																						comboLeather,
																																						GroupLayout.PREFERRED_SIZE,
																																						82,
																																						GroupLayout.PREFERRED_SIZE))
																																.addComponent(
																																		chkLeather))
																												.addGap(0,
																														51,
																														Short.MAX_VALUE))))
																.addGroup(
																		panel7Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel7Layout
																								.createParallelGroup()
																								.addGroup(
																										panel7Layout
																												.createSequentialGroup()
																												.addGap(21,
																														21,
																														21)
																												.addComponent(
																														comboPottery,
																														GroupLayout.PREFERRED_SIZE,
																														82,
																														GroupLayout.PREFERRED_SIZE))
																								.addComponent(
																										chkPottery))
																				.addGap(0,
																						189,
																						Short.MAX_VALUE)))
												.addContainerGap()));
				panel7Layout
						.setVerticalGroup(panel7Layout
								.createParallelGroup()
								.addGroup(
										panel7Layout
												.createSequentialGroup()
												.addGroup(
														panel7Layout
																.createParallelGroup()
																.addGroup(
																		panel7Layout
																				.createSequentialGroup()
																				.addGap(26,
																						26,
																						26)
																				.addComponent(
																						chkPottery)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						comboPottery,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(23,
																						23,
																						23)
																				.addComponent(
																						chkGems)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						comboGems,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))
																.addGroup(
																		panel7Layout
																				.createSequentialGroup()
																				.addContainerGap()
																				.addComponent(
																						btnCraft)
																				.addGap(60,
																						60,
																						60)
																				.addComponent(
																						chkLeather)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						comboLeather,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)))
												.addContainerGap(24,
														Short.MAX_VALUE)));
			}
			tabbedPane1.addTab("Crafting", panel7);

			{

				btnFletch.setText("Start Fletching!");
				btnFletch.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btnFletchActionPerformed(e);
					}
				});

				label7.setText("Select type of log:");

				comboLogType.setModel(new DefaultComboBoxModel<>(new String[] {
						"Normal Log", "Oak Log", "Willow Log", "Maple Log",
						"Yew Log", "Magic Log" }));

				label8.setText("Select type of bow:");

				label9.setText("Select action:");

				radShortbow.setText("Shortbow");
				radShortbow.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						radShortbowActionPerformed(e);
					}
				});

				radLongbow.setText("Shieldbow");
				radLongbow.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						radLongbowActionPerformed(e);
					}
				});

				radCut.setText("Cutting");
				radCut.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						radCutActionPerformed(e);
					}
				});

				radString.setText("Stringing");
				radString.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						radStringActionPerformed(e);
					}
				});

				GroupLayout panel9Layout = new GroupLayout(panel9);
				panel9.setLayout(panel9Layout);
				panel9Layout
						.setHorizontalGroup(panel9Layout
								.createParallelGroup()
								.addGroup(
										panel9Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel9Layout
																.createParallelGroup()
																.addGroup(
																		panel9Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel9Layout
																								.createParallelGroup()
																								.addComponent(
																										label7)
																								.addComponent(
																										comboLogType,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE))
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						93,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnFletch))
																.addGroup(
																		panel9Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel9Layout
																								.createParallelGroup()
																								.addComponent(
																										label8)
																								.addGroup(
																										panel9Layout
																												.createSequentialGroup()
																												.addGap(10,
																														10,
																														10)
																												.addGroup(
																														panel9Layout
																																.createParallelGroup()
																																.addComponent(
																																		radLongbow)
																																.addComponent(
																																		radShortbow))))
																				.addGap(53,
																						53,
																						53)
																				.addGroup(
																						panel9Layout
																								.createParallelGroup()
																								.addComponent(
																										label9)
																								.addGroup(
																										panel9Layout
																												.createSequentialGroup()
																												.addGap(10,
																														10,
																														10)
																												.addGroup(
																														panel9Layout
																																.createParallelGroup()
																																.addComponent(
																																		radCut)
																																.addComponent(
																																		radString))))
																				.addGap(0,
																						64,
																						Short.MAX_VALUE)))
												.addContainerGap()));
				panel9Layout
						.setVerticalGroup(panel9Layout
								.createParallelGroup()
								.addGroup(
										panel9Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel9Layout
																.createParallelGroup()
																.addComponent(
																		btnFletch)
																.addGroup(
																		panel9Layout
																				.createSequentialGroup()
																				.addComponent(
																						label7)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						comboLogType,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)))
												.addGap(27, 27, 27)
												.addGroup(
														panel9Layout
																.createParallelGroup(
																		GroupLayout.Alignment.BASELINE)
																.addComponent(
																		label8)
																.addComponent(
																		label9))
												.addPreferredGap(
														LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(
														panel9Layout
																.createParallelGroup()
																.addGroup(
																		panel9Layout
																				.createSequentialGroup()
																				.addComponent(
																						radCut)
																				.addGap(0,
																						0,
																						0)
																				.addComponent(
																						radString))
																.addGroup(
																		panel9Layout
																				.createSequentialGroup()
																				.addComponent(
																						radShortbow)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						radLongbow)))
												.addContainerGap(23,
														Short.MAX_VALUE)));
			}
			tabbedPane1.addTab("Fletching", panel9);

			{

				btnMoney.setText("Start Profitting!");
				btnMoney.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btnMoneyActionPerformed(e);
					}
				});

				radSoftClay.setText("Soften Clay");
				radSoftClay.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						radSoftClayActionPerformed(e);
					}
				});

				radVials.setText("Fill Empty vials");
				radVials.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						radVialsActionPerformed(e);
					}
				});

				radFillBasket.setText("Fill baskets");
				radFillBasket.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						radFillBasketActionPerformed(e);
					}
				});

				comboFill.setModel(new DefaultComboBoxModel<>(new String[] {
						"Strawberries", "Oranges", "Apples", "Bananas" }));
				comboFill.setEnabled(false);

				GroupLayout panel8Layout = new GroupLayout(panel8);
				panel8.setLayout(panel8Layout);
				panel8Layout
						.setHorizontalGroup(panel8Layout
								.createParallelGroup()
								.addGroup(
										panel8Layout
												.createSequentialGroup()
												.addGap(20, 20, 20)
												.addGroup(
														panel8Layout
																.createParallelGroup()
																.addGroup(
																		panel8Layout
																				.createSequentialGroup()
																				.addComponent(
																						radFillBasket)
																				.addGap(77,
																						77,
																						77)
																				.addComponent(
																						comboFill,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(0,
																						38,
																						Short.MAX_VALUE))
																.addGroup(
																		panel8Layout
																				.createSequentialGroup()
																				.addGroup(
																						panel8Layout
																								.createParallelGroup()
																								.addComponent(
																										radVials)
																								.addComponent(
																										radSoftClay))
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED,
																						76,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnMoney)))
												.addContainerGap()));
				panel8Layout
						.setVerticalGroup(panel8Layout
								.createParallelGroup()
								.addGroup(
										panel8Layout
												.createSequentialGroup()
												.addContainerGap()
												.addGroup(
														panel8Layout
																.createParallelGroup()
																.addGroup(
																		panel8Layout
																				.createSequentialGroup()
																				.addComponent(
																						radSoftClay)
																				.addGap(18,
																						18,
																						18)
																				.addComponent(
																						radVials))
																.addComponent(
																		btnMoney))
												.addGap(18, 18, 18)
												.addGroup(
														panel8Layout
																.createParallelGroup(
																		GroupLayout.Alignment.BASELINE)
																.addComponent(
																		radFillBasket)
																.addComponent(
																		comboFill,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addContainerGap(47,
														Short.MAX_VALUE)));
			}
			tabbedPane1.addTab("Money Making", panel8);

		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(contentPaneLayout
				.createParallelGroup().addGroup(
						contentPaneLayout
								.createSequentialGroup()
								.addComponent(tabbedPane1,
										GroupLayout.PREFERRED_SIZE, 313,
										GroupLayout.PREFERRED_SIZE)
								.addGap(0, 1, Short.MAX_VALUE)));
		contentPaneLayout.setVerticalGroup(contentPaneLayout
				.createParallelGroup().addGroup(
						contentPaneLayout
								.createSequentialGroup()
								.addComponent(tabbedPane1,
										GroupLayout.PREFERRED_SIZE, 207,
										GroupLayout.PREFERRED_SIZE)
								.addGap(0, 2, Short.MAX_VALUE)));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - hiiiii jacobfromhf
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JScrollPane scrollPane1;
	private JList<String> llistFruit;
	private JButton btnSpawn;
	private JComboBox<String> comboSpawn;
	private JLabel label1;
	private JLabel label2;
	private JPanel panel2;
	private JCheckBox chkPowerchop;
	private JCheckBox chkMousekeyChop;
	private JCheckBox chkHatchetInInv;
	private JComboBox<String> comboHatchet;
	private JButton btnChop;
	private JPanel panel3;
	private JButton btnFish;
	private JComboBox<String> comboFishing;
	private JCheckBox chkPowerfish;
	private JCheckBox chkMousekeyFish;
	private JPanel panel4;
	private JButton btnThieve;
	private JComboBox<String> comboThieve;
	private JTextField txtFoodId;
	private JLabel label4;
	private JLabel label5;
	private JSpinner spinFoodAmount;
	private JTextField txtEatAt;
	private JLabel label6;
	private JLabel label10;
	private JPanel panel5;
	private JLabel label3;
	private JComboBox<String> comboBonfire;
	private JButton btnBonfire;
	private JCheckBox chkFireSpirit;
	private JPanel panel7;
	private JButton btnCraft;
	private JCheckBox chkPottery;
	private JComboBox<String> comboPottery;
	private JCheckBox chkGems;
	private JComboBox<String> comboGems;
	private JCheckBox chkLeather;
	private JComboBox<String> comboLeather;
	private JPanel panel9;
	private JButton btnFletch;
	private JLabel label7;
	private JComboBox<String> comboLogType;
	private JLabel label8;
	private JLabel label9;
	private JRadioButton radShortbow;
	private JRadioButton radLongbow;
	private JRadioButton radCut;
	private JRadioButton radString;
	private JPanel panel8;
	private JButton btnMoney;
	private JRadioButton radSoftClay;
	private JRadioButton radVials;
	private JRadioButton radFillBasket;
	private JComboBox<String> comboFill;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
