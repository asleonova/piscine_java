package edu.school21.print.app;

import edu.school21.print.logic.preprocessor.PreProcessor;
import edu.school21.print.logic.preprocessor.PreProcessorToUpperImpl;
import edu.school21.print.logic.printer.Printer;
import edu.school21.print.logic.printer.PrinterWithPrefixImpl;
import edu.school21.print.logic.renderer.Renderer;
import edu.school21.print.logic.renderer.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
        public static void main(String[] args) {
//            PreProcessor preProcessor = new PreProcessorToUpperImpl();
//            Renderer renderer = new RendererErrImpl(preProcessor);
//            PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
//            printer.setPrefix("Prefix");
//            printer.print("Hello!");

            ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
            Printer printer = context.getBean("printerWithPrefix", Printer.class);
            printer.print("Hello!");
    }
}
