// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }// package agency.caragency.controller;

// import agency.caragency.model.Branch;
// import agency.caragency.service.BranchService;
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

// import java.util.List;

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(BranchController.class)
// class BranchControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     BranchService service;

//     private static final String BASE = "/api/branches";

//     public BranchControllerTest() {
//     }

//     public BranchControllerTest(ObjectMapper mapper, MockMvc mvc) {
//         this.mapper = mapper;
//         this.mvc = mvc;
//     }

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Branch branch(Integer id, String name, String address, String phone) {
//         Branch b = new Branch();
//         b.setId(id);
//         b.setName(name);
//         b.setAddress(address);
//         b.setPhone(phone);
//         return b;
//     }

//     /* ============================
//      * GET /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<Branch> branches = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444"),
//                 branch(2, "Sucursal Puebla", "Calle Reforma 456", "5556667777")
//         );

//         when(service.getAllBranches()).thenReturn(branches);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"))
//                 .andExpect(jsonPath("$[1].address").value("Calle Reforma 456"));
//     }

//     /* ============================
//      * GET /search?name=Teziutlán
//      * ============================ */

//     @Test
//     @DisplayName("GET /search → 200 si hay coincidencias")
//     void searchBranches_ok() throws Exception {
//         List<Branch> results = List.of(
//                 branch(1, "Sucursal Teziutlán", "Av. Principal 123", "2223334444")
//         );

//         when(service.searchBranchesByName("Teziutlán")).thenReturn(results);

//         mvc.perform(get(BASE + "/search").param("name", "Teziutlán"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("GET /search → 200 sin resultados")
//     void searchBranches_empty() throws Exception {
//         when(service.searchBranchesByName("Nope")).thenReturn(List.of());

//         mvc.perform(get(BASE + "/search").param("name", "Nope"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(0));
//     }

//     /* ============================
//      * POST /api/branches
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Branch input = branch(null, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");
//         Branch saved = branch(10, "Sucursal Teziutlán", "Av. Principal 123", "2223334444");

//         when(service.saveBranch(any(Branch.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.branch_id").value(10))
//                 .andExpect(jsonPath("$.name").value("Sucursal Teziutlán"));
//     }

//     @Test
//     @DisplayName("POST inválido → 400 por @Valid")
//     void create_invalid() throws Exception {
//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content("{}"))
//                 .andExpect(status().isBadRequest());
//     }

//     /* ============================
//      * DELETE /{id}
//      * ============================ */

//     @Test
//     @DisplayName("DELETE existente → 204 sin body")
//     void delete_ok() throws Exception {
//         doNothing().when(service).deleteBranch(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Branch not found")).when(service).deleteBranch(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }

//     public BranchService getService() {
//         return service;
//     }
// }