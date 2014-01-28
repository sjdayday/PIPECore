package pipe.models.component;

import pipe.visitor.foo.PetriNetComponentVisitor;


public interface PetriNetComponent {

    boolean isSelectable();

    boolean isDraggable();

    /**
     * Visitor pattern, this is particularly useful when we do not know
     * the exact type of Component, we can visit them to perform actions
     *
     * @param visitor
     */
    void accept(PetriNetComponentVisitor visitor);

    /**
     * @return objectId
     */
    String getId();

    void setId(String id);

    void setName(String name);

}
