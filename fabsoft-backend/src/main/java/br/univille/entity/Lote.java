package br.univille.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lotes")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lote_id")
    private Long id;

    private String identificador;
    
    @Column(name = "area_m2")
    private Double areaM2;

    @Column(name = "valor_total_base")
    private BigDecimal valorTotalBase;
    
    private String tipo;

    // AQUI ESTAVA O PROBLEMA: @JsonBackReference escondia o empreendimento.
    // Mudei para @JsonIgnoreProperties("lotes") para mostrar o empreendimento mas cortar o loop.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empreendimento_id")
    @JsonIgnoreProperties("lotes") 
    private Empreendimento empreendimento;

    // Ignora frações por enquanto para evitar complexidade
    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore 
    private List<Fracao> fracoes;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }
    public Double getAreaM2() { return areaM2; }
    public void setAreaM2(Double areaM2) { this.areaM2 = areaM2; }
    public BigDecimal getValorTotalBase() { return valorTotalBase; }
    public void setValorTotalBase(BigDecimal valorTotalBase) { this.valorTotalBase = valorTotalBase; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Empreendimento getEmpreendimento() { return empreendimento; }
    public void setEmpreendimento(Empreendimento empreendimento) { this.empreendimento = empreendimento; }
    public List<Fracao> getFracoes() { return fracoes; }
    public void setFracoes(List<Fracao> fracoes) { this.fracoes = fracoes; }
}