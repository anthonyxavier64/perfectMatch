/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.JobSessionBeanLocal;
import ejb.session.stateless.OfferSessionBeanLocal;
import ejb.session.stateless.PostingSessionBeanLocal;
import ejb.session.stateless.ProjectSessionBeanLocal;
import ejb.session.stateless.StartUpSessionBeanLocal;
import ejb.session.stateless.StudentSessionBeanLocal;
import entity.Job;
import entity.Offer;
import entity.Posting;
import entity.Project;
import entity.StartUp;
import enumeration.Industry;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import util.exception.CreateNewPostingException;
import util.exception.InputDataValidationException;
import util.exception.JobNotFoundException;
import util.exception.OfferNotFoundException;
import util.exception.PostingNotFoundException;
import util.exception.ProjectNotFoundException;
import util.exception.StartUpNotFoundException;

/**
 *
 * @author user
 */
@Named(value = "postingsManagedBean")
@ViewScoped
public class PostingsManagedBean implements Serializable {

    @EJB
    private StudentSessionBeanLocal studentSessionBean;

    @EJB
    private ProjectSessionBeanLocal projectSessionBean;

    @EJB
    private JobSessionBeanLocal jobSessionBean;

    @EJB
    private StartUpSessionBeanLocal startUpSessionBean;

    @EJB
    private PostingSessionBeanLocal postingSessionBean;
    
    @EJB
    private OfferSessionBeanLocal offerSessionBean;

    @Inject
    private viewPostingManagedBean viewPostingManagedBean;
    
    @Inject
    private StartupLoginManagedBean startUpLoginManagedBean;
    
    private List<Posting> listOfPostings;
    private List<Posting> filteredPostings;
    private List<Offer> offers;
    private List<Industry> industries;
    
    private Posting newPosting;
    private Project newProject;
    private long projectPostingId;
    private List<Project> listOfProjects;
    private Job newJob;
    private long jobPostingId;
    private List<Job> listOfJobs;
    private Long offerId;
    private Long startUpId;
    private Long studentId;
    
    private Posting selectedPostingToUpdate;
    private Project selectedProjectToUpdate;
    private Industry selectedProjectToUpdateIndustry;
    private List<String> selectedProjectToUpdateRequiredSkills;
    
    private Job selectedJobToUpdate;
    private Industry selectedJobToUpdateIndustry;
    private List<String> selectedJobToUpdateRequiredSkills;
    
    private Posting selectedPostingtoDelete;
    private List<Offer> updatedOffers;
    private List<List<String>> listOfSkillSets;
    
    /**
     * Creates a new instance of PostingsManagedBean
     */
    public PostingsManagedBean() {
        newProject = new Project();
        newJob = new Job();

    }
    
    @PostConstruct
    public void postConstruct()
    {
        setListOfPostings(postingSessionBean.retrieveAllPostings());
        setListOfProjects(postingSessionBean.retrieveAllProjects());
        setListOfJobs(postingSessionBean.retrieveAllJobs());
        List<Industry> listOfIndustries = new ArrayList<Industry>();
        for (Industry industry: Industry.values()) {
            listOfIndustries.add(industry);
        }
        setIndustries(listOfIndustries);
        System.out.println(industries);

        String[] requiredSkillsOneArray = new String[]{"Java", "Javascript", "SQL", "Web services"};
        List<String> requiredSkillsOne = Arrays.asList(requiredSkillsOneArray);

        String[] requiredSkillsTwoArray = new String[]{"Marketing", "Communication", "Critical thinking", "Flexible"};
        List<String> requiredSkillsTwo = Arrays.asList(requiredSkillsTwoArray);

        List<List<String>> listToInit = new ArrayList<>();
        listToInit.add(requiredSkillsOne);
        listToInit.add(requiredSkillsTwo);

        setListOfSkillSets(listToInit);
        System.out.println(listOfSkillSets.get(0));
    }
    
