
package com.mycompany.unoamuchosjpahibernate;

import com.mycompany.controlador.CntAutenticar;
import com.mycompany.vista.FrmAutenticar;

/**
 *
 * @author jbsparta121
 */
public class UnoAMuchosJPAHibernate {

    public static void main(String[] args) {
        FrmAutenticar frmAutenticar = new FrmAutenticar();
        CntAutenticar cntAutenticar = new CntAutenticar(frmAutenticar);
        frmAutenticar.setVisible(true);
    }
}
