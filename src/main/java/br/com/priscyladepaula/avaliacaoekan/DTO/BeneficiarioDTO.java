package br.com.priscyladepaula.avaliacaoekan.DTO;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class BeneficiarioDTO {

    private Long id;
    private String nome;
    private Long telefone;
    private LocalDate dataNascimento;
    private Date dataInclusao;
    private Date dataAtualizacao;

}
