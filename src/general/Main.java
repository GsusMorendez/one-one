package general;

import vistas.*;

public class Main {

	public static void main(String[] args) {
		
		//Instanciando los objetos
		Modelo miModelo = new Modelo();
		Controlador miControlador = new Controlador();
		
		Inicial miInicial = new Inicial();
		Login miLogin = new Login();
		Registro miRegistro = new Registro();
		PrincipalAlumno miPrincipalAlumno = new PrincipalAlumno();
		PrincipalProfesor miPrincipalProfesor = new PrincipalProfesor();
		ProponerEmpresaAlumno miProponerEmpresaAlumno = new ProponerEmpresaAlumno();
		ElegirEmpresas miElegirEmpresas = new ElegirEmpresas();
		VerElegidas miVerElegidas = new VerElegidas();
		VerAlumnos miVerAlumnos = new VerAlumnos();
		VerEleccionesDeAlumnos miVerEleccionesDeAlumnos = new VerEleccionesDeAlumnos();
		VerEmpresas miVerEmpresas = new VerEmpresas();
		VerMatchesAlumno miVerMatchesAlumno = new VerMatchesAlumno();
		VerMatchesProfesor miVerMatchesProfesor = new VerMatchesProfesor();
		
		//estableciendo atributos del Controlador
		miControlador.setMiModelo(miModelo);
		miControlador.setMisPantallas(miInicial);
		miControlador.setMisPantallas(miLogin);
		miControlador.setMisPantallas(miRegistro);
		miControlador.setMisPantallas(miPrincipalAlumno);
		miControlador.setMisPantallas(miPrincipalProfesor);
		miControlador.setMisPantallas(miProponerEmpresaAlumno);
		miControlador.setMisPantallas(miElegirEmpresas);
		miControlador.setMisPantallas(miVerElegidas);
		miControlador.setMisPantallas(miVerAlumnos);
		miControlador.setMisPantallas(miVerEleccionesDeAlumnos);
		miControlador.setMisPantallas(miVerEmpresas);
		miControlador.setMisPantallas(miVerMatchesAlumno);
		miControlador.setMisPantallas(miVerMatchesProfesor);
		
		//estableciendo atributos del Modelo
		miModelo.setMisPantallas(miInicial);
		miModelo.setMisPantallas(miLogin);
		miModelo.setMisPantallas(miRegistro);
		miModelo.setMisPantallas(miPrincipalAlumno);
		miModelo.setMisPantallas(miPrincipalProfesor);
		miModelo.setMisPantallas(miProponerEmpresaAlumno);
		miModelo.setMisPantallas(miElegirEmpresas);
		miModelo.setMisPantallas(miVerElegidas);
		miModelo.setMisPantallas(miVerAlumnos);
		miModelo.setMisPantallas(miVerEleccionesDeAlumnos);
		miModelo.setMisPantallas(miVerEmpresas);
		miModelo.setMisPantallas(miVerMatchesAlumno);
		miModelo.setMisPantallas(miVerMatchesProfesor);
		
		//estableciendo el controlador y el modelo en cada pantalla
		miInicial.setMiControlador(miControlador);
		miInicial.setMiModelo(miModelo);
		
		miLogin.setMiControlador(miControlador);
		miLogin.setMiModelo(miModelo);
		
		miRegistro.setMiControlador(miControlador);
		miRegistro.setMiModelo(miModelo);
		
		miPrincipalAlumno.setMiControlador(miControlador);
		miPrincipalAlumno.setMiModelo(miModelo);
		
		miPrincipalProfesor.setMiModelo(miModelo);
		miPrincipalProfesor.setMiControlador(miControlador);
		
		miProponerEmpresaAlumno.setMiControlador(miControlador);
		miProponerEmpresaAlumno.setMiModelo(miModelo);
		
		miElegirEmpresas.setMiControlador(miControlador);
		miElegirEmpresas.setMiModelo(miModelo);
		
		miVerElegidas.setMiModelo(miModelo);
		miVerElegidas.setMiControlador(miControlador);
		
		miVerAlumnos.setMiModelo(miModelo);
		miVerAlumnos.setMiControlador(miControlador);
		
		miVerEleccionesDeAlumnos.setMiModelo(miModelo);
		miVerEleccionesDeAlumnos.setMiControlador(miControlador);
		
		miVerEmpresas.setMiControlador(miControlador);
		miVerEmpresas.setMiModelo(miModelo);
		
		miVerMatchesAlumno.setMiControlador(miControlador);
		miVerMatchesAlumno.setMiModelo(miModelo);
		
		miVerMatchesProfesor.setMiModelo(miModelo);
		miVerMatchesProfesor.setMiControlador(miControlador);
		
		//relaciones necesarias entre algunas vistas
		miInicial.setMiLogin(miLogin);
		miLogin.setMiRegistro(miRegistro);
		miInicial.setMiRegistro(miRegistro);
		miProponerEmpresaAlumno.setMiLogin(miLogin);
		miPrincipalAlumno.setMiElegirEmpresas(miElegirEmpresas);
		miPrincipalAlumno.setMiLogin(miLogin);
		miPrincipalAlumno.setMiVerMatchesAlumno(miVerMatchesAlumno);
		miElegirEmpresas.setMiLogin(miLogin);
		miElegirEmpresas.setMiVerElergidas(miVerElegidas);
		miVerElegidas.setMiLogin(miLogin);
		miPrincipalProfesor.setMiVerAlumnos(miVerAlumnos);
		miPrincipalProfesor.setMiLogin(miLogin);
		miPrincipalProfesor.setMiVerEleccionesDeAlumnos(miVerEleccionesDeAlumnos);
		miPrincipalProfesor.setMiVerEmpresas(miVerEmpresas);
		miVerEleccionesDeAlumnos.setMiLogin(miLogin);
		miVerEmpresas.setMiLogin(miLogin);
		miVerMatchesAlumno.setMiLogin(miLogin);
		miVerMatchesProfesor.setMiLogin(miLogin);
		miVerEleccionesDeAlumnos.setMiVerMatchesProfesor(miVerMatchesProfesor);
		miVerMatchesProfesor.setMiVerEleccionesDeAlumnos(miVerEleccionesDeAlumnos);
		miVerElegidas.setMiElegirEmpresas(miElegirEmpresas);
		
		
		
		
		
		
		miInicial.setVisible(true);
	}

}
