package Calcu;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.awt.*;
import static java.awt.Font.PLAIN;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculadora extends JFrame {
	
	//Hacemos la pantalla
	JLabel display;
	
	//Ponemos cuantos botones va a tener la calculadora.
	int numerobotones=17;
	
	//Hacemos una array de botones para los numeros y las operaciones
	JButton botones[]=new JButton[numerobotones];
	
	//Hacemos un array de Strings para poner encima de los numeros
	String textoBotones[]= {
			"Resultado",
			"7","8","9","/",
			"4","5","6","*",
			"1","2","3","-",
			"C","0",".","+",
	};
	
	//Array de la posicion x de cada boton
	int posXBotones[]= {
			15,
			15,80,145,210,
			15,80,145,210,
			15,80,145,210,
			15,80,145,210
	};

	//Array de la posicion y de cada boton
	int posYBotones[]= {
			90,
			155,155,155,155,
			220,220,220,220,
			285,285,285,285,
			350,350,350,350,	
	};
	
	//Array de índices de botones que se corresponden a numeros (orden en que se van a pintar)
	int numeroBotones[]= {
			14,9,10,
			11,5,6,
			7,1,2,
			  3,
			
	};
	
	//Array de índices del array de botones que corresponden a operaciones (orden en que se van a pintar)
	int[] operacionBotones= {16,12,8,4};
	
	//Alto y ancho de cada botón
	int anchoBoton=50;
	
	int altoBoton=50;
	
	//Para indicar que he terminado de escribir dígitos de un número y paso al siguiente.
	boolean nuevoNumero=true;
	
	//Para indicar si ya he usado el punto decimal en ese número(solo puede haber uno.
	boolean puntodecimal=false;
	
	//Para almacenar resultado parciales y totales de las operaciones realizadas.
	double operando1 =0;
	
	double operando2=0;
	
	double resultado=0;
	
	//Para almacenar el String de la operacion realizada(+,-,*,/)
	String operacion="";
	
	
	
	public void iniciar() {
	
		initMarco();
		initDisplay(); //Iniciamos la pantalla
		initBotones();
		eventosNumeros();
		eventoDecimal();
		eventosOperaciones();
		eventoResultado();
		eventoLimpiar();
	}
	
	private void initDisplay() { //Vamos a crear la pantalla
		
		display=new JLabel("0"); //Iniciamos el Label en 0
		display.setBounds(15,15,245,60); //Le damos el ancho alto x e y)
		display.setOpaque(true); //Para que se vea como una pantalla.
		display.setBackground(Color.BLACK); //Lo ponemos en negro.
		display.setForeground(Color.ORANGE); //Este va a ser el color de la fuente
		display.setBorder(new LineBorder(Color.DARK_GRAY));//Creamos un bordo y lo ponemos de color
		display.setFont(new Font("MONOSPACED",PLAIN,24)); //Ponemos la fuente recuerda que hace falta importarla.
		display.setHorizontalAlignment(SwingConstants.RIGHT);//Alineamiento horizontal derecha.
		add(display);
		
	}
	
	private void initBotones() { //Ahora creamos los botones
		
		for(int i=0;i<numerobotones;i++) {
			botones[i]=new JButton(textoBotones[i]); //Iniciamos el Jbutton en el bucle para que rellene los 17 botones con su texto.
			int size=(i==0)? 24:16; //El boton resultado va a tener un tamaño de fuente menor del de los demas.
			int ancho=(i==0)? 245:anchoBoton; //El boton de resultado va a ser mas ancho que todos los demas.
			/* Esto significa que si es el boton resultado ancho= 245 si es cualquier otro anchoboton.*/
			/*Las linea de antes es operador ternarios(?) y son equivalentes a:
			 * if(i==0){
			 * 	int ancho=245;
			 * }else{
			 * 	int ancho=anchoBoton;
			 * }*/
			botones[i].setBounds(posXBotones[i],posYBotones[i],ancho,altoBoton); //Dimensiones y donde van a estar situados
			botones[i].setFont(new Font("IMPACT",PLAIN,size)); //Como va a ser la fuente de tamaño y estilo.
			botones[i].setOpaque(true);//Para poder darle color de fondo.
			botones[i].setFocusPainted(false); //Esto evita que salga un recuadro azul cuando tenga el foco.
			botones[i].setBackground(Color.DARK_GRAY);//El color del fondo.
			botones[i].setForeground(Color.ORANGE);//El color de los numeros.
			botones[i].setBorder(new LineBorder(Color.CYAN));
			add(botones[i]);
				
		}
		botones[13].setBackground(Color.RED);
		botones[13].setForeground(Color.BLACK);
	}
	
	private void initMarco() {
		
		setLayout(null); //Le vamos a dar un Layout absoluto.
		setTitle("Calculadora2.0");
		setSize(290,455);
		setResizable(true);
		getContentPane().setBackground(Color.BLACK);
		
	}
	private void eventosNumeros() { //Eventos asociados a los botones con numeros
		
		for (int i=0;i<10;i++) {
			
			int nBoton=numeroBotones[i];
			botones[nBoton].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					//Si es un nuevo numero y no es 0 sustituo el valor en el display.
					if(nuevoNumero) {
						if(!textoBotones[nBoton].equals("0")) {
							display.setText(textoBotones[nBoton]);
							nuevoNumero=false;//Ya np es in nuevo numero.
						}
					}
					//Si no es un nuevo numero le añado los dígitos que ya hubiera.
					else {
						display.setText(display.getText()+textoBotones[nBoton]);
					}
				}
				
			});
		}

    }

    private void eventoDecimal(){ //Necesitamos configurar el botón del punto decimal.
    	
    	botones[15].addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//Si todavía no se ha añadido el punto decimal al número actual.
				
				if(!puntodecimal) {
					display.setText(display.getText() + textoBotones[15]);
					puntodecimal=true;//Ya no se pueden añadir más puntos decimales a este número.
					nuevoNumero=false; //Por si empiezo el número con punto decimal(ejemplo .537)
				}
				
			}
    		
    	});

    }

    private void eventosOperaciones() { //Ahora vamos con los eventos de las operaciones.
    	
    	for (int nBoton:operacionBotones) { //Bucle for each de el array operacionBotones y en vez de e es nBoton)
    		botones[nBoton].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//Si no tiene una operacion pendiente a realizar
					if(operacion.equals("")) { 
						//Asocio la operacion del botón a la variable
						operacion=textoBotones[nBoton];	
						
						//Asigno el operando2 el valor del display(como un double)
						operando2=Double.parseDouble(display.getText());
						
						//Reseteo para poder introducir otro número y otro decimal
						nuevoNumero=true;
						puntodecimal=false;
					
					}else {
						operando2=resultado(); //Se almacena en operando2 para poder encadenar las operaciones posteriores.
						operacion=textoBotones[nBoton];
					}
					//SOUT para asegurarme de que estoy guardando los valores adecuados.
					System.out.println(operando2+""+operacion+""+operando1);
				}
    			
    			
    		});
    	}
    }
    
    //Calcula el resultado en función de la operación seleccionada y lo devuelve formateando el display.
    private double resultado() {
    	
    	//Recojo el valor del display
    	operando1=Double.parseDouble(display.getText());
    	
    	//Selecciono y realizo la operacion
    	switch(operacion) {
    	
    		case"+": resultado=operando2+operando1;
    			break;
    		
    		case"-": resultado=operando2-operando1;
    			break;
    		
    		case"*": resultado=operando2*operando1;
    			break;
    		
    		case"/": resultado=operando2/operando1;
    			break;

    	}
    	//Formateo y muestro en el display
    	Locale localeActual=Locale.GERMAN;
    	DecimalFormatSymbols simbolos=new DecimalFormatSymbols(localeActual);
    	simbolos.setDecimalSeparator('.'); //CUIDAO QUE ES UN CHAR NO STRING SIEMPRE VA ENTRE COMILLAS SIMPLES
    	DecimalFormat formatoResultado=new DecimalFormat("#.####",simbolos);
    	display.setText(String.valueOf(formatoResultado.format(resultado)));
    	
    	//Limpio variables para poder seguir.
    	limpiar();
    	
    	//Devuelvo el valor del resultado.
    	return resultado;
    }
    
    //Resetea los valores de la calculadora para poder continuar haciendo operaciones.
    private void limpiar() { //Lo q va a hacer el boton de limpiar
    	
    	operando1=operando2=0;
    	operacion="";
    	nuevoNumero=true;
    	puntodecimal=false;
    }
    	

    private void eventoResultado() { //Evento para que el boton resultado haga lo suyo
    	
    	botones[0].addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				//Al pulsar el boton resultado directamente calculo y reseteo la calculadora.
				//Sin necesidad de almacenar el resultado para futuras operaciones.
				resultado();
				
			}
    		
    		
    	});
    	botones[0].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				botones[0].setBackground(Color.GREEN);
				botones[0].setForeground(Color.WHITE);
				botones[0].setText("=");
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				botones[0].setBackground(Color.DARK_GRAY);
				botones[0].setForeground(Color.ORANGE);
				botones[0].setText("Resultado");
				
				
			}});

    }

    private void eventoLimpiar() { //Evento para el boton C
    	
    	botones[13].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				display.setText("0");
				limpiar();
				
			}
    		
    	});
    	botones[13].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				botones[13].setText("BORRAR?");
				botones[13].setFont(new Font("IMPACT",PLAIN,11));
				botones[13].setBackground(Color.WHITE);
				botones[13].setForeground(Color.RED);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				botones[13].setText("C");
				botones[13].setFont(new Font("IMPACT",PLAIN,12));
				botones[13].setBackground(Color.RED);
				botones[13].setForeground(Color.BLACK);
				
			}
    		
    		
    	});
    }

    
	
}

