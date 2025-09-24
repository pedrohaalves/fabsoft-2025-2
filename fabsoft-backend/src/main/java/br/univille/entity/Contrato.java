package br.univille.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contrato_id")
    private Long id;

    @Column(name = "codigo_contrato")
    private String codigoContrato;

    @Column(name = "data_assinatura")
    private LocalDate dataAssinatura;

    @Column(name = "caminho_documento_pdf")
    private String caminhoDocumentoPdf;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id", referencedColumnName = "venda_id")
    private Venda venda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(String codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public LocalDate getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(LocalDate dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public String getCaminhoDocumentoPdf() {
        return caminhoDocumentoPdf;
    }

    public void setCaminhoDocumentoPdf(String caminhoDocumentoPdf) {
        this.caminhoDocumentoPdf = caminhoDocumentoPdf;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }


}