package edu.eci.arsw.exam.events;

import edu.eci.arsw.exam.FachadaPersistenciaOfertas;
import edu.eci.arsw.exam.Oferta;
import edu.eci.arsw.exam.MainFrame;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfertaListener {

    @Autowired
    private FachadaPersistenciaOfertas fachada;

    @RabbitListener(queues = "ofertas.queue")
    public void recibirOferta(Oferta oferta) {
        System.out.println("Oferta recibida: " + oferta.getCompradorId() + " - $" + oferta.getMonto());

        boolean aceptada = fachada.agregarOferta(oferta.getProductCode(), oferta);

        if (aceptada) {
            System.out.println("Oferta aceptada para producto: " + oferta.getProductCode());

            if (fachada.isSubastaCerrada(oferta.getProductCode())) {
                Oferta ganador = fachada.getGanador(oferta.getProductCode());
                MainFrame.mostrarGanadorEnUI(oferta.getProductCode(), ganador);
                notificarGanador(ganador);
            }
        }
    }
}