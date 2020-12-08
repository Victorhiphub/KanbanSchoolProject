package GUI;

import flujodetrabajo.Actividad;
import flujodetrabajo.Fase;
import flujodetrabajo.FlujoDeTrabajo;
import flujodetrabajo.Tarea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class GeneralUI extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonActividad;
    private JTextField textFieldActividad;
    private JLabel toDO;
    private JLabel inProgress;
    private JLabel doNE;
    private JList listinprogress;
    private JList listdone;
    private JPanel panelprincipal;
    private JList listodo;
    private JButton buttonTarea;
    private JTextField textFielTarea;
    private JComboBox comboBoxActividad;
    private JTable tablero;
    private FlujoDeTrabajo flujoDeTrabajo;
    private FlujoDeTrabajo flujo2;
    private FlujoDeTrabajo flujo3;
    private DefaultListModel model ;
    private DefaultListModel model1;
    private DefaultListModel model2;
    private Component component;

    public GeneralUI() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        flujoDeTrabajo= new FlujoDeTrabajo("kanban");
        flujo2 = new FlujoDeTrabajo("inProgress");
        flujo3 =new FlujoDeTrabajo("Done");


        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        // Creacion de Las actividades por defecto son agregadas al flujo de trabajo del todo
        buttonActividad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Actividad actividad = new Actividad(textFieldActividad.getText(),flujoDeTrabajo);
                flujoDeTrabajo.getActividades().add(actividad);
                actualizarVersion();
            }
        });
        //Creacion de las tareas por defecto su actividad se obtiene del combobox y se agrega al flujo de trabajo
        buttonTarea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actividad actividad = flujoDeTrabajo.getActividades().get(comboBoxActividad.getSelectedIndex());
                Tarea tarea = new Tarea(textFielTarea.getText(),actividad, flujoDeTrabajo);
                flujoDeTrabajo.getTareas().add(tarea);
                actividad.getTareas().add(tarea);
                actualizarVersion();
            }
        });
        listodo.addMouseListener(new MouseAdapter() {
            int index;
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                index= listodo.getSelectedIndex();
                String [] array ={"inProgress","Done"};
              int opcion=JOptionPane.showOptionDialog(null,"Escoje un opcion","Elige...",0,JOptionPane.QUESTION_MESSAGE,null,array,"Teclado");
                switch (opcion){
                    //caso para pasar del todo al inprogress
                    case 0:
                        Tarea tarea = flujoDeTrabajo.getTareas().get(index);
                        Actividad actividad = tarea.getActividad();
                        flujo2.getActividades().add(actividad);
                        flujo2.getTareas().add(tarea);
                        model1.addElement(model.getElementAt(index));
                        model.removeElementAt(index);
                        flujoDeTrabajo.getTareas().remove(index);
                        System.out.println(tarea.getActividad());
                        //flujo2.getActividades().add(index,tarea);
                       // model1.addElement(model.getElementAt(index));
                        //model.removeElementAt(index);
                       // flujoDeTrabajo.get
                        //flujoDeTrabajo.getActividades().getTareas().remove(index);
                        actualizarVersion();
                        break;
                    //caso para pasar del todo al done
                    case 1:
                        Tarea tarea1 = flujoDeTrabajo.getTareas().get(index);
                        Actividad actividad1 = tarea1.getActividad();
                        flujo3.getActividades().add(actividad1);
                        flujo3.getTareas().add(tarea1);
                        model2.addElement(model.getElementAt(index));
                        model.removeElementAt(index);
                        flujoDeTrabajo.getTareas().remove(index);
                        System.out.println(tarea1.getActividad());
                       // Actividad actividad1 = new Actividad((String)model.getElementAt(index),flujo3);
                       // flujo3.getActividades().add(actividad1);
                       // model2.addElement(model.getElementAt(index));
                       // model.removeElementAt(index);
                       // flujoDeTrabajo.getActividades().remove(index);
                        actualizarVersion();

                        break;

                }
            }

        });
        listinprogress.addMouseListener(new MouseAdapter() {
            int index;
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                index= listinprogress.getSelectedIndex();
                String [] array ={"toDO","Done"};
                int opcion=JOptionPane.showOptionDialog(null,"Escoje un opcion","Elige...",0,JOptionPane.QUESTION_MESSAGE,null,array,"Teclado");
                switch (opcion){
                    case 0:
                        Tarea tarea = flujo2.getTareas().get(index);
                        Actividad actividad = tarea.getActividad();
                        flujoDeTrabajo.getActividades().add(actividad);
                        flujoDeTrabajo.getTareas().add(tarea);
                        model.addElement(model1.getElementAt(index));
                        model1.removeElementAt(index);
                        flujo2.getTareas().remove(index);
                        System.out.println(tarea.getActividad());
                       // Actividad actividad = new Actividad((String)model1.getElementAt(index),flujoDeTrabajo);
                       // flujoDeTrabajo.getActividades().add(actividad);
                       // model.addElement(model1.getElementAt(index));
                       // model1.removeElementAt(index);
                       // flujo2.getActividades().remove(index);
                        actualizarVersion();
                        break;
                    case 1:
                        Tarea tarea1 = flujo2.getTareas().get(index);
                        Actividad actividad1 = tarea1.getActividad();
                        flujo3.getActividades().add(actividad1);
                        flujo3.getTareas().add(tarea1);
                        model2.addElement(model1.getElementAt(index));
                        model1.removeElementAt(index);
                        flujo2.getTareas().remove(index);
                        System.out.println(tarea1.getActividad());
                       // Actividad actividad1 = new Actividad((String)model1.getElementAt(index),flujo3);
                       // flujo3.getActividades().add(actividad1);
                       // model2.addElement(model1.getElementAt(index));
                       // model1.removeElementAt(index);
                       // flujo2.getActividades().remove(index);
                        actualizarVersion();
                        break;

                }
            }


        });
        listdone.addMouseListener(new MouseAdapter() {
            int index;
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                index= listdone.getSelectedIndex();
                String [] array ={"toDo","inProgress"};
                int opcion=JOptionPane.showOptionDialog(null,"Escoje un opcion","Elige...",0,JOptionPane.QUESTION_MESSAGE,null,array,"Teclado");
                switch (opcion){
                    case 0:
                        Tarea tarea = flujo3.getTareas().get(index);
                        Actividad actividad = tarea.getActividad();
                        flujoDeTrabajo.getActividades().add(actividad);
                        flujoDeTrabajo.getTareas().add(tarea);
                        model.addElement(model2.getElementAt(index));
                        model2.removeElementAt(index);
                        flujo3.getTareas().remove(index);
                        System.out.println(tarea.getActividad());
                       // Actividad actividad = new Actividad((String)model2.getElementAt(index),flujoDeTrabajo);
                       // flujoDeTrabajo.getActividades().add(actividad);
                       // model.addElement(model2.getElementAt(index));
                       // model2.removeElementAt(index);
                       // flujo3.getActividades().remove(index);
                        actualizarVersion();
                        break;
                    case 1:
                        Tarea tarea1 = flujo3.getTareas().get(index);
                        Actividad actividad1 = tarea1.getActividad();
                        flujo2.getActividades().add(actividad1);
                        flujo2.getTareas().add(tarea1);
                        model1.addElement(model2.getElementAt(index));
                        model2.removeElementAt(index);
                        flujo2.getTareas().remove(index);
                        System.out.println(tarea1.getActividad());
                       // Actividad actividad1 = new Actividad((String)model2.getElementAt(index),flujo2);
                       // flujo2.getActividades().add(actividad1);
                       // model1.addElement(model2.getElementAt(index));
                       // model2.removeElementAt(index);
                       // flujo3.getActividades().remove(index);
                        actualizarVersion();
                        break;

                }
            }
        });
    }
    //Metodo de ubicacion
    protected boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else
                return false;
        } else
            return false;
    }
    private void onOK() {
        // add your code here
        dispose();
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void buscarRepetido(FlujoDeTrabajo f1){
       for(int i=0;i<f1.getActividades().size()-1;i++){
           for(int j=f1.getActividades().size()-1; j>0;j--){
               if(f1.getActividades().get(i).getNombre().equals(f1.getActividades().get(j).getNombre())){
                   f1.getActividades().remove(j);
               }
           }
       }
    }

    private void actualizarVersion(){
        comboBoxActividad.removeAllItems();
        //elimina actividades repetidas en el flujo de trabajo
        buscarRepetido(flujoDeTrabajo);
        buscarRepetido(flujo2);
        buscarRepetido(flujo3);
        for (int j = 0; j < flujoDeTrabajo.getActividades().size(); j++) {
            comboBoxActividad.addItem(flujoDeTrabajo.getActividades().get(j).getNombre());
        }
        model = new DefaultListModel();
        listodo.setModel(model);
        model1 =new DefaultListModel();
        listinprogress.setModel(model1);
        model2 =new DefaultListModel();
        listdone.setModel(model2);
        for(int i=0; i< flujoDeTrabajo.getTareas().size(); i++){
            model.addElement(flujoDeTrabajo.getTareas().get(i).getNombre());
        }
        for (int j=0;j<flujo2.getTareas().size();j++){
            model1.addElement(flujo2.getTareas().get(j).getNombre());
        }
        for (int k=0;k<flujo3.getTareas().size();k++){
            model2.addElement(flujo3.getTareas().get(k).getNombre());
        }


    }


    public static void main(String[] args) {
        GeneralUI dialog = new GeneralUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
