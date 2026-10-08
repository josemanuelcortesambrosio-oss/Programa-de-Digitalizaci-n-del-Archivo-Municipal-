package archivo.municipal.dao;

import archivo.municipal.modelo.Expediente;
import java.util.List;

public interface ExpedienteDAO {
    boolean guardar(Expediente expediente);
    List<Expediente> listar();
    List<Expediente> buscar(String texto);
}