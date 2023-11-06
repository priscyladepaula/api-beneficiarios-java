package br.com.priscyladepaula.avaliacaoekan.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.priscyladepaula.avaliacaoekan.DTO.BeneficiarioDTO;
import br.com.priscyladepaula.avaliacaoekan.model.Beneficiario;
import br.com.priscyladepaula.avaliacaoekan.repository.BeneficiarioRepository;

@Service
public class BeneficiarioService {

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    public List<BeneficiarioDTO> getAllBeneficiarios(){
        var _beneficiariosDTO = this.beneficiarioRepository.findAll()
        .stream()
        .map(this::convertEntityToDto)
        .collect(Collectors.toList());

        return _beneficiariosDTO;
    }
 
    private BeneficiarioDTO convertEntityToDto(Beneficiario beneficiario){
        BeneficiarioDTO beneficiarioDTO = new BeneficiarioDTO();
        beneficiarioDTO.setId(beneficiario.getId());
        beneficiarioDTO.setNome(beneficiario.getNome());
        beneficiarioDTO.setTelefone(beneficiario.getTelefone());
        beneficiarioDTO.setDataNascimento(beneficiario.getDataNascimento());
        beneficiarioDTO.setDataInclusao(beneficiario.getDataInclusao());
        beneficiarioDTO.setDataAtualizacao(beneficiario.getDataAtualizacao());

        return beneficiarioDTO;
    }

}
