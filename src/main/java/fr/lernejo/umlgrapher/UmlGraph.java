package fr.lernejo.umlgrapher;

import java.util.Arrays;
import java.util.List;

public class UmlGraph {
    private final InternalGraphRepresentation internalGraphRepresentation;

    public UmlGraph(Class... ts) {
        List<Class> tsl = Arrays.asList(ts);
        this.internalGraphRepresentation = new InternalGraphRepresentation(tsl);
    }

    public String as(GraphType graphType) {
        final Formatter formatter;
        if (graphType == GraphType.Mermaid) {
            formatter = new MermaidFormatter();
            return formatter.format(this.internalGraphRepresentation);
        }

        return null;
    }
}
