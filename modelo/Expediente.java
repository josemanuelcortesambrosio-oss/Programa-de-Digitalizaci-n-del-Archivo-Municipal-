package archivo.municipal.modelo;

import java.time.LocalDate;

public class Expediente {
    private int id;
    private String numeroExpediente;
    private int idArea;
    private String area;
    private String asunto;
    private String responsable;
    private LocalDate fechaApertura;
    private String estado;
    private String ubicacion;

    public Expediente() {}

    public Expediente(String numeroExpediente, int idArea, String asunto, String responsable, LocalDate fechaApertura, String estado, String ubicacion) {
        this.numeroExpediente = numeroExpediente;
        this.idArea = idArea;
        this.asunto = asunto;
        this.responsable = responsable;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumeroExpediente() { return numeroExpediente; }
    public void setNumeroExpediente(String numeroExpediente) { this.numeroExpediente = numeroExpediente; }

    public int getIdArea() { return idArea; }
    public void setIdArea(int idArea) { this.idArea = idArea; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }

    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate fechaApertura) { this.fechaApertura = fechaApertura; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
}
