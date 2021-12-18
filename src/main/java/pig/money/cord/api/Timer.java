package pig.money.cord.api;

public class Timer {
    private long time;
    long startTime = System.currentTimeMillis();
    public Timer( ) {
        this.time = -1L;
    }
    public final boolean passed( final long delay ) {
        return passed( delay, false );
    }
    public boolean passed( final long delay, final boolean reset ) {
        if ( reset ) this.reset( );
        return System.currentTimeMillis( ) - this.time >= delay;
    }
    public final void reset( ) {
        this.time = System.currentTimeMillis( );
        this.startTime = System.currentTimeMillis( );
    }
}