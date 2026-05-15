package ar.edu.unlu.models;


import org.bson.types.ObjectId;

public class Cliente {
    private ObjectId id;
    private String dni;
    private String nombre;
    private String email;

    public Cliente() {
    }

    public Cliente(String nombre, String dni, String email) {
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
