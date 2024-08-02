package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.PageDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.mocks.PessoaMock;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
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
import org.springframework.data.domain.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ObjectMapper objectMapper;

    private PessoaMock pessoaMock;

    @BeforeEach
    public void setUp() {
        pessoaMock = new PessoaMock();
        ReflectionTestUtils.setField(pessoaService, "objectMapper", getObjectMapperInstance());
    }

    @Test
    void deveListarTodasPessoasPaginadoComSucesso() throws RegraDeNegocioException {
        //Arrange
        Integer pagina = 0;
        Integer tamanho = 10;
        String filter = "idPessoa";

        List<Pessoa> pessoasMock = List.of(
                pessoaMock.retornarPessoaEntity(1),
                pessoaMock.retornarPessoaEntity(2),
                pessoaMock.retornarPessoaEntity(3)
        );

        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(filter).ascending().and(Sort.by("idPessoa")));
        Page<Pessoa> pagePessoa = new PageImpl<>(pessoasMock, pageable, pessoasMock.size());

        when(pessoaRepository.findAll(pageable)).thenReturn(pagePessoa);

        //Act
        PageDTO<PessoaDTO> resultadoPaginaDTO = pessoaService.list(pagina, tamanho, filter, "ASCENDING");

        //Assertions
        assertNotNull(resultadoPaginaDTO);
        assertEquals(pagina, resultadoPaginaDTO.getPage());
        assertEquals(tamanho, resultadoPaginaDTO.getSize());
        assertEquals(3, resultadoPaginaDTO.getContent().size());

    }

    @Test
    void deveBuscarUmaPessoaPorId() throws RegraDeNegocioException {
        //Arrange
        Integer idParaBuscar = 1;
        Pessoa pessoa = pessoaMock.retornarPessoaEntity(1);

        when(pessoaRepository.findById(idParaBuscar)).thenReturn(Optional.of(pessoa));

        //Act
        PessoaDTO resultado = pessoaService.findById(idParaBuscar);

        //Assert
        assertNotNull(resultado);
        assertEquals("Pedro1", resultado.getNome());
    }

    @Test
    void deveLancarUmaExcecaoAoBuscarUmaPessoaPorId() throws RegraDeNegocioException {
        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(RegraDeNegocioException.class, () -> pessoaService.findById(anyInt()));
    }

    @Test
    void deveCriarUmaPessoaComSucesso() {
        Pessoa pessoa = pessoaMock.retornarPessoaEntity(1);

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        PessoaCreateDTO pessoaCreateDTO = pessoaMock.retornarPessoaCreateDto(1);

        PessoaDTO resultado = pessoaService.create(pessoaCreateDTO);

        assertNotNull(resultado);
        assertEquals(resultado.getNome(), pessoaCreateDTO.getNome());
        assertEquals(resultado.getEmail(), pessoaCreateDTO.getEmail());
        assertEquals(resultado.getDataNascimento(), pessoaCreateDTO.getDataNascimento());
        assertEquals(resultado.getCpf(), pessoaCreateDTO.getCpf());
    }

    @Test
    void deveAtualizarOsDadosDeUmaPessoaExistenteComSucesso() throws RegraDeNegocioException {
        Pessoa pessoaParaAtualizar = pessoaMock.retornarPessoaEntity(1);

        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoaParaAtualizar));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaParaAtualizar);

        PessoaCreateDTO pessoaUpdateDTO = pessoaMock.retornarPessoaUpdateDto(1);
        PessoaDTO resultado = pessoaService.update(anyInt(), pessoaUpdateDTO);

        assertNotNull(resultado);
        assertEquals(resultado.getNome(), pessoaUpdateDTO.getNome());
        assertEquals(resultado.getEmail(), pessoaUpdateDTO.getEmail());
        assertEquals(resultado.getDataNascimento(), pessoaUpdateDTO.getDataNascimento());
        assertEquals(resultado.getCpf(), pessoaUpdateDTO.getCpf());
    }

    @Test
    void deveDeletarUmaPessoaComSucesso() throws RegraDeNegocioException {
        Integer idParaDeletar = 1;
        Pessoa pessoa = pessoaMock.retornarPessoaEntity(1);

        when(pessoaRepository.findById(idParaDeletar)).thenReturn(Optional.of(pessoa));

        pessoaService.delete(pessoa.getIdPessoa());

        verify(pessoaRepository, times(1)).delete(pessoa);
    }


    public static ObjectMapper getObjectMapperInstance() {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;
    }
}