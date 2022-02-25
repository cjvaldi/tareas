package calculadoragui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * La clase Interfaz gestiona la CalculadoraGui
 * @author Alumno Cristian J. Valdivieso Valenzuela
 */
public class Interfaz implements ActionListener {

    //    Renombrando Variables x Cristian Valdivieso
    //JTextField jt1, jt2;  El control JTextField permite al operador del programa ingresar una cadena de caracteres por teclado.
    JTextField jTexto1, jTexto2;

    //Panel pN, pb1, pb3;
    Panel panelNumeros, pBloqueMemoria, pBloqueOperadores; //panelNumeros caja de texto donde aparecen los numeros y operaciones

    //JPanel pS, pb2;
    JPanel panelSimbolos, pBloqueNumerico;   // panelsimbolos donde se encuentran los numeros , y operadores

    //JButton mc, mr, ms, mMas, mMenos, numeros[], operaciones[];
    /**
     * Variables que gestionan los cálculos de memoria
     */
    JButton memoryCancel, memoryResult, memoryStorage, memoryMas, memoryMenos, numeros[], operaciones[];

    //String oper[]={"R", "C", "+", "/", "-" ,"*", "="},  ax="" cadena que guarda las operaciones realizadas = axioma;
    /**
     * Variable que guarda los botones de operaciones
     */
    String operadores[] = {"R", "C", "+", "/", "-", "*", "="}, axioma = "";

    //float n1, n2, nr, M;//variables para las operaciones
    float numeroIng1, numeroIng2, numeroResultante, memoria;//variables para las operaciones

    //int tipOp; //para controlar el tipo de operacion que se realiza
    int tipOperador; //para controlar el tipo de operacion que se realiza +-/*

