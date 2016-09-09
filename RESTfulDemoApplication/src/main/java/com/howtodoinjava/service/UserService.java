package com.howtodoinjava.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user-management")
@Path("/user-management")
public class UserService {
	
	@GET
	@Produces("application/json")
	@Path("listalldish")
	public Response getAllMeals(){
		System.err.println("getAllMeals started");
		List<String> dishes = this.getDishes("dishes");
		System.err.println("getAllMeals end");
		return Response.ok(dishes).build();
	}
	
	@GET
	@Produces("application/json")
	@Path("listallcategories")
	public Response getAllCategories(){
		List<String> dishes = this.getDishes("categories");
		return Response.ok(dishes).build();
	}
	
	@GET
	@Path("/getDishByCategory/{param}")
	public Response getMealByCat(@PathParam("id") int id){
		List<String> dishes = this.getDishes("dishes where categories_id="+id);
		return Response.ok(dishes).build();
	}

	public List<String> getDishes(String what) {
		List<String> lista = new ArrayList<String>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/CrudDB", "postgres", "admin");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+what+";");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tmp = "id = " + id + "; " + "name = " + name;
				lista.add(tmp);
				/*
				 * int age = rs.getInt("age"); String address =
				 * rs.getString("address"); float salary =
				 * rs.getFloat("salary");
				 */
			//	System.out.print("ID = " + id + " | ");
			//	System.out.print("NAME = " + name);
				/*
				 * System.out.println( "AGE = " + age ); System.out.println(
				 * "ADDRESS = " + address ); System.out.println( "SALARY = " +
				 * salary );
				 */
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		
		return lista;
	}
}