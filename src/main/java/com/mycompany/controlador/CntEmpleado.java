
package com.mycompany.controlador;

import com.mycompany.modelo.*;
import com.mycompany.vista.FrmEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jbsparta121
 */
public class CntEmpleado implements ActionListener {
    private FrmEmpleado frmEmpleado;
    private Empleado empleado;
    private EmpleadoDAO empleadoDAO;
    private CorreoElectronico correoElectronico;
    private CorreoElectronicoDAO correoElectronicoDAO;
    private Set<CorreoElectronico> correosElectronicos;
    
    public CntEmpleado(FrmEmpleado frmEmpleado, EmpleadoDAO empleadoDAO) {
        this.frmEmpleado = frmEmpleado;
        this.empleadoDAO = empleadoDAO;
        registrarControladores();
    }
    
    private void registrarControladores() {
        frmEmpleado.getBtnGrabar().addActionListener(this);
        frmEmpleado.getBtnConsultar().addActionListener(this);
        frmEmpleado.getBtnActualizar().addActionListener(this);
        frmEmpleado.getBtnEliminar().addActionListener(this);
        frmEmpleado.getBtnEliminarCorreo().addActionListener(this);
        frmEmpleado.getBtnLimpiar().addActionListener(this);
        frmEmpleado.getBtnAdicionar().addActionListener(this);
    }
    
    
    
    public void obtenerListadoDeCorreosElectronicos() {
        correoElectronicoDAO = new CorreoElectronicoDAO();
        List<CorreoElectronico> listaCorreos = correoElectronicoDAO.listar(empleado);
        String correo[] = new String[1];
        for (CorreoElectronico correoElectronico : listaCorreos) {
            correo[0] = correoElectronico.getCorreo();
            frmEmpleado.getDtmCorreosElectronicos().addRow(correo);
        }
    }
    
    public void obtenerEmpleado() {
        Integer id = Integer.valueOf(frmEmpleado.getTxtId().getText());
        String nombre = frmEmpleado.getTxtNombre().getText();
        Long timeInMiliSeconds = frmEmpleado.getDateFechaNacimiento().getDate().getTime();
        Date fechaNacimiento = new Date(timeInMiliSeconds);
        empleado = new Empleado(id, nombre, fechaNacimiento);       
    }
    
    public void crearListadoCorreosElectronicos() {
        DefaultTableModel dtmCorreosElectronicos = frmEmpleado.getDtmCorreosElectronicos();
        correosElectronicos = new HashSet();
        int filas = dtmCorreosElectronicos.getRowCount();
        int columnas = dtmCorreosElectronicos.getColumnCount();
        String correo = null;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                correo = (String) dtmCorreosElectronicos.getValueAt(i, j);
            }
            correosElectronicos.add(new CorreoElectronico(correo, empleado));
        }
    }
    
    private void grabar() {
        obtenerEmpleado();
        crearListadoCorreosElectronicos();
        empleado.setCorreosElectronicos(correosElectronicos);
        for(CorreoElectronico correo: correosElectronicos) {
   
            JOptionPane.showMessageDialog(frmEmpleado, correo.getCorreo() + " " + correo.getEmpleado().getId());
            
        }
        if (empleadoDAO.grabar(empleado)) {
            JOptionPane.showMessageDialog(null, "Empleado almacenado");
        } else {
            JOptionPane.showMessageDialog(null,"Empleado no almacenado");
        }
    }
    
    private void consultar() {
        Integer id = Integer.valueOf(frmEmpleado.getTxtId().getText());
        empleado = empleadoDAO.consultar(id);
        if (empleado != null) {
            frmEmpleado.getTxtNombre().setText(empleado.getNombre());
            frmEmpleado.getDateFechaNacimiento().setDate(empleado.getFechaNacimiento());
            obtenerListadoDeCorreosElectronicos();
        } else {
            JOptionPane.showMessageDialog(frmEmpleado, "Empleado no encontrado");
        }
    }
    
    private void modificarCorreo(){
        Integer id = Integer.valueOf(frmEmpleado.getTxtId().getText());
        String correo = frmEmpleado.getTxtCorreosElectronicos().getText();
        correoElectronico = new CorreoElectronico(correo,new Empleado(id));
        if(!correoElectronicoDAO.actualizar(correoElectronico)) {
            
        }
    }
    
    private void eliminar() {
        Integer id = Integer.valueOf(frmEmpleado.getTxtId().getText());
        empleado = new Empleado(id);
        if (empleadoDAO.eliminar(empleado)) {
            JOptionPane.showMessageDialog(frmEmpleado, "Empleado borrado");
        } else {
            JOptionPane.showMessageDialog(frmEmpleado, "Empleado no borrado");
        }
    }
    
    private void limpiarTabla() {
        int a = frmEmpleado.getDtmCorreosElectronicos().getRowCount()-1;
        for(int i = a; i >= 0; i--) {
            frmEmpleado.getDtmCorreosElectronicos().removeRow(i);
        }
    }
    
    private void limpiar() {
        frmEmpleado.getTxtId().setText("");
        frmEmpleado.getTxtNombre().setText("");
        frmEmpleado.getDateFechaNacimiento().setDate(null);
    }
    
    private void obtenerCorreo() {
        String correo = frmEmpleado.getTxtCorreosElectronicos().getText();
        Object datos[] = {correo};
        frmEmpleado.getDtmCorreosElectronicos().addRow(datos);
        frmEmpleado.getTxtCorreosElectronicos().setText("");
    }
    
    private void eliminarCorreo() {
        int numeroFila = frmEmpleado.getTblCorreosElectronicos().getSelectedRow();
        int respuesta = JOptionPane.showConfirmDialog(frmEmpleado, "Quiere eliminar el correo seleccionado?" +
                frmEmpleado.getDtmCorreosElectronicos().getValueAt(numeroFila, 0));
        String correo = frmEmpleado.getDtmCorreosElectronicos().getValueAt(numeroFila, 0).toString();
        if(respuesta == JOptionPane.YES_OPTION) {
            correoElectronicoDAO = new CorreoElectronicoDAO();
            empleado = new Empleado(Integer.parseInt(frmEmpleado.getTxtId().getText()));
            correoElectronico = new CorreoElectronico(correo,empleado);
            correoElectronicoDAO.eliminar(correoElectronico);
            frmEmpleado.getDtmCorreosElectronicos().removeRow(numeroFila);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmEmpleado.getBtnAdicionar()) {
            obtenerCorreo();
        } else if (e.getSource() == frmEmpleado.getBtnGrabar()) {
            grabar();
            limpiar();
            limpiarTabla();
        } else if (e.getSource() == frmEmpleado.getBtnConsultar()) {
            consultar();
        } else if (e.getSource() == frmEmpleado.getBtnEliminar()) {
            eliminar();
            limpiar();
            limpiarTabla();
        } else if (e.getSource() == frmEmpleado.getBtnActualizar()) {
            
        } else if (e.getSource() == frmEmpleado.getBtnEliminarCorreo()) {
            eliminarCorreo();
        } else {
            limpiar();
            limpiarTabla();
        }
    }
}
