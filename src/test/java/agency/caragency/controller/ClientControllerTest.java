// // package agency.caragency.controller;

// // import agency.caragency.model.Client;
// // import agency.caragency.service.ClientService;
// // import com.fasterxml.jackson.databind.ObjectMapper;
// // import jakarta.persistence.EntityNotFoundException;
// // import org.junit.jupiter.api.BeforeEach;
// // import org.junit.jupiter.api.DisplayName;
// // import org.junit.jupiter.api.Test;
// // import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// // import org.springframework.boot.test.mock.mockito.MockBean;
// // import org.springframework.http.MediaType;
// // import org.springframework.test.web.servlet.MockMvc;
// // import org.springframework.beans.factory.annotation.Autowired;

// // import java.util.List;

// // import static org.mockito.Mockito.*;
// // import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// // import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// // @WebMvcTest(ClientController.class)
// // class ClientControllerTest {

// //     @Autowired
// //     MockMvc mvc;

// //     @Autowired
// //     ObjectMapper mapper;

// //     @MockBean
// //     ClientService service;

// //     private static final String BASE = "/api/clients";

// //     @BeforeEach
// //     void setup() {
// //         reset(service);
// //     }

// //     private Client client(Integer id, String name, String email, String phone, String taxId, String address) {
// //         Client c = new Client();
// //         c.setId(id);
// //         c.setFullName(name);
// //         c.setEmail(email);
// //         c.setPhone(phone);
// //         c.setTaxId(taxId);
// //         c.setAddress(address);
// //         return c;
// //     }

// //     /* ============================
// //      * GET /api/clients
// //      * ============================ */

// //     @Test
// //     @DisplayName("GET all → 200 con lista")
// //     void getAll_ok() throws Exception {
// //         List<Client> clients = List.of(
// //                 client(1, "Zury Sarahi", "zury@example.com", "2223334444", "ABC123456", "Teziutlán"),
// //                 client(2, "Ana López", "ana@example.com", "5556667777", "XYZ987654", "Puebla")
// //         );

// //         when(service.getAllClients()).thenReturn(clients);

// //         mvc.perform(get(BASE))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.length()").value(2))
// //                 .andExpect(jsonPath("$[0].full_name").value("Zury Sarahi"))
// //                 .andExpect(jsonPath("$[1].tax_id").value("XYZ987654"));
// //     }

// //     /* ============================
// //      * GET /search?name=Zury
// //      * ============================ */

// //     @Test
// //     @DisplayName("GET /search → 200 si hay coincidencias")
// //     void searchClients_ok() throws Exception {
// //         List<Client> results = List.of(
// //                 client(1, "Zury Sarahi", "zury@example.com", "2223334444", "ABC123456", "Teziutlán")
// //         );

// //         when(service.searchClientsByName("Zury")).thenReturn(results);

// //         mvc.perform(get(BASE + "/search").param("name", "Zury"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.length()").value(1))
// //                 .andExpect(jsonPath("$[0].full_name").value("Zury Sarahi"));
// //     }

// //     @Test
// //     @DisplayName("GET /search → 200 sin resultados")
// //     void searchClients_empty() throws Exception {
// //         when(service.searchClientsByName("Nope")).thenReturn(List.of());

// //         mvc.perform(get(BASE + "/search").param("name", "Nope"))
// //                 .andExpect(status().isOk())
// //                 .andExpect(jsonPath("$.length()").value(0));
// //     }

// //     /* ============================
// //      * POST /api/clients
// //      * ============================ */

// //     @Test
// //     @DisplayName("POST válido → 201 con body")
// //     void create_ok() throws Exception {
// //         Client input = client(null, "Zury Sarahi", "zury@example.com", "2223334444", "ABC123456", "Teziutlán");
// //         Client saved = client(10, "Zury Sarahi", "zury@example.com", "2223334444", "ABC123456", "Teziutlán");

// //         when(service.saveClient(any(Client.class))).thenReturn(saved);

// //         mvc.perform(post(BASE)
// //                 .contentType(MediaType.APPLICATION_JSON)
// //                 .content(mapper.writeValueAsString(input)))
// //                 .andExpect(status().isCreated())
// //                 .andExpect(jsonPath("$.client_id").value(10))
// //                 .andExpect(jsonPath("$.full_name").value("Zury Sarahi"));
// //     }

// //     @Test
// //     @DisplayName("POST inválido → 400 por @Valid")
// //     void create_invalid() throws Exception {
// //         mvc.perform(post(BASE)
// //                 .contentType(MediaType.APPLICATION_JSON)
// //                 .content("{}"))
// //                 .andExpect(status().isBadRequest());
// //     }

// //     /* ============================
// //      * DELETE /{id}
// //      * ============================ */

// //     @Test
// //     @DisplayName("DELETE existente → 204 sin body")
// //     void delete_ok() throws Exception {
// //         doNothing().when(service).deleteClient(5);

// //         mvc.perform(delete(BASE + "/{id}", 5))
// //                 .andExpect(status().isNoContent())
// //                 .andExpect(content().string(""));
// //     }

// //     @Test
// //     @DisplayName("DELETE no existente → 404")
// //     void delete_notFound() throws Exception {
// //         doThrow(new EntityNotFoundException("Client not found")).when(service).deleteClient(404);

// //         mvc.perform(delete(BASE + "/{id}", 404))
// //                 .andExpect(status().isNotFound());
// //     }
// // }