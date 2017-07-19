package barbot.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 2/19/2017.
 */
public class BaseController {
    protected Map success(Map result) {
        HashMap resultMap = new HashMap();
        resultMap.put("result", "success");
        resultMap.put("data", result != null ? result : new HashMap());
        return resultMap;
    }

    protected Map error(Map result) {
        HashMap resultMap = new HashMap();
        resultMap.put("result", "error");
        resultMap.put("data", result != null ? result : new HashMap());
        return resultMap;
    }
}
