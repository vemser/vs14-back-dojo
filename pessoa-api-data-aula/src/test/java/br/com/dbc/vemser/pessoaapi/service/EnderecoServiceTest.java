package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.mocks.EnderecoMock;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private ObjectMapper objectMapper;

    private EnderecoMock enderecoMock;

//    private EnderecoMock enderecoMock;

    @BeforeEach
    public void setUp(){
        enderecoMock = new EnderecoMock();
        ReflectionTestUtils.setField(enderecoService, "objectMapper", getObjectMapperInstance());
    }

    @Test
    void deveListarTodosEnderecosPaginadoComSucesso () {

        Integer pagina = 0;
        Integer tamanho = 10;
        String filter = "idEndereco";

        List<Endereco> enderecosMocks = List.of(
                enderecoMock.retornarEnderecoEntity(1),
                enderecoMock.retornarEnderecoEntity(2),
                enderecoMock.retornarEnderecoEntity(3)
        );

        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("tipo"));
        Page<Endereco> pageEndereco = new 







    }



    public static ObjectMapper getObjectMapperInstance() {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;
    }
}