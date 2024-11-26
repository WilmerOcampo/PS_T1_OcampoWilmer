package pe.edu.cibertec.T1.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.edu.cibertec.T1.model.Cliente;
import pe.edu.cibertec.T1.model.TipoCliente;
import pe.edu.cibertec.T1.service.impl.ClienteServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@WebMvcTest(ClienteControllerTest.class)
class ClienteControllerTest {

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ClienteServiceImpl service;

    @BeforeEach
    void setUp() {
        controller = new ClienteController(service);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listarClientes() {
        List<Cliente> list = generateClients();

        when(service.listarClientes()).thenReturn(list);
        ResponseEntity<?> response = controller.listarClientes();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Cliente> body = (List<Cliente>) response.getBody();
        assertEquals(2, body.size());
    }

    @Test
    void obtenerCliente() {
        Cliente client = generateClient();

        when(service.obtenerCliente(anyInt())).thenReturn(client);
        ResponseEntity<?> response = controller.obtenerCliente(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Cliente body = (Cliente) response.getBody();
        assertEquals(1, body.getId());
    }

    @Test
    void registrarCliente() {
        Cliente client = generateClient();

        when(service.registrarCliente(any(Cliente.class))).thenReturn(client);
        ResponseEntity<?> response = controller.registrarCliente(client);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Cliente body = (Cliente) response.getBody();
        assertEquals(1, body.getId());
    }

    @Test
    void modificarCliente() {
        Cliente client = generateClient();

        when(service.registrarCliente(any(Cliente.class))).thenReturn(client);
        ResponseEntity<?> response = controller.registrarCliente(client);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Cliente body = (Cliente) response.getBody();
        assertEquals(1, body.getId());
    }

    @Test
    void eliminarCliente() {
        when(service.eliminarCliente(anyInt())).thenReturn("Cliente eliminado");
        controller.eliminarCliente(1);
        verify(service, times(1)).eliminarCliente(anyInt());
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