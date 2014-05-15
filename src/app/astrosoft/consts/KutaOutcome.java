package app.astrosoft.consts;

import app.astrosoft.util.Internalization;

/**
 * Created by E. Rajasekar on 5/15/14.
 */
public class KutaOutcome {

    int gained;
    private Result result;

    public enum Result {

        Present, NotPresent, Neutral;

        public String toString() {

            return Internalization.getString(this.name());
        }
    }

    public KutaOutcome(int kutaGained, int kutaMaxValue){
        this.gained = kutaGained;
        this.result = computeResult(kutaMaxValue);
    }

    private Result computeResult(int kutaMaxValue){
        double percentGained = Math.ceil(gained / kutaMaxValue);
        if (percentGained > 0.5) {
            return Result.Present;
        }
        else if(percentGained < 0.5) {
            return Result.NotPresent;
        }
        else{
            return Result.Neutral;
        }
    }

    public int getGained() {
        return gained;
    }

    public Result getResult() {
        return result;
    }
}
