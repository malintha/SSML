package org.macaws.ontology;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/*
 * Created by Himan Gamage
 * 
 */
public class ExpandingController extends Thread {

	@Override
	public void run() {
		crossJoin();
	}

	private void crossJoin() {
		JenaHandler handler = JenaHandler.getInstance();
		ArrayList<OntClass> ontClassList = new ArrayList<OntClass>();
		Set<String> coupledIndividuals = new HashSet<String>();

		//adding the categories to a list
		for (Iterator<OntClass> i = handler.getOntModel().listClasses(); i
				.hasNext();) {
			ontClassList.add(i.next());
		}

		//coupling them
		for (int i = 0; i < ontClassList.size(); i++) {
			for (int j = i; j < ontClassList.size(); j++) {

				OntClass ontClass1 = ontClassList.get(i);
				OntClass ontClass2 = ontClassList.get(j);
				
				System.out.println("ontclass 1 :" + ontClass1.getURI()
						+ "\t ontclass 2 :" + ontClass2.getURI() + "\t");

				//looping the instances of ontclass 1
				for (Iterator<Individual> k = handler.getOntModel()
						.listIndividuals(ontClass1); k.hasNext();) {
					Individual individual1 = k.next();

					//looping the individuals of ontclass2
					for (Iterator<Individual> l = handler.getOntModel()
							.listIndividuals(ontClass2); l.hasNext();) {
						Individual individual2 = l.next();

						//check both catogories instances are equal
						if (!individual1.getURI().equals(individual2.getURI())) {
							String checkingValue = "[" + individual1.getURI()
									+ "*****" + individual2.getURI() + "]";
							
							//removing the duplicate checking couples
							if (!coupledIndividuals.contains(checkingValue)) {
								coupledIndividuals.add(checkingValue);
								System.out.println("\t\tChecking value :"
										+ checkingValue);
								
								System.out.println("\tRelationships for "+individual1.getLocalName()+" array size is "+getExistingRelationship(individual1).size());
								/*
								 * add the code here to check the internet for data
								 * 
								 * 
								 * 
								 */

							}
						}

					}
				}
				System.out.println();
			}

		}

	}

	private boolean hasRelationship(Individual individual1,
			Individual individual2) {
		boolean flag = false;
		for (StmtIterator m = individual1.listProperties(); m.hasNext();) {
			Statement s = m.next();
			if (!s.getPredicate().getLocalName().equals("type")) {
				if (s.getObject().toString().equals(individual2.getURI())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	private List<String> getExistingRelationship(Individual individual1) {
		List<String> relationships = new ArrayList<String>();
		for (StmtIterator m = individual1.listProperties(); m.hasNext();) {
			Statement s = m.next();
			if (!s.getPredicate().getLocalName().equals("type")) {
				relationships.add(s.getPredicate().getLocalName());

			}
		}
		return relationships;
	}

	public static void main(String args[]) {
		new ExpandingController().start();
	}

}
