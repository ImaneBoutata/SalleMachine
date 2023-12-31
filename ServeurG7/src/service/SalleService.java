/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author hp
 */
public class SalleService extends UnicastRemoteObject implements IDao<Salle> {

    public SalleService() throws RemoteException {
        super();
    }

    @Override
    public boolean create(Salle s) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(s);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                etat = false;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return etat;
    }

    @Override
    public boolean update(Salle s) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(s);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                etat = false;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return etat;

    }

    @Override
    public boolean delete(Salle s) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(s);
            tx.commit();
            etat = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                etat = false;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return etat;
    }

    @Override
    public Salle findById(int id) throws RemoteException {
        Session session = null;
        Transaction tx = null;
        Salle salle = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            salle = (Salle) session.get(Salle.class, id);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return salle;
    }

    @Override
    public List<Salle> findAll() throws RemoteException {
        Session session = null;
        Transaction tx = null;
        List<Salle> salles = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            salles = session.getNamedQuery("findAllS").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return salles;
    }
    
      @Override
    public List<Machine> findMachinesBySalle(Salle s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

