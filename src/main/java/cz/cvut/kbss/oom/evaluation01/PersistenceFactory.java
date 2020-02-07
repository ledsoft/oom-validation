package cz.cvut.kbss.oom.evaluation01;

import cz.cvut.kbss.jopa.Persistence;
import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProperties;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProvider;
import cz.cvut.kbss.ontodriver.config.OntoDriverProperties;
import cz.cvut.kbss.ontodriver.owlapi.OwlapiDataSource;
import cz.cvut.kbss.ontodriver.owlapi.config.OwlapiOntoDriverProperties;
import openllet.owlapi.OpenlletReasonerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PersistenceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(PersistenceFactory.class);

    private EntityManagerFactory emf;

    PersistenceFactory(String ontologyFile, String ontologyIri) {
        init(ontologyFile, ontologyIri);
    }

    public void init(String ontologyFile, String ontologyIri) {
        LOG.info("Initializing connection to ontology <{}> in file {}.", ontologyIri, ontologyFile);
        final Map<String, String> props = new HashMap<>();
        // Here we set up basic storage access properties - driver class, physical location of the storage
        final File ontoFile = new File(ontologyFile);
        assert ontoFile.exists();
        props.put(JOPAPersistenceProperties.ONTOLOGY_PHYSICAL_URI_KEY, "file://" + ontoFile.getAbsolutePath());
        props.put(JOPAPersistenceProperties.ONTOLOGY_URI_KEY, ontologyIri);
        props.put(JOPAPersistenceProperties.DATA_SOURCE_CLASS, OwlapiDataSource.class.getName());
        final File mappingFile = new File("dl-ontology" + File.separator + "mapping");
        assert mappingFile.exists();
        props.put(OwlapiOntoDriverProperties.MAPPING_FILE_LOCATION, "file://" + mappingFile.getAbsolutePath());
        // View transactional changes during transaction
        props.put(OntoDriverProperties.USE_TRANSACTIONAL_ONTOLOGY, Boolean.TRUE.toString());
        // Ontology language
        props.put(JOPAPersistenceProperties.LANG, "en");
        // Where to look for entity classes
        props.put(JOPAPersistenceProperties.SCAN_PACKAGE, "cz.cvut.kbss.oom.evaluation01");
        // Use Pellet for reasoning
        props.put(OntoDriverProperties.REASONER_FACTORY_CLASS, OpenlletReasonerFactory.class.getName());
        // Persistence provider name
        props.put(JOPAPersistenceProperties.JPA_PERSISTENCE_PROVIDER, JOPAPersistenceProvider.class.getName());

        this.emf = Persistence.createEntityManagerFactory("Evaluation01", props);
    }

    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        emf.close();
    }
}
