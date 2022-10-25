package fr.lernejo.umlgrapher;
import java.lang.reflect.Modifier;

public class UmlType {
    private final String name;
    private final String packageName;
    // Indicate if a class is interface, enum, etc.
    private final String modifierName;
    public UmlType(Class t) {
        this.name = t.getSimpleName();
        this.packageName = t.getPackageName();
        this.modifierName = getUmlModifierName(t);
    }
    public String getName() {
        return this.name;
    }
    public String getPackageName() {
        return this.packageName;
    }
    public String getModifierName() {
        return modifierName;
    }
    private String getUmlModifierName(Class t) {
        if(t.isInterface()) {
            return "interface";
        }
        if(t.isEnum()) {
            return  "enum";
        }
        if(Modifier.isAbstract(t.getModifiers())) {
            return "abstract";
        }
        return null;
    }
}
