package br.com.priscyladepaula.avaliacaoekan.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.priscyladepaula.avaliacaoekan.DTO.BeneficiarioDTO;
import br.com.priscyladepaula.avaliacaoekan.model.Beneficiario;
import br.com.priscyladepaula.avaliacaoekan.model.Documento;
// import br.com.priscyladepaula.avaliacaoekan.model.Documento;
import br.com.priscyladepaula.avaliacaoekan.repository.BeneficiarioRepository;
import br.com.priscyladepaula.avaliacaoekan.service.BeneficiarioService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/beneficiario")
@OpenAPIDefinition(info = @Info(title = "Avaliação Ekan - API", description = "API para cadastrar beneficiários. Banco de dados: H2.", version = "1.0"))
public class BeneficiarioController {

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private BeneficiarioService beneficiarioService;

    @PostMapping("/")
    public ResponseEntity<Beneficiario> create(@RequestBody Beneficiario beneficiario) {

        Beneficiario _beneficiario = this.beneficiarioRepository.save(beneficiario);
        return new ResponseEntity<>(_beneficiario, HttpStatus.CREATED);
    }

    @GetMapping("/all/")
    public ResponseEntity<List<BeneficiarioDTO>> listAll() {
        List<BeneficiarioDTO> beneficiarios = this.beneficiarioService.getAllBeneficiarios();
        return new ResponseEntity<>(beneficiarios, HttpStatus.OK);
    }

    @GetMapping("/{id}/documentos")
    public ResponseEntity<Set<Documento>> listAllDocumentos(@PathVariable Integer id){
        Beneficiario beneficiario = this.beneficiarioRepository.findById(id).orElse(null);

        if(beneficiario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<Documento> documentos = beneficiario.getDocumentos();
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Beneficiario beneficiario, @PathVariable Integer id) {
        Beneficiario _beneficiario = this.beneficiarioRepository.findById(id).orElse(null);

        if (_beneficiario != null) {
            _beneficiario.setNome(beneficiario.getNome());
            _beneficiario.setTelefone(beneficiario.getTelefone());
            _beneficiario.setDataNascimento(beneficiario.getDataNascimento());
            _beneficiario.setDocumentos(beneficiario.getDocumentos());

            this.beneficiarioRepository.saveAndFlush(_beneficiario);
            return new ResponseEntity<>("Cadastro do beneficiário atualizado com sucesso!", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Beneficiário não encontrado!", HttpStatus.NOT_FOUND);
        }

        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        var _beneficiario = this.beneficiarioRepository.findById(id).orElse(null);

        if (_beneficiario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Beneficiário não encontrado!");
        }

        this.beneficiarioRepository.deleteById(id);
        return ResponseEntity.ok().body("Cadastro removido com sucesso!");
    }

}
