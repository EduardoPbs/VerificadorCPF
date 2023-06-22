package br.edu.fema.domain.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CpfService {
    // Verificação do resto da divisão;
    private Integer standard(Integer number){
        if(number < 2) return 0;
        if(number > 10) return 0;
        return number;
    }

    public String verify(String cpf) {
        final List<Integer> digits = new ArrayList<>();
        Integer total = 0;
        Integer counter = 10;

        // Verificação do primeiro dígito do CPF;
        for(int i = 0; i < cpf.substring(0, 9).length(); i++){
            Integer digit = Integer.parseInt(String.valueOf(cpf.charAt(i)));
            total += digit * counter;
            counter--;
        }
        // Cálculo do primeiro dígito e adiciona na lista;
        digits.add(11 - standard(total % 11));

        // Verificação do segundo dígito do CPF;
        total = 0;
        counter = 11;
        for(int j = 0; j < cpf.substring(0, 10).length(); j++){
            Integer digit = Integer.parseInt(String.valueOf(cpf.charAt(j)));
            total += digit * (counter);
            counter--;
        }

        // Cálculo do segundo dígito e adiciona na lista;
        digits.add(11 - standard(total % 11));

        // Tranforamando dígitos em String apenas :) e retornando;
        String validators = String.valueOf(digits.get(0)) + String.valueOf(digits.get(1));
        return validators;
    }

}
