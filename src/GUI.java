import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    // Obtiene la resolucion de la pantalla
    // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final Font fuentePredeterminada = new Font("Cabin",Font.PLAIN,20);
    private JPanel panel;
    private JTextField nombreTarea;
    private JTable toDoList;
    private DefaultTableModel modelo;

    // Interfaz grafica
    public GUI(){
        setTitle("To DO List");
        setSize(500,680);
        setLocation(10,10);
        setResizable(false);

        addComponent();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addComponent(){
        addPane();
        addNameLabel();
        addTextName();
        buttonCreate();
        deleteButton();
        addNameLabel();
        addTableToDoList();
    }

    public void addPane(){
        panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
    }

    public void addNameLabel(){
        JLabel label = new JLabel();

        label.setLocation(20,10);
        label.setSize(200,50);
        label.setText("Nombre de la tarea:");
        label.setFont(fuentePredeterminada);

        panel.add(label);
    }

    public void addTextName(){
        nombreTarea = new JTextField();

        nombreTarea.setLocation(20,50);
        nombreTarea.setSize(400,50);
        nombreTarea.setFont(fuentePredeterminada);

        panel.add(nombreTarea);

    }

    public void buttonCreate(){
        JButton botonCrear = new JButton();


        botonCrear.setLocation(20,110);
        botonCrear.setLayout(null);
        botonCrear.setText("Agregar Tarea");
        botonCrear.setSize(200,60);
        botonCrear.setFont(fuentePredeterminada);

        panel.add(botonCrear);

        ActionListener actionListener;
        actionListener = e -> createFunction();

        botonCrear.addActionListener(actionListener);
    }

    public void deleteButton(){
        JButton botonBorrar = new JButton();


        botonBorrar.setLocation(250,110);
        botonBorrar.setLayout(null);
        botonBorrar.setText("Eliminar Tarea");
        botonBorrar.setSize(200,60);
        botonBorrar.setFont(fuentePredeterminada);

        panel.add(botonBorrar);

        ActionListener actionListener = e -> deleteFunction();

        botonBorrar.addActionListener(actionListener);
    }

    public void addTableToDoList(){
        modelo = new DefaultTableModel();

        modelo.addColumn("Lista de tareas");

        toDoList = new JTable(modelo);
        toDoList.setLayout(null);
        toDoList.setRowHeight(50);
        toDoList.setEnabled(true);
        toDoList.setFont(fuentePredeterminada);

        toDoList.setLocation(20,180);
        toDoList.setSize(400,450);

        panel.add(toDoList);

        // Añade scrolls en el caso de que se creen demasiadas tareas

        JScrollPane scroll = new JScrollPane(toDoList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setBounds(20,180,400,450);
        panel.add(scroll);

    }

    // Funcionalidad de la aplicacion

    private void createFunction(){
        //Quita los espacios en blanco para evaluar la palabra
        String tarea = nombreTarea.getText().trim();

        //Si la longitud de la palabra es 0 avisa al usuario que la tarea no tiene nombre
        if(tarea.length() == 0){

            JOptionPane.showMessageDialog(null,"El nombre de la tarea esta vacio");

        } else {
            // Inserta una nueva fila con la tarea y numero de id

            Object[] datos = new Object[100];

            datos[0] = tarea;

            modelo.addRow(datos);
        }
    }

    private void deleteFunction(){
        //se usa try catch para gestinar la exepcion ArrayIndexOutOfBoundsException por si el usuario borra
        //sin que hayan tareas
        try {
            /* si la tarea seleccionada .getSelectedRow(); retornara el indice de la fila, si la fila no es
            seleccionada retornara un -1
             */
            int row = toDoList.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(null,"Por favor seleccione una tarea para eliminar");
            }

            modelo.removeRow(row);
            System.out.println(row);

        } catch (ArrayIndexOutOfBoundsException exception){
            System.out.println(" ");
        }
    }
}
