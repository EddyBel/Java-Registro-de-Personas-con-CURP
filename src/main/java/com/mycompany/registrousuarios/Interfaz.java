/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.registrousuarios;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author ben_9
 */
public class Interfaz extends javax.swing.JFrame {

    /*
    * Sección encargada de manejar el estado de la interfaz gráfica (GUI) en un formulario.
    * Las siguientes variables actúan como "estado" para almacenar los datos ingresados por el usuario
    * en los campos de texto y seleccionados en los botones. Estos datos se utilizan para realizar operaciones
    * de inserción, actualización o consulta en la base de datos.
     */
    MYSQL db = new MYSQL(); // Instancia de la clase MYSQL para interactuar con la base de datos.
    String dataGenero;
    String dataNombres;
    String dataApellidoPaterno;
    String dataApellidoMaterno;
    String dataFechaNacimiento;
    String dataEntidad;
    String dataCurp;

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        cargarEstados();
        limpiaLaTabla();
        actualizarTablaConBD();
        obtenerElCurpConClick();
    }

    /**
     * Este método carga los nombres de los estados de la clase Constantes en un
     * JComboBox llamado 'entidad'. Primero elimina cualquier ítem previamente
     * agregado al JComboBox y luego agrega todos los estados definidos en la
     * clase Constantes. El propósito es mostrar una lista de estados para que
     * el usuario pueda seleccionarlos.
     */
    private void cargarEstados() {
        // Elimina todos los elementos previos en el JComboBox 'entidad'
        entidad.removeAllItems();

        // Itera a través de los nombres de los estados proporcionados por la clase Constantes
        for (String estado : Constantes.obtenerNombresEstados()) {
            // Agrega cada estado al JComboBox 'entidad'
            entidad.addItem(estado);
        }
    }

    /**
     * Esta función limpia por completo la tabla de datos (JTable), eliminando
     * todas sus filas. Se obtiene el modelo de la tabla, que es responsable de
     * gestionar los datos de la tabla, y luego se eliminan todas las filas de
     * la misma.
     */
    private void limpiaLaTabla() {
        // Obtener el modelo de la tabla, el cual es responsable de almacenar y manipular los datos de la JTable
        DefaultTableModel modeloTabla = (DefaultTableModel) this.Tabla.getModel();

        // Obtener el número actual de filas en el modelo de la tabla
        int rowCount = modeloTabla.getRowCount();

        // Eliminar filas de abajo hacia arriba para evitar errores de indexación durante el proceso de eliminación
        for (int i = rowCount - 1; i >= 0; i--) {
            // Eliminar la fila en la posición 'i' del modelo de la tabla
            modeloTabla.removeRow(i);
        }
    }

    /**
     * Esta función agrega un `ListSelectionListener` a la tabla `JTable`. El
     * listener se activa cuando el usuario hace clic sobre una fila de la
     * tabla, y obtiene el valor de la columna 6 (que corresponde al campo CURP)
     * de la fila seleccionada, almacenándolo en el campo de texto `curp` y en
     * la variable de estado `dataCurp`.
     */
    private void obtenerElCurpConClick() {
        // Agregar un listener para el modelo de selección de la tabla, que se ejecuta cuando se selecciona una fila
        this.Tabla.getSelectionModel().addListSelectionListener(e -> {
            // Verificar si el cambio de selección no es un ajuste visual temporal
            if (!e.getValueIsAdjusting()) {
                // Obtener la fila que ha sido seleccionada
                int row = this.Tabla.getSelectedRow();  // La fila seleccionada

                // Verificar si la fila seleccionada es válida (no es -1, que indica que no hay fila seleccionada)
                if (row != -1) {
                    int column = 6;  // Indicar la columna 6 que contiene el valor de la CURP
                    // Recuperar el valor de la celda correspondiente a la CURP en la fila seleccionada
                    String valor = this.Tabla.getValueAt(row, column).toString();

                    // Asignar el valor recuperado al campo de texto `curp` en la interfaz
                    this.curp.setText(valor);

                    // También almacenar el valor de CURP en la variable de estado `dataCurp`
                    this.dataCurp = valor;
                } else {
                    // Si no hay una fila seleccionada, se puede mostrar un mensaje en consola o manejarlo de alguna forma
                    System.out.println("No se ha seleccionado ninguna fila.");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellidoPaterno = new javax.swing.JTextField();
        nombres = new javax.swing.JTextField();
        apellidoMaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        entidad = new javax.swing.JComboBox<>();
        btnMasculino = new javax.swing.JCheckBox();
        btnFemenino = new javax.swing.JCheckBox();
        btnEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        curp = new javax.swing.JTextField();
        btnGenerarCurp = new javax.swing.JButton();
        btnObtenerPersonaPorCURP = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre(s)");

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("REGISTRO DE USUARIOS");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Apellido Paterno");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Apellido Materno");

        apellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoPaternoActionPerformed(evt);
            }
        });

        nombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombresActionPerformed(evt);
            }
        });

        apellidoMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoMaternoActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Fecha de Nacimiento");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Entidad Federativa");

        entidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        entidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entidadActionPerformed(evt);
            }
        });

        btnMasculino.setBackground(new java.awt.Color(204, 204, 204));
        btnMasculino.setForeground(new java.awt.Color(0, 0, 0));
        btnMasculino.setText("Hombre");
        btnMasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasculinoActionPerformed(evt);
            }
        });

        btnFemenino.setBackground(new java.awt.Color(204, 204, 204));
        btnFemenino.setForeground(new java.awt.Color(0, 0, 0));
        btnFemenino.setText("Mujer");
        btnFemenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFemeninoActionPerformed(evt);
            }
        });

        btnEnviar.setText("Validar CURP");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre(s)", "A Paterno", "A Materno", "Fecha Nacimeinto", "Entidad", "Genero", "CURP"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("CURP ( O ingresalo si ya cuentas con uno )");

        curp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                curpActionPerformed(evt);
            }
        });

        btnGenerarCurp.setText("Generar CURP");
        btnGenerarCurp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarCurpActionPerformed(evt);
            }
        });

        btnObtenerPersonaPorCURP.setText("Buscar Datos con CURP");
        btnObtenerPersonaPorCURP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObtenerPersonaPorCURPActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMasculino, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(btnFemenino, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nombres, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(apellidoPaterno, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fechaNacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addComponent(entidad, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(apellidoMaterno)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(curp, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(btnGenerarCurp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnObtenerPersonaPorCURP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(272, 272, 272))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(curp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(apellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(btnGenerarCurp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(6, 6, 6))
                            .addComponent(btnObtenerPersonaPorCURP, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(entidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar)
                        .addComponent(btnActualizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMasculino)
                    .addComponent(btnFemenino))
                .addGap(18, 18, 18)
                .addComponent(btnEnviar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void entidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entidadActionPerformed

    /**
     * Esta función se ejecuta cuando el usuario hace clic en el checkbox de
     * "Hombre" (btnMasculino). Al hacer clic, se asigna el valor "H" a la
     * variable de estado `dataGenero`, indicando que el género seleccionado es
     * masculino. Además, si el checkbox de "Femenino" (btnFemenino) está
     * seleccionado, se desmarca automáticamente.
     */
    private void btnMasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasculinoActionPerformed
        // TODO add your handling code here:
        // Asignar el valor "H" a la variable de estado `dataGenero` para indicar que el género es masculino
        this.dataGenero = "H";

        // Si el checkbox de "Femenino" está seleccionado, desmarcarlo
        if (this.btnFemenino.isSelected()) {
            this.btnFemenino.setSelected(false);
        }
    }//GEN-LAST:event_btnMasculinoActionPerformed

    /**
     * Esta función se ejecuta cuando el usuario hace clic en el checkbox de
     * "Femenino" (btnFemenino). Al hacer clic, se asigna el valor "M" a la
     * variable de estado `dataGenero`, indicando que el género seleccionado es
     * femenino. Además, si el checkbox de "Masculino" (btnMasculino) está
     * seleccionado, se desmarca automáticamente.
     */
    private void btnFemeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFemeninoActionPerformed
        // TODO add your handling code here:
        // Asignar el valor "M" a la variable de estado `dataGenero` para indicar que el género es femenino
        this.dataGenero = "M";

        // Si el checkbox de "Masculino" está seleccionado, desmarcarlo
        if (this.btnMasculino.isSelected()) {
            this.btnMasculino.setSelected(false);
        }
    }//GEN-LAST:event_btnFemeninoActionPerformed

    private void nombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombresActionPerformed

    private void apellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoPaternoActionPerformed

    private void apellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoMaternoActionPerformed

    /**
     * Este método se ejecuta cuando el usuario hace clic en el botón "Enviar"
     * (btnEnviar). Realiza la validación de los datos ingresados y, si la CURP
     * es válida, registra un nuevo usuario en la base de datos. En caso de
     * éxito, actualiza la tabla con los datos de la base de datos y limpia los
     * campos de entrada. Si ocurre un error o si la CURP es inválida, muestra
     * un mensaje de error.
     */
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:
        // Obtener los datos ingresados en los campos de entrada
        obtenerLosDatos();

        // Validar que la CURP no sea nula ni vacía
        Boolean tieneCurp = validarNoNulosNiVacios(this.dataCurp);

        // Si la CURP es válida
        if (tieneCurp) {
            try {
                // Insertar los datos del nuevo usuario en la base de datos
                db.insertarValores(this.dataNombres, this.dataApellidoPaterno, this.dataApellidoMaterno,
                        this.dataFechaNacimiento, this.dataEntidad, this.dataGenero, this.dataCurp);

                // Actualizar la tabla con los datos más recientes desde la base de datos
                actualizarTablaConBD();

                // Limpiar los campos de entrada para preparar el formulario para un nuevo registro
                limpiarLasEntradas();
            } catch (SQLException ex) {
                // Si ocurre un error en la base de datos, mostrar un mensaje de error
                javax.swing.JOptionPane.showMessageDialog(this, "Ocurrio un error en la base de datos.");
            }
        } else {
            // Si la CURP no es válida, mostrar un mensaje solicitando una CURP válida
            javax.swing.JOptionPane.showMessageDialog(this, "Ingresa una CURP valida.");
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void curpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_curpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_curpActionPerformed

    /**
     * Este método se ejecuta cuando el usuario hace clic en el botón "Generar
     * CURP" (btnGenerarCurp). Obtiene los datos ingresados en los campos de
     * texto, valida que todos los campos estén completos y, si es así, genera
     * una CURP utilizando los datos. La CURP generada se muestra en un cuadro
     * de diálogo y en un campo de texto. Si alguno de los campos está vacío o
     * hay un error al generar la CURP, se muestra un mensaje de error.
     */
    private void btnGenerarCurpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarCurpActionPerformed
        try {
            // Obtener los datos ingresados en los campos de entrada
            obtenerLosDatos();

            // Validar que todos los campos requeridos tengan datos
            Boolean sonValidos = validarNoNulosNiVacios(this.dataNombres, this.dataApellidoPaterno, this.dataApellidoMaterno,
                    this.dataEntidad, this.dataFechaNacimiento, this.dataGenero);

            // Si todos los datos son válidos (no son nulos ni vacíos)
            if (sonValidos) {
                // Llamar al método para generar la CURP
                String curp = generarNuevaCURP();

                // Asignar la CURP generada al campo correspondiente
                this.dataCurp = curp;

                // Mostrar la CURP generada en un cuadro de diálogo
                javax.swing.JOptionPane.showMessageDialog(this, "¡Tu CURP ES EL SIGUIENTE! :: " + this.dataCurp);

                // Mostrar la CURP en el campo de texto de la interfaz
                this.curp.setText(curp);
            } else {
                // Si alguno de los campos está vacío, mostrar un mensaje de advertencia
                javax.swing.JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            }

        } catch (Exception e) {
            // Si ocurre un error al generar la CURP, mostrar un mensaje de error
            javax.swing.JOptionPane.showMessageDialog(this, "Error al generar la CURP!!!.");
            System.out.println("ERROR:: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGenerarCurpActionPerformed

    /**
     * Este método se ejecuta cuando el usuario hace clic en el botón "Obtener
     * Persona por CURP" (btnObtenerPersonaPorCURP). Recupera la CURP ingresada
     * en el campo correspondiente, utiliza esa CURP para buscar la información
     * del usuario en la base de datos, y luego llena los campos del formulario
     * con los datos recuperados. Si la CURP no corresponde a un usuario en la
     * base de datos, se muestra un mensaje de advertencia.
     */
    private void btnObtenerPersonaPorCURPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObtenerPersonaPorCURPActionPerformed
        // TODO add your handling code here:
        try {
            // Recuperar la CURP ingresada en el campo de texto
            this.dataCurp = this.curp.getText();

            // Llamar al método para obtener los datos del usuario de la base de datos
            String[] persona = db.obtenerPersonaPorCURP(this.dataCurp);

            // Verificar si se encontró un usuario con la CURP proporcionada
            if (persona != null) {

                // Asignar los valores recuperados a los campos correspondientes en la interfaz gráfica
                this.nombres.setText(persona[0]);          // Nombre del usuario
                this.apellidoPaterno.setText(persona[1]);  // Apellido paterno del usuario
                this.apellidoMaterno.setText(persona[2]);  // Apellido materno del usuario

                try {
                    // Convertir la fecha de nacimiento del formato string a tipo Date
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = format.parse(persona[3]);
                    this.fechaNacimiento.setDate(fecha);   // Asignar la fecha al campo de fecha de nacimiento
                } catch (ParseException ex) {
                    // Si ocurre un error al convertir la fecha, imprimir un mensaje de error
                    System.out.println("No se pudo convertir la fecha");
                }

                // Asignar la entidad federativa al campo correspondiente (dropdown o combo box)
                this.entidad.setSelectedItem(persona[4]);

                // Asignar el valor del género
                this.dataGenero = persona[5];

                // Configurar los botones de selección de género en función del valor recuperado
                if ("H".equals(this.dataGenero)) {
                    this.btnMasculino.setSelected(true);    // Si el género es masculino, marcar el botón masculino
                    this.btnFemenino.setSelected(false);    // Desmarcar el botón femenino
                } else if ("M".equals(this.dataGenero)) {
                    this.btnFemenino.setSelected(true);     // Si el género es femenino, marcar el botón femenino
                    this.btnMasculino.setSelected(false);   // Desmarcar el botón masculino
                }
            } else {
                // Si no se encontró un usuario con la CURP proporcionada, mostrar un mensaje de advertencia
                javax.swing.JOptionPane.showMessageDialog(this, "No encontramos ningun usuario.");
            }
        } catch (Exception e) {
            // Error en caso de no obtener usuario
            System.out.println("Ocurrio un error al obtener la informacion!!");
        }
    }//GEN-LAST:event_btnObtenerPersonaPorCURPActionPerformed

    /**
     * Este método se ejecuta cuando el usuario hace clic en el botón "Eliminar"
     * (btnEliminar). Recupera la CURP ingresada en el campo correspondiente,
     * valida que no esté vacía, y luego llama a un método de la base de datos
     * para eliminar el usuario correspondiente. Después de la eliminación, se
     * actualiza la tabla con los datos actuales de la base de datos y limpia
     * los campos del formulario. Si la CURP no está ingresada, se muestra un
     * mensaje de advertencia.
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:

        try {
            // Recuperar la CURP ingresada en el campo de texto
            this.dataCurp = this.curp.getText();

            // Verificar que la CURP no esté vacía
            if (this.dataCurp != null && !this.dataCurp.isEmpty()) {

                // Llamar al método de la base de datos para eliminar al usuario con la CURP proporcionada
                db.eliminarPersonaPorCURP(dataCurp);

                // Mostrar un mensaje indicando que la CURP fue eliminada con éxito
                javax.swing.JOptionPane.showMessageDialog(this, "CURP :: " + this.dataCurp + " Eliminada con éxito");

                // Actualizar la tabla con los datos más recientes de la base de datos
                actualizarTablaConBD();

                // Limpiar los campos de entrada del formulario
                limpiarLasEntradas();
            } else {
                // Si no se ingresa una CURP, mostrar un mensaje de advertencia
                javax.swing.JOptionPane.showMessageDialog(this, "Ingresa una CURP.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al eliminar el usuario");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Este método se ejecuta cuando el usuario hace clic en el botón
     * "Actualizar" (btnActualizar). Recupera los datos de los campos de entrada
     * del formulario, valida que no haya campos vacíos, y luego actualiza el
     * registro correspondiente en la base de datos usando la CURP
     * proporcionada. Si la actualización es exitosa, también genera una nueva
     * CURP para el usuario y actualiza la base de datos. Después de la
     * actualización, se recarga la tabla con los datos más recientes y limpia
     * los campos del formulario. Si no se completan todos los campos, muestra
     * un mensaje solicitando que se llenen.
     */
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        try {
            // Recuperar los datos de los campos de entrada
            obtenerLosDatos();

            // Validar que los datos esenciales no estén vacíos
            Boolean sonValidos = validarNoNulosNiVacios(this.dataNombres, this.dataApellidoPaterno, this.dataApellidoMaterno, this.dataEntidad, this.dataFechaNacimiento, this.dataGenero);

            if (sonValidos) {
                try {
                    // Actualizar los datos del usuario en la base de datos usando la CURP proporcionada
                    db.actualizarPersonaPorCURP(this.dataNombres, this.dataApellidoPaterno, this.dataApellidoMaterno, this.dataFechaNacimiento, this.dataEntidad, this.dataGenero, this.dataCurp);

                    // Generar una nueva CURP para el usuario y actualizarla en la base de datos
                    String nuevaCurp = generarNuevaCURP();
                    db.actualizarCurpPorCURP(this.dataCurp, nuevaCurp);

                    // Actualizar la tabla con los datos más recientes de la base de datos
                    actualizarTablaConBD();

                    // Limpiar los campos de entrada del formulario
                    limpiarLasEntradas();

                    // Mostrar un mensaje de éxito con la nueva CURP
                    javax.swing.JOptionPane.showMessageDialog(this, "Usuario Actualizado!! Nueva CURP :: " + nuevaCurp);

                } catch (SQLException ex) {
                    // En caso de error en la base de datos, mostrar un mensaje en la consola
                    System.out.println("Ocurrió un error al actualizar el registro!!!");
                }
            } else {
                // Si hay campos vacíos, mostrar un mensaje solicitando que se llenen
                javax.swing.JOptionPane.showMessageDialog(this, "Rellena todos los campos.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el registro");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    // LOGICA DE INTERFAZ Y FUNCIONES AUXILIARES 
    /**
     * Esta función auxiliar valida que todas las cadenas de texto pasadas como
     * parámetros no sean nulas ni vacías. Si alguna de las cadenas es nula o
     * vacía, la función retorna `false`; de lo contrario, retorna `true`.
     *
     * @param valores Las cadenas de texto a validar.
     * @return `true` si todas las cadenas son diferentes de null y no están
     * vacías, `false` en caso contrario.
     */
    public static boolean validarNoNulosNiVacios(String... valores) {
        return Arrays.stream(valores).allMatch(valor -> valor != null && !valor.isEmpty());
    }

    /**
     * Esta función recupera los datos de las entradas de texto y otros
     * componentes de la interfaz de usuario, y los almacena en las variables
     * correspondientes para ser procesados o almacenados en la base de datos.
     *
     * Asocia los valores de los campos de texto, el JComboBox y el JDateChooser
     * con las variables que contienen la información del usuario.
     */
    private void obtenerLosDatos() {
        // Recuperar el texto ingresado en los campos de texto
        this.dataNombres = this.nombres.getText();  // Almacena el nombre
        this.dataApellidoPaterno = this.apellidoPaterno.getText();  // Almacena el apellido paterno
        this.dataApellidoMaterno = this.apellidoMaterno.getText();  // Almacena el apellido materno
        this.dataCurp = this.curp.getText();  // Almacena la CURP

        // Recuperar la entidad seleccionada en el JComboBox y almacenarla
        this.dataEntidad = this.entidad.getSelectedItem().toString();  // Almacena el estado

        // Recuperar la fecha seleccionada en el JDateChooser y formatearla
        Date fecha = this.fechaNacimiento.getDate();  // Obtiene la fecha
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");  // Define el formato de fecha
        this.dataFechaNacimiento = formato.format(fecha);  // Almacena la fecha en formato 'yyyy-MM-dd'
    }

    /**
     * Esta función limpia todos los campos de entrada en la interfaz de
     * usuario, restableciendo tanto los componentes visuales como las variables
     * internas que almacenan la información del usuario.
     *
     * Elimina el texto de los campos de texto, resetea la selección del
     * JComboBox, desmarca los botones de género y limpia las variables que
     * almacenan la información de los datos del usuario.
     */
    private void limpiarLasEntradas() {
        // Limpiar los campos de texto
        this.nombres.setText("");  // Limpia el campo de nombre
        this.apellidoPaterno.setText("");  // Limpia el campo de apellido paterno
        this.apellidoMaterno.setText("");  // Limpia el campo de apellido materno
        this.curp.setText("");  // Limpia el campo de CURP

        // Limpiar el campo de fecha de nacimiento
        this.fechaNacimiento.setDate(null);  // Elimina la fecha seleccionada en el JDateChooser

        // Resetear el JComboBox de entidades a su primer valor (por defecto)
        this.entidad.setSelectedIndex(0);  // Resetea el JComboBox de estados al primer índice

        // Desmarcar los botones de género
        this.btnMasculino.setSelected(false);  // Desmarca el botón de Masculino
        this.btnFemenino.setSelected(false);  // Desmarca el botón de Femenino

        // Limpiar las variables internas que almacenan los datos
        this.dataNombres = null;  // Limpia la variable de nombre
        this.dataApellidoMaterno = null;  // Limpia la variable de apellido materno
        this.dataApellidoPaterno = null;  // Limpia la variable de apellido paterno
        this.dataEntidad = null;  // Limpia la variable de estado
        this.dataCurp = null;  // Limpia la variable de CURP
        this.dataFechaNacimiento = null;  // Limpia la variable de fecha de nacimiento
        this.dataGenero = null;  // Limpia la variable de género
    }

    /**
     * Esta función agrega un nuevo registro a la tabla JTable. Los datos del
     * registro son pasados como parámetros y se añaden como una nueva fila en
     * el modelo de la tabla (DefaultTableModel).
     *
     * @param nombre Nombre de la persona.
     * @param apellidoPaterno Apellido paterno de la persona.
     * @param apellidoMaterno Apellido materno de la persona.
     * @param fechaNacimiento Fecha de nacimiento de la persona en formato
     * "yyyy-MM-dd".
     * @param entidad Entidad federativa de la persona.
     * @param genero Género de la persona (por ejemplo: "H" para masculino, "M"
     * para femenino).
     * @param curp CURP de la persona.
     */
    private void agregarUnRegistroEnTabla(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String entidad, String genero, String curp) {
        DefaultTableModel model = (DefaultTableModel) Tabla.getModel(); // Obtener el modelo de la tabla (DefaultTableModel)
        model.addRow(new Object[]{nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, entidad, genero, curp}); // Agregar una nueva fila con los datos proporcionados
    }

    /**
     * Esta función actualiza el contenido de la tabla JTable con los datos más
     * recientes obtenidos de la base de datos. Primero limpia la tabla y luego
     * agrega los registros de las personas recuperadas desde la base de datos.
     *
     * La función consulta la base de datos a través de un método de la clase
     * `db` que obtiene una lista de registros, luego limpia la tabla actual y
     * agrega cada registro recuperado de la base de datos.
     */
    private void actualizarTablaConBD() {
        List<String[]> personas = db.obtenerPersonas(); // Obtener la lista de personas desde la base de datos
        limpiaLaTabla(); // Limpiar la tabla actual
        for (String[] persona : personas) { // Iterar sobre cada registro de persona
            agregarUnRegistroEnTabla(persona[0], persona[1], persona[2], persona[3], persona[4], persona[5], persona[6]); // Agregar cada persona como una fila en la tabla
        }
    }

    /**
     * Esta función genera una nueva CURP (Clave Única de Registro de Población)
     * utilizando los datos proporcionados desde la interfaz de usuario. La CURP
     * se genera mediante la clase `CurpGenerador`, que utiliza los datos de la
     * persona (nombre, apellidos, fecha de nacimiento, entidad y género) para
     * crear una CURP válida.
     *
     * @return La CURP generada como un string.
     */
    private String generarNuevaCURP() {
        CurpGenerador generador = new CurpGenerador(); // Instancia de la clase CurpGenerador
        // Llamada al método generarCURP de la clase CurpGenerador, pasando los datos actuales
        String curp = generador.generarCURP(this.dataNombres, this.dataApellidoPaterno, this.dataApellidoMaterno, this.dataFechaNacimiento, this.dataEntidad, this.dataGenero);
        return curp; // Retorna la CURP generada
    }

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JTextField apellidoMaterno;
    private javax.swing.JTextField apellidoPaterno;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JCheckBox btnFemenino;
    private javax.swing.JButton btnGenerarCurp;
    private javax.swing.JCheckBox btnMasculino;
    private javax.swing.JButton btnObtenerPersonaPorCURP;
    private javax.swing.JTextField curp;
    private javax.swing.JComboBox<String> entidad;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombres;
    // End of variables declaration//GEN-END:variables
}
