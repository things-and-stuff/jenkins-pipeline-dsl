package eu.thingsandstuff.pipeline

public interface JobRef<P extends Project> {
    JobType getJobType()
    String getJobName(P project)
}
