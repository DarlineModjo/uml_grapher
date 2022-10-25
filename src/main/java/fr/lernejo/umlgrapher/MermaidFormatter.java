package fr.lernejo.umlgrapher;

import java.util.Iterator;
import java.util.Set;

public class MermaidFormatter implements Formatter {

    @Override
    public String format(InternalGraphRepresentation internalGraphRepresentation) {
        final Set<UmlType> umlTypes = internalGraphRepresentation.getUmlTypes();
        final Set<UmlRelation> umlRelations = internalGraphRepresentation.getUmlRelations();

        String graph = "classDiagram\n" + this.formatUmlTypes(umlTypes);

        for(Iterator<UmlRelation> it = umlRelations.iterator(); it.hasNext();) {
            var umlRelation = it.next();
            var umlRelationType = umlRelation.getUmlRelationType();
            // parent <|-- ou <|.. child : extends ou implements
            graph += umlRelation.getParent()
                + getAppropriateInheritanceSymbol(umlRelationType)
                + umlRelation.getChild() + " : " + umlRelationType.name().toLowerCase() + "\n";
        }

        return graph;
    }

    private String formatUmlTypes(Set<UmlType> umlTypes) {
        String graphPart = "";
        for(Iterator<UmlType> it = umlTypes.iterator(); it.hasNext();) {
            var umlType = it.next();
            graphPart += "class "+ umlType.getName();

            String modifierName = umlType.getModifierName();
            if (modifierName != null) {
                graphPart += " {\n    <<"+ modifierName +">>\n}";
            }

            graphPart += "\n";
        }

        return graphPart;
    }

    private String getAppropriateInheritanceSymbol(UmlRelationType umlRelationType) {
        if(umlRelationType == UmlRelationType.Extends) {
            return " <|-- ";
        }

        return " <|.. ";
    }
}
