package eu.thingsandstuff.pipeline.link

import eu.thingsandstuff.pipeline.JobRef
import eu.thingsandstuff.pipeline.Project
import javaposse.jobdsl.dsl.helpers.publisher.PublisherContext

public class ManualLink<P extends Project> extends AbstractPublishersFocusedJobChainLink<P> {

    static <P extends Project> ManualLink<P> manual(JobRef<P> to) {
        new ManualLink<P>(to)
    }

    private ManualLink(JobRef<P> to) {
        super(to)
    }

    @Override
    Closure publisherClosureFor(String linkEndJobName) {
        return {
            (delegate as PublisherContext).with {
                buildPipelineTrigger(linkEndJobName) {
                    parameters {
                        currentBuild()
                        sameNode()
                    }
                }
            }
        }
    }

}
