package modelo;

import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import static java.sql.JDBCType.NULL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import modelo.ContarLineas;
import modelo.Ver_Imprimir;

/**
 *
 * @author Luis Fernando Paxel
 */
public class Control extends javax.swing.JFrame {

    ContarLineas Contar;
    JFileChooser chooser = new JFileChooser();
    File archivo;
    Ver_Imprimir impresora;
    private File ficheroa;
    fondopanel fondo1 = new fondopanel();

    /////////////////////////////////////////////////
    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errores;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    public Control() {
        this.setContentPane(fondo1);

        initComponents();
        init();
        setLocationRelativeTo(null);
        Contar = new ContarLineas(TexPane22);
        jScrollPane4.setRowHeaderView(Contar);
        //    TexPane22.setLineWrap(true);
        //   colors();
        //    TextareaLineas.setWrapStyleWord(true);

    }

    //NUMERADOR YA LO TIENE EL PROYECTO FINAL - PERO INCLUYE LA PARTE DEL TIMER Y COLOREADO
    private void init() {
        title = "Prueba"; //INICIA NUMERACIÓN
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, TexPane22, title, ".html");
        addWindowListener(new WindowAdapter() {
            //@Override
            public void WindowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        //Functions.setLineNumberOnJTextComponent(TexPane22); //TERMINA NUMERACIÓN
        /*timerKeyReleased = new Timer(300, ((e) -> {
            timerKeyReleased.stop();
            analisisColor();
        }));*/  //INICIA TIMER A LA HORA DE SOLTAR TECLA
        Functions.setLineNumberOnJTextComponent(TexPane22); //Pone los numeros de linea
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();

            int posicion = TexPane22.getCaretPosition();
            TexPane22.setText(TexPane22.getText().replaceAll("[\r]+", ""));
            TexPane22.setCaretPosition(posicion);

            analisisColor();

        });
        Functions.insertAsteriskInName(this, TexPane22, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errores = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{"<html>\n</html>", "<body>\n\t</body>", "<head>\n\t</head>",
            "oeste", "sur", "norte", "pintar"}, TexPane22, () -> {                          //FUNCION AUTOCOMPLETAR
            timerKeyReleased.restart();
        });

    }

    private void ClearFields() {
        tokens.clear();
        errores.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;

    }

    private void analisisColor() {
        textsColor.clear();
        AnalizadorColor analizador;

        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = TexPane22.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            analizador = new AnalizadorColor(entrada);
            while (true) {
                TextColor textColor = analizador.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, TexPane22, new Color(250, 250, 250));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TexPane22 = new javax.swing.JTextPane();
        jPanel5 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnColor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaGrafico = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemAbrir = new javax.swing.JMenuItem();
        itemNuevo = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        itemGuardarcomo = new javax.swing.JMenuItem();
        itemImprimir = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemCopiar = new javax.swing.JMenuItem();
        itemPegar = new javax.swing.JMenuItem();
        itemDesacher = new javax.swing.JMenuItem();
        itemEliminar = new javax.swing.JMenuItem();
        itemReemplazar = new javax.swing.JMenuItem();
        iteSeleccionar = new javax.swing.JMenuItem();
        itemBuscar = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        itemAlejar = new javax.swing.JMenuItem();
        itemAcercar = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 954, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        jPanel3.setLayout(null);

        TexPane22.setBackground(new java.awt.Color(51, 51, 51));
        TexPane22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        TexPane22.setForeground(new java.awt.Color(255, 255, 255));
        TexPane22.setCaretColor(new java.awt.Color(255, 255, 255));
        TexPane22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TexPane22KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(TexPane22);

        jPanel3.add(jScrollPane4);
        jScrollPane4.setBounds(0, 0, 1000, 320);

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3), "Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 3, 20), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setForeground(new java.awt.Color(0, 204, 204));

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Open Sans Semibold", 1, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 102, 102));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/trash_1.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLimpiar.setIconTextGap(10);
        btnLimpiar.setSelected(true);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir.setFont(new java.awt.Font("Open Sans Semibold", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 102, 102));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logout.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalir.setIconTextGap(20);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnColor.setBackground(new java.awt.Color(255, 255, 255));
        btnColor.setFont(new java.awt.Font("Open Sans Semibold", 1, 18)); // NOI18N
        btnColor.setForeground(new java.awt.Color(0, 102, 102));
        btnColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh.png"))); // NOI18N
        btnColor.setText("Color");
        btnColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnColor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnColor.setIconTextGap(18);
        btnColor.setInheritsPopupMenu(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnColor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        txtAreaGrafico.setBackground(new java.awt.Color(51, 51, 51));
        txtAreaGrafico.setColumns(20);
        txtAreaGrafico.setForeground(new java.awt.Color(255, 255, 255));
        txtAreaGrafico.setRows(5);
        txtAreaGrafico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        txtAreaGrafico.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(txtAreaGrafico);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(465, 465, 465)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 204, 204));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setFont(new java.awt.Font("Ebrima", 1, 15)); // NOI18N

        jMenu1.setBackground(new java.awt.Color(0, 153, 153));
        jMenu1.setBorder(null);
        jMenu1.setForeground(new java.awt.Color(0, 0, 0));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/folder.png"))); // NOI18N
        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jMenu1.setIconTextGap(10);

        itemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemAbrir.setBackground(new java.awt.Color(204, 255, 255));
        itemAbrir.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        itemAbrir.setForeground(new java.awt.Color(0, 0, 0));
        itemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/folder.png"))); // NOI18N
        itemAbrir.setText("Abrir");
        itemAbrir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        itemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(itemAbrir);

        itemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemNuevo.setBackground(new java.awt.Color(255, 255, 255));
        itemNuevo.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        itemNuevo.setForeground(new java.awt.Color(0, 0, 0));
        itemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add-file.png"))); // NOI18N
        itemNuevo.setText("Nuevo");
        itemNuevo.setBorder(null);
        itemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevo);

        itemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemGuardar.setBackground(new java.awt.Color(204, 255, 255));
        itemGuardar.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        itemGuardar.setForeground(new java.awt.Color(0, 0, 0));
        itemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/diskette.png"))); // NOI18N
        itemGuardar.setText("Guardar");
        itemGuardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(itemGuardar);

        itemGuardarcomo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemGuardarcomo.setBackground(new java.awt.Color(255, 255, 255));
        itemGuardarcomo.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        itemGuardarcomo.setForeground(new java.awt.Color(0, 0, 0));
        itemGuardarcomo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/diskette (1).png"))); // NOI18N
        itemGuardarcomo.setText("Guardar como");
        itemGuardarcomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarcomoActionPerformed(evt);
            }
        });
        jMenu1.add(itemGuardarcomo);

        itemImprimir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemImprimir.setBackground(new java.awt.Color(204, 255, 255));
        itemImprimir.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        itemImprimir.setForeground(new java.awt.Color(0, 0, 0));
        itemImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/printer.png"))); // NOI18N
        itemImprimir.setText("Imprimir");
        itemImprimir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        itemImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemImprimirActionPerformed(evt);
            }
        });
        jMenu1.add(itemImprimir);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_END, 0));
        itemSalir.setBackground(new java.awt.Color(255, 255, 255));
        itemSalir.setFont(new java.awt.Font("Dialog", 3, 13)); // NOI18N
        itemSalir.setForeground(new java.awt.Color(0, 0, 0));
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/arrow.png"))); // NOI18N
        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(0, 153, 153));
        jMenu2.setBorder(null);
        jMenu2.setForeground(new java.awt.Color(0, 0, 0));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit.png"))); // NOI18N
        jMenu2.setText("Edición");
        jMenu2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jMenu2.setIconTextGap(12);

        itemCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemCopiar.setBackground(new java.awt.Color(204, 255, 255));
        itemCopiar.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        itemCopiar.setForeground(new java.awt.Color(0, 0, 0));
        itemCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/documents.png"))); // NOI18N
        itemCopiar.setText("Copiar");
        itemCopiar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jMenu2.add(itemCopiar);

        itemPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemPegar.setBackground(new java.awt.Color(255, 255, 255));
        itemPegar.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        itemPegar.setForeground(new java.awt.Color(0, 0, 0));
        itemPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/files.png"))); // NOI18N
        itemPegar.setText("Pegar");
        jMenu2.add(itemPegar);

        itemDesacher.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemDesacher.setBackground(new java.awt.Color(204, 255, 255));
        itemDesacher.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        itemDesacher.setForeground(new java.awt.Color(0, 0, 0));
        itemDesacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/undo.png"))); // NOI18N
        itemDesacher.setText("Desacher");
        itemDesacher.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jMenu2.add(itemDesacher);

        itemEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemEliminar.setBackground(new java.awt.Color(255, 255, 255));
        itemEliminar.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        itemEliminar.setForeground(new java.awt.Color(0, 0, 0));
        itemEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/trash.png"))); // NOI18N
        itemEliminar.setText("Eliminar");
        jMenu2.add(itemEliminar);

        itemReemplazar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemReemplazar.setBackground(new java.awt.Color(204, 255, 255));
        itemReemplazar.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        itemReemplazar.setForeground(new java.awt.Color(0, 0, 0));
        itemReemplazar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/switch.png"))); // NOI18N
        itemReemplazar.setText("Reemplazar");
        itemReemplazar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        itemReemplazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReemplazarActionPerformed(evt);
            }
        });
        jMenu2.add(itemReemplazar);

        iteSeleccionar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        iteSeleccionar.setBackground(new java.awt.Color(255, 255, 255));
        iteSeleccionar.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        iteSeleccionar.setForeground(new java.awt.Color(0, 0, 0));
        iteSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/select-all.png"))); // NOI18N
        iteSeleccionar.setText("Seleccionar todo");
        jMenu2.add(iteSeleccionar);

        itemBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemBuscar.setBackground(new java.awt.Color(204, 255, 255));
        itemBuscar.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        itemBuscar.setForeground(new java.awt.Color(0, 0, 0));
        itemBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/magnifying-glass.png"))); // NOI18N
        itemBuscar.setText("Buscar");
        itemBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        itemBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarActionPerformed(evt);
            }
        });
        jMenu2.add(itemBuscar);

        jMenuBar1.add(jMenu2);

        jMenu4.setBackground(new java.awt.Color(0, 153, 153));
        jMenu4.setForeground(new java.awt.Color(0, 0, 0));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/simple-data-format.png"))); // NOI18N
        jMenu4.setText("Formato");
        jMenu4.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jMenu4.setIconTextGap(12);

        jMenuItem14.setBackground(new java.awt.Color(204, 255, 255));
        jMenuItem14.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem14.setText("Formato");
        jMenu4.add(jMenuItem14);

        jMenuBar1.add(jMenu4);

        jMenu5.setBackground(new java.awt.Color(0, 153, 153));
        jMenu5.setForeground(new java.awt.Color(0, 0, 0));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        jMenu5.setText("Ver");
        jMenu5.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jMenu5.setIconTextGap(12);

        jMenu3.setBackground(new java.awt.Color(204, 255, 255));
        jMenu3.setForeground(new java.awt.Color(0, 0, 0));
        jMenu3.setText("Zoom");

        itemAlejar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemAlejar.setBackground(new java.awt.Color(204, 255, 255));
        itemAlejar.setForeground(new java.awt.Color(0, 0, 0));
        itemAlejar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/zoom-out.png"))); // NOI18N
        itemAlejar.setText("Alejar");
        jMenu3.add(itemAlejar);

        itemAcercar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PLUS, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemAcercar.setBackground(new java.awt.Color(204, 255, 255));
        itemAcercar.setForeground(new java.awt.Color(0, 0, 0));
        itemAcercar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/zoom-out.png"))); // NOI18N
        itemAcercar.setText("Acercar");
        itemAcercar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        itemAcercar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAcercarActionPerformed(evt);
            }
        });
        jMenu3.add(itemAcercar);

        jMenu5.add(jMenu3);

        jMenuBar1.add(jMenu5);

        jMenu6.setBackground(new java.awt.Color(0, 153, 153));
        jMenu6.setForeground(new java.awt.Color(0, 0, 0));
        jMenu6.setText("Ayuda");
        jMenu6.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jMenu6.setIconTextGap(12);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem17.setBackground(new java.awt.Color(204, 255, 255));
        jMenuItem17.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/question.png"))); // NOI18N
        jMenuItem17.setText("Acerca de ayuda");
        jMenu6.add(jMenuItem17);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem18.setBackground(new java.awt.Color(204, 255, 255));
        jMenuItem18.setForeground(new java.awt.Color(0, 0, 0));
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/testing.png"))); // NOI18N
        jMenuItem18.setText("Versión de la app");
        jMenuItem18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jMenu6.add(jMenuItem18);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void itemAcercarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcercarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemAcercarActionPerformed

    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".html", ".txt", "html", "txt");
        fc.setFileFilter(filtro);
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fc.showOpenDialog(this.getContentPane());
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            ficheroa = fc.getSelectedFile();
            try (FileReader fr = new FileReader(this.ficheroa)) {
                String cadena = "";
                int valor = fr.read();
                while (valor != -1) {
                    cadena += (char) valor;
                    valor = fr.read();
                }
                TexPane22.setText(cadena);
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }//GEN-LAST:event_itemAbrirActionPerformed
//METODO PARA ENCONTRAR LAS ULTIMAS CADENAS

    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            //  \\W = [A-Za-Z0-9]
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    //METODO PARA ENCONTRAR LAS PRIMERAS CADENAS 
    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

