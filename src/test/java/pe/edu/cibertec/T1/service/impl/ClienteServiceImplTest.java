package pe.edu.cibertec.T1.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import pe.edu.cibertec.T1.model.Cliente;
import pe.edu.cibertec.T1.model.TipoCliente;
import pe.edu.cibertec.T1.repo.IClienteRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(ClienteServiceImplTest.class)
class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl service;

    @Mock
    private IClienteRepo repository;

    @BeforeEach
    void setUp() {
        service = new ClienteServiceImpl(repository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listarClientes() {
        List<Cliente> list = generateClients();

        when(repository.findAll()).thenReturn(list);
        List<Cliente> response = service.listarClientes();
        assertEquals(2, response.size());
    }

    @Test
    void obtenerCliente() {
        Cliente client = generateClient();

        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(client));
        Cliente response = service.obtenerCliente(1);
        assertEquals(1, response.getId());
    }

    @Test
    void registrarCliente() {
        Cliente client = generateClient();

        when(repository.save(any(Cliente.class))).thenReturn(client);
        Cliente response = service.registrarCliente(client);
        assertEquals(1, response.getId());
    }

    @Test
    void modificarCliente() {
        Cliente client = generateClient();

        when(repository.save(any(Cliente.class))).thenReturn(client);
        Cliente response = service.modificarCliente(client);
        assertEquals(1, response.getId());
    }

    @Test
    void eliminarCliente() {
        doNothing().when(repository).deleteById(anyInt());
        String response = service.eliminarCliente(1);
        assertEquals("Cliente eliminado", response);
    }

    private List<Cliente> generateClients() {
        TipoCliente type = new TipoCliente();
        type.setId(1);
        type.setNombre("USER");

        Cliente client1 = new Cliente();
        client1.setId(1);
        client1.setNombre("Wilmer");
        client1.setApellidoPaterno("Ocampo");
        client1.setApellidoMaterno("Quispe");
        client1.setDireccion("Av. La Cultura 805");
        client1.setTelefono("966375724");
        client1.setTipoCliente(type);

        Cliente client2 = new Cliente();
        client2.setId(1);
        client2.setNombre("Wilmer");
        client2.setApellidoPaterno("Ocampo");
        client2.setApellidoMaterno("Quispe");
        client2.setDireccion("Av. La Cultura 805");
        client2.setTelefono("966375724");
        client2.setTipoCliente(type);

        return List.of(client1, client2);
    }

    private Cliente generateClient() {
        TipoCliente type = new TipoCliente();
        type.setId(1);
        type.setNombre("USER");

        Cliente client = new Cliente();
        client.setId(1);
        client.setNombre("Wilmer");
        client.setApellidoPaterno("Ocampo");
        client.setApellidoMaterno("Quispe");
        client.setDireccion("Av. La Cultura 805");
        client.setTelefono("966375724");
        client.setTipoCliente(type);

        return client;
    }
}