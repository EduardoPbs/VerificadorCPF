package br.edu.fema.adapter.in.api;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(of = {"cpf", "status", "valid", "firstValidator", "secondValidator"})
public class CpfResponse {
    private final String cpf;
    private final String status;
    private final Boolean valid;
    private final String digits;

    public CpfResponse(String cpf, String status, Boolean valid, String digits){
        this.cpf = cpf;
        this.status = status;
        this.valid = valid;
        this.digits = digits;
    }


}
