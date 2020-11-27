package com.benoni.spring.interview.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.util.Calendar;

import com.benoni.spring.interview.demo.api.model.Cliente;
import com.benoni.spring.interview.demo.mapper.ClienteMappers;
import com.benoni.spring.interview.demo.repositories.entities.ClienteEntity;
import com.benoni.spring.interview.demo.utils.DateUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClienteMapperTest {
    private final ClienteMappers target = new ClienteMappers();

    @Test
    public void can_convert_idade() {

        ClienteEntity c1 = new ClienteEntity();
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(2000, 0, 1);
        c1.setDataNascimento(dataNascimento);
        try (MockedStatic<DateUtils> mocked = mockStatic(DateUtils.class)) {
            mocked.when(() -> DateUtils.getAge(dataNascimento)).thenReturn(Integer.valueOf(20));
            Cliente expected = target.mapperCliente().map(c1, Cliente.class);
            assertEquals(20, (int) expected.getIdade());
        }
    }
}
