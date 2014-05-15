package app.astrosoft.consts;

import app.astrosoft.util.Internalization;

/**
 * Created by E. Rajasekar on 5/15/14.
 */
public class KutaOutcome {

    int gained;
    private Result result;
    Kuta kuta;

    public enum Result {

        Present, NotPresent, Neutral;
        //TODO: I18N

      }

    public KutaOutcome(int kutaGained, Kuta kuta){
        this.gained = kutaGained;
        this.kuta = kuta;
        this.result = computeResult(kuta.maxValue());
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

    public boolean isPresent(){
        return result == Result.Present;
    }

    public int getGained() {
        return gained;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KutaOutcome{");
        sb.append("gained=").append(gained);
        sb.append(", result=").append(result);
        sb.append(", kuta=").append(kuta);
        sb.append('}');
        return sb.toString();
    }
}
