package JDBCProjet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import JDBCProjet.beans.Client;
import JDBCProjet.connection.Connexion;
import JDBCProjet.dao.IDao;

public class ClientService implements  IDao<Client>{

	@Override
	public boolean create(Client c) {
		String seq ="insert into client (nom,prenom) values ('"+ c.getNom()+"','" +c.getPrenom()+"')";
		try {
			Statement stat=Connexion.getConnection().createStatement();
			int nbl=stat.executeUpdate(seq);
			if(nbl >0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println("Error creating SQL statement: "+e.getMessage());
		    return false;
		    
		}
		
		
	}

	@Override
	public List<Client> findAll() {
		try {
			Statement stmt = Connexion.getConnection().createStatement();
		try {
			List<Client> l = new ArrayList<>();
			String query = "SELECT * from client";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				l.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3)));
				
			}
			return l;
			}
			catch (SQLException e) {
				System.err.println("Error executing query: " +e.getMessage());
				return null;
			}
		
		}
			catch (SQLException e) {
				System.err.println("Error creating SQL statement: "
				+e.getMessage());
				return null;
				}
	}

	@Override
	public Client findById(int id) {
		try {
			PreparedStatement pstmt = Connexion.getConnection().prepareStatement( "SELECT * from client where id=?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next(); 
			return new Client(rs.getInt(1),rs.getString(2),rs.getString(3));
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public boolean delete(Client c) {
		try {
			PreparedStatement pstmt = Connexion.getConnection().prepareStatement( "delete from client where id=?");
			pstmt.setInt(1,c.getId());
			pstmt.executeUpdate();
			return true;
		}
		catch(SQLException e){
			System.err.println("Error browsing query results: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Client c) {
		try {
			PreparedStatement pstmt = Connexion.getConnection().prepareStatement( "UPDATE client SET nom = ? WHERE id = ?");
			pstmt.setInt(2,c.getId());
			pstmt.setString(1, c.getNom() );
			pstmt.executeUpdate();
			return true;
		}
		catch(SQLException e){
			System.err.println("Error browsing query results: " + e.getMessage());
			return false;
		}
	}

}