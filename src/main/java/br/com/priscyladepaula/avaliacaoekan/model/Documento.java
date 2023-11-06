package br.com.priscyladepaula.avaliacaoekan.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_documentos")
@EntityListeners(AuditingEntityListener.class)
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Integer id;

    private String tipoDocumento;
    private String descricao;

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

    public Documento() {

    }

    public Documento(String tipoDocumento, String descricao, Date dataInclusao, Date dataAlteracao) {
        this.tipoDocumento = tipoDocumento;
        this.descricao = descricao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAlteracao;
    }

}
