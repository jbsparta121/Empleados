
package com.mycompany.modelo;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jbsparta121
 */
public class CorreoElectronicoDAO {
    private Session sesion;
    private Transaction trns;
    
    private void iniciaOperacion(){
        sesion = HibernateUtil.getSessionFactory().openSession();
        trns = sesion.beginTransaction();
    }
    
    
    private void manejaException(HibernateException e){
       trns.rollback();
       throw new HibernateException("Error en la capa de datos." + e);
    }
    
    public List<CorreoElectronico> listar(Empleado empleado) {
        List<CorreoElectronico> listaCorreos = null;
        iniciaOperacion();
        listaCorreos = sesion.createQuery("from CorreoElectronico where empleado = " + empleado.getId()).list();
        return listaCorreos;
    }
    
    public boolean eliminar(CorreoElectronico correoElectronico){
        boolean ok = false;
        
        try{
            iniciaOperacion();
            sesion.delete(correoElectronico);
            trns.commit();
            ok = true;          
        }
        catch(HibernateException e){
            manejaException(e);
            ok = false;
        }
    return ok;
    }  
    
    public boolean actualizar(CorreoElectronico correoElectronico){
        boolean ok = false;
        
        try{
            iniciaOperacion();
            sesion.update(correoElectronico);
            trns.commit();
            ok = true;          
        }
        catch(HibernateException e){
            manejaException(e);
            ok =  false;
        }
    return ok;
    }
}
