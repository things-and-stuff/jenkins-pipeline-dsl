package eu.thingsandstuff.pipeline.link

import eu.thingsandstuff.pipeline.JobRef
import eu.thingsandstuff.pipeline.Project
import javaposse.jobdsl.dsl.Job

interface JobChainLink<P extends Project> {
    JobRef<P> getEnd()
    void configure(Job linkStartJob, P project)
}


