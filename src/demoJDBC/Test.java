package demoJDBC;

import java.util.List;

import JDBCProjet.beans.Client;
import JDBCProjet.connection.Connexion;
import JDBCProjet.service.ClientService;

	public class Test {
		public static void main(String args[]) {
		Connexion.getConnection();

		ClientService cs=new ClientService();
		/*Client c=new Client ("firas","saadi");
		cs.create(c);*/
		
		List<Client> lc =cs.findAll();
		for (Client cl :lc) {
			System.out.println(cl.toString());
		}
		
		/*Client c=cs.findById(1);
		System.out.println(c.toString());*/
		
		//cs.delete(c);
	
		cs.update(new Client (2,"firas","saadi"));
		
	}
}
