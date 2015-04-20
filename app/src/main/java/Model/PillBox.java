package Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by CharlesPK3 on 4/3/15.
 */
public class PillBox {
    private static Map<String, Pill> pills = new HashMap<String, Pill>();
    private static Map<Integer, List<Alarm>> dailySchedule = new HashMap<Integer, List<Alarm>>();

    public Map<String, Pill> getPills() {
        return Collections.unmodifiableMap(pills);
    }

    public void addPill(String pillName, Pill pill) {
        pills.put(pillName, pill);
    }

    public void addAlarm(Alarm alarm){
        boolean[] days = alarm.getDayOfWeek();
        for (int i=0; i<7; i++){
            if (days[i] == true){
                if (dailySchedule.containsKey(i+1)){
                    dailySchedule.get(i+1).add(alarm);
                    Collections.sort(dailySchedule.get(i+1));
                } else {
                    List<Alarm> schedule = new LinkedList<Alarm>();
                    schedule.add(alarm);
                    dailySchedule.put(i+1, schedule);
                }
            }
        }
    }

    public List<Alarm> getAlarms(int dayOfWeek) {
        if(dailySchedule.containsKey(dayOfWeek)) {
            return dailySchedule.get(dayOfWeek);
        } else {
            return null;
        }

    }
}
