/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import static entity.Posting_.startup;
import entity.Project;
import entity.StartUp;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CreateNewPostingException;

/**
 *
 * @author Antho
 */
@Stateless
public class ProjectSessionBean implements ProjectSessionBeanLocal {

    @PersistenceContext(unitName = "PerfectMatch-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Project> retrieveAllProjects() {
        Query query = em.createQuery("SELECT p FROM Project p");
        return query.getResultList();
    }

    @Override
    public long createNewProject(Project project, Long startupId) throws CreateNewPostingException {
        try {
            Query query = em.createQuery("SELECT s FROM StartUp s WHERE s.startupId = :startupId");
            query.setParameter("startupId", startupId);
            StartUp startup = (StartUp)query.getSingleResult();
            
            project.setStartup(startup);
            startup.getProjects().add(project);
            
            em.persist(project);
            em.flush();
        } catch (Exception ex) {
            throw new CreateNewPostingException("Could not create new posting!");
        }
        return project.getPostingId();
    }

}
