package org.macaws.ontology;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;

//import org.apache.jena.riot.RDFDataMgr;
//import org.apache.jena.riot.RDFFormat;
//import org.w3c.dom.ranges.Range;
//
//import com.github.jsonldjava.core.RDFDataset.Literal;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.XSD;
/*
 * Created by Himan Gamage
 * 
 */


public class JenaHandler {
	private static PrintStream out=null;
	private static JenaHandler instance = null;
	private static OntModel ontModel = null;

	private final String NS = "http://myonth.com/cricket/ontology#";
	private static final String FILE_URL = "ontology/cricket.owl";
	private final String ABBREVIATED_SYNTAX = "RDF/XML-ABBREV";
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();


	protected JenaHandler() {
		if (ontModel == null) {
            InputStream in = classLoader.getResourceAsStream(FILE_URL);
//			InputStream in = FileManager.get().open(FILE_URL);
			ontModel = ModelFactory.createOntologyModel();
			ontModel.read(in, ABBREVIATED_SYNTAX);
		}
	}
	
	public static OntModel getOntModel(){
		return ontModel;
	}

	public static JenaHandler getInstance() {
		if (instance == null) {
			instance = new JenaHandler();
		}
		return instance;
	}

	public static PrintStream getPrintStream() throws FileNotFoundException{
		if(out==null){
			out = new PrintStream(FILE_URL);
		}
		return out;
		
	}
	public boolean isClassAvailable(String className) {
		OntClass artefact = ontModel.getOntClass(NS + className);

		if (artefact == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isObjectPropertyAvailable(String propertyName) {
		ObjectProperty artefact = ontModel.getObjectProperty(NS + propertyName);

		if (artefact == null) {
			return false;
		} else {
			return true;
		}
	}

	public Iterator<OntClass> listSubClasses(String className) {
		OntClass artefact = ontModel.getOntClass(NS + className);
		return artefact.listSubClasses();

	}

	public void addClass(String className, String label)
			throws FileNotFoundException {

		OntClass persistClass = ontModel.createClass(NS + className);
		persistClass.addLabel(label, "en");
		;
		PrintStream p = new PrintStream(FILE_URL);
		ontModel.write(p, ABBREVIATED_SYNTAX, null);
		p.close();
	}

	public void addClass(String className, String label, String subClass)
			throws FileNotFoundException {

		OntClass persistClass = ontModel.createClass(NS + className);
		persistClass.addLabel(label, "en");
		getOntClass(subClass).addSubClass(persistClass);
		PrintStream p = new PrintStream(FILE_URL);
		ontModel.write(p, ABBREVIATED_SYNTAX, null);
		p.close();
	}

	public OntClass getOntClass(String name) {
		return ontModel.getOntClass(NS + name);

	}

	public void createObjectTypeProperty(String propertyName, String domain,
			String range, String label) throws FileNotFoundException {

		ObjectProperty property = ontModel.createObjectProperty(NS
				+ propertyName);

		property.addDomain(getOntClass(domain));
		property.addRange(getOntClass(range));
		property.addLabel(label, "en");
		PrintStream p = new PrintStream(FILE_URL);
		ontModel.write(p, ABBREVIATED_SYNTAX, null);
		p.close();
	}

	public void createDataTypeProperty(String propertyName, String domain,
			String label,Resource range
			) throws FileNotFoundException {

		DatatypeProperty property = ontModel.createDatatypeProperty(NS
				+ propertyName);

		property.addDomain(getOntClass(domain));
		property.addRange(range);
		property.addLabel(label, "en");
		PrintStream p = new PrintStream(FILE_URL);
		ontModel.write(p, ABBREVIATED_SYNTAX, null);
		p.close();
	}

	public void addObjectTypeProperty(String subject, String predicate, String object) throws FileNotFoundException {

		Individual subj = ontModel.getIndividual(NS + subject);
		Individual obj = ontModel.getIndividual(NS + object);
		ObjectProperty pred = ontModel.getObjectProperty(NS + predicate);
		
		ontModel.add(subj, pred, obj);
		ontModel.write(getPrintStream(), ABBREVIATED_SYNTAX, null);

	}
	public void addDataTypeProperty(String subject, String predicate, String object) throws FileNotFoundException {

		Individual subj = ontModel.getIndividual(NS + subject);
		DatatypeProperty pred = ontModel.getDatatypeProperty(NS + predicate);
		
		ontModel.add(subj, pred, object);
		ontModel.write(getPrintStream(), ABBREVIATED_SYNTAX, null);

	}

	public void addIndividuals(String ontClass, String individual)
			throws FileNotFoundException {
		OntClass ontClassRef = ontModel.getOntClass(NS + ontClass);
		ontClassRef.createIndividual(NS + individual);

		PrintStream p = new PrintStream(FILE_URL);
		ontModel.write(p, ABBREVIATED_SYNTAX, null);
	}

}
