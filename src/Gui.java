import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Gui extends JFrame 
{

	private JPanel contentPane;
	public List lstAktien;
	public List lstKurse;
	public List lstMethoden;
	public static ArrayList<Aktie> aktien = new ArrayList<Aktie>();
	public static ArrayList<Double> currentKurse = new ArrayList<Double>();
	
	private JTextField txtErgebnis;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater
		(
			new Runnable() 
			{
				public void run() 
				{
					try 
					{
						Gui frame = new Gui();
						frame.setVisible(true);
						
						frame.displayAktien();
						frame.displayMethoden();	
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		);
	}

	/**
	 * Gui wird erstellt / Konstruktor wird aufgerufen
	 */
	public Gui() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Aufruf der Konstruktoren der Klasse List
		lstAktien = new List();
		lstKurse = new List();
		lstMethoden = new List();
		
		lstAktien.addMouseListener
		(
			new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					String aktienname = lstAktien.getSelectedItem();
					System.out.println("Kurse der " + aktienname + "-Aktie werden geladen...");
					
					lstKurse.removeAll();
					oeffnenKurse(aktienname);
				}
		
			}
		);
		
		//Listen, Textfields Maße,.. etc.
		lstAktien.setBounds(10, 10, 171, 197);
		contentPane.add(lstAktien);
		
		lstKurse.setBounds(10, 213, 171, 231);
		contentPane.add(lstKurse);
		
		lstMethoden.setBounds(273, 10, 171, 197);
		contentPane.add(lstMethoden);
		
		txtErgebnis = new JTextField();
		txtErgebnis.setEditable(false);
		txtErgebnis.setBounds(273, 229, 171, 26);
		contentPane.add(txtErgebnis);
		txtErgebnis.setColumns(10);
		
		JButton btnBerechnen = new JButton("Berechnen");
		btnBerechnen.addActionListener
		(
			new ActionListener() 
			{
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) 
				{
					double[] kurse = currentKurse.stream().mapToDouble(Double::doubleValue).toArray();
					
					try
					{
						switch (lstMethoden.getSelectedItem())
						{
							case "RSI30":
								txtErgebnis.setText(Double.toString(TA.rsi(30, kurse.length, kurse)));
								break;
							
							case "RSI60":
								txtErgebnis.setText(Double.toString(TA.rsi(60, kurse.length, kurse)));
								break;
							
							case "GD30":
								txtErgebnis.setText(Double.toString(TA.gd(30, kurse.length, kurse)));
								break;
								
							case "GD90":
								txtErgebnis.setText(Double.toString(TA.gd(90, kurse.length, kurse)));
								break;
		
							case "RS14":
								txtErgebnis.setText(Double.toString(TA.rs(14, kurse.length, kurse)));
								break;
						}
					}
					catch (Exception e1)
					{
						System.out.println(e1);
						
						/*for (int i = 0; i < kurse.length; i++)
						**{
						**	System.out.println(kurse[i]);
						**}
						*/
					}
					
				}
			}
		);
		btnBerechnen.setBounds(273, 267, 117, 29);
		contentPane.add(btnBerechnen);
	}
	
	private void oeffnenKurse (String aktienname) 
	{
		String line;
		currentKurse.removeAll(currentKurse);
		
		try 
		{
			Scanner f = new Scanner(new FileReader("/Users/lukadjordjevic/git/aktienanalyse/src/Kurse/" + aktienname + ".txt"));
			while (f.hasNextLine())
			{
				line = f.nextLine();
				currentKurse.add(Double.parseDouble(line));
				lstKurse.add(line);
			}
			f.close();
		} 
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
	
	private void displayMethoden() 
	{
		lstMethoden.add("RSI30");
		lstMethoden.add("RSI60");
		lstMethoden.add("GD30");
		lstMethoden.add("GD90");
		lstMethoden.add("RS14");
	}
	
	public void displayAktien() 
	{
		String line;
		try 
		{
			Scanner f = new Scanner(new FileReader("/Users/lukadjordjevic/git/aktienanalyse/src/Kurse/Aktien.txt"));
			while (f.hasNextLine())
			{
				line = f.nextLine();
				lstAktien.add(line);
			}
			f.close();
		} 
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
	
}
