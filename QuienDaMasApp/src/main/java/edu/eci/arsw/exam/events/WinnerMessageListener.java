package edu.eci.arsw.exam.events;

import edu.eci.arsw.exam.IdentityGenerator;
import edu.eci.arsw.exam.Oferta;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class WinnerMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Oferta ofertaGanadora = (Oferta) ois.readObject();
            ois.close();

            System.out.println("EL COMPRADOR " + IdentityGenerator.actualIdentity +
                    " COMPRO EL PRODUCTO " + ofertaGanadora.getProductCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}