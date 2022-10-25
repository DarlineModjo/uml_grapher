package fr.lernejo.umlgrapher;

public class UmlRelation {
    private final String parent;
    private final String child;
    private final UmlRelationType umlRelationType;

    public UmlRelation(String parent, String child, UmlRelationType umlRelationType) {
        this.parent = parent;
        this.child = child;
        this.umlRelationType = umlRelationType;
    }

    public String getParent() {
        return this.parent;
    }

    public String getChild() {
        return this.child;
    }

    public UmlRelationType getUmlRelationType() {
        return this.umlRelationType;
    }
}
