package ejercicio2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOEmpleados {

	/*
	 * PROPIEDADES Y M�TODOS SINGLETON
	 */

	private Connection con = null;

	private static DAOEmpleados instance = null;

	private DAOEmpleados() throws SQLException {
		con = DBConnectionEmpleados.getConnection();
	}

	public static DAOEmpleados getInstance() throws SQLException {
		if (instance == null)
			instance = new DAOEmpleados();

		return instance;
	}

	/*
	 * M�TODOS PROPIOS DE LA CLASE DAO
	 */

	public void insert(Empleado e) throws SQLException {

		try (PreparedStatement ps = con.prepareStatement(
				"INSERT INTO empleados (nombre, apellidos, fecha_nacimiento, sueldo) VALUES (?, ?, ?, ?)");) {
			ps.setString(1, e.getNombre());
			ps.setString(2, e.getApellido());
			ps.setDate(3, Date.valueOf(e.getFechaNacimiento()));
			ps.setFloat(4, e.getSueldo());

			ps.executeUpdate();
		}

	}

	public List<Empleado> findAll() throws SQLException {

		List<Empleado> result = null;

		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados");) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (result == null)
					result = new ArrayList<>();

				result.add(new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getDate("fecha_nacimiento").toLocalDate(), rs.getFloat("sueldo")));
			}
			rs.close();
		}

		return result;
	}

	public Empleado findByPk(int id) throws SQLException {

		Empleado result = null;
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados WHERE id = ?");) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
						rs.getDate("fecha_nacimiento").toLocalDate(), rs.getFloat("sueldo"));
			}

			rs.close();
		}

		return result;

	}

	public void delete(int id) throws SQLException, EmpleadoException {
		Empleado empleado=findByPk(id);
		if (empleado==null) {
			throw new EmpleadoException("No existe el empleado con id " + id);
		}

		try ( PreparedStatement ps = con.prepareStatement("DELETE FROM empleados WHERE id = ?");) {
			
			ps.setInt(1, id);

			ps.executeUpdate();

		}
	}

	public void update(Empleado e) throws SQLException {

		if (e.getId() == 0)
			return;

		try( PreparedStatement ps = con.prepareStatement(
				"UPDATE empleados SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, sueldo = ? WHERE id = ?");){

		ps.setString(1, e.getNombre());
		ps.setString(2, e.getApellido());
		ps.setDate(3, Date.valueOf(e.getFechaNacimiento()));
		ps.setFloat(4, e.getSueldo());
		ps.setInt(5, e.getId());

		ps.executeUpdate();

	
		}

	}

	public void cerrarSesion() throws SQLException {
		con.close();

	}

}
