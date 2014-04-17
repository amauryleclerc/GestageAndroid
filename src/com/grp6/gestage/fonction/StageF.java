package com.grp6.gestage.fonction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.grp6.gestage.library.DatabaseHandler;
import com.grp6.gestage.library.JSONParser;
import com.grp6.gestage.metier.AnneeScol;
import com.grp6.gestage.metier.Classe;
import com.grp6.gestage.metier.Etudiant;
import com.grp6.gestage.metier.Filiere;
import com.grp6.gestage.metier.MaitreStage;
import com.grp6.gestage.metier.Organisation;
import com.grp6.gestage.metier.Personne;
import com.grp6.gestage.metier.Stage;

/**
 * Class StageF
 * 
 * @author windows
 *
 */
public class StageF  extends Config {
	
	/**
	 * Variable
	 */
	private JSONParser jsonParser;

	/**
	 * Constructor
	 */
	public StageF( ){
		jsonParser = new JSONParser( );
	}
	
	/**
	 * Method getSelected
	 * 
	 * @param numClasse
	 * @return
	 * @throws JSONException
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public List<Stage> getSelected(int numClasse) throws JSONException, IllegalStateException, IOException{
		ArrayList<Stage> lesStages = new ArrayList<Stage>();
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "stage"));
		params.add(new BasicNameValuePair("fonc", "getSelected"));
		params.add(new BasicNameValuePair("classe",Integer.toString(numClasse)));
		
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		JSONArray json_Stages = json.getJSONArray("stages");
		for (int i = 0; i < json_Stages.length(); i++) {
			lesStages.add(chargerUnEnregistrement((JSONObject) json_Stages.get(i)));
			}
		
		return lesStages;
	}

	/**
	 * Method chargerUnEnregistrement
	 * 
	 * @param json
	 * @return
	 */
	private Stage chargerUnEnregistrement(JSONObject json){
		Stage unStage = new Stage(0,null,null,null,null,null,null, null, null, null, null, null, null, false);
		try {

			unStage.setNum_stage(json.getInt("numStage"));
			unStage.setDateFin((Date)json.get("dateFin"));
			unStage.setDateDebut((Date)json.get("dateDebut"));
			unStage.setDateVisiteStage((Date)json.get("dateVisiteStage"));
			unStage.setBilanTravaux(json.getString("bilanTravaux"));
			unStage.setCommentaires(json.getString("commentaires"));
			unStage.setDivers(json.getString("divers"));
			unStage.setVille(json.getString("ville"));
			unStage.setParticipationCcf(json.getBoolean("participationCCF"));
			unStage.setRessourcesOutils(json.getString("ressourcesOutils"));
			JSONArray json_Organisation = json.getJSONArray("organisation");
			JSONArray json_Etudiant = json.getJSONArray("etudiant");
			JSONArray json_MaitreStage = json.getJSONArray("maitreStage");
			JSONArray json_anneeScol = json.getJSONArray("anneeScol");
			
			AnneeScol uneAnneeScol = AnneeScolF.chargerUnEnregistrement(json_anneeScol.getJSONObject(0));
			unStage.setAnneescol(uneAnneeScol);
			Etudiant unEtudiant = EtudiantF.chargerUnEnregistrement(json_Etudiant.getJSONObject(0));
			unStage.setEtudiant(unEtudiant);
			MaitreStage unMaitreStage = MaitreStageF.chargerUnEnregistrement(json_MaitreStage.getJSONObject(0));
			unStage.setMaitreStage(unMaitreStage);
			Organisation uneOrganisation = OrganisationF.chargerUnEnregistrement(json_Organisation.getJSONObject(0));
			unStage.setOrganisation(uneOrganisation);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unStage;
	}
	
}
