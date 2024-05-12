package middlleware;

public abstract class Midlleware {
    private Midlleware next;

    /**
     * Builds chains of middleware objects.
     */
    public static Midlleware link(Midlleware first, Midlleware... chain) {
        Midlleware head = first;
        for (Midlleware nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }  

    /**
     * Subclasses will implement this method with concrete checks.
     */
    public abstract boolean check(String email, String password);

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}