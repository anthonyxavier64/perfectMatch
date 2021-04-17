/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Job;
import enumeration.Industry;
import java.text.SimpleDateFormat;

/**
 *
 * @author yappeizhen
 */
public class JobWrapper extends PostingWrapper {

    public JobWrapper() {
        super();
    }

    public static JobWrapper convertJobToJobWrapper(Job job) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JobWrapper newJobWrapper = new JobWrapper();
        newJobWrapper.setPostingId(job.getPostingId());
        newJobWrapper.setTitle(job.getTitle());
        newJobWrapper.setDescription(job.getDescription());
        newJobWrapper.setPay(job.getPay());
        newJobWrapper.setStartup(StartUpWrapper.convertStartUpToStartUpWrapper(job.getStartup()));

        if (job.getEarliestStartDate() != null) {
            newJobWrapper.setEarliestStartDate(simpleDateFormat.format(job.getEarliestStartDate()));
        }

        if (job.getLatestStartDate() != null) {
            newJobWrapper.setLatestStartDate(simpleDateFormat.format(job.getLatestStartDate()));
        }

        newJobWrapper.setIndustry(job.getIndustry());

        String[] skillsArray = job.getRequiredSkills().toArray(new String[0]);
        newJobWrapper.setRequiredSkills(skillsArray);
        newJobWrapper.setIsProject(false);

        return newJobWrapper;
    }

}
