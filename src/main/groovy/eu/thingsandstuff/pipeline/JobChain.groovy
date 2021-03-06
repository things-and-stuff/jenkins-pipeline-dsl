package eu.thingsandstuff.pipeline

import eu.thingsandstuff.pipeline.link.AutoLink
import eu.thingsandstuff.pipeline.link.JobChainLink
import javaposse.jobdsl.dsl.Job

class JobChain<P extends Project> {

    static <P extends Project> JobChain<P> of(JobRef<P> startJob) {
        new JobChain(startJob)
    }

    private final JobRef<P> start
    private final List<JobChainLink<P>> links = []

    private JobChain(JobRef<P> start) {
        this.start = start
    }

    JobChain<P> then(JobRef<P> job) {
        return then(AutoLink.auto(job))
    }

    JobChain<P> then(JobChainLink link) {
        links.add(link)
        return this
    }

    void linkJobs(Map<JobType, Job> jobsByType, P project) {
        Job linkStartJob = getJob(jobsByType, start.jobType)
        links.each { JobChainLink<P> link ->
            link.configure(linkStartJob, project)
            linkStartJob = getJob(jobsByType, link.end.jobType)
        }
    }

    private Job getJob(Map<JobType, Job> jobsByType, JobType jobType) {
        Job linkStartJob = jobsByType[jobType]
        assert linkStartJob, "Attempted to configure links starting from job of type [$jobType], but no such job is defined"
        linkStartJob
    }

}