//METODO PARA PINTAS LAS PALABRAS RESEVADAS
    private void colors() {

        final StyleContext cont = StyleContext.getDefaultStyleContext();
        //COLORES 
        final AttributeSet attred = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);
        final AttributeSet attgreen = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0, 255, 54));
        final AttributeSet attblue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0, 147, 255));
        final AttributeSet attblack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0, 0, 0));

        //STYLO 
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }

                int after = findFirstNonWordChar(text, offset + str.length());

                int wordL = before;
                int wordR = before;

                /*
                while (wordR <= after) {

                    //if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                    if (text.substring(wordL, wordR).matches("(\\W)*(/|<|>)")) {
                        setCharacterAttributes(wordL, wordR - wordL, attblue, false);

                    }
                    wordL = wordR;

                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(html|title|body)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attred, false);
                        }
                    }

                    // } 
                    wordR++;
                }
                 */
                // VER COMO IMPLEMENTAR EL METODO PARA QUE RECONOZCA LETRAS Y SIMBOLOZ     
            }

            @Override

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
            }
        };

        JTextPane txt = new JTextPane(doc);
        String temp = TexPane22.getText();

        TexPane22.setStyledDocument(txt.getStyledDocument());
        TexPane22.setText(temp);

    }


    private void itemGuardarcomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarcomoActionPerformed
        // TODO add your handling code here:

        abrirGuardar();
    }//GEN-LAST:event_itemGuardarcomoActionPerformed

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed
        // TODO add your handling code here:
        if (ficheroa == null) {
            abrirGuardar();
        } else {
            escribirFichero();
        }
    }//GEN-LAST:event_itemGuardarActionPerformed
    private void abrirGuardar() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".txt", "txt");
        fc.setFileFilter(filtro);
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fc.showSaveDialog(this.getContentPane());
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            ficheroa = fc.getSelectedFile();
            escribirFichero();

        }
    }

    private void escribirFichero() {
        try (FileWriter fw = new FileWriter(ficheroa)) {
            fw.write(TexPane22.getText());
        } catch (IOException ex) {
            Logger.getLogger(Control.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void itemImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemImprimirActionPerformed
        // TODO add your handling code here:
        String AreaEntrada = this.TexPane22.getText();
        String AreaSalida = this.txtAreaGrafico.getText();
        String[] options = {"Imprimir Entrada", "Imprimir Salida"};
        ImageIcon icon = new ImageIcon("src/Imagenes/Impresoras.png");
        String n = (String) JOptionPane.showInputDialog(null, "Opciones",
                "Impresora", JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

        if (n != null) {

            switch (n) {

                case "Imprimir Entrada":
                    impresora = new Ver_Imprimir(AreaEntrada);
                    impresora.imprimir();
                    break;

                case "Imprimir Salida":
                    impresora = new Ver_Imprimir(AreaSalida);
                    impresora.imprimir();

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Por favor elija una opcion correcta ", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Imagenes/Error1.png"));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cancelado ", "Cancelar", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("src/Imagenes/Error1.png"));

        }

    }//GEN-LAST:event_itemImprimirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        TexPane22.setText("");
        txtAreaGrafico.setText("");
        //    String lineas = TextareaLineas.getText();
        //    System.out.println("lineas\n" + lineas);

    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoActionPerformed
        TexPane22.setText("");
        ficheroa = null;

    }//GEN-LAST:event_itemNuevoActionPerformed

    class resaltador extends DefaultHighlighter.DefaultHighlightPainter {

        public resaltador(Color color) {
            super(color);
        }
    }

    Highlighter.HighlightPainter resaltador = new resaltador(Color.orange);

    public void quitarResaltado(JTextComponent txt) {
        Highlighter res = txt.getHighlighter();
        Highlighter.Highlight[] resa = res.getHighlights();
        for (Highlighter.Highlight resa1 : resa) {
            res.removeHighlight(resa1);
        }

    }

    public void resaltar(JTextComponent txt, String palabra) {
        quitarResaltado(TexPane22);
        try {
            Highlighter res = txt.getHighlighter();
            Document doc = txt.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;
            while ((pos = text.toUpperCase().indexOf(palabra.toUpperCase(), pos)) >= 0) {

                res.addHighlight(pos, pos + palabra.length(), resaltador);
                pos += palabra.length();
            }
        } catch (BadLocationException ex) {
        }
    }
    private void itemBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarActionPerformed
        // TODO add your handling code here:
        int conta = 0;
        String elemento;
        StringTokenizer palabras = new StringTokenizer(TexPane22.getText());
        String buscar = JOptionPane.showInputDialog(rootPane, "Ingrese la palabra que desea buscar:", "Buscar", JOptionPane.PLAIN_MESSAGE);

        while (palabras.hasMoreElements()) {
            elemento = palabras.nextToken();
            if (elemento.toLowerCase().contains(buscar.toLowerCase())) {
                conta++;
            }
        }
        JOptionPane.showMessageDialog(rootPane, Integer.toString(conta) + " coincidencias encontradas", "Coincidencias", JOptionPane.INFORMATION_MESSAGE);
        resaltar(TexPane22, buscar);
    }//GEN-LAST:event_itemBuscarActionPerformed

    private void itemReemplazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemReemplazarActionPerformed
        int conta = 1;
        String buscar, elemento, reemplazar, resultado;
        StringTokenizer palabras = new StringTokenizer(TexPane22.getText());
        buscar = JOptionPane.showInputDialog(rootPane, "Ingrese la palabra que desea buscar:", "Buscar", JOptionPane.PLAIN_MESSAGE);
        while (palabras.hasMoreElements()) {
            elemento = palabras.nextToken();
            if (elemento.equals(buscar) || elemento.equals(buscar + ".") || elemento.equals(buscar + ",") || elemento.equals(buscar + ";") || elemento.equals(buscar + ":")) {
                conta++;
            }
        }
        reemplazar = JOptionPane.showInputDialog(rootPane, Integer.toString(conta) + " coincidencias encontradas\n" + "Con que palabra desea reemplazar el texto buscado:", "Reemplazar", JOptionPane.PLAIN_MESSAGE);

        elemento = TexPane22.getText();
        resultado = elemento.replace(buscar, reemplazar);
        TexPane22.setText("");
        TexPane22.setText(resultado);

    }//GEN-LAST:event_itemReemplazarActionPerformed

    private void TexPane22KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TexPane22KeyReleased
        // TODO add your handling code here:
        int keyCode = evt.getKeyCode();
        if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 48 && keyCode <= 57)
                || (keyCode >= 97 && keyCode <= 122) || (keyCode != 27 && !(keyCode >= 37
                && keyCode <= 40) && !(keyCode >= 16
                && keyCode <= 18) && keyCode != 524
                && keyCode != 20)) {

            if (!getTitle().contains("*")) {
                setTitle(getTitle() + "*");
            }
        }
    }//GEN-LAST:event_TexPane22KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Control.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Control.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Control.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Control.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Control().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane TexPane22;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenuItem iteSeleccionar;
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemAcercar;
    private javax.swing.JMenuItem itemAlejar;
    private javax.swing.JMenuItem itemBuscar;
    private javax.swing.JMenuItem itemCopiar;
    private javax.swing.JMenuItem itemDesacher;
    private javax.swing.JMenuItem itemEliminar;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JMenuItem itemGuardarcomo;
    private javax.swing.JMenuItem itemImprimir;
    private javax.swing.JMenuItem itemNuevo;
    private javax.swing.JMenuItem itemPegar;
    private javax.swing.JMenuItem itemReemplazar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea txtAreaGrafico;
    // End of variables declaration//GEN-END:variables
 class fondopanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("fondo33.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