    //boolean t=false;//control sobre escribir un nuevo numero despues de alguna operacion cambia a true cuando se ha realizado una operacion
    boolean swNumero = false;//control sobre escribir un nuevo numero despues de alguna operacion cambia a true cuando se ha realizado una operacion
/**
 * Metodo que Gestiona la clase principal de CalculadoraGUI
 * desde aqui se crea la interfaz de la cálculadora y se reealizan todas las 
 * operaciones solicitadas
 */
    public Interfaz() {

        JFrame jfMain = new JFrame("Calculator");
        jfMain.setLayout(new BorderLayout(4, 4));

        norte();
        sur();

        jfMain.add(panelNumeros, BorderLayout.NORTH);
        jfMain.add(panelSimbolos, BorderLayout.CENTER);

        jfMain.setLocation(100, 80);
        jfMain.setResizable(false);
        jfMain.setVisible(true);
        jfMain.setSize(300, 380);
        jfMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
/**
 * Método que crea la caja de la cálculadora
 */
    public void norte() {

        panelNumeros = new Panel(null);

        jTexto1 = new JTextField("");
        jTexto2 = new JTextField("0");

        jTexto1.setHorizontalAlignment(JTextField.RIGHT);
        jTexto2.setHorizontalAlignment(JTextField.RIGHT);

        //Quitar bordes a los campos de texto
        jTexto1.setBorder(BorderFactory.createLineBorder(Color.white));
        jTexto2.setBorder(BorderFactory.createLineBorder(Color.white));

        //desabilitando los campos de texto
        jTexto1.setEditable(false);
        jTexto2.setEditable(false);

        jTexto1.setBackground(Color.white);
        jTexto2.setBackground(Color.white);

        panelNumeros.add(jTexto1);
        panelNumeros.add(jTexto2);

        jTexto1.setBounds(35, 10, 200, 15);
        jTexto2.setBounds(35, 25, 200, 30);

        panelNumeros.setSize(270, 47);
        panelNumeros.setVisible(true);

    }
/**
 * Método que llama a los botones
 */
    public void sur() {

        panelSimbolos = new JPanel(new BorderLayout(6, 50));
        panelSimbolos.setLayout(new BorderLayout(4, 4));

        botMem();
        botNum();
        botOpe();

        panelSimbolos.add(pBloqueMemoria, BorderLayout.NORTH);
        panelSimbolos.add(pBloqueNumerico, BorderLayout.CENTER);
        panelSimbolos.add(pBloqueOperadores, BorderLayout.EAST);

        panelSimbolos.setSize(270, 330);
    }
/**
 * Método que muestra los botones de Memoria 
 * MC MR MS M+ M-
 */
    public void botMem() {

        pBloqueMemoria = new Panel(null);

        memoryCancel = new JButton("MC");
        memoryResult = new JButton("MR");
        memoryStorage = new JButton("MS");
        memoryMas = new JButton("M+");
        memoryMenos = new JButton("M-");

        memoryCancel.setFont(new Font("Arial", Font.BOLD, 11));
        memoryResult.setFont(new Font("Arial", Font.BOLD, 11));
        memoryStorage.setFont(new Font("Arial", Font.BOLD, 11));
        memoryMas.setFont(new Font("Arial", Font.BOLD, 11));
        memoryMenos.setFont(new Font("Arial", Font.BOLD, 11));

        memoryCancel.setMargin(new Insets(1, 1, 1, 1));
        memoryResult.setMargin(new Insets(1, 1, 1, 1));
        memoryStorage.setMargin(new Insets(1, 1, 1, 1));
        memoryMas.setMargin(new Insets(1, 1, 1, 1));
        memoryMenos.setMargin(new Insets(1, 1, 1, 1));

        memoryCancel.setBounds(35, 0, 33, 33);
        memoryResult.setBounds(78, 0, 33, 33);
        memoryStorage.setBounds(121, 0, 33, 33);
        memoryMas.setBounds(164, 0, 33, 33);
        memoryMenos.setBounds(207, 0, 33, 33);

        pBloqueMemoria.add(memoryCancel);
        pBloqueMemoria.add(memoryResult);
        pBloqueMemoria.add(memoryStorage);
        pBloqueMemoria.add(memoryMas);
        pBloqueMemoria.add(memoryMenos);

        memoryCancel.addActionListener(this);
        memoryResult.addActionListener(this);
        memoryStorage.addActionListener(this);
        memoryMas.addActionListener(this);
        memoryMenos.addActionListener(this);

        pBloqueMemoria.setSize(270, 45);
        pBloqueMemoria.setVisible(true);
    }
/**
 * Método que muestra los botones numericos
 */
    public void botNum() {

        pBloqueNumerico = new JPanel(null);

        //int nx3=121, nx2=121, nx1=121, n3y=0, n2y=43, n1y=86;
        int posicionFila3 = 121, posicionFila2 = 121, posicionFila1 = 121, posicionColunma3 = 0, posicionColunma2 = 43, posicionColunma1 = 86;
        numeros = new JButton[11];

        //*****************************************
        //bloque para crear los botones, añadirlos y asignar numeros
        for (int i = 0; i <= 10; i++) {
            if (i <= 9) {
                numeros[i] = new JButton("" + i);
                pBloqueNumerico.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);
            } else {
                numeros[i] = new JButton(".");
                pBloqueNumerico.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);
            }
        }
        //******************************************
        //bloque para posicionar botones
        for (int i = 10; i >= 0; i--) {
            if (i == 10) {
                numeros[i].setBounds(121, 129, 35, 35);
            } else {
                if (i <= 9 && i >= 7) {
                    numeros[i].setBounds(posicionFila3, posicionColunma3, 35, 35);
                    posicionFila3 -= 43;
                } else if (i <= 6 && i >= 4) {
                    posicionColunma3 += 43;
                    numeros[i].setBounds(posicionFila2, posicionColunma2, 35, 35);
                    posicionFila2 -= 43;
                } else if (i <= 3 && i >= 1) {
                    posicionColunma3 += 43;
                    numeros[i].setBounds(posicionFila1, posicionColunma1, 35, 35);
                    posicionFila1 -= 43;
                } else if (i == 0) {
                    numeros[i].setBounds(35, 129, 78, 35);
                }
            }
        }
        pBloqueNumerico.setSize(170, 150);
        pBloqueNumerico.setVisible(true);
    }
