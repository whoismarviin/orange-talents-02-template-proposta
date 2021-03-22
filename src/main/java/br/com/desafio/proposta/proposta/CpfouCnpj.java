package br.com.desafio.proposta.proposta;


import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@CNPJ
@CPF
public @interface CpfouCnpj {
    String message() default "Cpf/Cnpj estão inválidos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
