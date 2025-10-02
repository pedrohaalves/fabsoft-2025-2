package br.univille.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venda_id")
    private Long id;

    @Column(name = "data_venda")
    private LocalDate dataVenda;

    @Column(name = "valor_total_negociado")
    private BigDecimal valorTotalNegociado;

    @Column(name = "forma_pagamento")
    private String formaPagamento;
    
    private String status; 

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fracao_id", referencedColumnName = "fracao_id")
    private Fracao fracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;

    @OneToOne(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Contrato contrato;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Parcela> parcelas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorTotalNegociado() {
        return valorTotalNegociado;
    }

    public void setValorTotalNegociado(BigDecimal valorTotalNegociado) {
        this.valorTotalNegociado = valorTotalNegociado;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Fracao getFracao() {
        return fracao;
    }

    public void setFracao(Fracao fracao) {
        this.fracao = fracao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    
}