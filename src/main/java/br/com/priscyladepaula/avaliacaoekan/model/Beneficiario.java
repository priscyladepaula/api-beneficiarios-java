package br.com.priscyladepaula.avaliacaoekan.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_beneficiarios")
@EntityListeners(AuditingEntityListener.class)
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Schema(hidden = true)
    private Long id;

    private String nome;
    private Long telefone;

    private LocalDate dataNascimento;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(hidden = true)
    @Column(updatable = false, nullable = false)
    @CreatedDate
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(hidden = true)
    @Column(nullable = false)
    @LastModifiedDate
    private Date dataAtualizacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "beneficiario_id"), inverseJoinColumns = @JoinColumn(name = "documento_id"))
    private Set<Documento> documentos;

    public Beneficiario() {

    }

    public Beneficiario(String nome, Long telefone, LocalDate dataNascimento, Date dataInclusao, Date dataAlteracao) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAlteracao;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

}
