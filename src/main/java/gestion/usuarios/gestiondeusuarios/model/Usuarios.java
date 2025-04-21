//Para el uso de algunas anotaciones se utilizó como fuente https://projectlombok.org/
//para hacer uso eficiente de lombok y aprovechar sus cualidades.

package gestion.usuarios.gestiondeusuarios.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Setter @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Setter @Getter
    @Pattern(regexp = "^[A-Za-z ]+$", message = "El nombre solo debe tener letras")
    @Column(name = "nombre")
    private String nombre;

    @Setter @Getter
    @Pattern(regexp = "^[A-Za-z ]+$", message = "El apellido solo debe tener letras")
    @Column(name = "apellido")
    private String apellido;

    @Setter @Getter
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "El formato de correo es invalido")
    @Column(name = "email")
    private String email;

    @Setter @Getter
    @NotNull
    @Size(min = 4, max = 8, message = "la contraseña debe tener entre 4 y 8 caracteres")
    @Column(name = "password")
    private String password;

    @Setter @Getter
    @Column(name = "direccion")
    private String direccion;

    @Setter @Getter
    @NotNull
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "el rol solo permite letras")
    @Column(name = "rol")
    private String rol;
}
