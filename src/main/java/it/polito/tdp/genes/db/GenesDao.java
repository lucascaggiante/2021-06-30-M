package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<Integer> getChromosome(){
		String sql = "SELECT Distinct(Chromosome)\n"
				+ "FROM genes\n"
				+ "";
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(res.getInt("Chromosome"));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}

	public List<Adiacenza> getAdiacenze() {
		String sql = "SELECT distinct g1.Chromosome as c1,g2.Chromosome as c2,SUM(distinct i.Expression_Corr) as peso "
				+"FROM genes g1,genes g2, interactions i "
				+"WHERE g1.Chromosome!=g2.Chromosome AND g1.Chromosome!=0 AND g2.Chromosome!=0 AND g1.GeneID=i.GeneID1 AND g2.GeneID=i.GeneID2 "
				+"GROUP BY g1.Chromosome,g2.Chromosome "
				+"ORDER BY g1.Chromosome,g2.Chromosome";
		List<Adiacenza> result = new ArrayList<Adiacenza>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				if(res.getDouble("peso")!=0.0) 
				{
				Adiacenza a = new Adiacenza(res.getInt("c1"),res.getInt("c2"),res.getDouble("peso"));
				result.add(a);
				}
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
}
