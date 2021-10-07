package br.com.zup.edu.recargadecelular.controller;

import br.com.zup.edu.recargadecelular.dto.RecargaRequest;
import br.com.zup.edu.recargadecelular.dto.TransacaoMessage;
import br.com.zup.edu.recargadecelular.model.Recarga;
import br.com.zup.edu.recargadecelular.repository.RecargaRepository;
import br.com.zup.edu.recargadecelular.service.RecargaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/recarga")
public class RecargaController {

    Logger logger = LoggerFactory.getLogger(RecargaController.class);


    @Autowired
    private RecargaRepository recargaRepository;

    @Autowired
    private RecargaProducer recargaProducer;

    @PostMapping
    public ResponseEntity<?> recarrega(@Valid @RequestBody RecargaRequest recargaRequest){
        logger.info("Chamando a API externa orquestrador");
        Recarga recarga = recargaRequest.toModel();
        recargaRepository.save(recarga);
        TransacaoMessage transacaoMessage = new TransacaoMessage(recargaRequest.getOperadora().toString(), recargaRequest.getValor(), LocalDateTime.now().toString(), 1L, 2L);
        recargaProducer.send(transacaoMessage);

        return ResponseEntity.ok().build();
    }
}
