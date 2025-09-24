package br.univille.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pagamento_id")
    private Long id;
    
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
      name = "pagamento_parcela", 
      joinColumns = @JoinColumn(name = "pagamento_id"), 
      inverseJoinColumns = @JoinColumn(name = "parcela_id"))
    private Set<Parcela> parcelasQuitadas;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public LocalDate getDataPagamento() {
      return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
      this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValorPago() {
      return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
      this.valorPago = valorPago;
    }

    public String getMetodoPagamento() {
      return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
      this.metodoPagamento = metodoPagamento;
    }

    public Cliente getCliente() {
      return cliente;
    }

    public void setCliente(Cliente cliente) {
      this.cliente = cliente;
    }

    public Set<Parcela> getParcelasQuitadas() {
      return parcelasQuitadas;
    }

    public void setParcelasQuitadas(Set<Parcela> parcelasQuitadas) {
      this.parcelasQuitadas = parcelasQuitadas;
    }

    
}