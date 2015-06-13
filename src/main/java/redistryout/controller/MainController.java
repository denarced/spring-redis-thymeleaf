package redistryout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redistryout.repository.RedisRepository;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private RedisRepository redisRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/keys")
    public @ResponseBody Set<String> keys() {
        return redisRepository.keys();
    }

    @RequestMapping("/values")
    public @ResponseBody Map<String, String> values() {
        return redisRepository.values();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(
        @RequestParam String key,
        @RequestParam String value) {

        redisRepository.add(key, value);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam String key) {
        redisRepository.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