/**
 * Método botOpe muestra los botones de las operaciones
 */
    public void botOpe() {
        pBloqueOperadores = new Panel(null);
        int c = 0, x = 0, y = 0;
        operaciones = new JButton[7];
        for (int i = 0; i <= 6; i++) {
            if (c <= 1) {
                operaciones[i] = new JButton(operadores[i]);
                pBloqueOperadores.add(operaciones[i]);
                operaciones[i].setBounds(x, y, 30, 35);
                operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                operaciones[i].addActionListener(this);
                x += 33;
                c++;
            } else {
                if (i == 6) {
                    x = 0;
                    y += 43;
                    operaciones[i] = new JButton(operadores[i]);
                    pBloqueOperadores.add(operaciones[i]);
                    operaciones[i].setBounds(x, y, 65, 35);
                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x += 33;
                    c++;
                } else {
                    c = 0;
                    x = 0;
                    y += 43;
                    operaciones[i] = new JButton(operadores[i]);
                    pBloqueOperadores.add(operaciones[i]);
                    operaciones[i].setBounds(x, y, 30, 35);
                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x += 33;
                    c++;
                }
            }
        }
        pBloqueOperadores.setVisible(true);
        pBloqueOperadores.setSize(120, 200);
    }
/**
 * Método que evalua si es un número
 * @param ax para verificar el dato numerico
 * @return boolean
 */
    public boolean isN(String ax) {
        try {
            int n = Integer.parseInt(ax);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
//***********************************
/**
 * Método que recoje los datos introducidos por la calculadora
 * @param e 
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        String op = "";
        if (isN(e.getActionCommand())) { //cuando se oprimen numeros
            if (jTexto1.getText().equals("")) {
                setTexto(e);   // 
            } else {
                if (tipOperador == 0) {
                    if (swNumero) {
                        axioma = "";
                        jTexto1.setText(jTexto2.getText());
                        setTexto(e);
                        swNumero = false;
                    } else {
                        getTexto(e);
                    }
                } else {
                    getTexto(e);
                }
            }
        } else {//cuando se oprime el resto de botones

            if (e.getActionCommand().equals("R")) {  //muestra la raiz cuadrada
                raizCuadrada();
            }
            if (e.getActionCommand().equals("C")) {   //limpia la calculadora              
                clearCalculadora();
            }
            if (e.getActionCommand().equals("MC")) {  // limpia la memoria almacenada             
                clearMemory();
            }
            if (e.getActionCommand().equals("MR")) {   // muestra los datos de memoria             
                mostrarMemory();
            }
            if (e.getActionCommand().equals("MS")) {    //guarda en memoria            
                saveMemory();
            }
            if (e.getActionCommand().equals("M+")) {    // suma los valores en memoria           
                sumaMemory();
            }
            if (e.getActionCommand().equals("M-")) {    // resta los valores en memoria              
                restaMemory();
            }
            if (e.getActionCommand().equals(".")) {//usar el punto para los decimales
                axioma = "";
                if (numeros[10].isEnabled()) {
                    numeros[10].setEnabled(false);
                    axioma = jTexto2.getText() + ".";
                    jTexto2.setText(axioma);
                }
            }
            if (e.getActionCommand().equals("+")) {//cuando se decide sumar
                setOperationSuma();
            }
            if (e.getActionCommand().equals("-")) {//cuando se decide restar
                setOperationResta();
            }
            if (e.getActionCommand().equals("*")) {//cuando se decide multiplicar
                setOperationMultiplica();
            }
            if (e.getActionCommand().equals("/")) {//cuando se decide dividir
                setOperationDivide();
            }
            if (e.getActionCommand().equals("=") && !jTexto2.getText().equals("")) {
                swNumero = true;
                operation(tipOperador);  // cálcula la operación
            }
        }
    }
/**
 * Método que realiza la Suma
 */
    public void setOperationSuma(){
        numeros[10].setEnabled(true);
        axioma = "";
        if (tipOperador == 1) {
        } else {
            lineaAxioma("+");
            tipOperador = 1;
        }
    }
/**
 * Método que realiza la Resta
 */
    public void setOperationResta() {
        numeros[10].setEnabled(true);
        axioma = "";
        if (tipOperador == 2) {
        } else {
            lineaAxioma("-");
            tipOperador = 2;
        }
    }
/**
 * Método que realiza la Multiplicación
 */
    public void setOperationMultiplica() {
        numeros[10].setEnabled(true);
        axioma = "";
        if (tipOperador == 3) {
        } else {
            lineaAxioma("*");
            tipOperador = 3;
        }
    }
/**
 * Método que realiza la División
 */
    public void setOperationDivide()  {
        numeros[10].setEnabled(true);
        axioma = "";
        if (tipOperador == 4) {
        } else {
            lineaAxioma("/");
            tipOperador = 4;
        }
    }
/**
 * Método que muestra el número seleccionado
 * @param e valor ingresa desde la calculadorea
 */
    public void setTexto(ActionEvent e) {
        axioma += e.getActionCommand();
        jTexto2.setText(axioma);
    }
/**
 * Método que muestra el número seleccionado
 * @param e valor ingresa desde la calculadorea
 */
    public void getTexto(ActionEvent e) {
        axioma = "";
        axioma += jTexto2.getText() + e.getActionCommand();
        jTexto2.setText(axioma);
    }
/**
 * Método que restar el valor de la pantalla con el valor de la memoria
 */
    public void restaMemory() throws NumberFormatException {
        //restar valor de la pantalla con el valor de la memoria
        memoria -= Float.parseFloat(jTexto2.getText());
    }
/**
 * Método que suma valor de la pantalla con el valor de la memoria
 */
    public void sumaMemory()  {
        //sumar valor de la pantalla con el valor de la memoria
        memoria += Float.parseFloat(jTexto2.getText());
    }
/**
 * Método que guarda un valor en la memoria
 */
    public void saveMemory() {
        memoryStorage.setForeground(Color.red);
        memoria = Float.parseFloat(jTexto2.getText());
    }
/**
 * Método para mostrar valor almacenado en la memoria
 */
    public void mostrarMemory() {
        jTexto1.setText("");
        jTexto2.setText(String.valueOf(memoria));
    }
/**
 * Método para limpiar la memoria de la calculadora
 */
    public void clearMemory() {
        memoryStorage.setForeground(Color.black);
        jTexto1.setText("");
        jTexto2.setText("0");
        memoria = 0;
    }
/**
 * Método que reinicia valores y limpia la pantalla
 */
    public void clearCalculadora() {
        tipOperador = 0;
        numeroIng1 = 0;
        numeroIng2 = 0;
        numeroResultante = 0;
        jTexto1.setText("");
        jTexto2.setText("0");
        axioma = "";
    }
/**
 * Método que realiza el cálculo de la raiz cuadrada
 * 
 */
    public void raizCuadrada()  {
        jTexto1.setText("");
        Float a = Float.parseFloat(jTexto2.getText());
        jTexto2.setText("" + Math.sqrt(a));
    }
/**
 * Método que concatena la operación y la agrega al axioma
 * @param xOperador simbolo matematico
 * 
 */
    public void lineaAxioma(String xOperador) {
        if (tipOperador == 0) {//validacion para no chocar con otras operaciones
            if (jTexto1.getText().equals("")) {
                numeroIng1 = Float.parseFloat(jTexto2.getText());
                axioma += jTexto1.getText() + jTexto2.getText();
                jTexto1.setText(axioma + " " + xOperador + " ");
                jTexto2.setText("");
            } else {
                if (!swNumero) {//validacion para nueva operacion
                    numeroIng1 = Float.parseFloat(jTexto2.getText());
                    axioma += jTexto2.getText();
                    jTexto1.setText(axioma + " " + xOperador + " ");
                    jTexto2.setText("");
                } else {//usar otras operaciones con la suma
                    numeroIng1 = Float.parseFloat(jTexto2.getText());
                    axioma += jTexto1.getText();
                    jTexto1.setText(axioma + " " + xOperador + " ");
                    jTexto2.setText("");
                }
            }
        }
    }
/**
 * Método que realiza la operación matemática entre 2 numeros
 * 
 * @param xOperacion operador matematico +,-,/,*
 *   
 */
    public void operation(int xOperacion)  {
        //operacion para la suma
        tipOperador = 0;
        axioma = "";
        axioma += jTexto1.getText() + jTexto2.getText();
        jTexto1.setText(axioma);
        numeroIng2 = Float.parseFloat(jTexto2.getText());
        if (xOperacion == 1) { // suma
            numeroResultante = numeroIng1 + numeroIng2;
        } else if (xOperacion == 2) {  //resta
            numeroResultante = numeroIng1 - numeroIng2;
        } else if (xOperacion == 3) {  //multiplica
            numeroResultante = numeroIng1 * numeroIng2;
        } else { // divide
            numeroResultante = numeroIng1 / numeroIng2;
        }
        jTexto2.setText(String.valueOf(numeroResultante));
    }
}