    public void viewPostingDetails(ActionEvent event) throws IOException
    {
        Long postingIdToView = (Long)event.getComponent().getAttributes().get("postingId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("postingIdToView", postingIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("postingsManagement.xhtml");
    }
    
    public void createNewProject(ActionEvent event) throws CreateNewPostingException, ProjectNotFoundException
    {                     
        StartUp currentStartUp = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");
        
        System.out.println(currentStartUp.getCompanyName());
        
        setStartUpId(currentStartUp.getStartupId());  
        
        long projectId = projectSessionBean.createNewProject(getNewProject(), getStartUpId());
        Project project = postingSessionBean.retrieveProjectByProjectId(projectId);
        getListOfPostings().add(project);
        if(getFilteredPostings() != null)
        {
            getFilteredPostings().add(project);
        }
        setNewProject(new Project());
        setStartUpId(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Project created successfully (Project ID: " + projectId + ")", null));
    }
    
    
    public void createNewJob(ActionEvent event) throws CreateNewPostingException, JobNotFoundException, StartUpNotFoundException
    {   
        
        StartUp currentStartUp = (StartUp) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("currentStartUp");
        
        System.out.println(currentStartUp.getCompanyName());
        
        setStartUpId(currentStartUp.getStartupId());            
        
        long jobId = jobSessionBean.createNewJob(getNewJob(), getStartUpId());
        Job job = postingSessionBean.retrieveJobByJobId(jobId);
        getListOfPostings().add(job);
        if(getFilteredPostings() != null)
        {
            getFilteredPostings().add(job);
        }
        setNewJob(new Job());
        setStartUpId(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Job created successfully (Project ID: " + jobId + ")", null));
    }
    
    public void doUpdateProject(ActionEvent event) throws ProjectNotFoundException, OfferNotFoundException 
    {
        
        setSelectedProjectToUpdate((Project)event.getComponent().getAttributes().get("selectedProjectToUpdate"));
        
        System.out.println(getSelectedProjectToUpdate().getPostingId());
        setSelectedProjectToUpdateIndustry((Industry)event.getComponent().getAttributes().get("selectedProjectToUpdateIndustry"));
        setSelectedProjectToUpdateRequiredSkills((List<String>)event.getComponent().getAttributes().get("selectedProjectToUpdateRequiredSkills"));
        
        System.out.println(getSelectedProjectToUpdateIndustry());

        getSelectedProjectToUpdate().setIndustry(getSelectedProjectToUpdateIndustry());
        getSelectedProjectToUpdate().setRequiredSkills(getSelectedProjectToUpdateRequiredSkills());
        postingSessionBean.updatePosting(getSelectedProjectToUpdate());
    }
    
    public void doUpdateJob(ActionEvent event) throws JobNotFoundException, OfferNotFoundException 
    {
        
        setSelectedJobToUpdate((Job)event.getComponent().getAttributes().get("selectedJobToUpdate"));
        
        System.out.println(getSelectedJobToUpdate().getPostingId());
        setSelectedJobToUpdateIndustry((Industry)event.getComponent().getAttributes().get("selectedJobToUpdateIndustry"));
        setSelectedJobToUpdateRequiredSkills((List<String>)event.getComponent().getAttributes().get("selectedJobToUpdateRequiredSkills"));
        
        System.out.println(getSelectedJobToUpdateIndustry());

        getSelectedJobToUpdate().setIndustry(getSelectedJobToUpdateIndustry());
        getSelectedJobToUpdate().setRequiredSkills(getSelectedJobToUpdateRequiredSkills());
        postingSessionBean.updatePosting(getSelectedJobToUpdate());

    }
    
    public void deletePosting(ActionEvent event) 
    {
        try
        {
            Posting postingToDelete = (Posting)event.getComponent().getAttributes().get("postingToDelete");
            setSelectedPostingtoDelete(postingToDelete);
            System.out.println(getSelectedPostingtoDelete().getPostingId());
            postingSessionBean.deletePosting(getSelectedPostingtoDelete().getPostingId());
            
            getListOfPostings().remove(getSelectedPostingtoDelete());
            
            if(getFilteredPostings() != null)
            {
                getFilteredPostings().remove(getSelectedPostingtoDelete());
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirm delete?", null));
        }
        catch(PostingNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting posting: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    

    public viewPostingManagedBean getViewPostingManagedBean() {
        return viewPostingManagedBean;
    }

    public void setViewPostingManagedBean(viewPostingManagedBean viewPostingManagedBean) {
        this.viewPostingManagedBean = viewPostingManagedBean;
    }

    public List<Posting> getListOfPostings() {
        return listOfPostings;
    }

    public void setListOfPostings(List<Posting> listOfPostings) {
        this.listOfPostings = listOfPostings;
    }

    public List<Posting> getFilteredPostings() {
        return filteredPostings;
    }

    public void setFilteredPostings(List<Posting> filteredPostings) {
        this.filteredPostings = filteredPostings;
    }

    public Posting getNewPosting() {
        return newPosting;
    }

    public void setNewPosting(Posting newPosting) {
        this.newPosting = newPosting;
    }

    public Long getStartUpId() {
        return startUpId;
    }

    public void setStartUpId(Long startUpId) {
        this.startUpId = startUpId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Posting getSelectedPostingToUpdate() {
        return selectedPostingToUpdate;
    }

    public void setSelectedPostingToUpdate(Posting selectedPostingToUpdate) {
        this.selectedPostingToUpdate = selectedPostingToUpdate;
    }

    public Project getNewProject() {
        return newProject;
    }

    public void setNewProject(Project newProject) {
        this.newProject = newProject;
    }

    public Job getNewJob() {
        return newJob;
    }

    public void setNewJob(Job newJob) {
        this.newJob = newJob;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public List<Offer> getUpdatedOffers() {
        return updatedOffers;
    }

    public void setUpdatedOffers(List<Offer> updatedOffers) {
        this.updatedOffers = updatedOffers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
    }

    public List<Project> getListOfProjects() {
        return listOfProjects;
    }

    public void setListOfProjects(List<Project> listOfProjects) {
        this.listOfProjects = listOfProjects;
    }

    public List<Job> getListOfJobs() {
        return listOfJobs;
    }

    public void setListOfJobs(List<Job> listOfJobs) {
        this.listOfJobs = listOfJobs;
    }

    public long getProjectPostingId() {
        return projectPostingId;
    }

    public long getJobPostingId() {
        return jobPostingId;
    }

    public List<List<String>> getListOfSkillSets() {
        return listOfSkillSets;
    }

    public void setListOfSkillSets(List<List<String>> listOfSkillSets) {
        this.listOfSkillSets = listOfSkillSets;
    }

    public StartupLoginManagedBean getStartUpLoginManagedBean() {
        return startUpLoginManagedBean;
    }

    public void setStartUpLoginManagedBean(StartupLoginManagedBean startUpLoginManagedBean) {
        this.startUpLoginManagedBean = startUpLoginManagedBean;
    }

    public Posting getSelectedPostingtoDelete() {
        return selectedPostingtoDelete;
    }

    public void setSelectedPostingtoDelete(Posting selectedPostingtoDelete) {
        this.selectedPostingtoDelete = selectedPostingtoDelete;
    }

    public Industry getSelectedJobToUpdateIndustry() {
        return selectedJobToUpdateIndustry;
    }

    public void setSelectedJobToUpdateIndustry(Industry selectedJobToUpdateIndustry) {
        this.selectedJobToUpdateIndustry = selectedJobToUpdateIndustry;
    }

    public Project getSelectedProjectToUpdate() {
        return selectedProjectToUpdate;
    }

    public void setSelectedProjectToUpdate(Project selectedProjectToUpdate) {
        this.selectedProjectToUpdate = selectedProjectToUpdate;
    }

    public Job getSelectedJobToUpdate() {
        return selectedJobToUpdate;
    }

    public void setSelectedJobToUpdate(Job selectedJobToUpdate) {
        this.selectedJobToUpdate = selectedJobToUpdate;
    }

    public List<String> getSelectedJobToUpdateRequiredSkills() {
        return selectedJobToUpdateRequiredSkills;
    }

    public void setSelectedJobToUpdateRequiredSkills(List<String> selectedJobToUpdateRequiredSkills) {
        this.selectedJobToUpdateRequiredSkills = selectedJobToUpdateRequiredSkills;
    }

    public Industry getSelectedProjectToUpdateIndustry() {
        return selectedProjectToUpdateIndustry;
    }

    public void setSelectedProjectToUpdateIndustry(Industry selectedProjectToUpdateIndustry) {
        this.selectedProjectToUpdateIndustry = selectedProjectToUpdateIndustry;
    }

    public List<String> getSelectedProjectToUpdateRequiredSkills() {
        return selectedProjectToUpdateRequiredSkills;
    }

    public void setSelectedProjectToUpdateRequiredSkills(List<String> selectedProjectToUpdateRequiredSkills) {
        this.selectedProjectToUpdateRequiredSkills = selectedProjectToUpdateRequiredSkills;
    }
    
}
