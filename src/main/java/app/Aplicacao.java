package app;

import static spark.Spark.*;

import service.*;

public class Aplicacao {

    public static PacienteService PacienteService = new PacienteService();
    public static PsicologoService PsicologoService = new PsicologoService();

    public static void main(String[] args) {
        port(6789);

        /* posts e gets de paciente */
        post("/Paciente", (request, response) -> PacienteService.addPaciente(request, response));

        get("/Paciente/:id", (request, response) -> PacienteService.getPaciente(request, response));

        get("/Paciente/update/:id", (request, response) -> PacienteService.updatePaciente(request, response));

        get("/Paciente/delete/:id", (request, response) -> PacienteService.removePaciente(request, response));

        get("/Paciente", (request, response) -> PacienteService.getAllPaciente(request, response));
        

        /* posts e gets de Psicologo */
        post("/Psicologo", (request, response) -> PsicologoService.addPsicologo(request, response));

        get("/Psicologo/:id", (request, response) -> PsicologoService.getPsicologo(request, response));

        get("/Psicologo/update/:id", (request, response) -> PsicologoService.updatePsicologo(request, response));

        get("/Psicologo/delete/:id", (request, response) -> PsicologoService.removePsicologo(request, response));

        get("/Psicologo", (request, response) -> PsicologoService.getAllPsicologo(request, response));


        /* posts e gets de anotacao */
        /* post("/Psicologo", (request, response) -> PsicologoService.addPsicologo(request, response));

        get("/Psicologo/:id", (request, response) -> PsicologoService.getPsicologo(request, response));

        get("/Psicologo/update/:id", (request, response) -> PsicologoService.updatePsicologo(request, response));

        get("/Psicologo/delete/:id", (request, response) -> PsicologoService.removePsicologo(request, response));

        get("/Psicologo", (request, response) -> PsicologoService.getAllPsicologo(request, response)); */


        /* posts e gets de consulta */
        /* post("/Psicologo", (request, response) -> PsicologoService.addPsicologo(request, response));

        get("/Psicologo/:id", (request, response) -> PsicologoService.getPsicologo(request, response));

        get("/Psicologo/update/:id", (request, response) -> PsicologoService.updatePsicologo(request, response));

        get("/Psicologo/delete/:id", (request, response) -> PsicologoService.removePsicologo(request, response));

        get("/Psicologo", (request, response) -> PsicologoService.getAllPsicologo(request, response)); */
    }
}
