package br.edu.fema.adapter.in.api;

import br.edu.fema.domain.service.CpfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cpf")
public class CpfController {

    @Autowired
    private CpfService service;

    @GetMapping(value = {"/{cpf}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CpfResponse> generate(
                @PathVariable(value = "cpf", required = true) String cpf){
        String status;
        Boolean valid;

        // Recebe os dígitos válidos;
        final String validators = service.verify(cpf);

        // Verifica se os ultimos dígitos são iguais ao resultado do calculo;
        if(!cpf.substring(9, 11).equals(validators)){
            status = "CPF inválido";
            valid = false;
        } else {
            status = "CPF válido";
            valid = true;
        }

        CpfResponse response = new CpfResponse(
                cpf, status, valid, validators
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("x-api-generated", "true")
                .body(response);
    }

}
