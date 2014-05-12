package uk.ac.imperial.pipe.dsl;

import uk.ac.imperial.pipe.models.component.place.Place;
import uk.ac.imperial.pipe.models.component.rate.NormalRate;
import uk.ac.imperial.pipe.models.component.rate.RateParameter;
import uk.ac.imperial.pipe.models.component.token.Token;
import uk.ac.imperial.pipe.models.component.transition.Transition;

import java.util.Map;

public class ATransition implements DSLCreator<Transition> {

    private String id;

    private int priority = 1;

    private boolean timed = false;

    private boolean infinite = false;

    /**
     * Functional rate value
     */
    private String rate = "";

    /**
     * Rate parameter for transition
     */
    private String rateParameter = "";

    private int x = 0;
    private int y = 0;

    private ATransition(String id) {this.id = id;}

    public static ATransition withId(String id) {
        return new ATransition(id);
    }

    @Override
    public Transition create(Map<String, Token> tokens, Map<String, Place> places, Map<String, Transition> transitions,
                             Map<String, RateParameter> rateParameters) {
        Transition transition = new Transition(id, id);
        transition.setPriority(priority);
        transition.setTimed(timed);
        transition.setInfiniteServer(infinite);
        transition.setX(x);
        transition.setY(y);

        if (!rate.isEmpty()) {
            transition.setRate(new NormalRate(rate));
        } else if (!rateParameter.isEmpty()) {
            transition.setRate(rateParameters.get(rateParameter));
        }

        transitions.put(id, transition);
        return transition;
    }

    public ATransition andPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public ATransition whichIsImmediate() {
        timed = false;
        return this;
    }

    public ATransition whichIsTimed() {
        timed = true;
        return this;
    }

    public ATransition andIsAnInfinite() {
        infinite = true;
        return this;
    }

    public ATransition andIsASingle() {
       infinite = false;
        return this;
    }

    /**
     * Added for redability e.g.
     * andASingle().server()
     */
    public ATransition server() {
        return this;
    }

    public ATransition andRate(String rate) {
        this.rate = rate;
        return this;
    }

    public ATransition withRateParameter(String rateParameterName) {
        this.rateParameter = rateParameterName;
        return this;
    }


    public ATransition locatedAt(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
}