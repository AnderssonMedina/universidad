package com.universidad.adnevaluador.domain.service;


import com.universidad.adnevaluador.domain.model.Mutant;
import com.universidad.adnevaluador.domain.model.Stats;
import com.universidad.adnevaluador.domain.service.dependendy.IResultMutantSave;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.when;

/**
 * ClassName
 **/
class MutantServiceTest {

    @Mock
    private IResultMutantSave resultMutantSave;

    @InjectMocks
    private MutantService mutantService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isMutantWhenDnaHasThreeMatches() {
        //Arrange
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTC"};
        when(resultMutantSave.validateExistDna(Arrays.toString(dna))).thenReturn(null);
        //Act
        boolean isMutant = mutantService.isMutant(dna);
        //Assert
        Assertions.assertTrue(isMutant);

    }
}