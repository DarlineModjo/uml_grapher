package fr.lernejo.umlgrapher;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InternalGraphRepresentation {
    private final Set<UmlType> umlTypes = new TreeSet<>(Comparator
        .<UmlType, String>comparing(ut->ut.getName())
        .thenComparing(ut->ut.getPackageName()));;
    private final Set<UmlRelation> umlRelations = new TreeSet<>(Comparator
        .<UmlRelation, String>comparing(ur->ur.getChild())
        .thenComparing(ur->ur.getUmlRelationType().name()));

    public InternalGraphRepresentation(List<Class> tsl) {
        for(Class t : tsl) {
            this.findUmlType(t);
            this.findUmlRelationWithInterface(t);
        }
    }

    public Set<UmlType> getUmlTypes() {
        return this.umlTypes;
    }

    public Set<UmlRelation> getUmlRelations() {
        return this.umlRelations;
    }

    private void findUmlType(Class t) {
        umlTypes.add(new UmlType(t));

        String className = t.getSimpleName();
        Class superClass = t.getSuperclass();
        if(superClass == null || superClass == Object.class) {
            return;
        }

        umlRelations.add(new UmlRelation(superClass.getSimpleName(), className, UmlRelationType.Extends));

        this.findUmlType(superClass);
        this.findUmlRelationWithInterface(superClass);
    }

    private void findUmlRelationWithInterface(Class t) {
        String className = t.getSimpleName();
        for(int i =0; i < t.getInterfaces().length; i++) {
            var tInterface = t.getInterfaces()[i];
            String superInterfaceName = tInterface.getSimpleName();
            UmlRelationType umlRelationType = t.isInterface() ? UmlRelationType.Extends : UmlRelationType.Implements;
            umlRelations.add(new UmlRelation(superInterfaceName, className, umlRelationType));

            this.findUmlType(tInterface);
            this.findUmlRelationWithInterface(tInterface);
        }
    }
}
