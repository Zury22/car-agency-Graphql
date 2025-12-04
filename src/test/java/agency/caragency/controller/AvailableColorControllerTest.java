// package agency.caragency.controller;

// import agency.caragency.model.AvailableColor;
// import agency.caragency.model.Car;
// import agency.caragency.service.AvailableColorService;
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

// @WebMvcTest(AvailableColorController.class)
// class AvailableColorControllerTest {

//     @Autowired
//     MockMvc mvc;

//     @Autowired
//     ObjectMapper mapper;

//     @MockBean
//     AvailableColorService service;

//     private static final String BASE = "/api/available_colors";

//     @BeforeEach
//     void setup() {
//         reset(service);
//     }

//     private Car car(Integer id, String brand, String model) {
//         Car c = new Car();
//         c.setId(id);
//         c.setBrand(brand);
//         c.setModel(model);
//         return c;
//     }

//     private AvailableColor color(Integer id, String name, String hex, Car car) {
//         AvailableColor c = new AvailableColor();
//         c.setId(id);
//         c.setColorName(name);
//         c.setHexCode(hex);
//         c.setCar(car);
//         return c;
//     }

//     /* ============================
//      * GET /api/available_colors
//      * ============================ */

//     @Test
//     @DisplayName("GET all → 200 con lista")
//     void getAll_ok() throws Exception {
//         List<AvailableColor> colors = List.of(
//                 color(1, "Rojo", "#FF0000", car(10, "Toyota", "Corolla")),
//                 color(2, "Azul", "#0000FF", car(11, "Honda", "Civic"))
//         );

//         when(service.getAllColors()).thenReturn(colors);

//         mvc.perform(get(BASE))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2))
//                 .andExpect(jsonPath("$[0].color_name").value("Rojo"))
//                 .andExpect(jsonPath("$[1].car.brand").value("Honda"));
//     }

//     /* ============================
//      * GET /car/{carId}
//      * ============================ */

//     @Test
//     @DisplayName("GET /car/{carId} → 200 con lista")
//     void getColorsByCar_ok() throws Exception {
//         List<AvailableColor> colors = List.of(
//                 color(1, "Rojo", "#FF0000", car(10, "Toyota", "Corolla"))
//         );

//         when(service.getColorsByCarId(10)).thenReturn(colors);

//         mvc.perform(get(BASE + "/car/{carId}", 10))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(1))
//                 .andExpect(jsonPath("$[0].color_name").value("Rojo"))
//                 .andExpect(jsonPath("$[0].car.model").value("Corolla"));
//     }

//     /* ============================
//      * POST /api/available_colors
//      * ============================ */

//     @Test
//     @DisplayName("POST válido → 201 con body")
//     void create_ok() throws Exception {
//         Car car = car(10, "Toyota", "Corolla");
//         AvailableColor input = color(null, "Verde", "#00FF00", car);
//         AvailableColor saved = color(20, "Verde", "#00FF00", car);

//         when(service.saveColor(any(AvailableColor.class))).thenReturn(saved);

//         mvc.perform(post(BASE)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(mapper.writeValueAsString(input)))
//                 .andExpect(status().isCreated())
//                 .andExpect(jsonPath("$.color_id").value(20))
//                 .andExpect(jsonPath("$.color_name").value("Verde"))
//                 .andExpect(jsonPath("$.car.brand").value("Toyota"));
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
//         doNothing().when(service).deleteColor(5);

//         mvc.perform(delete(BASE + "/{id}", 5))
//                 .andExpect(status().isNoContent())
//                 .andExpect(content().string(""));
//     }

//     @Test
//     @DisplayName("DELETE no existente → 404")
//     void delete_notFound() throws Exception {
//         doThrow(new EntityNotFoundException("Color not found")).when(service).deleteColor(404);

//         mvc.perform(delete(BASE + "/{id}", 404))
//                 .andExpect(status().isNotFound());
//     }
// }