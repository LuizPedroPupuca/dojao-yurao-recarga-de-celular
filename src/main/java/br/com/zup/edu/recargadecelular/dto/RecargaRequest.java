package br.com.zup.edu.recargadecelular.dto;

import br.com.zup.edu.recargadecelular.model.Operadora;
import br.com.zup.edu.recargadecelular.model.Recarga;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class RecargaRequest {

    @NotNull @Positive
    private BigDecimal valor;

    @NotNull
    private String numero;

    @NotNull
    private Operadora operadora;

    public RecargaRequest(BigDecimal valor, String numero, Operadora operadora) {
        this.valor = valor;
        this.numero = numero;
        this.operadora = operadora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNumero() {
        return numero;
    }

    public Operadora getOperadora() {
        return operadora;
    }

    public Recarga toModel(){
        return new Recarga(valor, numero, operadora);
    }
}
