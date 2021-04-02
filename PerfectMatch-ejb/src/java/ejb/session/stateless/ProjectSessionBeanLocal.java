/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Project;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreateNewPostingException;

/**
 *
 * @author Antho
 */
@Local
public interface ProjectSessionBeanLocal {

    List<Project> retrieveAllProjects();

    long createNewProject(Project project, Long startupId) throws CreateNewPostingException;
    
}
