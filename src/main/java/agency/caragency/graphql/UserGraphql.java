package agency.caragency.graphql;

import agency.caragency.dto.UserDTO;
import agency.caragency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserGraphql {

    private final UserService userService;

    // obtener todos los usuarios
    @QueryMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    //  obtener usuario por ID
    @QueryMapping
    public UserDTO getUserById(@Argument Integer id) {
        return userService.getUserById(id);
    }

    // obtener usuario por email
    @QueryMapping
    public UserDTO getUserByEmail(@Argument String email) {
        return userService.getUserByEmail(email);
    }

    //  crear usuario
    @MutationMapping
    public UserDTO createUser(@Argument UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    //  actualizar usuario
    @MutationMapping
    public UserDTO updateUser(@Argument Integer id, @Argument UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    //  eliminar usuario
    @MutationMapping
    public Boolean deleteUser(@Argument Integer id) {
        userService.deleteUser(id);
        return true;
    }

    //  habilitar/deshabilitar usuario
    @MutationMapping
    public Boolean toggleUserStatus(@Argument Integer id) {
        userService.toggleUserStatus(id);
        return true;
    }
}



// query {
//   getAllUsers {
//     id
//     username
//     email
//     enabled
//     profile {
//       id
//       firstName
//       lastName
//       phone
//       address
//     }
//   }
// }


// query {
//   getUserById(id: "1") {
//     id
//     username
//     email
//     enabled
//     profile {
//       firstName
//       lastName
//       phone
//     }
//   }
// }



// query {
//   getUserByEmail(email: "juan@example.com") {
//     id
//     username
//     email
//     enabled
//   }
// }

// query {
//   getUserProfileById(id: "1") {
//     id
//     firstName
//     lastName
//     phone
//     address
//     userId
//   }
// }

// query {
//   getUserProfileByUserId(userId: "1") {
//     id
//     firstName
//     lastName
//     phone
//     address
//   }
// }


// mutation {
//   createUser(userDTO: {
//     username: "juanperez"
//     email: "juan@example.com"
//     password: "password123"
//     enabled: true
//   }) {
//     id
//     username
//     email
//     enabled
//   }
// }


// mutation {
//   updateUser(id: "1", userDTO: {
//     username: "juanperez_updated"
//     email: "juan.updated@example.com"
//     password: "newpassword456"
//     enabled: true
//   }) {
//     id
//     username
//     email
//     enabled
//   }
// }


// mutation {
//   deleteUser(id: "1")
// }

// mutation {
//   toggleUserStatus(id: "1")
// }

// mutation {
//   createUserProfile(profileDTO: {
//     firstName: "Juan"
//     lastName: "Pérez"
//     phone: "+52 228 123 4567"
//     address: "Calle Principal #123, Xalapa, Veracruz"
//     userId: "1"
//   }) {
//     id
//     firstName
//     lastName
//     phone
//     address
//     userId
//   }
// }

// mutation {
//   updateUserProfile(id: "1", profileDTO: {
//     firstName: "Juan Carlos"
//     lastName: "Pérez García"
//     phone: "+52 228 987 6543"
//     address: "Av. Revolución #456, Xalapa, Ver."
//     userId: "1"
//   }) {
//     id
//     firstName
//     lastName
//     phone
//     address
//   }
// }


// mutation {
//   deleteUserProfile(id: "1")
// }
