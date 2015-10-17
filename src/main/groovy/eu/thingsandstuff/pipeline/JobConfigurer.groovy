package eu.thingsandstuff.pipeline

import javaposse.jobdsl.dsl.Job

interface JobConfigurer<P extends Project> {
    void preConfigure(Job job, P project)
    void postConfigure(Job job, P project)
}