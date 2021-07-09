package edu.school21.print.logic.renderer;

import edu.school21.print.logic.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {

    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void renderPrint(String message) {
        System.out.println(preProcessor.preProcessing(message));
    }
}
