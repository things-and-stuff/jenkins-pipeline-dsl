package eu.thingsandstuff.pipeline.link

import eu.thingsandstuff.pipeline.JobRef
import eu.thingsandstuff.pipeline.Project
import javaposse.jobdsl.dsl.Job

class CombinedJobChainLink<P extends Project> implements JobChainLink<P> {
    
    static <P extends Project> CombinedJobChainLink<P> of(JobChainLink<P>... links) {
        return new CombinedJobChainLink<P>(links.toList().asImmutable())
    }

    private final List<JobChainLink<P>> links
    
    private CombinedJobChainLink(List<JobChainLink<P>> links) {
        this.links = links
    }

    @Override
    JobRef<P> getEnd() {
        links.first().getEnd()
    }

    @Override
    void configure(Job linkStartJob, P project) {
        links.each { JobChainLink<P> link ->
            link.configure(linkStartJob, project)
        }
    }
}
