package com.universidad.adnevaluador.application;


import com.universidad.adnevaluador.domain.service.MutantService;
import com.universidad.adnevaluador.infrastructure.shared.dto.DNASequenceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

/**
 * ClassName
 **/
class MutantCtrTest {

    @Mock
    MutantService mutantService;

    @InjectMocks
    MutantCtr mutantCtr;

    @Mock
    ModelMapper mapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        ModelMapper mapper = Mockito.mock(ModelMapper.class);
    }

    @Test
    void validatMutantDnaOkAndDnaMutant(){
        //Arrange
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTC"};
        DNASequenceDto dnaSequenceDto = new DNASequenceDto();
        dnaSequenceDto.setDna(dna);

        when(mutantService.isMutant(dna)).thenReturn(true);
        //Act
        ResponseEntity<String> httpResponse = mutantCtr.isMutant(dnaSequenceDto);
        //Assert
        Assertions.assertEquals(HttpStatus.OK, httpResponse.getStatusCode());
    }

    @Test
    void validateMutantDnaBadRequest(){
        //Arrange
        String[] dna = {"ATGCGA","CAGTGC1","TTATGT","AGAAGG","CCCCTA","TCACTC"};
        DNASequenceDto dnaSequenceDto = new DNASequenceDto();
        dnaSequenceDto.setDna(dna);

        when(mutantService.isMutant(dna)).thenReturn(true);
        //Act
        ResponseEntity<String> httpResponse = mutantCtr.isMutant(dnaSequenceDto);
        //Assert
        Assertions.assertEquals(httpResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void validatemutantDnaOKAndDnaHuman(){
        //Arrange
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTC"};
        DNASequenceDto dnaSequenceDto = new DNASequenceDto();
        dnaSequenceDto.setDna(dna);
        when(mutantService.isMutant(dna)).thenReturn(false);
        //Act
        ResponseEntity<String> httpResponse = mutantCtr.isMutant(dnaSequenceDto);
        //Assert
        Assertions.assertEquals(httpResponse.getStatusCode(), HttpStatus.FORBIDDEN);
    }
}