package eu.thingsandstuff.pipeline

//TODO include view creation before opensourcing
interface PipelineTemplate<P extends Project> {
    void configurePipeline(PipelineBuilder<P> pipelineBuilder, P project)
}