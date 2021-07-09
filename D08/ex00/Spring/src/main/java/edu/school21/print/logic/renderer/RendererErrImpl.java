package edu.school21.print.logic.renderer;

import edu.school21.print.logic.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderPrint(String message) {
        System.err.println(preProcessor.preProcessing(message));
    }
}
