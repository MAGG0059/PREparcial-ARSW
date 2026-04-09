package edu.eci.arsw.exam.events;

import edu.eci.arsw.exam.FachadaPersistenciaOfertas;
import edu.eci.arsw.exam.Oferta;
import edu.eci.arsw.exam.MainFrame;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class OfertaListener {

    private FachadaPersistenciaOfertas fachada;
    private RabbitTemplate rabbitTemplate;

    public void setFachada(FachadaPersistenciaOfertas fachada) {
        this.fachada = fachada;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void recibirOferta(Oferta oferta) {
        System.out.println("Oferta recibida: " + oferta.getCompradorId() + " - $" + oferta.getMonto());

        boolean aceptada = fachada.agregarOferta(oferta.getProductCode(), oferta);

        if (aceptada) {
            System.out.println("Oferta aceptada para producto: " + oferta.getProductCode());
        }

        if (fachada.isSubastaCerrada(oferta.getProductCode())) {
            Oferta ganador = fachada.getGanador(oferta.getProductCode());
            MainFrame.mostrarGanadorEnUI(oferta.getProductCode(), ganador);

            if (rabbitTemplate != null) {
                rabbitTemplate.convertAndSend("WINNER-EXCHANGE", "winner", ganador);
                System.out.println("Notificacion enviada al ganador: " + ganador.getCompradorId());
            }
        }
    }
}