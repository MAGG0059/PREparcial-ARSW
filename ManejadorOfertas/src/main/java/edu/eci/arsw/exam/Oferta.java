package edu.eci.arsw.exam;

import java.io.Serializable;

public class Oferta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String compradorId;
    private String productCode;
    private int monto;

    public Oferta() {}

    public Oferta(String compradorId, String productCode, int monto) {
        this.compradorId = compradorId;
        this.productCode = productCode;
        this.monto = monto;
    }

    public String getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(String compradorId) {
        this.compradorId = compradorId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}