// package agency.caragency.test.java.agency.caragency.controller;

// import agency.caragency.model.User;
// import agency.caragency.service.UserService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import jakarta.persistence.EntityNotFoundException;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.beans.factory.annotation.Autowired;

// import java.util.Optional;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(UserController.class)
// class UserControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     UserService service;

//     private static final String BASE = "/api/users";

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private User user(Integer id, String username, String email, String password, Boolean enabled) {
//         User u = new User();
//         u.setId(id);
//         u.setUsername(username);
//         u.setEmail(email);
//         u.setPassword(password);
//         u.setEnabled(enabled);
//         return u;
//     }

//     /* ============================
//      * GET /username/{username}
//      * ============================ */

//     @Test
//     @DisplayName("GET /username/{username} → 200 si existe")
//     void getByUsername_ok() throws Exception {
//         when(service.getByUsername("zury"))
//                 .thenReturn(Optional.of(user(1, "zury", "zury@example.com", "1234", true)));

//         mvc.perform(get(BASE + "/username/{username}", "zury"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.username").value("zury"))
//                 .andExpect(jsonPath("$.email").value("zury@example.com"))
//                 .andExpect(jsonPath("$.enabled").value(true));
//     }

//     @Test
//     @DisplayName("GET /username/{username} → 200 con Optional.empty")
//     void getByUsername_notFound() throws Exception {
//         when(service.getByUsername("nope")).thenReturn(Optional.empty());

//         mvc.perform(get(BASE + "/username/{username}", "nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string(""));
//     }

//     /* ============================
//      * GET /email/{email}
//      * ============================ */

//     @Test
//     @DisplayName("GET /email/{email} → 200 si existe")
//     void getByEmail_ok() throws Exception {
//         when(service.getByEmail("zury@example.com"))
//                 .thenReturn(Optional.of(user(1, "zury", "zury@example.com", "1234", true)));

//         mvc.perform(get(BASE + "/email/{email}", "zury@example.com"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.username").value("zury"))
//                 .andExpect(jsonPath("$.email").value("zury@example.com"))
//                 .andExpect(jsonPath("$.enabled").value(true));
//     }

//     @Test
//     @DisplayName("GET /email/{email} → 200 con Optional.empty")
//     void getByEmail_notFound() throws Exception {
//         when(service.getByEmail("nope@example.com")).thenReturn(Optional.empty());

//         mvc.perform(get(BASE + "/email/{email}", "nope@example.com"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string(""));
//     }

//     /* ============================
//      * POST /api/users
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void createUser_ok() throws Exception {
//         User input = user(null, "zury", "zury@example.com", "1234", true);
//         User saved = user(10, "zury", "zury@example.com", "1234", true);

//         when(service.saveUser(any(User.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.id").value(10))
//                 .andExpect(jsonPath("$.username").value("zury"))
//                 .andExpect(jsonPath("$.enabled").value(true));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void createUser_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /api/users/{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void deleteUser_ok() throws Exception {
//         doNothing().when(service).deleteUser(33);

//         mvc.perform(delete(BASE + "/{id}", 33))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void deleteUser_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("User not found")).when(service).deleteUser(4040);

//         mvc.perform(delete(BASE + "/{id}", 4040))
//                 .andExpect(status().isNotFound());
//     }
// }
