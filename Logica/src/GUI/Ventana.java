package GUI;

//Propiedades de componentes
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Manejador de archivos .pl
import jpl.*;

//Componentes
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

//Libreria provista por atxy2k
//http://serprogramador.es/limitando-el-numero-de-elementos-en-un-jtextfield-restrictedtextfield/
import Atxy2k.CustomTextField.RestrictedTextField;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Ventana extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton botonResolver;
	private JButton botonVerificar;
	private JPanel jPanel1;
	private JPanel jPanel2;
	
	protected Color rojito = new Color(250, 128, 114);
	protected Color azul = new Color(173,216,230);
	protected Color azulcito = new Color(220,220,250);
	protected Color verdecito = new Color(50,205,50);
	protected Font fuente = new Font("Dialog", Font.BOLD, 36);
	
	protected JTextField[][] tabla;
	
	protected String confi = 	"x,7,x|4,8,5|x,2,3"+
								"3,x,x|x,2,7|8,x,9"+
								"x,x,8|x,x,6|x,x,x\n"+
	   
								"x,4,x|x,3,x|x,5,x"+
	   							"x,x,x|7,x,x|x,x,x"+
	   							"x,x,x|x,x,x|4,3,7\n"+
	   
	   							"6,x,x|x,x,3|2,x,8"+
	   							"x,x,x|8,x,x|3,x,4"+
	   							"x,3,9|2,7,x|x,x,x\n";
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Ventana inst = new Ventana();
				inst.setSize(470,600);
				inst.setResizable(false);
				inst.setBackground(Color.BLACK);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Ventana() {
		super();
		cargarPL();
		initGUI();
	}
	
	public boolean cargarPL()
	{
		String t1 = "consult('sudokuFinal.pl')";
		Query cargar = new Query(t1);
	
		boolean cargo = cargar.hasSolution();
		if (!cargo)
		{
			JOptionPane.showMessageDialog(null, "Error al cargar SudokuFinal.");
		}
		return cargo;
	}
	
	public String getTabla()
	{
		String cadena = "";
		for(int i = 0; i<tabla.length;i++)
		{
			for(int j = 0; j<tabla.length;j++)
			{
				if(tabla[i][j]!=null)
				{
					cadena = tabla[i][j].getText().equals("") ? cadena+"_," : cadena+tabla[i][j].getText()+",";
				}
				
			}
		}
		cadena = cadena.substring(0, 161);
		return cadena;
	}
	
	public String getColumna(int j)
	{
		String columna = "";
		
		for(int i = 0; i<tabla.length;i++)
		{
			if (tabla[i][j]!=null)
			{
				columna = tabla[i][j].getText().equals("") ? columna+"_," : columna+tabla[i][j].getText()+",";
			}
		}
		columna = columna.substring(0, 17);
		return columna;
	}
	
	public String getFila(int i)
	{
		String fila = "";
		
		for(int j = 0; j<tabla.length;j++)
		{
			if (tabla[i][j]!=null)
			{
				fila = tabla[i][j].getText().equals("") ? fila+"_," : fila+tabla[i][j].getText()+",";
			}
		}
		fila = fila.substring(0, 17);
		return fila;
	}
	
	
	public String getCuadricula(int i, int j)
	{
		String cuadricula = "";
		
		if ((0<=i)&&(i<=2))
		{
			if ((0<=j)&&(j<=2))
			{
				cuadricula = getCuadro(0,2,0,2);
			}
			else
			{
				if ((4<=j)&&(j<=6))
				{
					cuadricula = getCuadro(0,2,4,6);
				}
				else
				{
					cuadricula = getCuadro(0,2,8,10);
				}
			}
		}
		else
		{
			if ((4<=i)&&(i<=6))
			{
				if ((0<=j)&&(j<=2))
				{
					cuadricula = getCuadro(4,6,0,2);
				}
				else
				{
					if ((4<=j)&&(j<=6))
					{
						cuadricula = getCuadro(4,6,4,6);
					}
					else
					{
						cuadricula = getCuadro(4,6,8,10);
					}
				}
			}
			else
			{
				if ((0<=j)&&(j<=2))
				{
					cuadricula = getCuadro(8,10,0,2);
				}
				else
				{
					if ((4<=j)&&(j<=6))
					{
						cuadricula = getCuadro(8,10,4,6);
					}
					else
					{
						cuadricula = getCuadro(8,10,8,10);
					}
				}
			}
		}
		return cuadricula;
	}
	
	public String getCuadro(int xi, int xj, int yi, int yj)
	{
		String cuadricula = "";
		for(int i = xi;i<=xj;i++)
		{
			for(int j = yi;j<=yj;j++)
			{
				if (tabla[i][j]!=null)
				{
					cuadricula = tabla[i][j].getText().equals("") ? cuadricula+"_," : cuadricula+tabla[i][j].getText()+",";
				}
			}
		}
		cuadricula = cuadricula.substring(0, 17);
		return cuadricula;
		
	}
	
	public void llenarTablero()
	{
		char car;
		int c = 0;
		int i=0;
		int j=0;
		while(c<confi.length()) 
		{			
			car = confi.charAt(c);
			c++;
			if(j==11)
			{
				j=0;
				i++;
			}

			switch(car)
			{
			case('|'): 
			{
				j++;
				break;
			}

			case('\n'): 
			{
				i++;
				break;
			}

			case('x'): 
			{
				tabla[i][j].setText("");
				tabla[i][j].addKeyListener(new OyenteJTextField(tabla[i][j])); 
				tabla[i][j].setEditable(true);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				
				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('1'): 
			{
				tabla[i][j].setText("1");;
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('2'): 
			{
				tabla[i][j].setText("2");
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('3'): 
			{
				tabla[i][j].setText("3");
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('4'): 
			{
				tabla[i][j].setText("4");
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('5'): 
			{
				tabla[i][j].setText("5");
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('6'): 
			{
				tabla[i][j].setText("6");
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('7'): 
			{
				tabla[i][j].setText("7");
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('8'): 
			{
				tabla[i][j].setText("8");
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}

			case('9'): 
			{
				tabla[i][j].setText("9");
				tabla[i][j].setEditable(false);
				tabla[i][j].setFont(fuente);
				tabla[i][j].setHorizontalAlignment(JTextField.CENTER);
				tabla[i][j].setOpaque(true);
				tabla[i][j].setSize(50, 50);
				tabla[i][j].setBackground(azul);

				jPanel1.add(tabla[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				j++;
				break;
			}
			}
		}
	}
	
	public void vaciarTablero()
	{
		for(int i = 0;i<tabla.length;i++)
		{
			for(int j = 0;j<tabla.length;j++)
			{
				if (tabla[i][j]!=null)
				{
					tabla[i][j].setText("");
					tabla[i][j].setEditable(true);
					tabla[i][j].setBackground(azulcito);
				}
			}
		}
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				jPanel1.setOpaque(true);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				GridBagLayout layjPanel1 = new GridBagLayout();
				layjPanel1.rowWeights = new double[] {50.0, 50.0, 50.0, 50.0, 50.0, 50.0, 50.0, 50.0, 50.0};
				layjPanel1.rowHeights = new int[] {50, 50, 50, 10, 50, 50, 50, 10, 50, 50, 50};
				layjPanel1.columnWeights = new double[] {50.0, 50.0, 50.0, 50.0, 50.0, 50.0, 50.0, 50.0, 50.0};
				layjPanel1.columnWidths = new int[] {50, 50, 50, 10, 50, 50, 50, 10, 50, 50, 50};
				jPanel1.setLayout(layjPanel1);
				jPanel1.setPreferredSize(new java.awt.Dimension(882, 480));
			}
			
			tabla = new JTextField[11][11];
			RestrictedTextField rtf;
			for(int i = 0;i<tabla.length;i++)
			{
				for(int j = 0;j<tabla.length;j++)
				{
					if (i!=3 && i!=7 && j!=3 && j!=7)
					{
						tabla[i][j] = new JTextField("");
						tabla[i][j].setEditable(true);
						tabla[i][j].setBackground(azulcito);
						rtf = new RestrictedTextField(tabla[i][j]);
						rtf.setLimit(1);
						rtf.setOnlyNums(true);
					}
				}
			}
			llenarTablero();
			
			{
				jPanel2 = new JPanel();
				jPanel2.setOpaque(false);
				jPanel2.setLayout(null);
				getContentPane().add(jPanel2, BorderLayout.SOUTH);
				jPanel2.setPreferredSize(new java.awt.Dimension(882, 90));
				{
					botonVerificar = new JButton();
					jPanel2.add(botonVerificar);
					botonVerificar.addActionListener(new OyenteVerificar()); 
					botonVerificar.setText("Verificar");
					botonVerificar.setBounds(25, 29, 161, 37);
				}
				{
					botonResolver = new JButton();
					jPanel2.add(botonResolver);
					botonResolver.addActionListener(new OyenteResolver());
					botonResolver.setText("Resolver");
					botonResolver.setBounds(279, 30, 160, 36);
				}
			}
			
			JMenuBar barra = new JMenuBar();
			{
				barra.setSize(470,50);
				barra.setVisible(true);
				getContentPane().add(barra,BorderLayout.NORTH);
				barra.setPreferredSize(new java.awt.Dimension(882, 30));
				{
					JMenu jMenu1 = new JMenu();
					barra.add(jMenu1);
					jMenu1.setText("Archivo");
					{
						JMenu jMenu3 = new JMenu();
						jMenu1.add(jMenu3);
						jMenu3.setText("Cargar");
						{
							JMenuItem nivel1 = new JMenuItem();
							jMenu3.add(nivel1);
							nivel1.addActionListener(new OyenteNivel('1'));
							nivel1.setText("Nivel 1");
							
							JMenuItem nivel2 = new JMenuItem();
							jMenu3.add(nivel2);
							nivel2.addActionListener(new OyenteNivel('2'));
							nivel2.setText("Nivel 2");
							
							JMenuItem nivel3 = new JMenuItem();
							jMenu3.add(nivel3);
							nivel3.addActionListener(new OyenteNivel('3'));
							nivel3.setText("Nivel 3");
						}
						
						JMenuItem jMenuItem2 = new JMenuItem();
						jMenu1.add(jMenuItem2);
						jMenuItem2.addActionListener(new OyenteVaciar());
						jMenuItem2.setText("Vaciar");
											
						JMenuItem jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.addActionListener(new OyenteSalir());
						jMenuItem1.setText("Salir");
								
					}
				}
				{
					JMenu jMenu2 = new JMenu();
					barra.add(jMenu2);
					jMenu2.setText("Ayuda");
					{
						JMenuItem jMenuItem3 = new JMenuItem();
						jMenu2.add(jMenuItem3);
						jMenuItem3.addActionListener(new OyenteAcercade());
						jMenuItem3.setText("Acerca de");
					}
				}

			}
			
			
			pack();
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	
	
	private class OyenteVaciar implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			vaciarTablero();
			
		}
	}
	
	private class OyenteNivel implements ActionListener
	{
		protected char c;
		
		public OyenteNivel(char _c)
		{
			c = _c;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch(c)
			{
				case('1'):
				{
					confi = 	"x,x,x|7,4,8|3,x,x"+
								"2,3,x|6,5,x|4,7,9"+
								"7,x,6|3,x,x|x,x,x\n"+
   
								"8,2,4|9,x,x|1,x,x"+
								"1,7,x|x,x,x|5,4,6"+
								"x,x,3|x,x,x|x,x,8\n"+
   
   								"3,9,1|8,2,7|x,5,x"+
   								"x,6,2|4,1,9|7,x,3"+
   								"x,8,7|5,x,6|x,2,x\n";
					break;
				}
				
				case('2'):
				{
					confi = 	"x,x,x|x,x,x|3,6,x"+
								"x,x,5|x,x,x|8,9,7"+
								"x,x,x|4,x,x|5,2,x\n"+

								"3,6,2|x,x,x|x,x,x"+
								"5,8,4|x,x,x|x,3,6"+
								"7,x,x|5,6,x|x,x,x\n"+

								"x,x,x|6,x,x|x,x,x"+
								"x,2,x|9,3,x|x,7,8"+
								"6,x,x|x,8,7|x,4,5\n";
					break;
				}
				
				case('3'):
				{
					confi = 	"x,x,x|x,8,x|9,6,x"+
								"3,x,9|x,x,2|8,x,1"+
								"x,x,x|9,x,x|x,7,2\n"+
								
								"x,x,x|x,2,8|x,x,3"+
								"2,x,x|x,x,9|5,4,x"+
								"7,x,x|3,x,4|x,9,8\n"+
							
								"4,8,x|x,x,7|x,x,x"+
								"9,x,2|x,x,x|x,x,x"+
								"1,x,x|x,x,x|6,x,x\n";
					break;
				}
			}
			
			vaciarTablero();
			llenarTablero();
						
		}
		
	}
	
	private class OyenteSalir implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			System.exit(0); 
			
		}
	}
	
	private class OyenteAcercade implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String mensaje = "integrantes:\n"+
							 "Agra Federico - Loza Carlos\n"+
							 "Lógica para Ciencias de la Computación\n"+
							 "Copyright© 2014";
			JOptionPane.showMessageDialog(null, mensaje);
			
		}
	}
	
	
	private class OyenteVerificar implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String tablaAct = "comprobar(["+getTabla()+"])";
			Query verificar = new Query(tablaAct);
			if (verificar.hasSolution())
			{
				JOptionPane.showMessageDialog(null,"Felicitaciones! Has ganado!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Tu sudoku es horrendo. arreglalo!", "Mal", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	private class OyenteResolver implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String tablaAct = "resolver(["+getTabla()+"],Salida)";
			Query verificar = new Query(tablaAct);
			if (verificar.hasSolution())
			{
				int i = 0;
				int j = 0;
				int k = 0;
				char c;
				String resu = verificar.oneSolution().get("Salida").toString();
				while(i<tabla.length && k<resu.length())
				{
					if((i==3)||(i==7))
					{
						i++;
					}
					while(j<tabla.length && k<resu.length())
					{
						if((j==3)||(j==7))
						{
							j++;
						}
						c = resu.charAt(k);
						switch(c)
						{
							case('1'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("1");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							case('2'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("2");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							case('3'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("3");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							case('4'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("4");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							case('5'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("5");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							case('6'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("6");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							case('7'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("7");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							case('8'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("8");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							case('9'):
							{
								if (tabla[i][j].isEditable())
								{
									tabla[i][j].setText("9");
									tabla[i][j].setBackground(verdecito);
								}
								j++;
								break;
							}
							
						}
						k++;
					}
					j=0;
					i++;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No hay solucion para esta configuracion.");
			}
			
		}
		
	}
	
	private class OyenteJTextField implements KeyListener
	{
		protected JTextField celda;
		
		public OyenteJTextField(JTextField jtf)
		{
			celda = jtf;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
			int i = 0;
			int j = 0;
			boolean cortar = false;
			while(!cortar&&i<tabla.length)
			{
				while(!cortar&&j<tabla.length)
				{
					if(tabla[i][j]==(celda))
					{
						cortar = true;
					}
					else
					{
						j++;
					}
					
				}
				if (!cortar)
				{
					j=0;
					i++;
				}
				
			}
			
			String columna = getColumna(j);
			String fila = getFila(i);
			String cuadricula = getCuadricula(i,j);
			
			String t2 = "validar(["+columna+"]),validar(["+fila+"]),validar(["+cuadricula+"])";
			Query validar = new Query(t2);
			if (validar.hasSolution())
			{
				celda.setBackground(azulcito);
			}
			else
			{
				celda.setBackground(rojito);
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}
	}
}
