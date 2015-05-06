package fr.epita.sigl.mepa.core.service.impl;


import fr.epita.sigl.mepa.core.dao.ModelDao;
import fr.epita.sigl.mepa.core.domain.Model;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ModelServiceImplUTests {

    @Mock
    ModelDao mockedModelDao;

    @InjectMocks
    ModelServiceImpl modelService;

    @Test
    public void createModel_ShouldCreateANewModel_WithDateVeryCloseToNow() {
        // Given
        Model modelToCreate = new Model();
        Date now = new Date();
        long deltaInMilliseconds = 500;

        // When
        modelService.createModel(modelToCreate);

        // Then
        assertThat(modelToCreate.getCreated()).isCloseTo(now, deltaInMilliseconds);
    }

    @Test
    public void createModel_ShouldCreateANewModel_UsingModelDao() {
        // Given
        Model modelToCreate = new Model();

        // When
        modelService.createModel(modelToCreate);

        // Then
        verify(mockedModelDao).create(modelToCreate);
    }


}

