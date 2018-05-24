package util;

import java.util.HashMap;
import java.util.Map;


public class Constants {

    public static Map<String, String> progBars = new HashMap<>();

    static {
        String[] ids = {"<:progbar_mid_full:324910937863618560>", "<:progbar_mid_empty:324910938400751617>", "<:progbar_mid_swap:324910937771343873>", "<:progbar_start_full:324910938715193355>", "<:progbar_end_full:324910937741983744>", "<:progbar_start_empty:324910938224459778>", "<:progbar_end_empty:324910937368952832>"};
        String[] names = {"midfull", "midempty", "midswap", "startfull", "endfull", "startempty", "endempty"};
        for (int i = 0; i < names.length; i++) {
            progBars.put(names[i], ids[i]);
        }
    }



}
