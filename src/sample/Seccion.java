package sample;

public class Seccion {

    private int seccionId;
    private String descripcion;
    private int precio;

    public Seccion(int seccionId, String descripcion, int precio) {
        this.seccionId = seccionId;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Seccion{" +
                "seccionId=" + seccionId +
                ", descripcion='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +

                '}';
    }
}











