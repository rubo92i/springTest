package am.basic.springTest.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("request")
public class Container {


    public Map<String,Object> data = new HashMap<>();


}
