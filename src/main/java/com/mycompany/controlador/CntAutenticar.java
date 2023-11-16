
package com.mycompany.controlador;

import com.mycompany.modelo.EmpleadoDAO;
import com.mycompany.modelo.HibernateUtil;
import com.mycompany.vista.FrmAutenticar;
import com.mycompany.vista.FrmEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jbsparta121
 */
public class CntAutenticar implements ActionListener {
    private FrmAutenticar frmAutenticar;
    
    public CntAutenticar(FrmAutenticar frmAutenticar) {
        this.frmAutenticar = frmAutenticar;
        registrarControladores();
    }
    
    private void registrarControladores() {
        frmAutenticar.getBtnAutenticar().addActionListener(this);
    }
    
    public void conectar() {
        String usuario = frmAutenticar.getTxtUsuario().getText();
        String contraseña = frmAutenticar.getTxtContraseña().getText();
        if (HibernateUtil.getSessionFactory(usuario, contraseña) != null) {
            FrmEmpleado frmEmpleado = new FrmEmpleado();
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            CntEmpleado cntEmpleado = new CntEmpleado(frmEmpleado, empleadoDAO);
            frmAutenticar.dispose();
            frmEmpleado.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Fallo en la conexión");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmAutenticar.getBtnAutenticar()) {
            conectar();
        } 
    }
}
