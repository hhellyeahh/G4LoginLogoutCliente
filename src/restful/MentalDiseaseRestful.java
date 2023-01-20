/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import interfaces.MentalDiseaseInterface;
import java.util.logging.Level;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:MentalDiseaseFacadeREST
 * [entities.mentaldisease]<br>
 * USAGE:
 * <pre>
 *        MentalDiseaseRestful client = new MentalDiseaseRestful();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Leire
 */
public class MentalDiseaseRestful implements MentalDiseaseInterface {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/G4Aether/webresources";

    public MentalDiseaseRestful() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.mentaldisease");
    }

    public void create_XML(Object requestEntity) throws ClientErrorException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "MentalDiseaseImplementation: Exception creating mental disease, {0}",
                    ex.getMessage());
        }

        /*
         try {
            //Send mental disease data to web client for creation. 
            webMentalDisease.create_XML(mentalDisease);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "MentalDiseaseImplementation: Exception creating mentak disease, {0}",
                    ex.getMessage());
            throw new CreateException("Error creating user:\n" + ex.getMessage());
        }
         */
    }

    public void edit_XML(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        /*
          try {
            webMentalDisease.edit_XML(mentalDisease);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "MentalDiseaseImplementation: Exception updating mental disease, {0}",
                    ex.getMessage());
            throw new UpdateException("Error updating mental disease:\n" + ex.getMessage());
        }
         */
    }

    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
        /*
         try {
            webMentalDisease.remove(idMentalDisease.toString()); //TODO toString?????????
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "MentalDiseaseImplementation: Exception deleting mental disease, {0}",
                    ex.getMessage());
            throw new DeleteException("Error deleting mental disease:\n" + ex.getMessage());
        }
         */
    }

    public <T> T getAllMentalDiseases_XML(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        /*
         List<MentalDisease> mentalDiseases = null;
        try {
            LOGGER.info("MentalDiseaseImplementation: Finding all mental diseases from REST service (XML).");
            //Ask webMentalDisease for all mentaldiseases' data.
            mentalDiseases = webMentalDisease.getAllMentalDiseases_XML(new GenericType<List<MentalDisease>>() {});
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,
                    "MentalDiseaseImplementation: Exception finding all mental diseases, {0}",
                    ex.getMessage());
            throw new MentalDiseaseException("Error finding all mental diseases:\n" + ex.getMessage());
        }
        return mentalDiseases;
         */
    }

    public <T> T getMentalDiseasesById_XML(GenericType<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getMentalDiseasesByName_XML(GenericType<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getByName/{0}", new Object[]{name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllMentalDiseasesOrderByName_XML(GenericType<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllByName");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }

}
