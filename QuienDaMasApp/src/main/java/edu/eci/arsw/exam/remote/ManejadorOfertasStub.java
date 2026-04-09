package edu.eci.arsw.exam.remote;

import edu.eci.arsw.exam.Oferta;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ManejadorOfertasStub extends Remote {
    boolean agregarOferta(String productCode, Oferta oferta) throws RemoteException;
}