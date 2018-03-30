
package proyecto;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.net.*;
import java.lang.Object;
import java.lang.String;
import javax.swing.table.DefaultTableModel;

public class Proyecto {

        private MiPanel panel = new MiPanel();
        private JFrame pantalla,aux;
        private JDialog aux3;
        private JTextField nusuario,aux2,vector[];
        private JPasswordField cusuario;
        private JButton aceptar,salir;
        private JLabel usuario, contrasena;
        private JCheckBox check,check2;
        private Connection cone;
        
        public Proyecto()
        {    
                panel.setBounds(0,0,600,210);        
                pantalla = new JFrame("SistemaVial");
                pantalla.setSize(600,210);
                pantalla.setLocation(500,80);
                pantalla.setLayout(null);
                pantalla.setResizable(false);
                
                usuario = new JLabel("Usuario:");
                usuario.setBounds(40,20,100,50);
                        
                nusuario = new JTextField(30);
                nusuario.setEditable(true);
                nusuario.setBounds(140,38,320,24);
                nusuario.setHorizontalAlignment(JTextField.LEFT);
                
                contrasena = new JLabel("Contraseña:");
                contrasena.setBounds(30,60,100,50);
              
                cusuario = new JPasswordField(30);
                cusuario.setEditable(true);
                cusuario.setBounds(140,78,320,24);
                cusuario.setHorizontalAlignment(JPasswordField.LEFT);
                
                aceptar = new JButton("Aceptar");
                aceptar.setBounds(140,110,100,30);
                aceptar.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aceptar_un_usuario(evt);}});
                aceptar.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aceptar_un_usuario(ev);}});
                
                salir = new JButton("Salir");
                salir.setBounds(360,110,100,30);
                salir.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {salir(evt);}});
                salir.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { salir(ev);}});
                
                pantalla.add(usuario);
                pantalla.add(nusuario);
                pantalla.add(contrasena);
                pantalla.add(cusuario);
                pantalla.add(aceptar);
                pantalla.add(salir);
                pantalla.add(panel);
                pantalla.setVisible(true);
        }

        public void salir(KeyEvent evt)
        {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                    pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(0);
            }
                   
        }
        
        public void salir(ActionEvent evt)
        {
             pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             System.exit(0);
        }
        
        public void aceptar_un_usuario(KeyEvent evt)
        {
            MiPanel p = new MiPanel();
            Conexion conex = new Conexion();
            JDialog usuario = new JDialog();
            JButton eliminarc, eliminart, eliminarg;
            JButton cargarc, cargart, cargarg, cargarp, atras;
            JButton reporte1,reporte2,verg,verc;
            JLabel opciones,opciones2;
            
            char[] arrayC = cusuario.getPassword();
            String pass = new String(arrayC);
            
            cone = conex.hacerConexion(nusuario.getText(),pass);
            
            if(cone != null)
            {
               
              p.setBounds(0,0,300,600);
              usuario.setTitle(nusuario.getText());
              usuario.setLocation(500,80);
              usuario.setLayout(null);
              usuario.setResizable(false);
             
              atras = new JButton("Atras");
              
              if(nusuario.getText().equals("coordinador_vial2") )
              {
                
                 opciones = new JLabel("Opciones del coordinador");
                 eliminarc = new JButton("Eliminar Ciudadano");
                 eliminart = new JButton("Eliminar Taller");
                 eliminarg = new JButton("Eliminar Grua");
                 cargarc = new JButton("Cargar Ciudadano");
                 cargart = new JButton("Cargar Taller"); 
                 cargarg = new JButton("Cargar Grua");
                 reporte1 = new JButton("Reporte1");
                 reporte2 = new JButton("Reporte2");
                 
                 usuario.setSize(240,580);
                 opciones.setBounds(25,10,200,30);
                 eliminarc.setBounds(20,50,200,40);
                 eliminart.setBounds(20,100,200,40);
                 eliminarg.setBounds(20,150,200,40);
                 cargarc.setBounds(20,200,200,40);
                 cargart.setBounds(20,250,200,40);
                 cargarg.setBounds(20,300,200,40);
                 reporte1.setBounds(20,350,200,40);
                 reporte2.setBounds(20,400,200,40);
                 atras.setBounds(20,450,200,40);
                 
                 eliminarc.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Eliminar_ciudadano(ev);}});
                 eliminarc.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_ciudadano(evt);}});
                 eliminart.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { Eliminar_taller(ev);}});
                 eliminart.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_taller(evt);}});
                 eliminarg.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { Eliminar_grua(ev);}});
                 eliminarg.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_grua(evt);}});
                 cargarc.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_ciudadano(ev);}});
                 cargarc.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_ciudadano(evt);}});
                 cargart.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_taller(ev);}});
                 cargart.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_taller(evt);}});
                 cargarg.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { Cargar_grua(ev);}});
                 cargarg.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_grua(evt);}});
                 reporte1.addActionListener( new ActionListener(){@Override public void actionPerformed(ActionEvent ev) {try {Generar_reporte1(ev);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                 reporte1.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {try {Generar_reporte1(evt);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                 reporte2.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {try {Generar_reporte2(ev);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                 reporte2.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {try {Generar_reporte2(evt);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                 atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux3.setVisible(false);}});
                 atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux3.setVisible(false);}});
                 
                 usuario.add(opciones);
                 usuario.add(eliminarc);
                 usuario.add(eliminart);
                 usuario.add(eliminarg);
                 usuario.add(cargarc);
                 usuario.add(cargart);
                 usuario.add(cargarg);
                 usuario.add(reporte1);
                 usuario.add(reporte2);
         
              }
              else
              {
                 if(nusuario.getText().equals("operador_vial2")){
                    opciones2 = new JLabel("Opciones del operador");
                    cargarp = new JButton("Cargar Percance");
                    verg = new JButton("Ver Gruas");
                    verc = new JButton("Ver Ciudadanos");

                    usuario.setSize(240,340);
                    opciones2.setBounds(25,10,200,30);
                    cargarp.setBounds(20,50,200,40);
                    verg.setBounds(20,100,200,40);
                    verc.setBounds(20,150,200,40);
                    atras.setBounds(20,200,200,40);

                    atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux3.setVisible(false);}});
                    atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux3.setVisible(false);}});
                    cargarp.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cargar_percance(evt);}});
                    cargarp.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {cargar_percance(ev);}});
                    verg.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {try {ver_grua(evt);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                    verg.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {try {ver_grua(ev);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                    verc.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {try {ver_ciudadano(evt);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                    verc.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {try {ver_ciudadano(ev);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});

                    usuario.add(opciones2);
                    usuario.add(cargarp);
                    usuario.add(verg);
                    usuario.add(verc);
                 }
                 else{
                 
                 JOptionPane.showMessageDialog(null, "Usuario No Valido");
                 }
              }
                                   
              usuario.add(atras);
              usuario.add(p);
              usuario.setVisible(true);
            }
            aux3 = usuario;   
        }

        public void aceptar_un_usuario(ActionEvent ev)
        {
            MiPanel p = new MiPanel();
            Conexion conex = new Conexion();
            JDialog usuario = new JDialog();
            JButton eliminarc, eliminart, eliminarg;
            JButton cargarc, cargart, cargarg, atras, cargarp;
            JButton reporte1, reporte2, verg, verc;
            JLabel opciones,opciones2;
            
            char[] arrayC = cusuario.getPassword();
            String pass = new String(arrayC);
            cone = conex.hacerConexion(nusuario.getText(),pass);
            
            if(cone != null)
            {
              p.setBounds(0,0,300,600);
              usuario.setTitle(nusuario.getText());
              usuario.setLocation(500,80);
              usuario.setLayout(null);
              usuario.setResizable(false);
              
              atras = new JButton("Atras");
              
              if(nusuario.getText().equals("coordinador_vial2"))
              {
                 opciones = new JLabel("Opciones del coordinador");
                 eliminarc = new JButton("Eliminar Ciudadano");
                 eliminart = new JButton("Eliminar Taller");
                 eliminarg = new JButton("Eliminar Grua");
                 cargarc = new JButton("Cargar Ciudadano");
                 cargart = new JButton("Cargar Taller"); 
                 cargarg = new JButton("Cargar Grua");
                 reporte1 = new JButton("Reporte1");
                 reporte2 = new JButton("Reporte2");
                 
                 usuario.setSize(240,580);
                 opciones.setBounds(25,10,200,30);
                 eliminarc.setBounds(20,50,200,40);
                 eliminart.setBounds(20,100,200,40);
                 eliminarg.setBounds(20,150,200,40);
                 cargarc.setBounds(20,200,200,40);
                 cargart.setBounds(20,250,200,40);
                 cargarg.setBounds(20,300,200,40);
                 reporte1.setBounds(20,350,200,40);
                 reporte2.setBounds(20,400,200,40);
                 atras.setBounds(20,450,200,40);
                 
                 
                 eliminarc.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Eliminar_ciudadano(ev);}});
                 eliminarc.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_ciudadano(evt);}});
                 eliminart.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { Eliminar_taller(ev);}});
                 eliminart.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_taller(evt);}});
                 eliminarg.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { Eliminar_grua(ev);}});
                 eliminarg.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_grua(evt);}});
                 cargarc.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_ciudadano(ev);}});
                 cargarc.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_ciudadano(evt);}});
                 cargart.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_taller(ev);}});
                 cargart.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_taller(evt);}});
                 cargarg.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { Cargar_grua(ev);}});
                 cargarg.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_grua(evt);}});
                 reporte1.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {try {Generar_reporte1(ev);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                 reporte1.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {try {Generar_reporte1(evt);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                 reporte2.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {try {Generar_reporte2(ev);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                 reporte2.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {try {Generar_reporte2(evt);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                 atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux3.setVisible(false);}});
                 atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux3.setVisible(false);}});
                 
                 usuario.add(opciones);
                 usuario.add(eliminarc);
                 usuario.add(eliminart);
                 usuario.add(eliminarg);
                 usuario.add(cargarc);
                 usuario.add(cargart);
                 usuario.add(cargarg);
                 usuario.add(reporte1);
                 usuario.add(reporte2);
              }
              else
              {
                 if(nusuario.getText().equals("operador_vial2")){
                    opciones2 = new JLabel("Opciones del operador");
                    cargarp = new JButton("Cargar Percance");
                    verg = new JButton("Ver Gruas");
                    verc = new JButton("Ver Ciudadanos");

                    usuario.setSize(240,340);
                    opciones2.setBounds(25,10,200,30);
                    cargarp.setBounds(20,50,200,40);
                    verg.setBounds(20,100,200,40);
                    verc.setBounds(20,150,200,40);
                    atras.setBounds(20,200,200,40);

                    atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux3.setVisible(false);}});
                    atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux3.setVisible(false);}});
                    cargarp.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cargar_percance(evt);}});
                    cargarp.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {cargar_percance(ev);}});
                    verg.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {try {ver_grua(evt);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                    verg.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {try {ver_grua(ev);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                    verc.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {try {ver_ciudadano(evt);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});
                    verc.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {try {ver_ciudadano(ev);} catch (SQLException ex) {Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);}}});

                    usuario.add(opciones2);
                    usuario.add(cargarp);
                    usuario.add(verg);
                    usuario.add(verc);
                 }else{
                 JOptionPane.showMessageDialog(null, "Usuario No Valido");
                 }
              }
              usuario.add(atras);
              usuario.add(p);
              usuario.setVisible(true);
            } 
            aux3 = usuario;
        }

      
        public void Eliminar_ciudadano(ActionEvent ev)
        {
           MiPanel p = new MiPanel();
           JFrame eliminarc = new JFrame("Eliminar Ciudadano");
           JTextField ingci = new JTextField(30);
           JLabel inst = new JLabel("Ingrese la CEDULA del usuario a eliminar"); 
           JButton acept = new JButton("Eliminar");
           JButton cancel = new JButton("Atras");
           
           acept.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_ciudadanoAC(evt);}});
           acept.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { Eliminar_ciudadanoAC(ev);}});
           cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
           cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
           
           eliminarc.setSize(350,180);
           eliminarc.setLocation(500,80);
           eliminarc.setLayout(null);
           eliminarc.setResizable(false);
           
           p.setBounds(0,0,350,180);
           inst.setBounds(20,10,300,30);
           ingci.setBounds(20,40,300,20);
           acept.setBounds(20,70,100,40);
           cancel.setBounds(218,70,100,40);
               
           ingci.setEditable(true);
           ingci.setHorizontalAlignment(JTextField.LEFT);
                      
           eliminarc.add(inst);
           eliminarc.add(ingci);
           eliminarc.add(acept);
           eliminarc.add(cancel);
           eliminarc.add(p);
           eliminarc.setVisible(true);
           aux = eliminarc;
           aux2 = ingci;
       }
        
        public void Eliminar_ciudadano(KeyEvent evt)
        {
           MiPanel p = new MiPanel();
           JFrame eliminarc = new JFrame("Eliminar Ciudadano");
           JTextField ingci = new JTextField(30);
           JLabel inst = new JLabel("Ingrese la CEDULA del usuario a eliminar"); 
           JButton acept = new JButton("Eliminar");
           JButton cancel = new JButton("Atras");
           
           acept.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_ciudadanoAC(evt);}});
           acept.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Eliminar_ciudadanoAC(ev);}});
           cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
           cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});              
                 
           eliminarc.setSize(350,180);
           eliminarc.setLocation(500,80);
           eliminarc.setLayout(null);
           eliminarc.setResizable(false);

           p.setBounds(0,0,350,180);
           inst.setBounds(20,10,300,30);
           ingci.setBounds(20,40,300,20);
           acept.setBounds(20,70,100,40);
           cancel.setBounds(218,70,100,40);
          
           ingci.setEditable(true);
           ingci.setHorizontalAlignment(JTextField.LEFT);
                      
           eliminarc.add(inst);
           eliminarc.add(ingci);
           eliminarc.add(acept);
           eliminarc.add(cancel);
           eliminarc.add(p);
           eliminarc.setVisible(true);
           aux = eliminarc;
           aux2 = ingci;    
        }
        
        public void Eliminar_taller(ActionEvent ev)
        {
           MiPanel p = new MiPanel();
           JFrame eliminarc = new JFrame("Eliminar Taller");
           JTextField ingci = new JTextField(30);
           JLabel inst = new JLabel("Ingrese el ID del taller a eliminar"); 
           JButton acept = new JButton("Eliminar");
           JButton cancel = new JButton("Atras");
                                    
           eliminarc.setSize(350,180);
           eliminarc.setLocation(500,80);
           eliminarc.setLayout(null);
           eliminarc.setResizable(false);
    
           acept.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_tallerAC(evt);}});
           acept.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Eliminar_tallerAC(ev);}});
           cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
           cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
                  
           p.setBounds(0,0,350,180);
           inst.setBounds(20,10,300,30);
           ingci.setBounds(20,40,300,20);
           acept.setBounds(20,70,100,40);
           cancel.setBounds(218,70,100,40);
          
           ingci.setEditable(true);
           ingci.setHorizontalAlignment(JTextField.LEFT);
                      
           eliminarc.add(inst);
           eliminarc.add(ingci);
           eliminarc.add(acept);
           eliminarc.add(cancel);
           eliminarc.add(p);
           eliminarc.setVisible(true);
           aux = eliminarc;
           aux2 = ingci;
        }
        
        public void Eliminar_taller(KeyEvent evt)
        {
           MiPanel p = new MiPanel();
           JFrame eliminarc = new JFrame("Eliminar Taller");
           JTextField ingci = new JTextField(30);
           JLabel inst = new JLabel("Ingrese el ID del taller a eliminar"); 
           JButton acept = new JButton("Eliminar");
           JButton cancel = new JButton("Atras");
           
           eliminarc.setSize(350,180);
           eliminarc.setLocation(500,80);
           eliminarc.setLayout(null);
           eliminarc.setResizable(false);
            
           acept.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_tallerAC(evt);}});
           acept.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Eliminar_tallerAC(ev);}});
           cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
           cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
                        
           p.setBounds(0,0,350,180);
           inst.setBounds(20,10,300,30);
           ingci.setBounds(20,40,300,20);
           acept.setBounds(20,70,100,40);
           cancel.setBounds(218,70,100,40);
          
           ingci.setEditable(true);
           ingci.setHorizontalAlignment(JTextField.LEFT);
                      
           eliminarc.add(inst);
           eliminarc.add(ingci);
           eliminarc.add(acept);
           eliminarc.add(cancel);
           eliminarc.add(p);
           eliminarc.setVisible(true);
           aux = eliminarc;
           aux2 = ingci;
        }
        
        public void Eliminar_grua(ActionEvent ev)
        {
           MiPanel p = new MiPanel();
           JFrame eliminarc = new JFrame("Eliminar Grua");
           JTextField ingci = new JTextField(30);
           JLabel inst = new JLabel("Ingrese la PLACA de la grua a eliminar"); 
           JButton acept = new JButton("Eliminar");
           JButton cancel = new JButton("Atras");
        
           acept.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_gruaAC(evt);}});
           acept.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) { Eliminar_gruaAC(ev);}});
           cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
           cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
                         
           eliminarc.setSize(350,180);
           eliminarc.setLocation(500,80);
           eliminarc.setLayout(null);
           eliminarc.setResizable(false);
                       
           p.setBounds(0,0,350,180);
           inst.setBounds(20,10,300,30);
           ingci.setBounds(20,40,300,20);
           acept.setBounds(20,70,100,40);
           cancel.setBounds(218,70,100,40);
          
           ingci.setEditable(true);
           ingci.setHorizontalAlignment(JTextField.LEFT);
                      
           eliminarc.add(inst);
           eliminarc.add(ingci);
           eliminarc.add(acept);
           eliminarc.add(cancel);
           eliminarc.add(p);
           eliminarc.setVisible(true);
           aux = eliminarc;
           aux2 = ingci;
        }
        
        public void Eliminar_grua(KeyEvent evt)
        {
           MiPanel p = new MiPanel();
           JFrame eliminarc = new JFrame("Eliminar Grua");
           JTextField ingci = new JTextField(30);
           JLabel inst = new JLabel("Ingrese la PLACA de la grua a eliminar"); 
           JButton acept = new JButton("Eliminar");
           JButton cancel = new JButton("Atras"); 
           
           acept.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Eliminar_gruaAC(evt);}});
           acept.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Eliminar_gruaAC(ev);}});
           cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
           cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
                     
           eliminarc.setSize(350,180);
           eliminarc.setLocation(500,80);
           eliminarc.setLayout(null);
           eliminarc.setResizable(false);
              
           ingci.setEditable(true);
           ingci.setHorizontalAlignment(JTextField.LEFT);
                       
           p.setBounds(0,0,350,180);
           inst.setBounds(20,10,300,30);
           ingci.setBounds(20,40,300,20);
           acept.setBounds(20,70,100,40);
           cancel.setBounds(218,70,100,40);
          
           eliminarc.add(inst);
           eliminarc.add(ingci);
           eliminarc.add(acept);
           eliminarc.add(cancel);
           eliminarc.add(p);
           eliminarc.setVisible(true);
           aux = eliminarc;
           aux2 = ingci;
        }
        
        public void Eliminar_ciudadanoAC(KeyEvent evt)
        {      
                  Conexion bd = new Conexion();
                  int eli;
                  
                  eli = bd.ejecutarSQL("DELETE FROM pro.ciudadano  WHERE ci_ciu = "+aux2.getText()+";",cone);
               
                  if(eli > 0)
                    JOptionPane.showMessageDialog(null,"Ciudadano Eliminado");
                  else
                   JOptionPane.showMessageDialog(null,"No se encontro el ciudadano");
        }
        
        public void Eliminar_ciudadanoAC(ActionEvent evt)
        {
               Conexion bd = new Conexion();
               int eli;
               
               eli = bd.ejecutarSQL("DELETE FROM pro.ciudadano WHERE ci_ciu = "+aux2.getText()+";",cone);
               
               
               if(eli > 0)
                    JOptionPane.showMessageDialog(null,"Ciudadano Eliminado");
               else
                   JOptionPane.showMessageDialog(null,"No se encontro el ciudadano");
      
        }
        
        public void Eliminar_tallerAC(KeyEvent evt)
        {
               Conexion bd = new Conexion();
               int eli;
               
               eli = bd.ejecutarSQL("DELETE FROM pro.taller  WHERE id = '"+aux2.getText()+"';",cone);
               
               if(eli > 0)
                    JOptionPane.showMessageDialog(null,"Taller Eliminado");
               else
                   JOptionPane.showMessageDialog(null,"No se encontro el Taller");
          
        }
        
        public void Eliminar_tallerAC(ActionEvent evt)
        {
               Conexion bd = new Conexion();
               int eli;
               
               eli = bd.ejecutarSQL("DELETE FROM pro.taller  WHERE id = '"+aux2.getText()+"';",cone);

               if(eli > 0)
                    JOptionPane.showMessageDialog(null,"Taller Eliminado");
               else
                   JOptionPane.showMessageDialog(null,"No se encontro el Taller");
          
        }
        
        public void Eliminar_gruaAC(KeyEvent evt)
        {
               Conexion bd = new Conexion();
               int eli;
              
               eli = bd.ejecutarSQL("DELETE FROM pro.grua  WHERE placa = '"+aux2.getText()+"';",cone);
              
               if(eli > 0)
                    JOptionPane.showMessageDialog(null,"Grua Eliminada");
               else
                   JOptionPane.showMessageDialog(null,"No se encontro la grua");
              
        }
        
        public void Eliminar_gruaAC(ActionEvent evt)
        {
               Conexion bd = new Conexion();
               int eli;
               
               eli = bd.ejecutarSQL("DELETE FROM pro.grua WHERE placa = '"+aux2.getText()+"';",cone);
        
               if(eli > 0)
                    JOptionPane.showMessageDialog(null,"Grua Eliminada");
               else
                   JOptionPane.showMessageDialog(null,"No se encontro la grua");
              
        }
        
        public void Cargar_ciudadano(ActionEvent ev)
        {
               MiPanel p = new MiPanel();
               JLabel presentacion = new JLabel("Ingrese los datos del nuevo ciudadano");
               JLabel cedula = new JLabel("Cedula:");
               JLabel nombre = new JLabel("Nombre:");
               JLabel telefono = new JLabel("Telefono:");
               JLabel edad = new JLabel("Edad:");
               JTextField ingcedula = new JTextField(30);
               JTextField ingnombre = new JTextField(30);
               JTextField ingtelefono = new JTextField(30);
               JTextField ingedad = new JTextField(15);
               JButton cargar = new JButton("Cargar");
               JButton cancel = new JButton("Atras");
               JFrame cargaru = new JFrame("Cargar Ciudadano");     
               vector = new JTextField[5];
               
               cargaru.setSize(350,255);
               cargaru.setLocation(500,80);
               cargaru.setLayout(null);
               cargaru.setResizable(false);

               cargar.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_ciudadanoAC(evt);}});
               cargar.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_ciudadanoAC(ev);}});
               cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
               cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
  
               p.setBounds(0,0,350,255);
               presentacion.setBounds(20,10,300,30);
               cedula.setBounds(20,45,100,30);
               ingcedula.setBounds(100,50,200,20);
               nombre.setBounds(20,75,100,30);
               ingnombre.setBounds(100,80,200,20);
               telefono.setBounds(20,105,100,30);
               ingtelefono.setBounds(100,110,200,20);
               edad.setBounds(20,135,100,30);
               ingedad.setBounds(100,140,200,20);
               cargar.setBounds(20,175,150,40);
               cancel.setBounds(180,175,150,40);
               
               ingcedula.setEditable(true);
               ingcedula.setHorizontalAlignment(JTextField.LEFT);
       
               ingnombre.setEditable(true);
               ingnombre.setHorizontalAlignment(JTextField.LEFT);
                  
               ingtelefono.setEditable(true);
               ingtelefono.setHorizontalAlignment(JTextField.LEFT);
                  
               ingedad.setEditable(true);
               ingedad.setHorizontalAlignment(JTextField.LEFT);
                  
               vector[0] = ingcedula;
               vector[1] = ingnombre;
               vector[2] = ingtelefono;
               vector[3] = ingedad;
                            
               cargaru.add(presentacion);
               cargaru.add(cedula);
               cargaru.add(ingcedula);
               cargaru.add(nombre);
               cargaru.add(ingnombre);
               cargaru.add(telefono);
               cargaru.add(ingtelefono);
               cargaru.add(edad);
               cargaru.add(ingedad);
               cargaru.add(cargar);
               cargaru.add(cancel);
               cargaru.add(p);
               cargaru.setVisible(true);
               aux = cargaru;
        }

        public void Cargar_ciudadano(KeyEvent ev)
        {
               MiPanel p = new MiPanel();
               JLabel presentacion = new JLabel("Ingrese los datos del nuevo ciudadano");
               JLabel cedula = new JLabel("Cedula");
               JLabel nombre = new JLabel("Nombre");
               JLabel telefono = new JLabel("Telefono");
               JLabel edad = new JLabel("Edad");
               JTextField ingcedula = new JTextField(30);
               JTextField ingnombre = new JTextField(30);
               JTextField ingtelefono = new JTextField(30);
               JTextField ingedad = new JTextField(15);
               JButton cargar = new JButton("Cargar");
               JButton cancel = new JButton("Atras");
               JFrame cargaru = new JFrame("Cargar Ciudadano");     
               vector = new JTextField[5];
               
               cargaru.setSize(350,255);
               cargaru.setLocation(500,80);
               cargaru.setLayout(null);
               cargaru.setResizable(false);

               cargar.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_ciudadanoAC(evt);}});
               cargar.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_ciudadanoAC(ev);}});
               cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
               cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
               
               p.setBounds(0,0,350,255);
               presentacion.setBounds(20,10,300,30);
               cedula.setBounds(20,45,100,30);
               ingcedula.setBounds(100,50,200,20);
               nombre.setBounds(20,75,100,30);
               ingnombre.setBounds(100,80,200,20);
               telefono.setBounds(20,105,100,30);
               ingtelefono.setBounds(100,110,200,20);
               edad.setBounds(20,135,100,30);
               ingedad.setBounds(100,140,200,20);
               cargar.setBounds(20,175,150,40);
               cancel.setBounds(180,175,150,40);
               
               ingcedula.setEditable(true);
               ingcedula.setHorizontalAlignment(JTextField.LEFT);
       
               ingnombre.setEditable(true);
               ingnombre.setHorizontalAlignment(JTextField.LEFT);
                  
               ingtelefono.setEditable(true);
               ingtelefono.setHorizontalAlignment(JTextField.LEFT);
                  
               ingedad.setEditable(true);
               ingedad.setHorizontalAlignment(JTextField.LEFT);
                  
               vector[0] = ingcedula;
               vector[1] = ingnombre;
               vector[2] = ingtelefono;
               vector[3] = ingedad;
                            
               cargaru.add(presentacion);
               cargaru.add(cedula);
               cargaru.add(ingcedula);
               cargaru.add(nombre);
               cargaru.add(ingnombre);
               cargaru.add(telefono);
               cargaru.add(ingtelefono);
               cargaru.add(edad);
               cargaru.add(ingedad);
               cargaru.add(cargar);
               cargaru.add(cancel);
               cargaru.add(p);
               cargaru.setVisible(true);
               aux = cargaru;
        }
        
        public void Cargar_taller(ActionEvent ev)
        {
               MiPanel p = new MiPanel();
               JCheckBox chequear = new JCheckBox("Cauchos");
               JCheckBox chequear2 = new JCheckBox("Tiempo");
               JLabel presentacion = new JLabel("Ingrese los datos del nuevo taller");
               JLabel id = new JLabel("ID:");
               JLabel nombre = new JLabel("Nombre Encargado:");
               JLabel tipo = new JLabel("Tipo:");
               JTextField ingid = new JTextField(30);
               JTextField ingnombre = new JTextField(30);
               JTextField ingtipo = new JTextField(30);
               JTextField ingtiempo = new JTextField(30);
               JTextField ingcauchos = new JTextField(30);
               JButton cargar = new JButton("Cargar");
               JButton cancel = new JButton("Atras");
               JFrame cargaru = new JFrame("Cargar Taller");     
               vector = new JTextField[5];
               
               cargaru.setSize(450,290);
               cargaru.setLocation(500,80);
               cargaru.setLayout(null);
               cargaru.setResizable(false);
               
               cargar.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_tallerAC(evt);}});
               cargar.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_tallerAC(ev);}});
               chequear.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Bloquear_rejilla(ev);}}); 
               chequear2.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Bloquear_rejilla2(ev);}});
               cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
               cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
            
               p.setBounds(0,0,450,290);
               presentacion.setBounds(20,10,300,30);
               id.setBounds(20,45,100,30);
               ingid.setBounds(200,50,200,20);
               nombre.setBounds(20,75,150,30);
               ingnombre.setBounds(200,80,200,20);
               tipo.setBounds(20,105,100,30);
               ingtipo.setBounds(200,110,200,20);
               chequear2.setBounds(20,135,100,30);
               ingtiempo.setBounds(200,140,200,20);
               chequear.setBounds(20,165,86,30);
               ingcauchos.setBounds(200,170,200,20);
               cargar.setBounds(20,195,150,40);
               cancel.setBounds(180,195,150,40);
               
               ingcauchos.setEditable(false);
               ingcauchos.setHorizontalAlignment(JTextField.LEFT);                

               ingtiempo.setEditable(false);
               ingtiempo.setHorizontalAlignment(JTextField.LEFT);               

               ingid.setEditable(true);
               ingid.setHorizontalAlignment(JTextField.LEFT);
       
               ingnombre.setEditable(true);
               ingnombre.setHorizontalAlignment(JTextField.LEFT);
                  
               ingtipo.setEditable(true);
               ingtipo.setHorizontalAlignment(JTextField.LEFT);
                  
               vector[0] = ingid;
               vector[1] = ingnombre;
               vector[2] = ingtipo;
               vector[3] = ingtiempo;
               vector[4] = ingcauchos;
               
               chequear.add(p);
               chequear2.add(p);
               cargaru.add(presentacion);
               cargaru.add(id);
               cargaru.add(ingid);
               cargaru.add(nombre);
               cargaru.add(ingnombre);
               cargaru.add(tipo);
               cargaru.add(ingtipo);
               cargaru.add(chequear2);
               cargaru.add(ingtiempo);
               cargaru.add(chequear);
               cargaru.add(ingcauchos);
               cargaru.add(cargar);
               cargaru.add(cancel);
               cargaru.add(p);
               cargaru.setVisible(true);
               aux = cargaru;
               check = chequear;
               check2 = chequear2;
        }
       
        public void Cargar_taller(KeyEvent evt)
        {
               MiPanel p = new MiPanel();
               JCheckBox chequear = new JCheckBox("Cauchos");
               JCheckBox chequear2 = new JCheckBox("Tiempo");
               JLabel presentacion = new JLabel("Ingrese los datos del nuevo taller");
               JLabel id = new JLabel("ID:");
               JLabel nombre = new JLabel("Nombre Encargado:");
               JLabel tipo = new JLabel("Tipo:");
               JTextField ingid = new JTextField(30);
               JTextField ingnombre = new JTextField(30);
               JTextField ingtipo = new JTextField(30);
               JTextField ingtiempo = new JTextField(30);
               JTextField ingcauchos = new JTextField(30);
               JButton cargar = new JButton("Cargar");
               JButton cancel = new JButton("Atras");
               JFrame cargaru = new JFrame("Cargar Taller");     
               vector = new JTextField[5];
               
               cargaru.setSize(450,290);
               cargaru.setLocation(500,80);
               cargaru.setLayout(null);
               cargaru.setResizable(false);
               
               cargar.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_tallerAC(evt);}});
               cargar.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_tallerAC(ev);}});
               chequear.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Bloquear_rejilla(ev);}}); 
               chequear2.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Bloquear_rejilla2(ev);}});
               cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
               cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
                           
               p.setBounds(0,0,450,290);
               presentacion.setBounds(20,10,300,30);
               id.setBounds(20,45,100,30);
               ingid.setBounds(200,50,200,20);
               nombre.setBounds(20,75,150,30);
               ingnombre.setBounds(200,80,200,20);
               tipo.setBounds(20,105,100,30);
               ingtipo.setBounds(200,110,200,20);
               chequear2.setBounds(20,135,100,30);
               ingtiempo.setBounds(200,140,200,20);
               chequear.setBounds(20,165,86,30);
               ingcauchos.setBounds(200,170,200,20);
               cargar.setBounds(20,195,150,40);
               cancel.setBounds(180,195,150,40);
               
               ingcauchos.setEditable(false);
               ingcauchos.setHorizontalAlignment(JTextField.LEFT);                
              
               ingtiempo.setEditable(false);
               ingtiempo.setHorizontalAlignment(JTextField.LEFT);               

               ingid.setEditable(true);
               ingid.setHorizontalAlignment(JTextField.LEFT);
       
               ingnombre.setEditable(true);
               ingnombre.setHorizontalAlignment(JTextField.LEFT);
                  
               ingtipo.setEditable(true);
               ingtipo.setHorizontalAlignment(JTextField.LEFT);
                  
               vector[0] = ingid;
               vector[1] = ingnombre;
               vector[2] = ingtipo;
               vector[3] = ingtiempo;
               vector[4] = ingcauchos;
               
               chequear.add(p);
               chequear2.add(p);
               cargaru.add(presentacion);
               cargaru.add(id);
               cargaru.add(ingid);
               cargaru.add(nombre);
               cargaru.add(ingnombre);
               cargaru.add(tipo);
               cargaru.add(ingtipo);
               cargaru.add(chequear2);
               cargaru.add(ingtiempo);
               cargaru.add(chequear);
               cargaru.add(ingcauchos);
               cargaru.add(cargar);
               cargaru.add(cancel);
               cargaru.add(p);
               cargaru.setVisible(true);
               aux = cargaru;
               check = chequear;
               check2 = chequear2;
        }
        
        public void Cargar_grua(ActionEvent ev)
        {
            MiPanel p = new MiPanel();
            JLabel presentacion = new JLabel("Ingrese los datos de la nueva grua");
            JLabel placa = new JLabel("Placa:");
            JLabel marca = new JLabel("Marca:");
            JLabel serial_ca = new JLabel("Serial Carroceria:");
            JLabel serial_cha = new JLabel("Serial Chasis:");
            JLabel carga = new JLabel("Carga:");
            JLabel modelo = new JLabel("Modelo:");
            JLabel tamano = new JLabel("Tamaño:");
            JLabel ano = new JLabel("Año de la grua:");
            JLabel tipo_g = new JLabel("Tipo de grua:");
            JTextField ingplaca = new JTextField();
            JTextField ingmarca = new JTextField();
            JTextField ingserial_ca = new JTextField();
            JTextField ingserial_cha = new JTextField();
            JTextField ingcarga = new JTextField();
            JTextField ingmodelo = new JTextField();
            JTextField ingtamano = new JTextField();
            JTextField ingano = new JTextField();
            JTextField ingtipo_g = new JTextField();
            JButton cargar = new JButton("Cargar");
            JButton cancel = new JButton("Atras");
            JFrame cargaru = new JFrame("Cargar Grua");     
            vector = new JTextField[9];
               
            cargaru.setSize(400,400);
            cargaru.setLocation(500,80);
            cargaru.setLayout(null);
            cargaru.setResizable(false);
            
            p.setBounds(0,0,400,400);
            presentacion.setBounds(20,10,300,30);
            placa.setBounds(20,45,100,30);
            ingplaca.setBounds(170,50,200,20);
            marca.setBounds(20,75,100,30);
            ingmarca.setBounds(170,80,200,20);
            serial_ca.setBounds(20,105,150,30);
            ingserial_ca.setBounds(170,110,200,20);
            serial_cha.setBounds(20,135,150,30);
            ingserial_cha.setBounds(170,140,200,20);
            carga.setBounds(20,165,86,30);
            ingcarga.setBounds(170,170,200,20);
            modelo.setBounds(20,195,86,30);
            ingmodelo.setBounds(170,200,200,20);
            tamano.setBounds(20,225,86,30);
            ingtamano.setBounds(170,230,200,20);
            ano.setBounds(20,255,150,30);
            ingano.setBounds(170,260,200,20);
            tipo_g.setBounds(20,285,150,30);
            ingtipo_g.setBounds(170,290,200,20);
            cargar.setBounds(20,315,150,40);
            cancel.setBounds(180,315,150,40);

            cargar.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_gruaAC(evt);}});
            cargar.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_gruaAC(ev);}});
            cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
            cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
               
            ingplaca.setEditable(true);
            ingplaca.setHorizontalAlignment(JTextField.LEFT);
            
            ingmarca.setEditable(true);
            ingmarca.setHorizontalAlignment(JTextField.LEFT);   
            
            ingserial_ca.setEditable(true);
            ingserial_ca.setHorizontalAlignment(JTextField.LEFT);    
            
            ingserial_cha.setEditable(true);
            ingserial_cha.setHorizontalAlignment(JTextField.LEFT);    
            
            ingcarga.setEditable(true);
            ingcarga.setHorizontalAlignment(JTextField.LEFT);
                  
            ingmodelo.setEditable(true);
            ingmodelo.setHorizontalAlignment(JTextField.LEFT);
                  
            ingtamano.setEditable(true);
            ingtamano.setHorizontalAlignment(JTextField.LEFT);
                  
            ingano.setEditable(true);
            ingano.setHorizontalAlignment(JTextField.LEFT);
                  
            ingtipo_g.setEditable(true);
            ingtipo_g.setHorizontalAlignment(JTextField.LEFT);
            
            vector[0] = ingplaca;
            vector[1] = ingmarca;
            vector[2] = ingserial_ca;
            vector[3] = ingserial_cha;
            vector[4] = ingcarga;
            vector[5] = ingmodelo;
            vector[6] = ingtamano;
            vector[7] = ingano;
            vector[8] = ingtipo_g;
        
            cargaru.add(presentacion);
            cargaru.add(placa);
            cargaru.add(ingplaca);
            cargaru.add(marca);
            cargaru.add(ingmarca);
            cargaru.add(serial_ca);
            cargaru.add(ingserial_ca);
            cargaru.add(serial_cha);
            cargaru.add(ingserial_cha);
            cargaru.add(carga);
            cargaru.add(ingcarga);
            cargaru.add(modelo);
            cargaru.add(ingmodelo);
            cargaru.add(tamano);
            cargaru.add(ingtamano);
            cargaru.add(ano);
            cargaru.add(ingano);
            cargaru.add(tipo_g);
            cargaru.add(ingtipo_g);
            cargaru.add(cargar);
            cargaru.add(cancel);
            cargaru.add(p);
            cargaru.setVisible(true);
            aux = cargaru;
        }

        public void Cargar_grua(KeyEvent evt)
        {
            MiPanel p = new MiPanel();
            JLabel presentacion = new JLabel("Ingrese los datos de la nueva grua");
            JLabel placa = new JLabel("Placa:");
            JLabel marca = new JLabel("Marca:");
            JLabel serial_ca = new JLabel("Serial Carroceria:");
            JLabel serial_cha = new JLabel("Serial Chasis:");
            JLabel carga = new JLabel("Carga:");
            JLabel modelo = new JLabel("Modelo:");
            JLabel tamano = new JLabel("Tamaño:");
            JLabel ano = new JLabel("Año de la grua:");
            JLabel tipo_g = new JLabel("Tipo de grua:");
            JTextField ingplaca = new JTextField();
            JTextField ingmarca = new JTextField();
            JTextField ingserial_ca = new JTextField();
            JTextField ingserial_cha = new JTextField();
            JTextField ingcarga = new JTextField();
            JTextField ingmodelo = new JTextField();
            JTextField ingtamano = new JTextField();
            JTextField ingano = new JTextField();
            JTextField ingtipo_g = new JTextField();
            JButton cargar = new JButton("Cargar");
            JButton cancel = new JButton("Atras");
            JFrame cargaru = new JFrame("Cargar Grua");     
            vector = new JTextField[9];
               
            cargaru.setSize(400,400);
            cargaru.setLocation(500,80);
            cargaru.setLayout(null);
            cargaru.setResizable(false);
            
            p.setBounds(0,0,400,400);
            presentacion.setBounds(20,10,300,30);
            placa.setBounds(20,45,100,30);
            ingplaca.setBounds(170,50,200,20);
            marca.setBounds(20,75,100,30);
            ingmarca.setBounds(170,80,200,20);
            serial_ca.setBounds(20,105,150,30);
            ingserial_ca.setBounds(170,110,200,20);
            serial_cha.setBounds(20,135,150,30);
            ingserial_cha.setBounds(170,140,200,20);
            carga.setBounds(20,165,86,30);
            ingcarga.setBounds(170,170,200,20);
            modelo.setBounds(20,195,86,30);
            ingmodelo.setBounds(170,200,200,20);
            tamano.setBounds(20,225,86,30);
            ingtamano.setBounds(170,230,200,20);
            ano.setBounds(20,255,150,30);
            ingano.setBounds(170,260,200,20);
            tipo_g.setBounds(20,285,150,30);
            ingtipo_g.setBounds(170,290,200,20);
            cargar.setBounds(20,315,150,40);
            cancel.setBounds(180,315,150,40);

            cargar.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {Cargar_gruaAC(evt);}});
            cargar.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {Cargar_gruaAC(ev);}});
            cancel.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
            cancel.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}});
               
            ingplaca.setEditable(true);
            ingplaca.setHorizontalAlignment(JTextField.LEFT);
            
            ingmarca.setEditable(true);
            ingmarca.setHorizontalAlignment(JTextField.LEFT);   
            
            ingserial_ca.setEditable(true);
            ingserial_ca.setHorizontalAlignment(JTextField.LEFT);    
            
            ingserial_cha.setEditable(true);
            ingserial_cha.setHorizontalAlignment(JTextField.LEFT);    
            
            ingcarga.setEditable(true);
            ingcarga.setHorizontalAlignment(JTextField.LEFT);
                  
            ingmodelo.setEditable(true);
            ingmodelo.setHorizontalAlignment(JTextField.LEFT);
                  
            ingtamano.setEditable(true);
            ingtamano.setHorizontalAlignment(JTextField.LEFT);
                  
            ingano.setEditable(true);
            ingano.setHorizontalAlignment(JTextField.LEFT);
                  
            ingtipo_g.setEditable(true);
            ingtipo_g.setHorizontalAlignment(JTextField.LEFT);
            
            vector[0] = ingplaca;
            vector[1] = ingmarca;
            vector[2] = ingserial_ca;
            vector[3] = ingserial_cha;
            vector[4] = ingcarga;
            vector[5] = ingmodelo;
            vector[6] = ingtamano;
            vector[7] = ingano;
            vector[8] = ingtipo_g;
        
            cargaru.add(presentacion);
            cargaru.add(placa);
            cargaru.add(ingplaca);
            cargaru.add(marca);
            cargaru.add(ingmarca);
            cargaru.add(serial_ca);
            cargaru.add(ingserial_ca);
            cargaru.add(serial_cha);
            cargaru.add(ingserial_cha);
            cargaru.add(carga);
            cargaru.add(ingcarga);
            cargaru.add(modelo);
            cargaru.add(ingmodelo);
            cargaru.add(tamano);
            cargaru.add(ingtamano);
            cargaru.add(ano);
            cargaru.add(ingano);
            cargaru.add(tipo_g);
            cargaru.add(ingtipo_g);
            cargaru.add(cargar);
            cargaru.add(cancel);
            cargaru.add(p);
            cargaru.setVisible(true);
            aux = cargaru;
        }
                
        public void Bloquear_rejilla(ActionEvent ev)
        {
            if(check.isSelected())
            {
                vector[4].setEditable(true);
                vector[4].setHorizontalAlignment(JTextField.LEFT);
            }
            else
            {
                vector[4].setEditable(false);
                vector[4].setHorizontalAlignment(JTextField.LEFT);                
            }
            
        }
        
        public void Bloquear_rejilla2(ActionEvent ev)
        {
            if(check2.isSelected())
            {
                vector[3].setEditable(true);
                vector[3].setHorizontalAlignment(JTextField.LEFT);
            }
            else
            {
                vector[3].setEditable(false);
                vector[3].setHorizontalAlignment(JTextField.LEFT);                
            }
            
        }
        
        public void Cargar_ciudadanoAC(KeyEvent evt)
        {
            Conexion bd = new Conexion();
            int car = -1;
             
            if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals(""))
                car = bd.ejecutarSQL("INSERT INTO proyecto.pro.ciudadano VALUES("+vector[0].getText()+",'"+vector[1].getText()+"',"+vector[3].getText()+","+vector[2].getText()+");",cone);
            
            if(car > 0)
                JOptionPane.showMessageDialog(null,"Ciudadano Cargado");
            else if(vector[0].getText().equals("") || vector[1].getText().equals("") || vector[2].getText().equals("") || vector[3].getText().equals(""))
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios");
            else
                JOptionPane.showMessageDialog(null,"No se pudo cargar el ciudadano");
        }
        
        public void Cargar_ciudadanoAC(ActionEvent evt)
        {
            Conexion bd = new Conexion();          
            int car = -1;
            
            if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals(""))
                car = bd.ejecutarSQL("INSERT INTO pro.ciudadano VALUES("+vector[0].getText()+",'"+vector[1].getText()+"',"+vector[3].getText()+","+vector[2].getText()+");",cone);
            
            if(car > 0)
                JOptionPane.showMessageDialog(null,"Ciudadano Cargado");
            else if(vector[0].getText().equals("") || vector[1].getText().equals("") || vector[2].getText().equals("") || vector[3].getText().equals(""))
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios");
            else
                JOptionPane.showMessageDialog(null,"No se pudo cargar el ciudadano");        
        }
        
        public void Cargar_tallerAC(KeyEvent evt)
        {   
            Conexion bd = new Conexion();
            int caucho = -1;
            int result = -1;
            
            if(!vector[4].getText().equals(""))
                caucho = Integer.parseInt(vector[4].getText());
                
            if(caucho <= 6)
            {
                if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && vector[3].getText().equals("") && vector[4].getText().equals(""))
                  result = bd.ejecutarSQL("INSERT INTO pro.taller VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','00:00:00',0);",cone);
                else if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals("") && !vector[4].getText().equals(""))
                    result = bd.ejecutarSQL("INSERT INTO pro.taller VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','"+vector[3].getText()+"',"+vector[4].getText()+");",cone);
                else if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals("") && vector[4].getText().equals(""))
                    result = bd.ejecutarSQL("INSERT INTO pro.taller VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','"+vector[3].getText()+"',0);",cone);
                else if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && vector[3].getText().equals("") && !vector[4].getText().equals(""))
                    result = bd.ejecutarSQL("INSERT INTO pro.taller VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','00:00:00',"+vector[4].getText()+");",cone);
                
                if(result > 0)
                    JOptionPane.showMessageDialog(null,"Taller Cargado");
                else if(vector[0].getText().equals("") || vector[1].getText().equals("") || vector[2].getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios sin contar cantidad de cauchos y el tiempo de pintado");
                else
                    JOptionPane.showMessageDialog(null,"No se pudo cargar el taller");
            }
            else
               JOptionPane.showMessageDialog(null, "Error: La cantidad minima de cauchos para un auto es de 6");
        }
       
        public void Cargar_tallerAC(ActionEvent ev)
        {
            Conexion bd = new Conexion();
            int caucho = -1;
            int result = -1;
            
            if(!vector[4].getText().equals(""))
                caucho = Integer.parseInt(vector[4].getText());
                
            if(caucho <= 6)
            {
                if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && vector[3].getText().equals("") && vector[4].getText().equals(""))
                  result = bd.ejecutarSQL("INSERT INTO pro.taller VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','00:00:00',0);",cone);
                else if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals("") && !vector[4].getText().equals(""))
                    result = bd.ejecutarSQL("INSERT INTO pro.taller VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','"+vector[3].getText()+"',"+vector[4].getText()+");",cone);
                else if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals("") && vector[4].getText().equals(""))
                    result = bd.ejecutarSQL("INSERT INTO pro.taller VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','"+vector[3].getText()+"',0);",cone);
                else if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && vector[3].getText().equals("") && !vector[4].getText().equals(""))
                    result = bd.ejecutarSQL("INSERT INTO pro.taller VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','00:00:00',"+vector[4].getText()+");",cone);
                
                if(result > 0)
                    JOptionPane.showMessageDialog(null,"Taller Cargado");
                else if(vector[0].getText().equals("") || vector[1].getText().equals("") || vector[2].getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios sin contar cantidad de cauchos y el tiempo de pintado");
                else
                    JOptionPane.showMessageDialog(null,"No se pudo cargar el taller");
            }
            else
               JOptionPane.showMessageDialog(null, "Error: La cantidad minima de cauchos para un auto es de 6");                  
        }
        
        public void Cargar_gruaAC(ActionEvent ev)
        {
            Conexion bd = new Conexion();          
            int car = -1;
            
            if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals("") && !vector[4].getText().equals("") && !vector[5].getText().equals("") && !vector[6].getText().equals("") && !vector[7].getText().equals("") && !vector[8].getText().equals(""))
                car = bd.ejecutarSQL("INSERT INTO pro.grua VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','"+vector[3].getText()+"',"+vector[4].getText()+",'"+vector[5].getText()+"',"+vector[6].getText()+","+vector[7].getText()+",'"+vector[8].getText()+"');",cone);
            
            if(car > 0)
                JOptionPane.showMessageDialog(null,"Grua cargada");
            else if(vector[0].getText().equals("") || vector[1].getText().equals("") || vector[2].getText().equals("") || vector[3].getText().equals("") || vector[4].getText().equals("") || vector[5].getText().equals("") || vector[6].getText().equals("") || vector[7].getText().equals("") || vector[8].getText().equals(""))
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios");
            else
                JOptionPane.showMessageDialog(null,"No se pudo cargar la grua");                    
        }
        
        public void Cargar_gruaAC(KeyEvent ev)
        {
            Conexion bd = new Conexion();          
            int car = -1;
            
            if(!vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals("") && !vector[4].getText().equals("") && !vector[5].getText().equals("") && !vector[6].getText().equals("") && !vector[7].getText().equals("") && !vector[8].getText().equals(""))
                car = bd.ejecutarSQL("INSERT INTO pro.grua VALUES('"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','"+vector[3].getText()+"',"+vector[4].getText()+",'"+vector[5].getText()+"',"+vector[6].getText()+","+vector[7].getText()+",'"+vector[8].getText()+"');",cone);
            
            if(car > 0)
                JOptionPane.showMessageDialog(null,"Grua cargada");
            else if(vector[0].getText().equals("") || vector[1].getText().equals("") || vector[2].getText().equals("") || vector[3].getText().equals("") || vector[4].getText().equals("") || vector[5].getText().equals("") || vector[6].getText().equals("") || vector[7].getText().equals("") || vector[8].getText().equals(""))
                JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios");
            else
                JOptionPane.showMessageDialog(null,"No se pudo cargar la grua");                               
        }
        
        public void Generar_reporte1(ActionEvent evt) throws SQLException
        {
                JLabel consulta1,consulta2,consulta3;
                JButton atras = new JButton("Atras");
                MiPanel p = new MiPanel();
                String consulta[] = new String[100];
                JFrame reporte1 = new JFrame("Reporte1");
                JTextArea area,area2,area3;
                Conexion bd = new Conexion();
                ResultSet list,list2,list3;
                int i = 0, j = 0, k = 0;
                
                consulta1 = new JLabel("Promedio de gruas solicitadas diariamente:");
                consulta2 = new JLabel("Municipios con mayor cantidad de accidentes:");
                consulta3 = new JLabel("Personas sin polizas que han sufrido percances:");
                Font auxFont1=consulta1.getFont(),auxFont2=consulta2.getFont(),auxFont3=consulta3.getFont() ;
                consulta1.setFont(new Font(auxFont1.getFontName(), auxFont1.getStyle(), 15));
                consulta2.setFont(new Font(auxFont2.getFontName(), auxFont2.getStyle(), 15));
                consulta3.setFont(new Font(auxFont3.getFontName(), auxFont3.getStyle(), 15));
                area = new JTextArea();
                area2 = new JTextArea();
                area3 = new JTextArea();
                list  = bd.obtenerResulset("select pro.promedio_fecha('01-01-2014','01-12-2014')",cone);
                list2 = bd.obtenerResulset("select y.municipio from(select count(p.municipio) as max_munc, p.municipio  from  pro.percance p group by (p.municipio)) as y,(select max(x.max_munc) as max_munc from(select count(p.municipio) as max_munc, p.municipio  from  pro.percance p group by (p.municipio)) as x) as w where y.max_munc=w.max_munc;",cone);
                list3 = bd.obtenerResulset("(select c.nombre_ciu,c.ci_ciu from pro.vehiculo v,pro.ciudadano c where v.placa not in(select p.placa from pro.vehiculo_tiene_poliza p) and c.ci_ciu = v.ci_ciu);",cone);
                
                if(list != null || list2 != null || list3 != null)
                {
                    while (list.next())
                    {
                     consulta[i] = "El promedio es:\n"+Float.toString(list.getFloat("promedio_fecha"));
                     area.append(consulta[i]+"\n");
                     i++;
                    }
                    
                    while (list2.next())
                    {
                     consulta[j] = list2.getString("municipio");
                     area2.append(consulta[j]+"\n");
                     j++;
                    }
                    
                    while (list3.next())
                    {
                     consulta[k] = Integer.toString(list3.getInt("ci_ciu"))+"  "+list3.getString("nombre_ciu");
                     area3.append(consulta[k]+"\n");
                     k++;
                    }   
                }
                
                area.setEditable(false);
                area2.setEditable(false);
                area3.setEditable(false);
                JScrollPane scrol,scrol2,scrol3;
                scrol  = new JScrollPane(area);
                scrol2 = new JScrollPane(area2);
                scrol3 = new JScrollPane(area3);

                atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
                atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
                
                p.setBounds(0,0,400,480);
                consulta1.setBounds(30,20,360,20);
                scrol.setBounds(60,40,250,70);
                consulta2.setBounds(20,140,360,20);
                scrol2.setBounds(60,160,250,70);
                consulta3.setBounds(20,260,360,20);
                scrol3.setBounds(60,280,250,70);
                atras.setBounds(120,360,140,40);
                
                reporte1.add(consulta1);
                reporte1.add(scrol);
                reporte1.add(consulta2);
                reporte1.add(scrol2);
                reporte1.add(consulta3);
                reporte1.add(scrol3);
                reporte1.add(atras);
                reporte1.setSize(400,450);
                reporte1.setLocation(500,80);
                reporte1.setLayout(null);
                reporte1.setResizable(false);
                reporte1.add(p);
                reporte1.setVisible(true);
                aux = reporte1;
        }      
        
        public void Generar_reporte1(KeyEvent ev) throws SQLException
        {
                JLabel consulta1,consulta2,consulta3;
                JButton atras = new JButton("Atras");
                MiPanel p = new MiPanel();
                String consulta[] = new String[100];
                JFrame reporte1 = new JFrame("Reporte1");
                JTextArea area,area2,area3;
                Conexion bd = new Conexion();
                ResultSet list,list2,list3;
                int i = 0, j = 0, k = 0;
                
                consulta1 = new JLabel("Promedio de gruas solicitadas diariamente:");
                consulta2 = new JLabel("Municipios con mayor cantidad de accidentes:");
                consulta3 = new JLabel("Personas sin polizas que han sufrido percances:");
                Font auxFont1=consulta1.getFont(),auxFont2=consulta2.getFont(),auxFont3=consulta3.getFont() ;
                consulta1.setFont(new Font(auxFont1.getFontName(), auxFont1.getStyle(), 15));
                consulta2.setFont(new Font(auxFont2.getFontName(), auxFont2.getStyle(), 15));
                consulta3.setFont(new Font(auxFont3.getFontName(), auxFont3.getStyle(), 15));
                area = new JTextArea();
                area2 = new JTextArea();
                area3 = new JTextArea();
                list  = bd.obtenerResulset("select pro.promedio_fecha('01-01-2014','01-12-2014')",cone);
                list2 = bd.obtenerResulset("select y.municipio from(select count(p.municipio) as max_munc, p.municipio  from  pro.percance p group by (p.municipio)) as y,(select max(x.max_munc) as max_munc from(select count(p.municipio) as max_munc, p.municipio  from  pro.percance p group by (p.municipio)) as x) as w where y.max_munc=w.max_munc;",cone);
                list3 = bd.obtenerResulset("(select c.nombre_ciu,c.ci_ciu from pro.vehiculo v,pro.ciudadano c where v.placa not in(select p.placa from pro.vehiculo_tiene_poliza p) and c.ci_ciu = v.ci_ciu);",cone);
                
                if(list != null || list2 != null || list3 != null)
                {
                    while (list.next())
                    {
                     consulta[i] = "El promedio es:\n"+Float.toString(list.getFloat("promedio_fecha"));;
                     area.append(consulta[i]+"\n");
                     i++;
                    }
                    
                    while (list2.next())
                    {
                     consulta[j] = list2.getString("municipio");
                     area2.append(consulta[j]+"\n");
                     j++;
                    }
                    
                    while (list3.next())
                    {
                     consulta[k] = Integer.toString(list3.getInt("ci_ciu"))+"  "+list3.getString("nombre_ciu");
                     area3.append(consulta[k]+"\n");
                     k++;
                    }   
                }
                
                area.setEditable(false);
                area2.setEditable(false);
                area3.setEditable(false);
                JScrollPane scrol,scrol2,scrol3;
                scrol  = new JScrollPane(area);
                scrol2 = new JScrollPane(area2);
                scrol3 = new JScrollPane(area3);

                atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
                atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
                
                p.setBounds(0,0,400,480);
                consulta1.setBounds(30,20,360,20);
                scrol.setBounds(60,40,250,70);
                consulta2.setBounds(20,140,360,20);
                scrol2.setBounds(60,160,250,70);
                consulta3.setBounds(20,260,360,20);
                scrol3.setBounds(60,280,250,70);
                atras.setBounds(120,360,140,40);
                
                reporte1.add(consulta1);
                reporte1.add(scrol);
                reporte1.add(consulta2);
                reporte1.add(scrol2);
                reporte1.add(consulta3);
                reporte1.add(scrol3);
                reporte1.add(atras);
                reporte1.setSize(400,450);
                reporte1.setLocation(500,80);
                reporte1.setLayout(null);
                reporte1.setResizable(false);
                reporte1.add(p);
                reporte1.setVisible(true);
                aux = reporte1;
        }
        
        public void Generar_reporte2(ActionEvent evt) throws SQLException
        {
             
                MiPanel p = new MiPanel();
                JLabel consulta4 = new JLabel("Siniestros de asegurados cuya poliza se vencieron en diciembre 2014");
                String consulta[] = new String[100];
                JFrame reporte2 = new JFrame("Reporte2");
                JTextArea area;
                Conexion bd = new Conexion();
                ResultSet list;
                JButton atras = new JButton("Atras");
                int i = 0, j = 0, k = 0;
                
                area = new JTextArea();
                list  = bd.obtenerResulset("select p.fecha, p.hora, p.ci_ciu, p.nro_persona_ac, p.ninos, p.causa, p.prioridad, p.nro_persona_ter, p.av_calle, p.municipio, p.parroquia, p.ciudad, p.km_aut, p.nombre_aut, p.calificacion  from (select c.ci_ciu from pro.vehiculo_tiene_poliza p,pro.ciudadano c,pro.vehiculo v where (p.f_vencimiento BETWEEN '2014-12-01' and '2014-12-31') and c.ci_ciu = v.ci_ciu and v.placa= p.placa) as x,pro.percance p where p.ci_ciu=x.ci_ciu  order by p.ci_ciu,p.fecha;",cone);
                
                if(list != null)
                {
                   
                    while (list.next())
                    {
                     consulta[i] =   list.getString("fecha")+"  "+list.getString("hora")+"  "+Integer.toString(list.getInt("ci_ciu"))+"  "+Integer.toString(list.getInt("nro_persona_ac"))+"  "+Integer.toString(list.getInt("ninos"))+"  "+list.getString("causa")+"  "+Integer.toString(list.getInt("prioridad"))+"  "+Integer.toString(list.getInt("nro_persona_ter"))+"  "+list.getString("av_calle")+"  "+list.getString("municipio")+"  "+list.getString("parroquia")+"  "+list.getString("ciudad")+"  "+Integer.toString(list.getInt("km_aut"))+"  "+list.getString("nombre_aut")+"  "+list.getString("calificacion");
                     area.append(consulta[i]+"\n");
                     i++;
                    }        
                }

                atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
                atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
                
                
                area.setEditable(false);
 
                JScrollPane scrol;
                scrol  = new JScrollPane(area);
                
                Font auxFont1=consulta4.getFont();
                consulta4.setFont(new Font(auxFont1.getFontName(), auxFont1.getStyle(), 15));
                
                
                consulta4.setBounds(10,10,550,50);
                p.setBounds(0,0,780,600);
                scrol.setBounds(5,80,760,360);
                atras.setBounds(330,450,140,40);
                
                reporte2.add(consulta4);
                reporte2.add(scrol);
                reporte2.setSize(780,540);
                reporte2.add(atras);
                reporte2.setLocation(500,80);
                reporte2.setLayout(null);
                reporte2.setResizable(false);
                reporte2.add(p);
                reporte2.setVisible(true); 
                aux = reporte2;
        }
        
        public void Generar_reporte2(KeyEvent ev) throws SQLException
        {
                MiPanel p = new MiPanel();
                JLabel consulta4 = new JLabel("Siniestros de asegurados cuya poliza se vencieron en diciembre 2014");
                String consulta[] = new String[100];
                JFrame reporte2 = new JFrame("Reporte2");
                JTextArea area;
                Conexion bd = new Conexion();
                ResultSet list;
                JButton atras = new JButton("Atras");
                int i = 0, j = 0, k = 0;
                
                area = new JTextArea();
                list  = bd.obtenerResulset("select p.fecha, p.hora, p.ci_ciu, p.nro_persona_ac, p.ninos, p.causa, p.prioridad, p.nro_persona_ter, p.av_calle, p.municipio, p.parroquia, p.ciudad, p.km_aut, p.nombre_aut, p.calificacion  from (select c.ci_ciu from pro.vehiculo_tiene_poliza p,pro.ciudadano c,pro.vehiculo v where (p.f_vencimiento BETWEEN '2014-12-01' and '2014-12-31') and c.ci_ciu = v.ci_ciu and v.placa= p.placa) as x,pro.percance p where p.ci_ciu=x.ci_ciu  order by p.ci_ciu,p.fecha;",cone);
                                
                if(list != null)
                {
                    while (list.next())
                    {
                     consulta[i] =   list.getString("fecha")+"  "+list.getString("hora")+"  "+Integer.toString(list.getInt("ci_ciu"))+"  "+Integer.toString(list.getInt("nro_persona_ac"))+"  "+Integer.toString(list.getInt("ninos"))+"  "+list.getString("causa")+"  "+Integer.toString(list.getInt("prioridad"))+"  "+Integer.toString(list.getInt("nro_persona_ter"))+"  "+list.getString("av_calle")+"  "+list.getString("municipio")+"  "+list.getString("parroquia")+"  "+list.getString("ciudad")+"  "+Integer.toString(list.getInt("km_aut"))+"  "+list.getString("nombre_aut")+"  "+list.getString("calificacion");
                     area.append(consulta[i]+"\n");
                     i++;
                    }        
                }

                atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
                atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
                
                
                area.setEditable(false);
 
                JScrollPane scrol;
                scrol  = new JScrollPane(area);
                
                Font auxFont1=consulta4.getFont();
                consulta4.setFont(new Font(auxFont1.getFontName(), auxFont1.getStyle(), 15));
                
                
                consulta4.setBounds(10,10,550,50);
                p.setBounds(0,0,780,600);
                scrol.setBounds(5,80,760,360);
                atras.setBounds(330,450,140,40);
                
                reporte2.add(consulta4);
                reporte2.add(scrol);
                reporte2.setSize(780,540);
                reporte2.add(atras);
                reporte2.setLocation(500,80);
                reporte2.setLayout(null);
                reporte2.setResizable(false);
                reporte2.add(p);
                reporte2.setVisible(true); 
                aux = reporte2;
        }
        
        public void ver_grua(KeyEvent ev) throws SQLException
        {
                JButton atras = new JButton("Atras");
                MiPanel p = new MiPanel();
                String consulta[] = new String[100];
                JFrame reporte2 = new JFrame("Datos de las gruas");
                JTextArea area;
                Conexion bd = new Conexion();
                ResultSet list;
                int i = 0;
                p.setBounds(0,0,540,540);
                
                area = new JTextArea();
                list  = bd.obtenerResulset("SELECT * FROM pro.grua;",cone);

                if(list != null)
                {
                    while (list.next())
                    {
                     consulta[i] = list.getString("placa")+"  "+list.getString("marca")+"  "+list.getString("serial_ca")+"  "+list.getString("serial_ch")+"  "+Integer.toString(list.getInt("carga"))+"  "+list.getString("modelo")+"  "+Integer.toString(list.getInt("tamano"))+"  "+list.getString("tipo_g");
                     area.append(consulta[i]+"\n");
                     i++;
                    }        
                }
                
                atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
                atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
                
                area.setEditable(false);
 
                JScrollPane scrol;
                scrol  = new JScrollPane(area);
                scrol.setBounds(20,50,480,335);
                atras.setBounds(190,420,140,40);                
                
                reporte2.add(scrol);
                reporte2.setSize(520,520);
                reporte2.setLocation(500,80);
                reporte2.setLayout(null);
                reporte2.setResizable(false);
                reporte2.add(atras);                
                reporte2.add(p);
                reporte2.setVisible(true);
                aux = reporte2;
        }
        
        public void ver_grua(ActionEvent evt) throws SQLException
        {
                JButton atras = new JButton("Atras");
                MiPanel p = new MiPanel();
                String consulta[] = new String[100];
                JFrame reporte2 = new JFrame("Datos de las gruas");
                JTextArea area;
                Conexion bd = new Conexion();
                ResultSet list;
                int i = 0;
                p.setBounds(0,0,540,540);
                
                area = new JTextArea();
                list  = bd.obtenerResulset("SELECT * FROM pro.grua;",cone);

                if(list != null)
                {
                    while (list.next())
                    {
                     consulta[i] = list.getString("placa")+"  "+list.getString("marca")+"  "+list.getString("serial_ca")+"  "+list.getString("serial_ch")+"  "+Integer.toString(list.getInt("carga"))+"  "+list.getString("modelo")+"  "+Integer.toString(list.getInt("tamano"))+"  "+list.getString("tipo_g");
                     area.append(consulta[i]+"\n");
                     i++;
                    }        
                }
                
                atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
                atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
                
                area.setEditable(false);
 
                JScrollPane scrol;
                scrol  = new JScrollPane(area);
                scrol.setBounds(20,50,480,335);
                atras.setBounds(190,420,140,40);                
                
                reporte2.add(scrol);
                reporte2.setSize(520,520);
                reporte2.setLocation(500,80);
                reporte2.setLayout(null);
                reporte2.setResizable(false);
                reporte2.add(atras);                
                reporte2.add(p);
                reporte2.setVisible(true);
                aux = reporte2;                                
        }
              
        public void ver_ciudadano(KeyEvent ev) throws SQLException
        {
                JButton atras = new JButton("Atras");
                MiPanel p = new MiPanel();
                String consulta[] = new String[100];
                JFrame reporte2 = new JFrame("Datos de las ciudadanos");
                JTextArea area;
                Conexion bd = new Conexion();
                ResultSet list;
                int i = 0;
                p.setBounds(0,0,520,490);
                
                
                area = new JTextArea();
                list  = bd.obtenerResulset("SELECT * FROM pro.ciudadano;",cone);

                
                if(list != null)
                {
                    while (list.next())
                    {
                     consulta[i] = Integer.toString(list.getInt("ci_ciu"))+"  "+list.getString("nombre_ciu")+"  "+Integer.toString(list.getInt("edad_ciu"))+"  "+list.getString("tlf");
                     area.append(consulta[i]+"\n");
                     i++;
                    }        
                }

                atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
                atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
  
                atras.setBounds(70,400,140,40);
                area.setEditable(false);
 
                JScrollPane scrol;
                scrol  = new JScrollPane(area);

                scrol.setBounds(20,40,250,335);

                reporte2.add(scrol);
                reporte2.setSize(290,480);
                reporte2.setLocation(500,80);
                reporte2.setLayout(null);
                reporte2.setResizable(false);
                reporte2.add(atras);
                reporte2.add(p);
                reporte2.setVisible(true);
                aux = reporte2;
                            
        }
        
        public void ver_ciudadano(ActionEvent evt) throws SQLException
        {
                JButton atras = new JButton("Atras");
                MiPanel p = new MiPanel();
                String consulta[] = new String[100];
                JFrame reporte2 = new JFrame("Datos de las ciudadanos");
                JTextArea area;
                Conexion bd = new Conexion();
                ResultSet list;
                int i = 0;
                p.setBounds(0,0,520,490);
                
                
                area = new JTextArea();
                list  = bd.obtenerResulset("SELECT * FROM pro.ciudadano;",cone);

                
                if(list != null)
                {
                    while (list.next())
                    {
                     consulta[i] = Integer.toString(list.getInt("ci_ciu"))+"  "+list.getString("nombre_ciu")+"  "+Integer.toString(list.getInt("edad_ciu"))+"  "+list.getString("tlf");
                     area.append(consulta[i]+"\n");
                     i++;
                    }        
                }

                atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
                atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
  
                atras.setBounds(70,400,140,40);
                area.setEditable(false);
 
                JScrollPane scrol;
                scrol  = new JScrollPane(area);

                scrol.setBounds(20,40,250,335);

                reporte2.add(scrol);
                reporte2.setSize(290,480);
                reporte2.setLocation(500,80);
                reporte2.setLayout(null);
                reporte2.setResizable(false);
                reporte2.add(atras);
                reporte2.add(p);
                reporte2.setVisible(true);
                aux = reporte2;
                                                        
        }
        
        public void cargar_percance(KeyEvent ev)
        {
            MiPanel p = new MiPanel();
            JFrame pantallap = new JFrame("Cargar Percance");
            JLabel bienvenida = new JLabel("Ingrese el nuevo percance a cargar");
            JLabel fecham = new JLabel("Fecha:");
            JLabel horam = new JLabel("Hora:");
            JLabel cim = new JLabel("Cedula del ciudadano:");
            JLabel nropacm = new JLabel("Numero de personas accidentadas:");
            JLabel ninosm = new JLabel("Numero de niños implicados en el");
            JLabel percancem = new JLabel("percance:");
            JLabel nroptem = new JLabel("Numero de personas de tercera");
            JLabel nroptem2 = new JLabel("edad implicados en el percance:");
            JLabel causam = new JLabel("Causa del percance:");
            JLabel prioridadm = new JLabel("Prioridad:");
            JLabel av_callem = new JLabel("Avenida o calle:");
            JLabel municipiom = new JLabel("Municipio:");
            JLabel parroquiam = new JLabel("Parroquia:");
            JLabel ciudadm = new JLabel("Ciudad:");
            JLabel km_autm = new JLabel("Kilometro de la autopista:");
            JLabel nombre_autm = new JLabel("Nombre de la autopista:");
            JLabel calificacionm = new JLabel("Calificacion del servicio:");
            JTextField fechat = new JTextField();
            JTextField horat = new JTextField();
            JTextField cit = new JTextField();
            JTextField nropact = new JTextField();
            JTextField ninost = new JTextField();
            JTextField nroptet = new JTextField();
            JTextField causat = new JTextField();
            JTextField prioridadt = new JTextField();
            JTextField av_callet = new JTextField();
            JTextField municipiot = new JTextField();
            JTextField parroquiat = new JTextField();
            JTextField ciudadt = new JTextField();
            JTextField km_autt = new JTextField();
            JTextField nombre_autt = new JTextField();
            JTextField calificaciont = new JTextField();
            JButton atras = new JButton("Atras");
            JButton cargarp = new JButton("Cargar");
            vector = new JTextField[18];
            
            atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
            atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
            cargarp.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cargar_percanceAC(evt);}});
            cargarp.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {cargar_percanceAC(ev);}});            
            av_callet.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle(evt);}});
            municipiot.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle(evt);}});
            parroquiat.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle(evt);}});
            ciudadt.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle(evt);}});
            km_autt.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle2(evt);}});
            nombre_autt.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle2(evt);}});
           
            p.setBounds(0,0,480,500);
            bienvenida.setBounds(20,10,300,30);
            fecham.setBounds(20,45,100,30);
            fechat.setBounds(20,70,200,20);
            horam.setBounds(20,80,100,30);
            horat.setBounds(20,105,200,20);
            cim.setBounds(20,115,200,30);
            cit.setBounds(20,140,200,20);
            nropacm.setBounds(20,150,250,30);
            nropact.setBounds(20,175,40,20);
            ninosm.setBounds(20,185,300,30);
            percancem.setBounds(20,210,100,13);
            ninost.setBounds(20,225,40,20);
            nroptem.setBounds(20,235,400,30);
            nroptem2.setBounds(20,250,400,30);
            nroptet.setBounds(20,275,40,20);
            causam.setBounds(20,290,300,30);
            causat.setBounds(20,315,200,20);
            prioridadm.setBounds(20,330,300,30);
            prioridadt.setBounds(20,355,40,20);
            av_callem.setBounds(250,45,200,30);
            av_callet.setBounds(250,70,200,20);
            municipiom.setBounds(250,80,200,30);
            municipiot.setBounds(250,105,200,20);
            parroquiam.setBounds(250,115,200,30);
            parroquiat.setBounds(250,140,200,20);
            ciudadm.setBounds(250,150,200,30);
            ciudadt.setBounds(250,175,200,20);
            km_autm.setBounds(250,185,300,30);
            km_autt.setBounds(250,210,40,20);
            nombre_autm.setBounds(250,225,300,30);
            nombre_autt.setBounds(250,250,200,20);
            calificacionm.setBounds(250,265,300,30);
            calificaciont.setBounds(250,290,200,20);
            cargarp.setBounds(80,380,140,40);
            atras.setBounds(250,380,140,40);
            
            vector[0] = av_callet;
            vector[1] = municipiot;
            vector[2] = parroquiat;
            vector[3] = ciudadt;
            vector[4] = km_autt;
            vector[5] = nombre_autt;
            vector[6] = fechat;
            vector[7] = horat;
            vector[8] = cit;
            vector[9] = nropact;
            vector[10] = ninost;
            vector[11] = nroptet;
            vector[12] = causat;
            vector[13] = prioridadt;
            vector[14] = calificaciont;
            
            pantallap.setSize(480,470);
            pantallap.setLocation(500,20);
            pantallap.setLayout(null);
            pantallap.setResizable(false);
            pantallap.add(bienvenida);
            pantallap.add(fecham);
            pantallap.add(fechat);
            pantallap.add(horam);
            pantallap.add(horat);
            pantallap.add(cim);
            pantallap.add(cit);
            pantallap.add(nropacm);
            pantallap.add(nropact);
            pantallap.add(ninosm);
            pantallap.add(percancem);
            pantallap.add(ninost);
            pantallap.add(nroptem);
            pantallap.add(nroptem2);
            pantallap.add(nroptet);
            pantallap.add(causam);
            pantallap.add(causat);
            pantallap.add(prioridadm);
            pantallap.add(prioridadt);
            pantallap.add(av_callem);
            pantallap.add(av_callet);
            pantallap.add(municipiom);
            pantallap.add(municipiot);
            pantallap.add(parroquiam);
            pantallap.add(parroquiat);
            pantallap.add(ciudadm);
            pantallap.add(ciudadt);
            pantallap.add(km_autm);
            pantallap.add(km_autt);
            pantallap.add(nombre_autm);
            pantallap.add(nombre_autt);
            pantallap.add(calificacionm);
            pantallap.add(calificaciont);
            pantallap.add(cargarp);
            pantallap.add(atras);
            pantallap.add(p);
            pantallap.setVisible(true);
            aux = pantallap;
        }
        
        public void cargar_percance(ActionEvent evt)
        {
            MiPanel p = new MiPanel();
            JFrame pantallap = new JFrame("Cargar Percance");
            JLabel bienvenida = new JLabel("Ingrese el nuevo percance a cargar");
            JLabel fecham = new JLabel("Fecha:");
            JLabel horam = new JLabel("Hora:");
            JLabel cim = new JLabel("Cedula del ciudadano:");
            JLabel nropacm = new JLabel("Numero de personas accidentadas:");
            JLabel ninosm = new JLabel("Numero de niños implicados en el");
            JLabel percancem = new JLabel("percance:");
            JLabel nroptem = new JLabel("Numero de personas de tercera");
            JLabel nroptem2 = new JLabel("edad implicados en el percance:");
            JLabel causam = new JLabel("Causa del percance:");
            JLabel prioridadm = new JLabel("Prioridad:");
            JLabel av_callem = new JLabel("Avenida o calle:");
            JLabel municipiom = new JLabel("Municipio:");
            JLabel parroquiam = new JLabel("Parroquia:");
            JLabel ciudadm = new JLabel("Ciudad:");
            JLabel km_autm = new JLabel("Kilometro de la autopista:");
            JLabel nombre_autm = new JLabel("Nombre de la autopista:");
            JLabel calificacionm = new JLabel("Calificacion del servicio:");
            JTextField fechat = new JTextField();
            JTextField horat = new JTextField();
            JTextField cit = new JTextField();
            JTextField nropact = new JTextField();
            JTextField ninost = new JTextField();
            JTextField nroptet = new JTextField();
            JTextField causat = new JTextField();
            JTextField prioridadt = new JTextField();
            JTextField av_callet = new JTextField();
            JTextField municipiot = new JTextField();
            JTextField parroquiat = new JTextField();
            JTextField ciudadt = new JTextField();
            JTextField km_autt = new JTextField();
            JTextField nombre_autt = new JTextField();
            JTextField calificaciont = new JTextField();
            JButton atras = new JButton("Atras");
            JButton cargarp = new JButton("Cargar");
            vector = new JTextField[20];
            
            atras.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {aux.setVisible(false);}});
            atras.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {aux.setVisible(false);}}); 
            cargarp.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cargar_percanceAC(evt);}});
            cargarp.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent ev) {cargar_percanceAC(ev);}});            
            av_callet.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle(evt);}});
            municipiot.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle(evt);}});
            parroquiat.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle(evt);}});
            ciudadt.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle(evt);}});
            km_autt.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle2(evt);}});
            nombre_autt.addKeyListener(new KeyAdapter() {public void keyReleased (KeyEvent evt) {cerrar_calle2(evt);}});
           
            p.setBounds(0,0,480,500);
            bienvenida.setBounds(20,10,300,30);
            fecham.setBounds(20,45,100,30);
            fechat.setBounds(20,70,200,20);
            horam.setBounds(20,80,100,30);
            horat.setBounds(20,105,200,20);
            cim.setBounds(20,115,200,30);
            cit.setBounds(20,140,200,20);
            nropacm.setBounds(20,150,250,30);
            nropact.setBounds(20,175,40,20);
            ninosm.setBounds(20,185,300,30);
            percancem.setBounds(20,210,100,13);
            ninost.setBounds(20,225,40,20);
            nroptem.setBounds(20,235,400,30);
            nroptem2.setBounds(20,250,400,30);
            nroptet.setBounds(20,275,40,20);
            causam.setBounds(20,290,300,30);
            causat.setBounds(20,315,200,20);
            prioridadm.setBounds(20,330,300,30);
            prioridadt.setBounds(20,355,40,20);
            av_callem.setBounds(250,45,200,30);
            av_callet.setBounds(250,70,200,20);
            municipiom.setBounds(250,80,200,30);
            municipiot.setBounds(250,105,200,20);
            parroquiam.setBounds(250,115,200,30);
            parroquiat.setBounds(250,140,200,20);
            ciudadm.setBounds(250,150,200,30);
            ciudadt.setBounds(250,175,200,20);
            km_autm.setBounds(250,185,300,30);
            km_autt.setBounds(250,210,40,20);
            nombre_autm.setBounds(250,225,300,30);
            nombre_autt.setBounds(250,250,200,20);
            calificacionm.setBounds(250,265,300,30);
            calificaciont.setBounds(250,290,200,20);
            cargarp.setBounds(80,380,140,40);
            atras.setBounds(250,380,140,40);
            
            vector[0] = av_callet;
            vector[1] = municipiot;
            vector[2] = parroquiat;
            vector[3] = ciudadt;
            vector[4] = km_autt;
            vector[5] = nombre_autt;
            vector[6] = fechat;
            vector[7] = horat;
            vector[8] = cit;
            vector[9] = nropact;
            vector[10] = ninost;
            vector[11] = nroptet;
            vector[12] = causat;
            vector[13] = prioridadt;
            vector[14] = calificaciont;
            
            pantallap.setSize(480,470);
            pantallap.setLocation(500,20);
            pantallap.setLayout(null);
            pantallap.setResizable(false);
            pantallap.add(bienvenida);
            pantallap.add(fecham);
            pantallap.add(fechat);
            pantallap.add(horam);
            pantallap.add(horat);
            pantallap.add(cim);
            pantallap.add(cit);
            pantallap.add(nropacm);
            pantallap.add(nropact);
            pantallap.add(ninosm);
            pantallap.add(percancem);
            pantallap.add(ninost);
            pantallap.add(nroptem);
            pantallap.add(nroptem2);
            pantallap.add(nroptet);
            pantallap.add(causam);
            pantallap.add(causat);
            pantallap.add(prioridadm);
            pantallap.add(prioridadt);
            pantallap.add(av_callem);
            pantallap.add(av_callet);
            pantallap.add(municipiom);
            pantallap.add(municipiot);
            pantallap.add(parroquiam);
            pantallap.add(parroquiat);
            pantallap.add(ciudadm);
            pantallap.add(ciudadt);
            pantallap.add(km_autm);
            pantallap.add(km_autt);
            pantallap.add(nombre_autm);
            pantallap.add(nombre_autt);
            pantallap.add(calificacionm);
            pantallap.add(calificaciont);
            pantallap.add(cargarp);
            pantallap.add(atras);
            pantallap.add(p);
            pantallap.setVisible(true);
            aux = pantallap;   
        }
        
        public void cerrar_calle(KeyEvent evt)
        {
            if(!vector[0].getText().equals("") || !vector[1].getText().equals("") || !vector[2].getText().equals("") || !vector[3].getText().equals(""))
            {
                  vector[4].setEditable(false);
                  vector[5].setEditable(false);
            }
            else
            {
                  vector[4].setEditable(true);
                  vector[5].setEditable(true);                            
            }   
        }
     
        public void cerrar_calle2(KeyEvent evt)
        {
            if(!vector[4].getText().equals("") || !vector[5].getText().equals(""))
            {
                  vector[0].setEditable(false);
                  vector[1].setEditable(false);
                  vector[2].setEditable(false);
                  vector[3].setEditable(false);
            }
            else
            {
                  vector[0].setEditable(true);
                  vector[1].setEditable(true);
                  vector[2].setEditable(true);
                  vector[3].setEditable(true);
            }   
        }
        
        public void cargar_percanceAC(ActionEvent ev)
        {
            Conexion bd = new Conexion();          
            int car = -1, aux = -1;
            
            if((Integer.parseInt(vector[10].getText()) + Integer.parseInt(vector[11].getText()) + 1) == Integer.parseInt(vector[9].getText()))
            {
                if(!vector[6].getText().equals("") && !vector[7].getText().equals("") && !vector[8].getText().equals("") && !vector[9].getText().equals("") && !vector[10].getText().equals("") && !vector[12].getText().equals("") && !vector[13].getText().equals("") && !vector[11].getText().equals("") && !vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals("") && !vector[14].getText().equals(""))
                {
                    car = bd.ejecutarSQL("INSERT INTO pro.percance VALUES('"+vector[6].getText()+"','"+vector[7].getText()+"',"+vector[8].getText()+","+vector[9].getText()+","+vector[10].getText()+",'"+vector[12].getText()+"',"+vector[13].getText()+","+vector[11].getText()+",'"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','"+vector[3].getText()+"',0,'','"+vector[14].getText()+"');",cone);
                
                    if(car > 0)
                        JOptionPane.showMessageDialog(null,"Percance Cargado");
                    aux = 1;
                }
                else if(!vector[6].getText().equals("") && !vector[7].getText().equals("") && !vector[8].getText().equals("") && !vector[9].getText().equals("") && !vector[10].getText().equals("") && !vector[12].getText().equals("") && !vector[13].getText().equals("") && !vector[11].getText().equals("") && !vector[4].getText().equals("") && !vector[5].getText().equals("") && !vector[14].getText().equals(""))
                {
                    car = bd.ejecutarSQL("INSERT INTO pro.percance VALUES('"+vector[6].getText()+"','"+vector[7].getText()+"',"+vector[8].getText()+","+vector[9].getText()+","+vector[10].getText()+",'"+vector[12].getText()+"',"+vector[13].getText()+","+vector[11].getText()+",'','','','','"+vector[4].getText()+"','"+vector[5].getText()+"','"+vector[14].getText()+"');",cone);
                
                  if(car > 0)
                        JOptionPane.showMessageDialog(null,"Percance Cargado");
                  aux = 1;
                }
                else if(aux == -1)
                    JOptionPane.showMessageDialog(null,"Todos los campos abiertos son obligatorios");
                else
                    JOptionPane.showMessageDialog(null,"No se pudo cargar el percance"); 
            }else
                 JOptionPane.showMessageDialog(null,"La cantidad de personas de tercera edad mas la cantidad de niños no se corresponde con la cantidad de accidentados");
        }

        
        public void cargar_percanceAC(KeyEvent evt)
        {
            Conexion bd = new Conexion();          
            int car = -1, aux = -1;
            
          if((Integer.parseInt(vector[10].getText()) + Integer.parseInt(vector[11].getText()) + 1) == Integer.parseInt(vector[9].getText()))
          {
            if(!vector[6].getText().equals("") && !vector[7].getText().equals("") && !vector[8].getText().equals("") && !vector[9].getText().equals("") && !vector[10].getText().equals("") && !vector[12].getText().equals("") && !vector[13].getText().equals("") && !vector[11].getText().equals("") && !vector[0].getText().equals("") && !vector[1].getText().equals("") && !vector[2].getText().equals("") && !vector[3].getText().equals("") && !vector[14].getText().equals(""))
            {
                car = bd.ejecutarSQL("INSERT INTO pro.percance VALUES('"+vector[6].getText()+"','"+vector[7].getText()+"',"+vector[8].getText()+","+vector[9].getText()+","+vector[10].getText()+",'"+vector[12].getText()+"',"+vector[13].getText()+","+vector[11].getText()+",'"+vector[0].getText()+"','"+vector[1].getText()+"','"+vector[2].getText()+"','"+vector[3].getText()+"',0,'','"+vector[14].getText()+"');",cone);
                
                if(car > 0)
                    JOptionPane.showMessageDialog(null,"Percance Cargado");
                 aux = 1;
            }
            else if(!vector[6].getText().equals("") && !vector[7].getText().equals("") && !vector[8].getText().equals("") && !vector[9].getText().equals("") && !vector[10].getText().equals("") && !vector[12].getText().equals("") && !vector[13].getText().equals("") && !vector[11].getText().equals("") && !vector[4].getText().equals("") && !vector[5].getText().equals("") && !vector[14].getText().equals(""))
            {
                car = bd.ejecutarSQL("INSERT INTO pro.percance VALUES('"+vector[6].getText()+"','"+vector[7].getText()+"',"+vector[8].getText()+","+vector[9].getText()+","+vector[10].getText()+",'"+vector[12].getText()+"',"+vector[13].getText()+","+vector[11].getText()+",'','','','','"+vector[4].getText()+"','"+vector[5].getText()+"','"+vector[14].getText()+"');",cone);
                
                if(car > 0)
                    JOptionPane.showMessageDialog(null,"Percance Cargado");
                aux = 1;
            }
            else if(aux == -1)
                JOptionPane.showMessageDialog(null,"Todos los campos abiertos son obligatorios");
            else
                JOptionPane.showMessageDialog(null,"No se pudo cargar el percance");
          }else
               JOptionPane.showMessageDialog(null,"La cantidad de personas de tercera edad mas la cantidad de niños no se corresponde con la cantidad de accidentados");
        }
        
        public static void main(String[] args) 
        {
             Proyecto p = new Proyecto();
        }
}