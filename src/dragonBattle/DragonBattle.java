//Dragon Battle by Joshua Schoerverth
//Program simply designed to simulate a RPG battle with an famous monster, a dragon.
//Starts off by asking for a name for the hero and drops the user off in a main hub, called the town.
//There they can prepare for battle, save, or load the previous adventure.
//When ready they can fight the dragon. At first the dragon starts off easy, but with every win it gets stronger.
//That's when the user needs to start to strategize with spells, items, and skills.
//Program continuously being upgraded with new features!

package dragonBattle;

import javax.swing.*;      
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DragonBattle extends JFrame
{
	public static String your_name;
	public int defense;
	public int boost;
	public boolean invincible = false;
	public int numberofbattles = 1;
	
	public boolean poisened = false;
	public JLabel poison = new JLabel("Poisoned");
	public boolean paralyzed = false;
	public JLabel paralysis = new JLabel("Paralyzed");
	public JLabel atkup = new JLabel("^Atk Up^");
	
	public JLabel name = new JLabel(your_name);
	public static int heroHP = 500;
	public static int heroMP = 200;
	public JLabel health = new JLabel("HP: " + heroHP + "/500");
	public JLabel magic = new JLabel("MP: " + heroMP + "/200");
	public int gold = 100;
	public JLabel money = new JLabel("G: " + gold);
	
	public JLabel name2 = new JLabel("GREAT DRAGON");
	public static int dragonHP = 1000;
	public JLabel dragonstat = new JLabel("HP: " + dragonHP);
	
	public static int turnNum = 0;
	public int tempTurn = 0;
	public JLabel turns = new JLabel("Turns: " + turnNum);
	
	public String spellList[] = {"Heal 2MP","Fire 3MP","Boost 5MP","Recover 7MP","Flames 6MP","Defense 5MP",
								 "Inferno 10MP","FullHeal 12MP","Back"};
	public JLabel spelltag = new JLabel("Select a spell...");
	public JComboBox<String> spells = new JComboBox<String>(spellList);
	public JButton spellselect = new JButton("Select");
	public JButton spellinfo = new JButton("Info");
	
	public int herbs = 10; public int antidotes = 10; public int unnumbs = 10; public int potions = 10;
	public String bag[] = {"Herb " + herbs,"Antidote " + antidotes,"UnNumb " + unnumbs,"Potion " + potions,"Back"};
	public JLabel bagtag = new JLabel("Select an item...");
	public JComboBox<String> items = new JComboBox<String>(bag);
	public JButton itemselect = new JButton("Select");
	public JButton iteminfo = new JButton("Info");
	
	public String skillList[] = {"Dragon Slash 2MP","Multi Slash 5MP","Final Slash 10MP","Back"};
	public JLabel skilltag = new JLabel("Select a skill...");
	public JComboBox<String> skills = new JComboBox<String>(skillList);
	public JButton skillselect = new JButton("Select");
	public JButton skillinfo = new JButton("Info");
	
	public JLabel picture = new JLabel(new ImageIcon("images.jpeg"));
	public JTextArea field = new JTextArea();
	
	public JLabel town = new JLabel("~The Peaceful Village~");
	public JButton sleep = new JButton("Rest at Inn. (10G)");
	public JButton battle = new JButton("Fight the Dragon!");
	public JButton exit = new JButton("Close...");
	public JButton save = new JButton("Save");
	public JButton load = new JButton("Load");
	public JButton snq = new JButton("Save & Quit");
	public JButton shop = new JButton("Shop");
	
	public JButton attack = new JButton("Attack");
	public JButton defend = new JButton("Defend");
	public JButton spell = new JButton("Spells");
	public JButton item = new JButton("Bag");
	public JButton skill = new JButton("Skills");
	public JButton run = new JButton("Run");
	public JButton help = new JButton("?");
	
	public JLabel theshop = new JLabel("~The Shop~");
	public JLabel herb = new JLabel("Herb:");			public JLabel herbprice = new JLabel("x5G");
	public JLabel antidote = new JLabel("Antidote:");	public JLabel antidoteprice = new JLabel("x10G");
	public JLabel unnumb = new JLabel("UnNumb:");		public JLabel unnumbprice = new JLabel("x10G");
	public JLabel potion = new JLabel("Potion:");		public JLabel potionprice = new JLabel("x15G");
	public JLabel qty = new JLabel("Quantity");
	public JTextArea herbarea = new JTextArea("0");
	public JTextArea antidotearea = new JTextArea("0");
	public JTextArea unnumbarea = new JTextArea("0");
	public JTextArea potionarea = new JTextArea("0");
	public JButton buy = new JButton("Buy");
	public JButton exitshop = new JButton("Leave");
		
	public DragonBattle()
	{
		setLayout(null);
		
		town.setBounds(0,0,1000,100);
		town.setVisible(true);
		add(town);
		
		theshop.setBounds(0,0,100,100);
		theshop.setVisible(false);
		add(theshop);
		
		sleep.setBounds(0, 100, 150, 50);
		sleep.setBackground(Color.green);
		sleep.setVisible(true);
		add(sleep);
		sleep.addActionListener(new SleepListener());
		
		shop.setBounds(150,100,150,50);
		shop.setBackground(Color.YELLOW);
		shop.setVisible(true);
		add(shop);
		shop.addActionListener(new ShopListener());
		
		battle.setBounds(25, 175, 250, 50);
		battle.setBackground(Color.red);
		battle.setVisible(true);
		add(battle);
		battle.addActionListener(new BattleListener());
		
		exit.setBounds(0, 250, 150, 50);
		exit.setBackground(Color.GRAY);
		exit.setVisible(true);
		add(exit);
		exit.addActionListener(new ExitListener());
		
		snq.setBounds(150,250,150,50);
		snq.setBackground(Color.LIGHT_GRAY);
		snq.setVisible(true);
		add(snq);
		snq.addActionListener(new SNQListener());
		
		save.setBounds(325,110,100,50);
		save.setVisible(true);
		add(save);
		save.addActionListener(new SaveListener());
		
		load.setBounds(325,160,100,50);
		load.setVisible(true);
		add(load);
		load.addActionListener(new LoadListener());
		
		field.setBounds(300, 250, 250, 100);
		field.setVisible(true);
	    field.setLineWrap(true);
	    field.setWrapStyleWord(true);
	    field.append("Welcome " + your_name + "!\nIn the village you can take your\ntime, or if your ready...\nFight the dragon!");
		add(field);
		
		turns.setBounds(0,0,100,100);
		turns.setVisible(false);
		add(turns);
		
		name.setBounds(300,0,1000,100);
		add(name);
		health.setBounds(300,10,100,100);
		add(health);
		magic.setBounds(300,20,100,100);
		add(magic);
		money.setBounds(300,30,1000,100);
		add(money);
		
		poison.setBounds(300, 40, 100, 100);
		poison.setForeground(Color.BLUE);
		poison.setVisible(false);
		add(poison);
		paralysis.setBounds(300,50,100,100);
		paralysis.setForeground(Color.DARK_GRAY);
		paralysis.setVisible(false);
		add(paralysis);
		
		atkup.setBounds(355,40,100,100);
		atkup.setForeground(Color.red);
		atkup.setVisible(false);
		add(atkup);
		
		name2.setBounds(0,10,100,100);
		name2.setVisible(false);
		add(name2);
		dragonstat.setBounds(0,20,300,100);
		dragonstat.setVisible(false);
		add(dragonstat);
		
		picture.setBounds(50,50,300,200);
		picture.setVisible(false);
		add(picture);
		
		attack.setBounds(0,250,100,50);
		attack.setVisible(false);
		add(attack);
		attack.addActionListener(new AttackListener());
		
		defend.setBounds(100,250,100,50);
		defend.setVisible(false);
		add(defend);
		defend.addActionListener(new DefendListener());
		
		spell.setBounds(0,300,100,50);
		spell.setVisible(true);
		add(spell);
		spell.addActionListener(new SpellListener());
		
		item.setBounds(100,300,100,50);
		item.setVisible(true);
		add(item);
		item.addActionListener(new ItemListener());
		
		skill.setBounds(200,250,100,50);
		skill.setVisible(false);
		add(skill);
		skill.addActionListener(new SkillListener());
		
		run.setBounds(200,300,100,50);
		run.setVisible(false);
		add(run);
		run.addActionListener(new RunListener());
		
		spelltag.setBounds(300,62,100,100);
		spelltag.setVisible(false);
		add(spelltag);
		spells.setBounds(300,120,100,25);
		spells.setVisible(false);
		add(spells);
		spellselect.setBounds(300,145,100,25);
		spellselect.setVisible(false);
		add(spellselect);
		spellselect.addActionListener(new SpellSelectListener());
		spellinfo.setBounds(300,170,100,25);
		spellinfo.setVisible(false);
		add(spellinfo);
		spellinfo.addActionListener(new SpellInfoListener());
		
		bagtag.setBounds(300,62,100,100);
		bagtag.setVisible(false);
		add(bagtag);
		items.setBounds(300,120,100,25);
		items.setVisible(false);
		add(items);
		itemselect.setBounds(300,145,100,25);
		itemselect.setVisible(false);
		add(itemselect);
		itemselect.addActionListener(new ItemSelectListener());
		iteminfo.setBounds(300,170,100,25);
		iteminfo.setVisible(false);
		add(iteminfo);
		iteminfo.addActionListener(new ItemInfoListener());
		
		skilltag.setBounds(300,62,100,100);
		skilltag.setVisible(false);
		add(skilltag);
		skills.setBounds(300,120,100,25);
		skills.setVisible(false);
		add(skills);
		skillselect.setBounds(300,145,100,25);
		skillselect.setVisible(false);
		add(skillselect);
		skillselect.addActionListener(new SkillSelectListener());
		skillinfo.setBounds(300,170,100,25);
		skillinfo.setVisible(false);
		add(skillinfo);
		skillinfo.addActionListener(new SkillInfoListener());
		
		help.setBounds(435,210,50,40);
		help.setVisible(true);
		add(help);
		help.addActionListener(new HelpListener());
		
		herb.setBounds(100,50,100,100);
		herb.setVisible(false);
		add(herb);
		herbarea.setBounds(155,90,95,20);
		herbarea.setVisible(false);
	    herbarea.setLineWrap(true);
	    herbarea.setWrapStyleWord(true);
	    add(herbarea);
		herbprice.setBounds(250,50,100,100);
		herbprice.setVisible(false);
		add(herbprice);
		
		antidote.setBounds(100,80,100,100);
		antidote.setVisible(false);
		add(antidote);
		antidotearea.setBounds(155,120,95,20);
		antidotearea.setVisible(false);
		antidotearea.setLineWrap(true);
		antidotearea.setWrapStyleWord(true);
	    add(antidotearea);
		antidoteprice.setBounds(250,80,100,100);
		antidoteprice.setVisible(false);
		add(antidoteprice);
		
		unnumb.setBounds(100,110,100,100);
		unnumb.setVisible(false);
		add(unnumb);
		unnumbarea.setBounds(155,150,95,20);
		unnumbarea.setVisible(false);
		unnumbarea.setLineWrap(true);
		unnumbarea.setWrapStyleWord(true);
	    add(unnumbarea);
		unnumbprice.setBounds(250,110,100,100);
		unnumbprice.setVisible(false);
		add(unnumbprice);
		
		potion.setBounds(100,140,100,100);
		potion.setVisible(false);
		add(potion);
		potionarea.setBounds(155,180,95,20);
		potionarea.setVisible(false);
		potionarea.setLineWrap(true);
		potionarea.setWrapStyleWord(true);
	    add(potionarea);
	    potionprice.setBounds(250,140,100,100);
	    potionprice.setVisible(false);
		add(potionprice);
		
		exitshop.setBounds(200,200,100,50);
		exitshop.setVisible(false);
		add(exitshop);
		exitshop.addActionListener(new CloseShopListener());
		
		buy.setBounds(100,200,100,50);
		buy.setVisible(false);
		add(buy);
		buy.addActionListener(new BuyListener());
		
		qty.setBounds(175,30,100,100);
		qty.setVisible(false);
		add(qty);
	}

	public static void main(String[] args)
	{
		your_name = JOptionPane.showInputDialog("Enter a name for your Hero: ");
		if (your_name == null || your_name.length() <= 0)
		{
			your_name = "Hero";
		}
		DragonBattle frame = new DragonBattle();
		frame.setSize(500,400);
		frame.setTitle("Dragon Battle");
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.cyan);
	}
	
	public void openTown()
	{
		getContentPane().setBackground(Color.cyan);
		town.setVisible(true);
		sleep.setVisible(true);
		battle.setVisible(true);
		exit.setVisible(true);
		save.setVisible(true);
		load.setVisible(true);
		snq.setVisible(true);
		shop.setVisible(true);
	}
	
	public void leaveTown()
	{
		town.setVisible(false);
		sleep.setVisible(false);
		battle.setVisible(false);
		exit.setVisible(false);
		save.setVisible(false);
		load.setVisible(false);
		snq.setVisible(false);
		shop.setVisible(false);
	}
	
	public void hideBattle()
	{
		attack.setVisible(false);
		defend.setVisible(false);
		run.setVisible(false);
		skill.setVisible(false);
		picture.setVisible(false);
		dragonstat.setVisible(false);
		name2.setVisible(false);
		turns.setVisible(false);
		boost = 0;
		atkup.setVisible(false);
		invincible = false;
		defense = 0;
	}
	
	public void startBattle()
	{
		getContentPane().setBackground(Color.ORANGE);
		attack.setVisible(true);
		defend.setVisible(true);
		run.setVisible(true);
		skill.setVisible(true);
		picture.setVisible(true);
		turns.setVisible(true);
		turns.setText("Turns: "+ turnNum);
		name2.setVisible(true);
		if(turnNum == 0)
		{
			dragonHP = 500 * numberofbattles;
		}
		dragonstat.setVisible(true);
		dragonstat.setText("HP: " + dragonHP);
	}
	
	public void openShop()
	{
		theshop.setVisible(true);
		herb.setVisible(true);
		herbarea.setVisible(true);
		herbprice.setVisible(true);
		antidote.setVisible(true);
		antidotearea.setVisible(true);
		antidoteprice.setVisible(true);
		unnumb.setVisible(true);
		unnumbarea.setVisible(true);
		unnumbprice.setVisible(true);
		potion.setVisible(true);
		potionarea.setVisible(true);
		potionprice.setVisible(true);
		buy.setVisible(true);
		exitshop.setVisible(true);
		qty.setVisible(true);
	}
	
	public void closeShop()
	{
		theshop.setVisible(false);
		herb.setVisible(false);
		herbarea.setVisible(false);
		herbprice.setVisible(false);
		antidote.setVisible(false);
		antidotearea.setVisible(false);
		antidoteprice.setVisible(false);
		unnumb.setVisible(false);
		unnumbarea.setVisible(false);
		unnumbprice.setVisible(false);
		potion.setVisible(false);
		potionarea.setVisible(false);
		potionprice.setVisible(false);
		buy.setVisible(false);
		exitshop.setVisible(false);
		item.setVisible(true);
		spell.setVisible(true);
		qty.setVisible(false);
	}
	
	public void hideSpell()
	{
		spelltag.setVisible(false);
		spells.setVisible(false);
		spellselect.setVisible(false);
		spellinfo.setVisible(false);
	}
	
	public void hideBag()
	{
		bagtag.setVisible(false);
		items.setVisible(false);
		itemselect.setVisible(false);
		iteminfo.setVisible(false);
	}
	
	public void hideSkill()
	{
		skilltag.setVisible(false);
		skills.setVisible(false);
		skillselect.setVisible(false);
		skillinfo.setVisible(false);
	}
	
	public void checkDragonHP()
	{
		if(dragonHP <= 0)
		{
			dragonstat.setText("HP: 0");
			getContentPane().setBackground(Color.YELLOW);
			picture.setVisible(false);
			JOptionPane.showMessageDialog(null, "Great Dragon is slain!");
			int rndm = (int)(Math.random()*1);
			if(rndm == 0)
			{
				JOptionPane.showMessageDialog(null, "Great Dragon has a chest.\n" + your_name + " opened it.\n");
				int treasure = 100 * numberofbattles;
				JOptionPane.showMessageDialog(null, your_name + " found " + treasure + " Gold!");
				gold += treasure;
				money.setText("G: " + gold);
				turnNum = 0;
			}
			numberofbattles++;
			hideBattle();
			openTown();
		}
	}
	
	public void heroCheckHP()
	{
		if(heroHP <= 0)
		{
			health.setText("HP: 0/300");
			getContentPane().setBackground(Color.BLACK);
			JOptionPane.showMessageDialog(null, your_name + " is defeated!");
			if (heroHP <= 0)
			{
				heroHP = 1;
				health.setText("HP: " + heroHP + "/300");
				field.setText("");
				field.append("You have been defated...\nBut don't give up!");
			}
			hideBattle();
			openTown();
		}
	}
	
	public void dragonTurn()
	{
		int action = (int)(Math.random()*10);
		if(action >= 0 && action <= 3)
		{
			field.append("Great Dragon attacks!" + "\n");
			if (invincible == false)
			{
				int damage = (int)((Math.random()*10)+90);
				damage -= defense;
				if(damage > 0)
				{
					heroHP -= damage;
					field.append(your_name + " takes " + damage + " damage!" + "\n");
					health.setText("HP: " + heroHP + "/500");
				}
				else
				{
					field.append("Miss..." + your_name + " takes no damage." + "\n");
				}
			}
			else 
			{
				field.append("But was deflected!\n");
				invincible = false;
			}
		}
		else if (action == 4)
		{
			field.append("Great Dragon blasts out flames!" + "\n");
			if (invincible == false)
			{
				int damage = (int)((Math.random()*10)+100);
				heroHP -= damage;
				health.setText("HP: " + heroHP + "/500");
				field.append(your_name + " takes " + damage + " damage!" + "\n");
			}
			else 
			{
				field.append("But was deflected!\n");
				invincible = false;
			}
		}
		else if (action == 5)
		{
			field.append("The Great Dragon let's out a roar!\n" + your_name + " is paralyzed on the spot!\n");
			paralysis.setVisible(true);
			paralyzed = true;
		}
		else if (action >= 6 && action <= 8)
		{
			field.append("Great Dragon attacks with its tail!" + "\n");
			if(invincible == false)
			{
				int damage = (int)((Math.random()*10)+50);
				damage -= defense;
				if(damage > 0)
				{
					heroHP -= damage;
					field.append(your_name + " takes " + damage + " damage!" + "\n");
					health.setText("HP: " + heroHP + "/500");			}
				else
				{
					field.append("Miss..." + your_name + " takes no damage." + "\n");
				}
			}
			else 
			{
				field.append("But was deflected!\n");
				invincible = false;
			}
		}
		else if(action > 8 && action <= 10)
		{
			field.append("Great Dragon bites with\npoison fangs!\n");
			if(invincible == false)
			{
				int damage = (int)((Math.random()*10)+20);
				heroHP -= damage;
				health.setText("HP: " + heroHP + "/500");
				poisened = true;
				poison.setVisible(true);
				field.append(your_name + " takes " + damage + " damage!\n" + your_name + " is poisoned!\n");
			}
			else
			{
				field.append("But it was deflected!\n");
				invincible = false;
			}
		}
		defense = 0;
		if(poisened == true)
		{
			heroHP -= 20;
			field.append(your_name + " suffers poison!\n20 damage!\n");
			health.setText("HP: " + heroHP + "/500");
		}
		if((turnNum - tempTurn) == 4)
		{
			boost = 0;
			field.append(your_name + "'s attack returns to normal.\n");
			atkup.setVisible(false);
		}
		turnNum++;
		turns.setText("Turns: " + turnNum);
		heroCheckHP();
	}
	
	class AttackListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			hideSpell();
			hideBag();
			hideSkill();
			
			field.setText("");
			field.append(your_name + " attacks!" + "\n");
			if (paralyzed == false)
			{
				int damage = (int)((Math.random()*10)+50) + boost;
				int crit = (int)(Math.random()*5);
				if (crit == 0)
				{
					field.append("Critical Hit!!!\n");
					damage *= 2;
				}
				dragonHP -= damage;
				dragonstat.setText("HP: " + dragonHP);
				field.append("Great Dragon takes " + damage + " damage!" + "\n");
				checkDragonHP();
			}
			else
			{
				field.append("But cannot move!\n");
			}
			if(dragonHP > 0)
			{
				dragonTurn();
			}
		}
	}
	
	class DefendListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			hideSpell();
			hideBag();
			hideSkill();
			
			field.setText("");
			field.append(your_name + " defends!" + "\n");
			defense = 50;
			dragonTurn();
		}
	}
	
	class SpellListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			field.append("Select a spell or \nselect back to exit." + "\n");
			hideSpell();
			hideBag();
			hideSkill();
			spells.setVisible(true);
			spelltag.setVisible(true);
			spellselect.setVisible(true);
			spellinfo.setVisible(true);
			save.setVisible(false);
			load.setVisible(false);
		}
	}
	
	class SpellSelectListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			String selected = (String)spells.getSelectedItem();
			if (selected == "Heal 2MP")
			{
				field.append(your_name + " casted Heal!\n");
				if (heroMP >= 2)
				{
					heroHP += 30;
					heroMP -= 2;
					if(heroHP > 500)
					{
						heroHP = 500;
					}
					health.setText("HP: " + heroHP + "/500");
					field.append(your_name + " healed 30 HP!\n");
				}
				else
				{
					field.append("But did not have enough MP!\n");
				}
			}
			else if (selected == "Fire 3MP" && town.isVisible() == false)
			{
				field.append(your_name + " casts Fire!\n");
				if(heroMP >= 3)
				{
					heroMP -= 2;
					dragonHP -= 20;
					field.append("Great Dragon takes 20 damage!\n");
					dragonstat.setText("HP: " + dragonHP);
				}
				else
				{
					field.append("But did not have enough MP!\n");
				}
			}
			else if (selected == "Boost 5MP" && town.isVisible() == false)
			{
				field.append(your_name + " casts Boost!\n");
				if (heroMP >= 5)
				{
					heroMP -= 5;
					boost += 20;
					atkup.setVisible(true);
					field.append(your_name + "'s attacks increases!\n");
					tempTurn = turnNum;
				}
				else
				{
					field.append("But did not have enough MP!\n");
				}
			}
			else if (selected == "Recover 7MP")
			{
				field.append(your_name + " casted Recover!\n");
				if (heroMP >= 7)
				{
					heroHP += 80;
					heroMP -= 7;
					if(heroHP > 500)
					{
						heroHP = 500;
					}
					health.setText("HP: " + heroHP + "/500");
					magic.setText("MP: " + heroMP + "/200");
					field.append(your_name + " healed 80 HP!\n");
				}
				else
				{
					field.append("But did not have enough MP!\n");
				}
			}
			else if (selected == "Flames 6MP" && town.isVisible() == false)
			{
				field.append(your_name + " casts Flames!\n");
				if(heroMP >= 6)
				{
					heroMP -= 6;
					dragonHP -= 40;
					field.append("Great Dragon takes 40 damage!\n");
				}
				else
				{
					field.append("But did not have enough MP!\n");
				}
			}
			else if (selected == "Defense 5MP" && town.isVisible() == false)
			{
				field.append(your_name + " casts Defense!\n");
				if (heroMP >= 5)
				{
					heroMP -= 5;
					invincible = true;
					field.append(your_name + " can resist an attacks\nfor a turn!\n");
				}
				else
				{
					field.append("But did not have enough MP!\n");
				}
			}
			else if (selected == "Inferno 10MP" && town.isVisible() == false)
			{
				field.append(your_name + " casts Inferno!\n");
				if(heroMP >= 10)
				{
					heroMP -= 10;
					dragonHP -= 80;
					field.append("Great Dragon takes 80 damage!\n");
					dragonstat.setText("HP: " + dragonHP);
				}
				else
				{
					field.append("But did not have enough MP!\n");
				}
			}
			else if (selected == "FullHeal 12MP")
			{
				field.append(your_name + " casted FullHeal!\n");
				if (heroMP >= 12)
				{
					heroMP -= 12;
					heroHP = 500;
					health.setText("HP: " + heroHP + "/500");
					magic.setText("MP: " + heroMP + "/200");
					field.append(your_name + " is fully healed!\n");
				}
				else
				{
					field.append("But did not have enough MP!\n");
				}
			}
			hideSpell();
			if (heroMP < 0)
			{
				heroMP = 0;
			}
			magic.setText("MP: " + heroMP + "/200");
			if(town.isVisible() == false && theshop.isVisible() == false)
			{
				if (selected != "Back")
				{
					checkDragonHP();
					if(dragonHP > 0)
					{
						dragonTurn();
					}
				}
			}
			if(town.isVisible() == true)
			{
				save.setVisible(true);
				load.setVisible(true);
			}
		}
	}
	
	class ItemListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			field.append("Select an item or \nselect back to exit." + "\n");
			hideSpell();
			hideBag();
			hideSkill();
			items.setVisible(true);
			bagtag.setVisible(true);
			itemselect.setVisible(true);
			iteminfo.setVisible(true);
			save.setVisible(false);
			load.setVisible(false);
		}
	}
	
	class ItemSelectListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			int selected = items.getSelectedIndex();
			if (selected == 0)
			{
				if (herbs > 0)
				{
					field.append(your_name + " used a Herb!\n");
					heroHP += 30;
					herbs -= 1;
					items.removeItemAt(0);
					items.insertItemAt("Herb " + herbs, 0);
					if(heroHP > 500)
					{
						heroHP = 500;
					}
					health.setText("HP: " + heroHP + "/500");
					field.append(your_name + " healed 30 HP!\n");
					hideBag();
					if(town.isVisible() == false && theshop.isVisible() == false)
					{
						dragonTurn();
					}
					if(town.isVisible() == true)
					{
						save.setVisible(true);
						load.setVisible(true);
					}
				}
				else
				{
					field.append("Don't have enough herbs.\n");
				}
			}
			else if (selected == 1)
			{
				if (antidotes > 0)
				{
					field.append(your_name + " used an Antidote!\n");
					antidotes -= 1;
					items.removeItemAt(1);
					items.insertItemAt("Antidote " + antidotes, 1);
					field.append(your_name + " is healed of Poison!\n");
					poisened = false;
					poison.setVisible(false);
					hideBag();
					if(town.isVisible() == false && theshop.isVisible() == false)
					{
						dragonTurn();
					}
					if(town.isVisible() == true)
					{
						save.setVisible(true);
						load.setVisible(true);
					}
				}
				else
				{
					field.append("Don't have enough anidotes.\n");
				}
			}
			else if (selected == 2)
			{
				if (antidotes > 0)
				{
					field.append(your_name + " used an UnNumb!\n");
					unnumbs -= 1;
					items.removeItemAt(2);
					items.insertItemAt("UnNumb " + unnumbs, 2);
					field.append(your_name + " is healed of Paralysis!\n");
					paralyzed = false;
					paralysis.setVisible(false);
					hideBag();
					if(town.isVisible() == false && theshop.isVisible() == false)
					{
						dragonTurn();
					}
					if(town.isVisible() == true)
					{
						save.setVisible(true);
						load.setVisible(true);
					}
				}
				else
				{
					field.append("Don't have enough unnumbs.\n");
				}
			}
			else if (selected == 3)
			{
				if (potions > 0)
				{
					field.append(your_name + " used a Potion!\n");
					heroHP += 80;
					potions -= 1;
					items.removeItemAt(3);
					items.insertItemAt("Potion " + potions, 3);
					if(heroHP > 500)
					{
						heroHP = 500;
					}
					health.setText("HP: " + heroHP + "/500");
					field.append(your_name + " healed 80 HP!\n");
					hideBag();
					if(town.isVisible() == false && theshop.isVisible() == false)
					{
						dragonTurn();
					}
					if(town.isVisible() == true)
					{
						save.setVisible(true);
						load.setVisible(true);
					}
				}
				else
				{
					field.append("Don't have enough potions.\n");
				}
			}
			else if(selected == 4)
			{
				hideBag();
			}
		}
	}
	
	class SkillListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			field.append("Select a skill or \nselect back to exit." + "\n");
			hideSpell();
			hideBag();
			hideSkill();
			skills.setVisible(true);
			skilltag.setVisible(true);
			skillselect.setVisible(true);
			skillinfo.setVisible(true);
		}
	}

	class SkillSelectListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			int selected = skills.getSelectedIndex();
			if(paralyzed == false)
			{
			if (selected == 0)
			{
				if(heroMP >= 2)
				{
					heroMP -= 2;
					magic.setText("MP: " + heroMP + "/200");
					field.append(your_name + " used Dragon Slash!\n");
					int damage = (int)((Math.random()*10)+90)+boost;
					dragonHP -= damage;
					dragonstat.setText("HP: " + dragonHP);
					field.append("Great Dragon takes " + damage + " damage!\n");
					checkDragonHP();
				}
				else
				{
					field.append("Not enough MP!\n");
				}
			}
			else if (selected == 1)
			{
				if(heroMP >= 5)
				{
					field.append(your_name + " used Multi Slash!\n");
					heroMP -= 5;
					magic.setText("MP: " + heroMP + "/200");
					int attacks = (int)(Math.random()*10);
					field.append("Hit " + attacks + " time(s)!\n");
					int damage = (int)(((Math.random()*10)+20)+boost) * attacks;
					dragonHP -= damage;
					dragonstat.setText("HP: " + dragonHP);
					field.append("Great Dragon takes " + damage + " damage!\n");
					checkDragonHP();
				}
				else
				{
					field.append("Not enough MP!\n");
				}
			}
			else if (selected == 2)
			{
				if (heroMP >= 10)
				{
					field.append(your_name + " used Final Slash!\n");
					heroMP -= 10;
					magic.setText("MP: " + heroMP + "/200");
					int damage = (int)((Math.random()*10)+200)+boost;
					dragonHP -= damage;
					dragonstat.setText("HP: " + dragonHP);
					field.append("Great Dragon takes " + damage + " damage!\n");
					checkDragonHP();
				}
				else
				{
					field.append("Not enough MP!\n");
				}
			}
			}
			else
			{
				field.append(your_name + " cannot move!\n");
			}
			hideSkill();
			if (dragonHP > 0)
			{
				dragonTurn();
			}
		}
	}
	
	class RunListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			hideSpell();
			hideBag();
			hideSkill();
			
			field.setText("");
			field.append(your_name + " tries to run!" + "\n");
			int run = (int)(Math.random()*5);
			if(run == 0)
			{
				JOptionPane.showMessageDialog(null, your_name + " successfully ran away...");
				field.setText("");
				field.append("Welcome back, " + your_name + "!\nNo shame on running!\n");
				hideBattle();
				openTown();
			}
			else
			{
				field.append("But the escape was cut off!" + "\n");
				dragonTurn();
			}
		}
	}
	
	class SleepListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			if(gold >= 10)
			{
				field.append(your_name + " goes to bed.\nFully healed!");
				heroHP = 500;
				heroMP = 200;
				gold -= 10;
				health.setText("HP: " + heroHP + "/500");
				magic.setText("MP: " + heroMP + "/200");
				money.setText("G: " + gold);
				paralyzed = false;
				poisened = false;
				poison.setVisible(false);
				paralysis.setVisible(false);
			}
			else
			{
				field.append("Not enough gold!\n");
			}
		}
	}
	
	class BattleListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			leaveTown();
			field.setText("");
			field.append("Great Dragon appears!\n");
			startBattle();
		}
	}
	
	class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null, "Keep working hard!");
			System.exit(0);
		}
	}

	class SaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			field.append("Saving...\n");
			try
			{
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("game.dat"));
				int data[] = {heroHP,heroMP,numberofbattles,herbs,antidotes,unnumbs,potions};
				output.writeObject(data);
				
				ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("name.txt"));
				output2.writeBytes(your_name);
				output.close();
				output2.close();
			}
			catch(IOException ex)
			{
				System.out.println("Save failed.");
			}
			field.append("Saved.\n");
		}
	}

	class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			field.append("Loading...\n");
			try
			{
				ObjectInputStream input = new ObjectInputStream(new FileInputStream("game.dat"));
				int[] data = (int[])(input.readObject());
				
				int index = 0;
				heroHP = data[index];
				health.setText("HP: " + heroHP + "/500");
				index++;
				heroMP = data[index];
				magic.setText("MP: " + heroMP + "/200");
				index++;
				numberofbattles = data[index];
				index++;
				
				herbs = data[index];
				items.removeItemAt(0);
				items.insertItemAt("Herb " + herbs, 0);
				index++;
				antidotes = data[index];
				items.removeItemAt(1);
				items.insertItemAt("Antidote " + antidotes, 1);
				index++;
				unnumbs = data[index];
				items.removeItemAt(2);
				items.insertItemAt("UnNumb " + unnumbs, 2);
				index++;
				potions = data[index];
				items.removeItemAt(3);
				items.insertItemAt("Potion " + potions, 3);
				
				ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("name.txt"));
				your_name = input2.readLine();
				name.setText(your_name);
				input.close();
				input2.close();
			}
			catch(IOException | ClassNotFoundException ex)
			{
				System.out.println("Load failed.");
			}
			field.append("Save successfully loaded!\n");
		}
	}
	
	class HelpListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (heroHP <= 110)
			{
				JOptionPane.showMessageDialog(null, "Low on health! Heal!");
			}
			if (poisened == true)
			{
				JOptionPane.showMessageDialog(null, "Poisoned! Use an antidote!");
			}
			if (paralyzed == true)
			{
				JOptionPane.showMessageDialog(null, "Paralyzed! Use an UnNumb!");
			}
			if (town.isVisible() == true)
			{
				JOptionPane.showMessageDialog(null, "Get gold by defeating the dragon!\nBut the dragon gets stronger\nwith each kill.");
			}
		}
	}
	
	class SpellInfoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int selected = spells.getSelectedIndex();
			if (selected == 0)
			{
				JOptionPane.showMessageDialog(null, "Heal 2MP\nRecovers 30HP.");
			}
			else if (selected == 1)
			{
				JOptionPane.showMessageDialog(null, "Fire 3MP\nAttack spell that does little damage.");
			}
			else if (selected == 2)
			{
				JOptionPane.showMessageDialog(null, "Boost 5MP\nIncreases attack.");
			}
			else if (selected == 3)
			{
				JOptionPane.showMessageDialog(null, "Recover 7MP\nRecovers 80HP.");
			}
			else if (selected == 4)
			{
				JOptionPane.showMessageDialog(null, "Flames 6MP\nAttack spell that does some damage.");
			}
			else if (selected == 5)
			{
				JOptionPane.showMessageDialog(null, "Defense 5MP\nMakes you invincible to one attack.");
			}
			else if (selected == 6)
			{
				JOptionPane.showMessageDialog(null, "Inferno 10MP\nAttack spell that does massive damage.");
			}
			else if (selected == 7)
			{
				JOptionPane.showMessageDialog(null, "FullHeal 12MP\nFully recovers HP.");
			}
			else if (selected == 8)
			{
				JOptionPane.showMessageDialog(null, "Go back.");
			}
		}
	}
	
	class ItemInfoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int selected = items.getSelectedIndex();
			if (selected == 0)
			{
				JOptionPane.showMessageDialog(null, "Herb\nRecovers 30HP.");
			}
			else if (selected == 1)
			{
				JOptionPane.showMessageDialog(null, "Antidote\nHeals poison.");
			}
			else if (selected == 2)
			{
				JOptionPane.showMessageDialog(null, "UnNumb\nHeals paralysis.");
			}
			else if (selected == 3)
			{
				JOptionPane.showMessageDialog(null, "Potion\nRecovers 80HP.");
			}
			else if (selected == 4)
			{
				JOptionPane.showMessageDialog(null, "Go back.");
			}
		}
	}
	
	class SkillInfoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int selected = skills.getSelectedIndex();
			if (selected == 0)
			{
				JOptionPane.showMessageDialog(null, "Dragon Slash 2MP\nDoes more damage to dragons.");
			}
			else if (selected == 1)
			{
				JOptionPane.showMessageDialog(null, "MultiSlash 5MP\nMultiple attacks to an opponent.");
			}
			else if (selected == 2)
			{
				JOptionPane.showMessageDialog(null, "Final Slash 10MP\nA powerful attack to an enemy.");
			}
			else if (selected == 3)
			{
				JOptionPane.showMessageDialog(null, "Go back.");
			}
		}
	}
	
	class SNQListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			field.append("Saving...\n");
			try
			{
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("game.dat"));
				int data[] = {heroHP,heroMP,numberofbattles,herbs,antidotes,unnumbs,potions};
				output.writeObject(data);
				
				ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("name.txt"));
				output2.writeBytes(your_name);
				output.close();
				output2.close();
			}
			catch(IOException ex)
			{
				System.out.println("Save failed.");
			}
			field.append("Saved.\n");
			JOptionPane.showMessageDialog(null, "Keep working hard!");
			System.exit(0);
		}
	}
	
	class ShopListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			leaveTown();
			openShop();
			item.setVisible(false);
			spell.setVisible(false);
		}
	}
	
	class CloseShopListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			closeShop();
			openTown();
		}
	}
	
	class BuyListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			field.setText("");
			String herbamount = herbarea.getText();
			herbamount = herbamount.replaceAll("[^0-9]", "");
			int herbint = Integer.parseInt(herbamount);
			int herbcost = herbint * 5;
			
			String antamount = antidotearea.getText();
			antamount = antamount.replaceAll("[^0-9]", "");
			int antint = Integer.parseInt(antamount);
			int antcost = antint * 10;
			
			String numbamount = unnumbarea.getText();
			numbamount = numbamount.replaceAll("[^0-9]", "");
			int numbint = Integer.parseInt(numbamount);
			int numbcost = numbint * 10;
			
			String potionamount = potionarea.getText();
			potionamount = potionamount.replaceAll("[^0-9]", "");
			int potionint = Integer.parseInt(potionamount);
			int potioncost = potionint * 10;
			
			int totalcost = (herbcost + antcost + numbcost + potioncost);
			if (totalcost > gold)
			{
				field.append("Not enough gold!\n");
			}
			else
			{
				gold -= totalcost;
				money.setText("G: " + gold);
				
				if(herbint > 0)
				{
					herbs += herbint;
					items.removeItemAt(0);
					items.insertItemAt("Herb " + herbs, 0);
					field.append("Bought " + herbint + " herbs.\n");
				}
				if(antint > 0)
				{
					antidotes += antint;
					items.removeItemAt(1);
					items.insertItemAt("Antidote " + antidotes, 1);
					field.append("Bought " + antint + " antidotes.\n");
				}
				if(numbint > 0)
				{
					unnumbs += numbint;
					items.removeItemAt(2);
					items.insertItemAt("UnNumb " + unnumbs, 2);
					field.append("Bought " + numbint + " UnNumbs.\n");
				}
				if(potionint > 0)
				{
					potions += potionint;
					items.removeItemAt(3);
					items.insertItemAt("Potion " + potions, 3);
					field.append("Bought " + potionint + " Potions.\n");
				}
			}
		}
	}
}
