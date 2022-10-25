package fr.lernejo.umlgrapher;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "UmlGrapher", mixinStandardHelpOptions = true, version = "UmlGrapher 1.0", description = "Creation de graph UML")
public class Launcher implements Callable<Integer> {
    @Option(names = {"-c", "--classes"}, description = "Renseigner les classes d'où faire partir l'analyse")
    private List<String> classNames;

    @Option(names = {"-g", "--graph-type"}, description = "Selectionner le type de graph (Mermaid)")
    private String graphTypeName = "Mermaid";

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Afficher le message d'aide")
    private boolean helpRequested = false;

    @Override
    public Integer call() throws Exception {
        if(classNames == null) {
            throw new Exception("Vous devez specifier les classes à parcourir avec l'option -c ou --classes");
        }

        Class[] classNamesToClass = new Class[classNames.size()];
        for(int i = 0; i < classNames.size(); i++) {
            classNamesToClass[i] = Class.forName(classNames.get(i));
        }

        UmlGraph graph = new UmlGraph(classNamesToClass);
        System.out.println(graph.as(GraphType.valueOf(graphTypeName)));

        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Launcher()).execute(args);
        System.exit(exitCode);
    }
}
