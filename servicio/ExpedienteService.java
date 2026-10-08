package archivo.municipal.servicio;

import archivo.municipal.dao.ExpedienteDAO;
import archivo.municipal.dao.ExpedienteDAOImpl;
import archivo.municipal.modelo.Expediente;
import java.util.List;

public class ExpedienteService {
    private final ExpedienteDAO expedienteDAO;

    public ExpedienteService() {
        expedienteDAO = new ExpedienteDAOImpl();
    }

    public boolean guardar(Expediente expediente) {
        if (expediente.getNumeroExpediente() == null || expediente.getNumeroExpediente().trim().isEmpty()) {
            return false;
        }
        if (expediente.getAsunto() == null || expediente.getAsunto().trim().isEmpty()) {
            return false;
        }
        return expedienteDAO.guardar(expediente);
    }

    public List<Expediente> listar() {
        return expedienteDAO.listar();
    }

    public List<Expediente> buscar(String texto) {
        return expedienteDAO.buscar(texto);
    }
}